package learn_to_code.springboot.controller;

import learn_to_code.springboot.props.FooProperties;
import learn_to_code.springboot.rabbit.Sender;
import learn_to_code.springboot.service.LongCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/*
 * Spring does not care if your {@code @Controller} is defined as controller or {@code RestController}. This annotation
 * is there simply to make it easier to developers to understand what are they dealing with
 */
@RestController

/* Loggin with spring-boot and Lombok is trivial. Simply annotate your class with @Slf4j (or any other logging annotation
from https://projectlombok.org/features/log for respective log). Spring-boot will autoconfigure required log.
You can then use normal spring-boot configuration for loggers, e.g. setting logging.file or logging.level */
@Slf4j
public class HelloWorldController {

    @Autowired
    private FooProperties props;

    @Autowired
    private LongCalculationService longCalculationService;
    @Autowired
    private Sender rabbitSender;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        log.debug("Logging with slf4j");
        return "Greetings from Spring Boot";
    }

    @GetMapping("/props")
    @ResponseBody
    public String getProps() {
        return "ip: " + props.getIp().toString() + " enabled: " + props.isEnabled() + " roles: " + props.getRoles();
    }

    /**
     * See {@link LongCalculationService} comment to info about caching. You can try calling this service with big numbers
     * 2 time in a row (e.g /math/100000000) to see that cache actually works
     */
    @GetMapping("/math/{numberOfRetries}")
    @ResponseBody
    public double calculate(@PathVariable int numberOfRetries) {
        log.info("Math started: ");
        log.info(" from: " + Instant.now());
        double result = longCalculationService.compute(numberOfRetries);
        log.info("   to: " + Instant.now());

        return result;
    }

    @GetMapping("/message/{content}")
    @ResponseBody
    public String sendMessage(@PathVariable String content) {
        rabbitSender.sendMessage(content);
        return "OK";
    }
}