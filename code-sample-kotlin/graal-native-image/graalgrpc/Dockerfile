# FROM oracle/graalvm-ce:20.0.0-java8 as graalvm
# For JDK 11
FROM oracle/graalvm-ce:20.0.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/graalgrpc
WORKDIR /home/app/graalgrpc

RUN native-image \
  -H:+TraceClassInitialization \
  -H:+ReportExceptionStackTraces \
  --no-server \
  --no-fallback \
  --allow-incomplete-classpath \
  --report-unsupported-elements-at-runtime \
  --verbose \ 
  --initialize-at-build-time='org.jooq.SQLDialect$ThirdParty' \
  --initialize-at-build-time=org.postgresql.Driver \
  --initialize-at-build-time=org.postgresql.util.SharedTimer \
  --initialize-at-build-time='org.postgresql.PGProperty' \
  --initialize-at-run-time=io.netty.util.internal.logging.Log4JLogger \
  --initialize-at-run-time=io.netty.handler.ssl.ReferenceCountedOpenSslContext \
  --initialize-at-run-time=io.netty.handler.ssl.JdkNpnApplicationProtocolNegotiator \
  --initialize-at-run-time=io.netty.handler.ssl.ReferenceCountedOpenSslEngine \
  --initialize-at-run-time=io.netty.handler.ssl.ConscryptAlpnSslEngine \
  --initialize-at-run-time='io.netty.handler.ssl.JettyAlpnSslEngine$ServerEngine' \
  --initialize-at-run-time='io.netty.handler.ssl.JettyAlpnSslEngine$ClientEngine' \
  --initialize-at-run-time=io.netty.handler.ssl.JettyNpnSslEngine \
  -cp build/libs/graalgrpc-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/graalgrpc/graalgrpc /app/graalgrpc
ENTRYPOINT ["/app/graalgrpc"]
