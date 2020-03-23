FROM oracle/graalvm-ce:19.3.1-java11 as graalvm
RUN gu install native-image

COPY . /home/app/nakedgraalgrpc
WORKDIR /home/app/nakedgraalgrpc

RUN native-image \
  --verbose \
  -H:+TraceClassInitialization \
  -H:+ReportExceptionStackTraces \
  -H:+AddAllCharsets \
  --no-server \
  --no-fallback \
  --allow-incomplete-classpath \
  --report-unsupported-elements-at-runtime \
  --enable-http \
  --enable-https \
  -H:EnableURLProtocols=http,https \
  --enable-all-security-services \
  --initialize-at-build-time='org.jooq.SQLDialect$ThirdParty' \
  --initialize-at-build-time=org.postgresql.Driver \
  --initialize-at-build-time=org.postgresql.util.SharedTimer \
  --initialize-at-build-time=org.postgresql.PGProperty \
  --initialize-at-build-time=com.orbitz.consul \
  --initialize-at-build-time=okhttp3.RequestBody \
  --initialize-at-build-time=com.google.common.base \
  --initialize-at-build-time=com.google.common.primitives \
  --initialize-at-build-time=com.google.common.util \
  --initialize-at-build-time=com.google.common.collect \
  --initialize-at-build-time=org.slf4j \
  --initialize-at-build-time=ch.qos.logback \
  --initialize-at-build-time=com.fasterxml.jackson.databind.ObjectMapper \
  --initialize-at-build-time=com.fasterxml.jackson.core.ObjectCodec \
  --initialize-at-build-time=com.fasterxml.jackson.core.TreeCodec \
  --initialize-at-run-time=com.fasterxml.jackson \
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

FROM debian:stable-slim
EXPOSE 8080
COPY --from=graalvm /home/app/nakedgraalgrpc/nakedgraalgrpc /app/nakedgraalgrpc
ENTRYPOINT ["/app/nakedgraalgrpc"]
