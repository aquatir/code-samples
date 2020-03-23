package codesample.kotlin.graalvm

import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

object Application {
    @JvmStatic
    fun main(args: Array<String>) {

        val srv = ServerStarter()
        srv.start()
        srv.blockUntilShutdown()

        println("Hello!")
    }
}

class ServerStarter {

    private var server: Server? = null

    fun start() {
        /* The port on which the server should run */
        val port = 50051
        server = ServerBuilder.forPort(port)
                .addService(ExampleEndpoint())
                .build()
                .start()
        println("Server started")
    }

    fun blockUntilShutdown() {
        server?.awaitTermination()
        println("Server stopped")
    }
}


class ExampleEndpoint() : ExampleServiceGrpc.ExampleServiceImplBase() {
    override fun send(request: ExampleRequest, responseObserver: StreamObserver<ExampleReply>) {

        println("Got you request")
        val reply = ExampleReply.newBuilder().setMessage("Hello, gRPC!").build()
        with(responseObserver) {
            onNext(reply)
            onCompleted()
        }
        println("Request processed")
    }
}

//class ExampleEndpoint() : ExampleServiceGrpc.ExampleServiceImplBase() {
//
//    private val logger = LoggerFactory.getLogger(ExampleEndpoint::class.java)
//
//    override fun send(request: ExampleRequest, responseObserver: StreamObserver<ExampleReply>) {
//
//        val ds: DataSource = HikariDataSource().apply {
//            driverClassName = "org.postgresql.Driver"
//            jdbcUrl = "jdbc:postgresql://pg.mynet:5432/test"
//            username = "postgres"
//            password = "postgres"
//
//            connectionTestQuery = "SELECT 1"
//            minimumIdle = 50
//            maximumPoolSize = 500
//            isAutoCommit = true
//            loginTimeout = 3
//            addDataSourceProperty("characterEncoding","utf8");
//            addDataSourceProperty("useUnicode","true");
//        }
//
//
//
//        val dslContext = DSL.using(ds, SQLDialect.POSTGRES)
//
//        val result = dslContext.execute("update transactions set amount = 100");
//        logger.info("num of rows $result")
//        val reply = ExampleReply.newBuilder().setMessage("Hello, gRPC!").build()
//        logger.info("success!")
//        with(responseObserver) {
//            onNext(reply)
//            onCompleted()
//        }
//    }
//}