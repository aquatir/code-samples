package codesample.kotlin.graalvm

import codesample.kotlin.ExampleRequest
import codesample.kotlin.ExampleServiceGrpc
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.test.annotation.MicronautTest

@MicronautTest
class ExampleServiceTest(blockingStub: ExampleServiceGrpc.ExampleServiceBlockingStub) : BehaviorSpec({
    given("grpc service") {
        `when`("called") {

            val exampleRequest = ExampleRequest.newBuilder().setName("Hello!").build()
            val result = blockingStub.send(exampleRequest).message

            then("expect success") {
                result shouldBe "Hello, gRPC!"
            }
        }
    }
})