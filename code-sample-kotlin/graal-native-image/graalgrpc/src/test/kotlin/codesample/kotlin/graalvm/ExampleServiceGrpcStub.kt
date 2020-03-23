package codesample.kotlin.graalvm

import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel


@Factory
internal class Clients {
    @Bean
    fun blockingExampleStubStub(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel): ExampleServiceGrpc.ExampleServiceBlockingStub {
        return ExampleServiceGrpc.newBlockingStub(
                channel
        )
    }
}