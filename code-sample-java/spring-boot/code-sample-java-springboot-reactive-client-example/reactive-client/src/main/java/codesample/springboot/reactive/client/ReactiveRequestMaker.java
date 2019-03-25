package codesample.springboot.reactive.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@RestController
public class ReactiveRequestMaker {

    private String targetUrl = "localhost:8081/value";
    private BlockingQueue<Long> requestBlockingQueue = new ArrayBlockingQueue<>(11000);
    private BlockingQueue<SampleDto> responseBlockingQueue = new ArrayBlockingQueue<>(11000);

    private WebClient webClient;

    private Runnable requestQueueSubscribeRunnable = () -> {
        while (true) {
            try {
                var result = this.requestBlockingQueue.poll(10, TimeUnit.SECONDS);
                if (result != null) {
                    // make non-blocking request
                    System.out.println("Making reactive request...");
                    this.webClient.get()
                            .retrieve()
                            .bodyToMono(SampleDto.class)
                            .subscribe(sampleDto -> this.responseBlockingQueue.add(sampleDto),
                                    System.err::println);


                } else {
                    System.out.println("No data in REQUEST queue in last 10 seconds");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable responseQueueSubscribeRunnable = () -> {
        while (true) {
            try {
                var result = this.responseBlockingQueue.poll(10, TimeUnit.SECONDS);
                if (result != null) {
                    System.out.println("Got async response: " + result);
                } else {
                    System.out.println("No data in RESPONSE queue for last 10 seconds");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    @PostConstruct
    public void subscribeToQueues() throws InterruptedException {
        var requestSubscribeThread = new Thread(requestQueueSubscribeRunnable);
        var responseSubscribeThread = new Thread(responseQueueSubscribeRunnable);
        requestSubscribeThread.start();
        responseSubscribeThread.start();

        this.webClient = WebClient.create(this.targetUrl);
    }

    @GetMapping("/")
    public Boolean getRandomValueWithReactiveClient() {
        for (int i = 0; i < 10000; i++) {
            this.requestBlockingQueue.add(0L);
        }
        return true;
    }



}
