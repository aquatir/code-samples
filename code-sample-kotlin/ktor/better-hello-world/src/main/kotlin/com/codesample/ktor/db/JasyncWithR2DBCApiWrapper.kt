package com.codesample.ktor.db

import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.postgresql.PostgreSQLConnection
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder.createConnectionPool
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.IsolationLevel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.Connection
import java.util.concurrent.CompletableFuture


class JasyncWithR2DBCApiWrapper(dbConfig: DBConfig) {

    private val factory = PostgresqlConnectionFactory(
        PostgresqlConnectionConfiguration.builder()
            .host(dbConfig.dbHost)
            .port(dbConfig.dbPort)  // optional, defaults to 5432
            .database(dbConfig.dbName)  // optional
            .username(dbConfig.username)
            .password(dbConfig.password)
            .build()
    )

    fun testStuff() {
        // Each JOOQ query should be unwrapped to plaint sql -> executed with r2dbc and then wrapped back
        // Implementation is possible but seems to be too complicated to worth it unless
        // really-really required

//        factory.create().flatMap {
//
//            try {
//                it.transactionIsolationLevel = IsolationLevel.READ_COMMITTED
//                it.createStatement("")
//
//
//            } catch (ex: Exception) {
//                it.rollbackTransaction()
//            } finally {
//                it.commitTransaction()
//            }
//        }
    }


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


