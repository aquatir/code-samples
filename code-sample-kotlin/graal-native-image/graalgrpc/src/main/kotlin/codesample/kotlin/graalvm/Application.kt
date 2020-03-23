package codesample.kotlin.graalvm

import com.zaxxer.hikari.HikariDataSource
import io.grpc.stub.StreamObserver
import io.micronaut.runtime.Micronaut
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.sql.DataSource

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("codesample.kotlin.graalvm")
                .mainClass(Application.javaClass)
                .start()
    }
}

@Singleton
class ExampleEndpoint() : ExampleServiceGrpc.ExampleServiceImplBase() {

    private val logger = LoggerFactory.getLogger(ExampleEndpoint::class.java)

    override fun send(request: ExampleRequest, responseObserver: StreamObserver<ExampleReply>) {

        val ds: DataSource = HikariDataSource().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = "jdbc:postgresql://pg.mynet:5432/test"
            username = "postgres"
            password = "postgres"

            connectionTestQuery = "SELECT 1"
            minimumIdle = 50
            maximumPoolSize = 500
            isAutoCommit = true
            loginTimeout = 3
            addDataSourceProperty("characterEncoding","utf8");
            addDataSourceProperty("useUnicode","true");
        }



        val dslContext = DSL.using(ds, SQLDialect.POSTGRES)

        val result = dslContext.execute("update transactions set amount = 100");
        logger.info("num of rows $result")
        val reply = ExampleReply.newBuilder().setMessage("Hello, gRPC!").build()
        logger.info("success!")
        with(responseObserver) {
            onNext(reply)
            onCompleted()
        }
    }
}