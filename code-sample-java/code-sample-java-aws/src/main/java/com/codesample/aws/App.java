package com.codesample.aws;

import software.amazon.awssdk.core.async.SdkPublisher;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.CompressionType;
import software.amazon.awssdk.services.s3.model.ExpressionType;
import software.amazon.awssdk.services.s3.model.SelectObjectContentEventStream;
import software.amazon.awssdk.services.s3.model.SelectObjectContentRequest;
import software.amazon.awssdk.services.s3.model.SelectObjectContentResponse;
import software.amazon.awssdk.services.s3.model.SelectObjectContentResponseHandler;
import software.amazon.awssdk.services.s3.model.selectobjectcontenteventstream.DefaultRecords;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class App {

    // default config — keys taken from home
    private static final SsmClient ssmClient = SsmClient.builder().build();
    private static final S3AsyncClient s3 = S3AsyncClient.builder().build();

    public static void main(String[] args) {
        readDragonData();
    }

    private static void readDragonData() {
        String bucketName = getBucketName();
        String key = getKey();
        String query = getQuery();

        TestHandler testHandler = new TestHandler();

        // See reference on AWS SDK for Java 2.x async programming
        // https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/asynchronous.html
        CompletableFuture<Void> selected = queryS3(s3, bucketName, key, query, testHandler);
        selected.join();

        for (SelectObjectContentEventStream events : testHandler.receivedEvents) {
            if (events instanceof DefaultRecords) {
                DefaultRecords defaultRecords = (DefaultRecords) events;
                String payload = defaultRecords.payload().asString(StandardCharsets.UTF_8);
                System.out.println(payload);
            }
        }

    }

    private static String getBucketName() {
        GetParameterRequest getParameterRequest = GetParameterRequest
                .builder()
                .name("dragon_data_bucket_name")
                .withDecryption(false).build();
        GetParameterResponse getParameterResponse = ssmClient.getParameter(getParameterRequest);
        return getParameterResponse.parameter().value();
    }

    private static String getKey() {
        GetParameterRequest getParameterRequest = GetParameterRequest
                .builder()
                .name("dragon_data_file_name")
                .withDecryption(false).build();
        GetParameterResponse getParameterResponse = ssmClient.getParameter(getParameterRequest);
        return getParameterResponse.parameter().value();
    }

    private static String getQuery() {
        // later on this method will return different results based
        // on query string parameters. For now, we will hardcode the results
        // to select *, which isn't the best showcase of S3 select
        // but don't worry we will get there
        return "select * from s3object[*][*] s";
    }

    private static CompletableFuture<Void> queryS3(S3AsyncClient s3,
                                                   String bucketName,
                                                   String key,
                                                   String query,
                                                   SelectObjectContentResponseHandler handler) {

        SelectObjectContentRequest select = SelectObjectContentRequest.builder()
                .bucket(bucketName)
                .key(key)
                .expression(query)
                .expressionType(ExpressionType.SQL)
                .outputSerialization(outputSerializationBuilder -> outputSerializationBuilder
                        .json(outputJsonBuilder -> {
                        })
                )
                .inputSerialization(
                        inputSerializationBuilder -> inputSerializationBuilder
                                .json(inputJsonBuilder -> inputJsonBuilder.type("Document"))
                                .compressionType(CompressionType.NONE)

                )
                .build();

        return s3.selectObjectContent(select, handler);
    }


    private static class TestHandler implements SelectObjectContentResponseHandler {
        private List<SelectObjectContentEventStream> receivedEvents = new ArrayList<>();

        @Override
        public void responseReceived(SelectObjectContentResponse response) {
        }

        @Override
        public void onEventStream(SdkPublisher<SelectObjectContentEventStream> publisher) {
            publisher.subscribe(receivedEvents::add);
        }

        @Override
        public void exceptionOccurred(Throwable throwable) {
        }

        @Override
        public void complete() {
        }
    }
}
