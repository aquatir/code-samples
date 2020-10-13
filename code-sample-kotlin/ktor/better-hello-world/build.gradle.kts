import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
}
group = "com.codesamples"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.3.9")

    // kotlin-serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")

    // ktor serialization support
    implementation("io.ktor:ktor-serialization:1.4.1")

    // ktor
    implementation("io.ktor:ktor-server-core:1.4.1")
    implementation("io.ktor:ktor-server-netty:1.4.1")

    // ktor-client
    implementation ("io.ktor:ktor-client-core:1.4.1")
    implementation ("io.ktor:ktor-client-okhttp:1.4.1")


    // logs
    implementation ("ch.qos.logback:logback-classic:1.2.3")
    implementation ("net.logstash.logback:logstash-logback-encoder:6.4")
    implementation ("org.codehaus.janino:janino:3.1.2")

    // test

    // kek TODO: remove
    // testImplementation("com.konghq:unirest-java:3.7.02")
    testImplementation(kotlin("test-junit"))
    testImplementation("io.ktor:ktor-server-tests:1.4.1")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
