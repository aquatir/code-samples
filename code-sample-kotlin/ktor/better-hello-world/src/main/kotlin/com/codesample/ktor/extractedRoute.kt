package com.codesample.ktor

import com.codesample.ktor.db.IsolationLevel
import com.codesample.ktor.db.JAsyncDbApiWrapper
import com.codesample.ktor.db.JooqBlockingApiCoroutinesWrapper
import com.codesample.ktor.db.performQuery
import com.comesample.ktor.jooq.tables.JPromocodes
import com.comesample.ktor.jooq.tables.JUsers
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.joda.time.LocalDateTime
import org.jooq.conf.ParamType
import org.jooq.impl.DSL
import java.lang.RuntimeException
import java.sql.Connection
import java.time.Month
import java.util.*

fun Routing.other() {
    get("/other") {
        call.respond(StatusResponse())
    }
}

// jooq db

fun Routing.jooqDb(jooBlockingApiWrapper: JooqBlockingApiCoroutinesWrapper) {
    route("/jooq") {
        get("/user") {
            val res = kotlin.runCatching {
                jooBlockingApiWrapper.asTransactionWithResult(Connection.TRANSACTION_READ_COMMITTED) { dsl ->
                    dsl.select(
                        JUsers.USERS.UUID,
                        JPromocodes.PROMOCODES.VALUE,
                        JUsers.USERS.CREATED_AT
                    )
                        .from(JUsers.USERS)
                        .leftJoin(JPromocodes.PROMOCODES)
                        .on(JPromocodes.PROMOCODES.EXTERNAL_USER_UUID.eq(JUsers.USERS.UUID))
                        .fetch()
                        .map { UserDto(it.component1(), it.component2(), it.component3()) }
                }
            }.getOrThrow()
            call.respond(UsersDto(res))
        }

        get("/user/{uuid}") {
            val param = call.parameters["uuid"]?.toUuid() ?: throw RuntimeException("no parameter found")

            val dbRes = jooBlockingApiWrapper.asTransactionWithResult(Connection.TRANSACTION_READ_COMMITTED) { dsl ->
                dsl.select(
                    JUsers.USERS.UUID,
                    JPromocodes.PROMOCODES.VALUE,
                    JUsers.USERS.CREATED_AT
                )
                    .from(JUsers.USERS)
                    .leftJoin(JPromocodes.PROMOCODES)
                    .on(JPromocodes.PROMOCODES.EXTERNAL_USER_UUID.eq(JUsers.USERS.UUID))
                    .where(JUsers.USERS.UUID.eq(param))
                    .fetchOne()
            }
            val userDto = UserDto(dbRes.component1(), dbRes.component2(), dbRes.component3())

            call.respond(userDto)
        }

        post("/user") {
            val res = jooBlockingApiWrapper.asTransactionWithResult(Connection.TRANSACTION_READ_COMMITTED) { dsl ->
                val dbResUser = dsl.insertInto(JUsers.USERS)
                    .set(JUsers.USERS.UUID, UUID.randomUUID())
                    .returning()
                    .fetchOne()
                val dbResPromocode = dsl.insertInto(JPromocodes.PROMOCODES)
                    .set(JPromocodes.PROMOCODES.UUID, UUID.randomUUID())
                    .set(JPromocodes.PROMOCODES.EXTERNAL_USER_UUID, dbResUser.uuid)
                    .set(JPromocodes.PROMOCODES.VALUE, randomString())
                    .returning()
                    .fetchOne()
                UserDto(dbResUser.uuid, dbResPromocode.value, dbResUser.createdAt)
            }
            call.respond(res)
        }
    }
}

// j-async with jooq

fun Routing.asyncDb(asyncDb: JAsyncDbApiWrapper) {
    route("/async") {
        get("/user") {

            // Doesn't set actual transaction isolation level. Can write a better wrapper
            val result = asyncDb.asTransactionWithResult(IsolationLevel.TRANSACTION_READ_COMMITTED) { conn ->
                conn.performQuery {
                    DSL.select(
                        JUsers.USERS.UUID,
                        JPromocodes.PROMOCODES.VALUE,
                        JUsers.USERS.CREATED_AT
                    )
                        .from(JUsers.USERS)
                        .leftJoin(JPromocodes.PROMOCODES)
                        .on(JPromocodes.PROMOCODES.EXTERNAL_USER_UUID.eq(JUsers.USERS.UUID))
                        .getSQL(ParamType.INLINED)
                }.rows.map {
                    UserDto(it.getAs<UUID>(0), it.getAs(1), it.getAs<LocalDateTime>(2).toJavaTime())
                }
            }
            call.respond(UsersDto(result))
        }

        get("/user/{uuid}") {
            val param = call.parameters["uuid"]?.toUuid() ?: throw RuntimeException("no parameter found")
            val userDto = asyncDb.asTransactionWithResult(IsolationLevel.TRANSACTION_READ_COMMITTED) { conn ->
                    conn.performQuery {
                        DSL.select(
                            JUsers.USERS.UUID,
                            JPromocodes.PROMOCODES.VALUE,
                            JUsers.USERS.CREATED_AT
                        )
                            .from(JUsers.USERS)
                            .leftJoin(JPromocodes.PROMOCODES)
                            .on(JPromocodes.PROMOCODES.EXTERNAL_USER_UUID.eq(JUsers.USERS.UUID))
                            .where(JUsers.USERS.UUID.eq(param))
                            .getSQL(ParamType.INLINED)
                    }.rows.map {
                        UserDto(it.getAs<UUID>(0), it.getAs(1), it.getAs<LocalDateTime>(2).toJavaTime())
                    }.first()
                }
            call.respond(userDto)
        }

        post("/user") {
            val res = asyncDb.asTransactionWithResult(IsolationLevel.TRANSACTION_READ_COMMITTED) { conn ->
                val userUuid = UUID.randomUUID()
                conn.performQuery {
                    DSL.insertInto(JUsers.USERS)
                        .set(JUsers.USERS.UUID, userUuid)
                        //.returning() // does not execute actual returning clause on postgres, so doesn't work with raw SQL
                        .getSQL(ParamType.INLINED)
                }

                val dbResUser = conn.performQuery {  // Returning doesn't work in raw SQL -> need to make this query
                    DSL.select(
                        JUsers.USERS.UUID,
                        JUsers.USERS.CREATED_AT
                    )
                        .from(JUsers.USERS)
                        .where(JUsers.USERS.UUID.eq(userUuid))
                        .getSQL(ParamType.INLINED)
                }.rows.map {
                    UserDto(uuid = it.getAs<UUID>(0), createdAt =  it.getAs<LocalDateTime>(1).toJavaTime())
                }.first()

                val promocode = randomString()
                conn.performQuery {
                    DSL.insertInto(JPromocodes.PROMOCODES)
                        .set(JPromocodes.PROMOCODES.UUID, UUID.randomUUID())
                        .set(JPromocodes.PROMOCODES.EXTERNAL_USER_UUID, dbResUser.uuid)
                        .set(JPromocodes.PROMOCODES.VALUE, promocode)
                        //.returning() <- doesn't work with raw sql! updating old user entry
                        .getSQL(ParamType.INLINED)
                }
                dbResUser.also { it.promocode = promocode }
            }
            call.respond(res)
        }
    }
}

private fun String?.toUuid(): UUID? {
    return if (this == null) null
    else
        UUID.fromString(this)
}

//private fun String?.asUuid(): UUID {
//    return this.toUuid()!!
//}

fun LocalDateTime.toJavaTime(): java.time.LocalDateTime {
    return java.time.LocalDateTime.of(
        this.year,
        Month.of(this.monthOfYear),
        this.dayOfMonth,
        this.hourOfDay,
        this.minuteOfHour,
        this.secondOfMinute) // lost mils
}

val large = 'A'..'Z'
fun randomString(): String {
    val promo = CharArray(6)
    for (i in promo.indices) {
        promo[i] = large.random()
    }
    return String(promo)
}

