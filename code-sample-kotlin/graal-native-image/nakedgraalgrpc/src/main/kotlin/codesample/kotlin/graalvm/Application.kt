package codesample.kotlin.graalvm

import com.google.common.net.HostAndPort
import com.orbitz.consul.Consul
import com.zaxxer.hikari.HikariDataSource
import io.grpc.ServerBuilder
import io.grpc.ServerInterceptors
import io.grpc.stub.StreamObserver
import io.prometheus.client.exporter.HTTPServer
import me.dinowernli.grpc.prometheus.Configuration
import me.dinowernli.grpc.prometheus.MonitoringServerInterceptor
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import javax.sql.DataSource


object Application {

    private val log = LoggerFactory.getLogger(Application::class.java)

    @JvmStatic
    fun main(args: Array<String>) {

        // Consul
        val consulClient = Consul
                .builder()
                //.withHostAndPort(HostAndPort.fromString("localhost:8500"))
                .withHostAndPort(HostAndPort.fromString("nakedgraalgrpc_consul_1.mynet:8500"))
                .build()
        val kvClient = consulClient.keyValueClient()

        val yamlReader = Yaml()
        val common = yamlReader.load<MutableMap<String, Any>>(kvClient.getValueAsString("config/common/data").get())
        log.info("Got common from consul: $common")

        // Datasource config
        val ds: DataSource = HikariDataSource().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = "jdbc:postgresql://pg.mynet:5432/test"
            //jdbcUrl = "jdbc:postgresql://localhost:5432/test"
            username = "postgres"
            password = "postgres"

            minimumIdle = 50
            maximumPoolSize = 500
            isAutoCommit = true
            loginTimeout = 3

            // TODO: Is it required?
            addDataSourceProperty("characterEncoding", "utf8");
            addDataSourceProperty("useUnicode", "true");
        }

        val dslContext = DSL.using(ds, SQLDialect.POSTGRES)
        dslContext.execute("SELECT 1") // load JOOQ


        val monitoringInterceptor = MonitoringServerInterceptor.create(Configuration.cheapMetricsOnly());
        // Application configuration
        val port = 50051
        val server = ServerBuilder
                .forPort(port)
                .addService(
                        ServerInterceptors.intercept(
                                ExampleEndpoint(dslContext).bindService(),
                                monitoringInterceptor
                        )
                )
                .build()
                .start()

        // This will start prometheus metrics collector over the simplest http server
        val prometheusHttpServer = HTTPServer(50052)

        log.info("GRPC Server started at 50051")
        server?.awaitTermination()
        prometheusHttpServer.stop()
    }
}

class ExampleEndpoint(private val dslContext: DSLContext) : ExampleServiceGrpc.ExampleServiceImplBase() {

    private val log = LoggerFactory.getLogger(ExampleEndpoint::class.java)

    override fun send(request: ExampleRequest, responseObserver: StreamObserver<ExampleReply>) {

        log.info("Got your request")

        val result = dslContext.execute("update transactions set amount = 100");
        log.info("num of rows $result")

        val reply = ExampleReply.newBuilder().setMessage("Hello, gRPC!").build()
        with(responseObserver) {
            onNext(reply)
            onCompleted()
        }
        log.info("Request processed")
    }
}