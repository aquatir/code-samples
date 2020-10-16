package com.codesample.ktor.db

import com.github.jasync.sql.db.*
import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.postgresql.PostgreSQLConnection
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder.createConnectionPool
import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import kotlinx.coroutines.future.future

class JAsyncDbApiWrapper(dbConfig: DBConfig) {

    private val connectionPool: ConnectionPool<PostgreSQLConnection> = createConnectionPool(
        "${dbConfig.jdbcPostgresUrl}?user=${dbConfig.username}&password=${dbConfig.password}"
    )

    suspend fun asTransaction(isolationLevel: IsolationLevel, task: suspend (con: SuspendingConnection) -> Unit) {
        asTransactionWithResult(isolationLevel, task)
        return
    }

    suspend fun <T> asTransactionWithResult(
        isolationLevel: IsolationLevel,
        task: suspend (con: SuspendingConnection) -> T
    ): T {

        // build-in transaction (no isolation level set available)
        // can help with https://github.com/jasync-sql/jasync-sql/pull/209 to make is
//        return connectionPool.asSuspending.inTransaction { conn ->
//            task.invoke(conn)
//        }


        // future based api. Should be able to set isolation level
        // Doesn't work! How to await with begin/commit/rollback?
        // Should check inTransaction implementation
        // :hmm:
        return connectionPool.use { concreteConnection ->
            val suspendingConnectionImpl = concreteConnection.asSuspending as SuspendingConnectionImpl
            val dispatcher = connectionPool.configuration.poolConfiguration.coroutineDispatcher
            CoroutineScope(Job() + dispatcher).future {
                concreteInTransactionWithIsolation(isolationLevel, suspendingConnectionImpl, task)
            }
        }.await()
    }

    private suspend fun <T> concreteInTransactionWithIsolation(
        isolationLevel: IsolationLevel,
        suspConnection: SuspendingConnection,
        f: suspend (SuspendingConnection) -> T
    ): T = coroutineScope {

        // This doesn't work... :hmm:
        // Maybe because it simply can not work with async driver?
        // suspConnection.sendQuery("BEGIN TRANSACTION ISOLATION LEVEL ${isolationLevel.asQueryString()}")
        suspConnection.sendQuery("BEGIN")
        suspConnection.sendQuery("SET TRANSACTION ${isolationLevel.asQueryString()}")
        try {
            val result = f(suspConnection)
            suspConnection.sendQuery("COMMIT")
            result
        } catch (e: Throwable) {
            suspConnection.sendQuery("ROLLBACK")
            throw e
        }
    }

}




suspend fun SuspendingConnection.performQuery(sqlString: () -> String): QueryResult {
    return this.sendQuery(sqlString.invoke())
}


enum class IsolationLevel {
    TRANSACTION_READ_UNCOMMITTED,
    TRANSACTION_READ_COMMITTED,
    TRANSACTION_REPEATABLE_READ,
    TRANSACTION_SERIALIZABLE;

    fun asQueryString() = when (this) {
        TRANSACTION_READ_UNCOMMITTED -> "TRANSACTION READ UNCOMMITTED"
        TRANSACTION_READ_COMMITTED -> "TRANSACTION READ COMMITTED"
        TRANSACTION_REPEATABLE_READ -> "TRANSACTION REPEATABLE READ"
        TRANSACTION_SERIALIZABLE -> "TRANSACTION SERIALIZABLE"
    }
}


