package codesample.kotlin.graalvm

import com.google.common.net.HostAndPort
import com.orbitz.consul.Consul
import com.zaxxer.hikari.HikariDataSource
import io.grpc.ServerBuilder
import io.grpc.ServerInterceptors
import io.grpc.stub.StreamObserver
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import io.prometheus.client.exporter.HTTPServer
import me.dinowernli.grpc.prometheus.Configuration
import me.dinowernli.grpc.prometheus.MonitoringServerInterceptor
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import java.time.Duration
import java.util.*
import javax.sql.DataSource


object Application {

    private val log = LoggerFactory.getLogger(Application::class.java)

    @JvmStatic
    fun main(args: Array<String>) {

        // Consul
        val consulClient = Consul
                .builder()
                //.withHostAndPort(HostAndPort.fromString("localhost:8500"))
                .withHostAndPort(HostAndPort.fromString("consul.mynet:8500"))
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


        // kafka config
        //val kafkaBrokerUrl = "localhost:9092"
        val kafkaBrokerUrl = "broker.mynet:9092"

        log.info("Creating kafka producer and consumer")

        val producer  = createProducer(kafkaBrokerUrl)
        val consumer = createConsumer(kafkaBrokerUrl)
                .apply { this.subscribe(listOf("new_topic")) }

        log.info("Created producer and consumer")
        log.info("About to launch consumer thread")

        val kafkaConsumerThread = KafkaConsumerThread("kafka-consumer-thread", consumer)
                .apply { start() }
        log.info("launched consumer thread")

        // GRPC config
        val monitoringInterceptor = MonitoringServerInterceptor.create(Configuration.cheapMetricsOnly());
        val port = 50051
        val server = ServerBuilder
                .forPort(port)
                .addService(
                        ServerInterceptors.intercept(
                                ExampleEndpoint(dslContext, producer).bindService(),
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
        kafkaConsumerThread.interrupt()
    }
}

class KafkaConsumerThread(threadName: String, private val consumer: Consumer<String, String>) : Thread(threadName) {

    private val log = LoggerFactory.getLogger(KafkaConsumerThread::class.java)

    override fun run() {

        while (!isInterrupted) {
            try {
                val record = consumer.poll(Duration.ofSeconds(10))
                if (record.count() == 0) {
                    log.info("no new messages")
                } else {
                    record.records("new_topic").forEach {
                        log.info("received on consumer: ${it.value()}")
                    }
                }
                consumer.commitSync()
            } catch (ex: Exception) {
                log.error("Something went wrong in kafka consumer thread: ", ex)
            }

        }
    }
}

class ExampleEndpoint(private val dslContext: DSLContext, private val producer: Producer<String, String>) : ExampleServiceGrpc.ExampleServiceImplBase() {

    private val log = LoggerFactory.getLogger(ExampleEndpoint::class.java)

    override fun send(request: ExampleRequest, responseObserver: StreamObserver<ExampleReply>) {

        log.info("Got your request")

        val result = dslContext.execute("update transactions set amount = 100");
        log.info("num of rows $result")

        log.info("Sending kafka message")

        val sendResult = producer.send(ProducerRecord("new_topic", "value")).get()
        log.info("offset: ${sendResult.offset()} partition: ${sendResult.partition()}")

        val reply = ExampleReply.newBuilder().setMessage("Hello, gRPC!").build()
        with(responseObserver) {
            onNext(reply)
            onCompleted()
        }
        log.info("Request processed")
    }
}

private fun createProducer(brokers: String): Producer<String, String> {
    val props = Properties()
            .apply {
                this["bootstrap.servers"] = brokers
                this["key.serializer"] = StringSerializer::class.java.canonicalName
                this["value.serializer"] = StringSerializer::class.java.canonicalName
            }
    return KafkaProducer(props)
}

fun createConsumer(brokers: String): Consumer<String, String> {
    val props = Properties()
            .apply {
                this["bootstrap.servers"] = brokers
                this["group.id"] = "hello_world"
                this["key.deserializer"] = StringDeserializer::class.java.canonicalName
                this["value.deserializer"] = StringDeserializer::class.java.canonicalName
            }
    return KafkaConsumer(props)
}