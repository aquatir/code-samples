package codesample.kotlin.graalvm

import com.zaxxer.hikari.HikariDataSource
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.sql.DataSource

object Application {
    @JvmStatic
    fun main(args: Array<String>) {

        val ds: DataSource = HikariDataSource().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = "jdbc:postgresql://pg.mynet:5432/test"
            //jdbcUrl = "jdbc:postgresql://localhost:5432/test"
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

        val port = 50051
        val server = ServerBuilder.forPort(port)
                .addService(ExampleEndpoint(dslContext))
                .build()
                .start()

        println("GRPC Server started at 50051")
        server?.awaitTermination()
    }
}

class ExampleEndpoint(private val dslContext: DSLContext) : ExampleServiceGrpc.ExampleServiceImplBase() {
    override fun send(request: ExampleRequest, responseObserver: StreamObserver<ExampleReply>) {

        println("Got you request")


        val result = dslContext.execute("update transactions set amount = 100");
        println("num of rows $result")

        val reply = ExampleReply.newBuilder().setMessage("Hello, gRPC!").build()
        with(responseObserver) {
            onNext(reply)
            onCompleted()
        }
        println("Request processed")
    }
}