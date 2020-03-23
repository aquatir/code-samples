package codesample.kotlin.graalvm

import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

object Application {
    @JvmStatic
    fun main(args: Array<String>) {

        val port = 50051
        val server = ServerBuilder.forPort(port)
                .addService(ExampleEndpoint())
                .build()
                .start()

        println("Server started")
        server?.awaitTermination()
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