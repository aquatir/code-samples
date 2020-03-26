FROM oracle/graalvm-ce:19.3.1 as graalvm
RUN gu install native-image

COPY . /home/app/nakedgraalgrpc
WORKDIR /home/app/nakedgraalgrpc

RUN native-image \
  --verbose \
  -H:+TraceClassInitialization \
  -H:+ReportExceptionStackTraces \
  --no-server \
  --no-fallback \
  --allow-incomplete-classpath \
  --report-unsupported-elements-at-runtime \
  -H:EnableURLProtocols=http,https \
  -H:+AddAllCharsets \
  -Dio.netty.noUnsafe=true \
  --initialize-at-build-time='org.jooq.SQLDialect$ThirdParty' \
  --initialize-at-build-time=org.postgresql.Driver \
  --initialize-at-build-time=org.postgresql.util.SharedTimer \
  --initialize-at-build-time='org.postgresql.PGProperty' \
  --initialize-at-build-time=com.orbitz.consul.AgentClient \
  --initialize-at-build-time='com.orbitz.consul.AgentClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.EventClient \
  --initialize-at-build-time='com.orbitz.consul.EventClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.SnapshotClient \
  --initialize-at-build-time='com.orbitz.consul.SnapshotClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.StatusClient \
  --initialize-at-build-time='com.orbitz.consul.StatusClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.AclClient \
  --initialize-at-build-time='com.orbitz.consul.AclClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.OperatorClient \
  --initialize-at-build-time='com.orbitz.consul.OperatorClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.HealthClient \
  --initialize-at-build-time='com.orbitz.consul.HealthClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.KeyValueClient \
  --initialize-at-build-time='com.orbitz.consul.KeyValueClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.PreparedQueryClient \
  --initialize-at-build-time='com.orbitz.consul.PreparedQueryClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.CoordinateClient \
  --initialize-at-build-time='com.orbitz.consul.CoordinateClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.CatalogClient \
  --initialize-at-build-time='com.orbitz.consul.CatalogClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.SessionClient \
  --initialize-at-build-time='com.orbitz.consul.SessionClient$Api' \
  --initialize-at-build-time=com.orbitz.consul.util \
  --initialize-at-build-time=com.orbitz.consul.model.catalog.CatalogDeregistration \
  --initialize-at-build-time=com.orbitz.consul.model.catalog.CatalogRegistration \
  --initialize-at-build-time=com.orbitz.consul.model.session.Session \
  --initialize-at-build-time=com.orbitz.consul.model.agent.Registration \
  --initialize-at-build-time=com.orbitz.consul.model.agent.Check \
  --initialize-at-build-time=com.orbitz.consul.model.query.PreparedQuery \
  --initialize-at-build-time=com.orbitz.consul.model.acl.Token \
  --initialize-at-build-time=com.orbitz.consul.model.acl.Policy \
  --initialize-at-build-time=com.orbitz.consul.model.acl.AclToken \
  --initialize-at-build-time=com.fasterxml.jackson.core.TreeCodec \
  --initialize-at-build-time=com.fasterxml.jackson.core.ObjectCodec \
  --initialize-at-build-time=com.fasterxml.jackson.databind.jsontype.SubtypeResolver \
  --initialize-at-build-time=com.fasterxml.jackson.core.type.ResolvedType \
  --initialize-at-build-time=okhttp3.RequestBody \
  --initialize-at-build-time=com.fasterxml.jackson \
  --initialize-at-build-time=com.google.common.base \
  --initialize-at-build-time=com.google.common.primitives \
  --initialize-at-build-time=com.google.common.util \
  --initialize-at-build-time=com.google.common.collect \
  --initialize-at-build-time=org.slf4j \
  --initialize-at-build-time=org.apache.kafka \
  --initialize-at-build-time=net.jpountz \
  --initialize-at-build-time=com.github.luben \
  --initialize-at-build-time=org.xerial.snappy \
  --initialize-at-build-time=ch.qos.logback \
  --initialize-at-run-time=io.grpc.netty.shaded.io.netty.buffer.AbstractByteBufAllocator \
  --initialize-at-run-time=io.netty.util.internal.logging.Log4JLogger \
  --initialize-at-run-time=io.netty.handler.ssl.ReferenceCountedOpenSslContext \
  --initialize-at-run-time=io.netty.handler.ssl.JdkNpnApplicationProtocolNegotiator \
  --initialize-at-run-time=io.netty.handler.ssl.ReferenceCountedOpenSslEngine \
  --initialize-at-run-time=io.netty.handler.ssl.ConscryptAlpnSslEngine \
  --initialize-at-run-time='io.netty.handler.ssl.JettyAlpnSslEngine$ServerEngine' \
  --initialize-at-run-time='io.netty.handler.ssl.JettyAlpnSslEngine$ClientEngine' \
  --initialize-at-run-time=io.netty.handler.ssl.JettyNpnSslEngine \
  -cp build/libs/nakedgraalgrpc-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/nakedgraalgrpc/nakedgraalgrpc /app/nakedgraalgrpc
ENTRYPOINT ["/app/nakedgraalgrpc"]
