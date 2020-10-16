package com.codesample.ktor.db

import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.*
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.Connection
import javax.sql.DataSource

data class DBConfig(
    val driverClassName: String,

    val dbHost: String,
    val dbPort: Int,
    val dbName: String,

    val username: String,
    val password: String,
    val numOfThreads: Int
) {
    val jdbcPostgresUrl = "jdbc:postgresql://$dbHost:$dbPort/$dbName"
}

class JooqBlockingApiCoroutinesWrapper(dbConfig: DBConfig) {
    private val datasource: DataSource

    init {
        datasource = HikariDataSource().configWith(dbConfig)
    }

    private fun HikariDataSource.configWith(conf: DBConfig): HikariDataSource {
        return this.apply {
            this.driverClassName = conf.driverClassName
            this.jdbcUrl = conf.jdbcPostgresUrl
            this.username = conf.username
            this.password = conf.password

            this.minimumIdle = conf.numOfThreads
            this.maximumPoolSize = conf.numOfThreads
            this.isAutoCommit = false
            this.loginTimeout = 3

            this.addDataSourceProperty("characterEncoding", "utf8");
            this.addDataSourceProperty("useUnicode", "true");
        }
    }


    suspend fun asTransaction(isolationLevel: Int, task: suspend (methodDslContext: DSLContext) -> Unit) {
        asTransactionWithResult(isolationLevel, task)
        return
    }

    suspend fun <T> asTransactionWithResult(
        isolationLevel: Int,
        task: suspend (methodDslContext: DSLContext) -> T): T = coroutineScope {

        val connection = getConnectionOnIO(isolationLevel)
        executeQuery(connection, task)
    }

    /** getConnection on [DataSource] may block -> get it from IO */
    private suspend fun getConnectionOnIO(isolationLevel: Int): Connection {
        return withContext(Dispatchers.IO) {
            datasource.connection.also {
                it.transactionIsolation = isolationLevel
            }
        }
    }

    /** Execute query and get result on provided scope */
    private suspend fun <T> executeQuery(connection: Connection, task: suspend (methodDslContext: DSLContext) -> T): T = coroutineScope {
        withContext(this.coroutineContext) {
            connection.use { con ->
                DSL.using(con, SQLDialect.POSTGRES).transactionResult { config ->
                    this.async { task.invoke(config.dsl()) }
                }
            }
        }.await()
    }
}
