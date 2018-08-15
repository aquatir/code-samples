package codesample.kotlin.sandbox.basics

import codesample.kotlin.sandbox.classes.*
import org.junit.Test

class BasicsRunner {

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