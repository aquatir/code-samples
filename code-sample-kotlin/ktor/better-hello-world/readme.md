### What is this 

Trying to implement complete production-ready project template with ktor and coroutines


### How to start

1. Create network `docker network create mynet`
2. start and configure pg 
    1. `docker run --name pg -e POSTGRES_PASSWORD=postgres --net mynet -p 5432:5432 -d postgres`
    2. `docker exec -it pg bash`
    3. `psql -U postgres`
    4. `create database db;`
3. Migrate. `gradlew.bat flywayMigrate -i`
4. start docker-compose (kafka + consul)
5. start app

### TODO

#### http

- **DONE** get requests
- **DONE** post requests (works with kotlinx serialization)
- **DONE** url (path) parameters (support both required and optional)
- **DONE** query (`?a=bla`) parameters
- **DONE** headers
- **DONE** interceptors (they are even typesafe!)
- **DONE** generic exception handling
- **DONE** filters (before and after request). Before -> interceptor. After -> CallLogging Feature. 
Can also create pipeline phases
- **DONE** log time taken for request processing (can do with custom coroutine scope key)
- **SKIP** files. Not done but documentation exists
- **DONE** ktor http client 
- **SKIP** client with proxy settings. Not done but documentation exists

#### ktor specific

- **DONE** Configure timeout for Netty of 10 seconds (for some reason embedded server can't watch paths. 
Throws `Module function provided as lambda cannot be unlinked for reload`. Worth an issue?)
- **DONE** Capturing events to stop stuff https://ktor.io/docs/lifecycle.html#monitor-events


#### Logging

- Make kotlinx serializations log much less (find where to configure)

#### Testing

- **DONE** Check if starting up whole app does take too much time or not (Not really... Starts almost instantly. Why 
even bother to use build-in test support?)
- Testcontainers (should be identical)
- **DONE** Hot-realod
- **SKIP** TestApplicationEngine? https://ktor.io/docs/lifecycle.html#monitor-events. Why use it if app starts almost 
instantly anyway? Maybe not so good with big apps but will see.

#### Tools

- **Coroutines postgres db**
    - **DONE** jooq with normal blocking connection pool. See [JooqBlockingJDBCApiCoroutinesWrapper.kt] The blocking 
    offloads into `Dispatchers.IO` which should be better than blocking http request coroutine, but a dedicated async 
    pool should provide better performance.
    - **FAILED** jasync-sql https://github.com/jasync-sql/jasync-sql. jasync uses it's own `Connection` which is probably 
    not a huge problem, but you also can not set isolation level inside transactions which is critical for my usecase. 
    - **FAILED** r2dbc-postgresql https://github.com/pgjdbc/r2dbc-postgresql. r2dbc also uses it's own implementation 
    of `Connection` interface, but you CAN set isolation level there. Some functional shenanigans would be required to 
    make jOOQ queries executed on r2dbc client. May look into this problem later. 
    - How micronaut / quarkus do it?
- **Kafka**. Should be simple... Kafka consumer it NOT thread-safe, so cannot launch on the same `Dispathers.Default`. 
Should create a dedicated thread for each consumer.
- **Consul**. Config loader from https://ktor.io/docs/configuration.html#custom. Normal blocking implementation in main
    should be an easy workaround if this approach doesn't work.

#### Production stuff

- prometheus metrics
- **DONE** json logs. Not ktor. Simple `logback.xml` config is enough and works as expected.
- **DONE** MDC with logs for coroutines. Works somehow, but need special coroutine scope, so no global `MDC.clear` after 
request.
    - call interceptor: https://ktor.io/docs/call-logging.html#mdc
    - also, this for custom stuff: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-thread-context-element/index.html

#### Async db links

- Might help: https://github.com/TechEmpower/FrameworkBenchmarks/pull/4247
- Or this: https://medium.com/@OhadShai/async-with-style-kotlin-web-backend-with-ktor-coroutines-and-jasync-mysql-b34e8c83e4bd
- or this: https://medium.com/@OhadShai/reactive-java-all-the-way-to-the-database-with-jasync-sql-and-javalin-c982365d7dd2
- or this: https://medium.com/@OhadShai/just-a-small-example-of-how-kotlin-coroutines-are-great-c9774fe8434
- or even this: https://matej.laitl.cz/bench-rust-kotlin-microservices/
- how micronaut / quarkus do it?

#### Issues to reproduce?

- For some reason embedded server can't watch paths.  Throws 
`Module function provided as lambda cannot be unlinked for reload`. Worth an issue?
