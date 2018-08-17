package codesample.kotlin.sandbox.basics

import codesample.kotlin.sandbox.classes.*
import org.junit.Test


class BasicsRunner {

    /**
     * The runners here can be started in IDEA by clicking on 'run test' button.
     * These examples are defined in such weird way to NOT specify main class
     * for spring-boot example (You CAN specify it, but most spring-boot apps don't do it)
     */
    @Test fun runHelloKotlin() = helloKotlin()
    @Test fun runHelloKotlinCollectionsTest() = helloKotlinCollections()
    @Test fun runLambdas() = lambdas()

    @Test fun runClasses() = classes()
    @Test fun runClassesSortOfMultipleInheritance() = classesSortOfMultipleInheritance()
    @Test fun runDataClasses() = dataClasses()
    @Test fun runGenerics() = generics()
    @Test fun runKotlinDelegate() = kotlinDelegate()
    @Test fun runKotlinObservable() = kotlinObservable()
    @Test fun runKotlinSealedClasses() = kotlinSealedClasses()

}