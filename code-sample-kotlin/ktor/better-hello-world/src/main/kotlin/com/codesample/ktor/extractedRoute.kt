package com.codesample.ktor

import com.codesample.ktor.db.JooqBlockingApiCoroutinesWrapper
import com.comesample.ktor.jooq.tables.JPromocodes
import com.comesample.ktor.jooq.tables.JUsers
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import java.lang.RuntimeException
import java.sql.Connection
import java.util.*

fun Routing.other() {
    get("/other") {
        call.respond(StatusResponse())
    }
}

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
                        .leftJoin(JPromocodes.PROMOCODES).on(JPromocodes.PROMOCODES.EXTERNAL_USER_UUID.eq(JUsers.USERS.UUID))
                        .fetch()
                        .map { UserDto(it.component1(), it.component2(), it.component3()) }
                }
            }.getOrThrow()
            call.respond(Users(res))
        }

        get("/user/{uuid}") {
            val param = call.parameters["uuid"]?.toUuid() ?: throw RuntimeException("no parameter found")
            val userDto = kotlin.runCatching {
                val dbRes = jooBlockingApiWrapper.asTransactionWithResult(Connection.TRANSACTION_READ_COMMITTED) { dsl ->
                    dsl.select(
                        JUsers.USERS.UUID,
                        JPromocodes.PROMOCODES.VALUE,
                        JUsers.USERS.CREATED_AT
                    )
                        .from(JUsers.USERS)
                        .leftJoin(JPromocodes.PROMOCODES).on(JPromocodes.PROMOCODES.EXTERNAL_USER_UUID.eq(JUsers.USERS.UUID))
                        .where(JUsers.USERS.UUID.eq(param))
                        .fetchOne()

                }
                UserDto(dbRes.component1(), dbRes.component2(), dbRes.component3())
            }.getOrThrow()
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

private fun String?.toUuid(): UUID? {
    return if (this == null) null
    else
        UUID.fromString(this)
}

val large = 'A'..'Z'
fun randomString(): String {
    val promo = CharArray(6)
    for (i in promo.indices) {
        promo[i] = large.random()
    }
    return String(promo)
}

