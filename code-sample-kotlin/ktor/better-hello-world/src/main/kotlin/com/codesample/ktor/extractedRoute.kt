package com.codesample.ktor

import com.codesample.ktor.db.JooqBlockingApiCoroutinesWrapper
import com.comesample.ktor.jooq.tables.JPromocodes
import com.comesample.ktor.jooq.tables.JUsers
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.util.*

fun Routing.other() {
    get("/other") {
        call.respond(StatusResponse())
    }
}

fun Routing.jooqDb(dbApiWrapper: JooqBlockingApiCoroutinesWrapper) {

    route("/jooq") {
        get("/basic") {
            val res = kotlin.runCatching {
                dbApiWrapper.asTransactionWithResult(Connection.TRANSACTION_READ_COMMITTED) { dsl ->
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

        post("/user") {
            val res = dbApiWrapper.asTransactionWithResult(Connection.TRANSACTION_READ_COMMITTED) { dsl ->
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

val large = 'A'..'Z'
fun randomString(): String {
    val promo = CharArray(6)
    for (i in promo.indices) {
        promo[i] = large.random()
    }
    return String(promo)
}

