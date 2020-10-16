import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import nu.studer.gradle.jooq.JooqEdition

plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    id("nu.studer.jooq") version "5.1.1"
    id("org.flywaydb.flyway") version "6.3.2"
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


    // db dependencies
    implementation("org.postgresql:postgresql:42.2.11")
    implementation("com.zaxxer:HikariCP:3.4.1")

    // jooq
    implementation("org.jooq:jooq:3.13.1")
    jooqGenerator("org.postgresql:postgresql:42.2.11")

    // jsync-db
    implementation("com.github.jasync-sql:jasync-postgresql:1.1.3")
    implementation("io.r2dbc:r2dbc-postgresql:0.8.5.RELEASE")

    // logs
    implementation ("ch.qos.logback:logback-classic:1.2.3")
    implementation ("net.logstash.logback:logstash-logback-encoder:6.4")
    implementation ("org.codehaus.janino:janino:3.1.2")

    // test

    testImplementation(kotlin("test-junit"))
    testImplementation("io.ktor:ktor-server-tests:1.4.1")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

jooq {
    version.set("3.13.1")
    edition.set(JooqEdition.OSS)

    configurations {
        create("main") {
            jooqConfiguration.apply {
                generateSchemaSourceOnCompilation.set(false) // Do not generate on each build
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://192.168.99.100:5432/test"
                    user = "postgres"
                    password = "postgres"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    strategy.apply {
                        name = "org.jooq.codegen.example.JPrefixGeneratorStrategy"
                    }
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "user_access"
                    }
                    generate.apply {
                        isImmutablePojos = false
                        isFluentSetters = true
                        isJavaTimeTypes = true
                    }
                    target.apply {
                        packageName = "com.comesample.ktor.jooq"
                        directory = "$projectDir/src/main/java"
                    }
                }
            }
        }
    }
}

flyway {
    url = "jdbc:postgresql://192.168.99.100:5432/test"
    user = "postgres"
    password = "postgres"
}

