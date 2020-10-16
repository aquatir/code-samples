package com.codesample.ktor

import com.codesample.ktor.db.JooqBlockingApiCoroutinesWrapper
import com.comesample.ktor.jooq.tables.JUsers
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.LoggerFactory
import java.sql.Connection

fun Routing.other() {
    get("/other") {
        call.respond(StatusResponse())
    }
}

fun Routing.db(dbApiWrapper: JooqBlockingApiCoroutinesWrapper) {

    val log = LoggerFactory.getLogger("DB")

    route("/db") {
        get("/basic") {
            val res = kotlin.runCatching {
                dbApiWrapper.asTransactionWithResult(Connection.TRANSACTION_READ_COMMITTED) {
                    it.selectFrom(JUsers.USERS)
                        .fetch()
                        .map { UserDto(it.component1(), it.component2()) }
                }
            }.getOrThrow()
            call.respond(Users(res))
        }
    }
}
