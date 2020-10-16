package com.codesample.ktor.db

import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.postgresql.PostgreSQLConnection
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder.createConnectionPool
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.Connection
import java.util.concurrent.CompletableFuture


class JasyncDbApiWrapper(dbConfig: DBConfig) {

    private val connectionPool: ConnectionPool<PostgreSQLConnection> = createConnectionPool(
        "jdbc:postgresql://${dbConfig.jdbcPostgresUrl}?user=${dbConfig.username}&password=${dbConfig.password}"
    )

//    suspend fun asTransaction(isolationLevel: Int, task: suspend (methodDslContext: DSLContext) -> Unit) {
//        asTransactionWithResult(isolationLevel, task)
//        return
//    }

//    suspend fun <T> asTransactionWithResult(
//        isolationLevel: Int,
//        task: suspend (methodDslContext: DSLContext) -> T): T = coroutineScope {
//
//        connectionPool.use { conn -> conn.inTransaction { innerConn ->
//            innerConn.iso
//        } }
//
//        withContext(this.coroutineContext) {
//            val kek = connectionPool.use { connection ->
//                val res = async { executeQuery(it, task) }
//                val ftr = CompletableFuture<T>()
//                ftr.complete(res)
//            }
//        }
//
//
//        return@coroutineScope kek
//    }

//    /** Execute query on [Connection] and get result on provided scope returning connection to pool */
//    private suspend fun <T> executeQuery(connection: Connection, task: suspend (methodDslContext: DSLContext) -> T): T = coroutineScope {
//        withContext(this.coroutineContext) {
//            connection.use { con ->
//                DSL.using(con, SQLDialect.POSTGRES).transactionResult { config ->
//                    this.async { task.invoke(config.dsl()) }
//                }
//            }
//        }.await()
//    }
}


