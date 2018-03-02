package learn_to_code.springboot.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

    @RabbitListener(queues = "${queue.name}")
    public void processMessage(String content) {
        log.info("Processing message content: {}",  content);
    }
}
