package codesample.kotlin.graalvm

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.zaxxer.hikari.HikariDataSource
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import javax.sql.DataSource


object Application {

    private val log = LoggerFactory.getLogger(Application::class.java)

    @JvmStatic
    fun main(args: Array<String>) {

        // TODO_1: Make it possible to set default level with logback.xml when compiling to native image. Not programmatically!
        (LoggerFactory.getLogger("io.grpc") as Logger).apply {
            level = Level.INFO
        }
        (LoggerFactory.getLogger("com.zaxxer.hikari") as Logger).apply {
            level = Level.INFO
        }

        // Datasource config
        val ds: DataSource = HikariDataSource().apply {
            driverClassName = "org.postgresql.Driver"
            //jdbcUrl = "jdbc:postgresql://pg.mynet:5432/test"
            jdbcUrl = "jdbc:postgresql://localhost:5432/test"
            username = "postgres"
            password = "postgres"

            minimumIdle = 50
            maximumPoolSize = 500
            isAutoCommit = true
            loginTimeout = 3

            // TODO: Is it required?
            addDataSourceProperty("characterEncoding","utf8");
            addDataSourceProperty("useUnicode","true");
        }

        val dslContext = DSL.using(ds, SQLDialect.POSTGRES)
        dslContext.execute("SELECT 1") // load JOOQ

        // Application configuration
        val port = 50051
        val server = ServerBuilder.forPort(port)
                .addService(ExampleEndpoint(dslContext))
                .build()
                .start()

        log.info("GRPC Server started at 50051")
        server?.awaitTermination()
    }
}

class ExampleEndpoint(private val dslContext: DSLContext) : ExampleServiceGrpc.ExampleServiceImplBase() {

    private val log = LoggerFactory.getLogger(ExampleEndpoint::class.java)

    override fun send(request: ExampleRequest, responseObserver: StreamObserver<ExampleReply>) {

        log.info("Got your request")

        val result = dslContext.execute("update transactions set amount = 100");
        log.info("num of rows $result")

        val reply = ExampleReply.newBuilder().setMessage("Hello, gRPC!").build()
        with(responseObserver) {
            onNext(reply)
            onCompleted()
        }
        log.info("Request processed")
    }
}