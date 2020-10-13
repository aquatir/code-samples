### What is this 

Trying to implement complete production-ready project template with ktor and coroutines

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
- TestApplicationEngine? https://ktor.io/docs/lifecycle.html#monitor-events

#### Tools

- **Coroutines postgres db**
    - jooq with normal blockable connection pool? Try it and load test!
    - jasync-sql https://github.com/jasync-sql/jasync-sql ?
    - Might help: https://github.com/TechEmpower/FrameworkBenchmarks/pull/4247
    - Or this: https://medium.com/@OhadShai/async-with-style-kotlin-web-backend-with-ktor-coroutines-and-jasync-mysql-b34e8c83e4bd
    - or this: https://medium.com/@OhadShai/reactive-java-all-the-way-to-the-database-with-jasync-sql-and-javalin-c982365d7dd2
    - or this: https://medium.com/@OhadShai/just-a-small-example-of-how-kotlin-coroutines-are-great-c9774fe8434
    - or even this: https://matej.laitl.cz/bench-rust-kotlin-microservices/
    - how micronaut / quarkus do it?
- **Kafka**. Should be simple. How micronaut / quarkus do it?
- **Consul**. Config loader from https://ktor.io/docs/configuration.html#custom 

#### Production stuff

- prometheus metrics
- json logs
- **DONE** MDC with logs for coroutines 
    - call interceptor: https://ktor.io/docs/call-logging.html#mdc
    - also, this for custom stuff: https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-thread-context-element/index.html


#### Issues to reproduce?

- For some reason embedded server can't watch paths.  Throws 
`Module function provided as lambda cannot be unlinked for reload`. Worth an issue?
