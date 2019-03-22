package codesample.springboot.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Used in {@link codesample.springboot.controller.HelloWorldController}
 */
@Service
@Slf4j
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${queue.name}")
    private String queueName;

    public void sendMessage(String content) {
        log.info("Sending message to: {} with content: {}", queueName, content);
        rabbitTemplate.convertAndSend(queueName, content);
    }
}
