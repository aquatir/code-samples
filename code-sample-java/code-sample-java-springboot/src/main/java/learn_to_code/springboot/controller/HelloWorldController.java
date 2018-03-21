package learn_to_code.springboot.controller;

import learn_to_code.springboot.events.MyEventPublisher;
import learn_to_code.springboot.props.FooProperties;
import learn_to_code.springboot.rabbit.Sender;
import learn_to_code.springboot.service.LongCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/*
 * This single annotation defines both @Controller and @ResponseBody
 */
@RestController

/* Loggin with spring-boot and Lombok is trivial. Simply annotate your class with @Slf4j (or any other logging annotation
from https://projectlombok.org/features/log for respective log). Spring-boot will autoconfigure required log.
You can then use normal spring-boot configuration for loggers, e.g. setting logging.file or logging.level */
@Slf4j
@RefreshScope
public class HelloWorldController {

    @Autowired
    private MyEventPublisher publisher;

    @Autowired
    private FooProperties props;

    @Autowired
    private LongCalculationService longCalculationService;
    @Autowired
    private Sender rabbitSender;

    @Autowired
    private ReloadableResourceBundleMessageSource myReloadableProperties;

    @Value("${prop.some_property}")
    private String remoteProperty;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        log.debug("Logging with slf4j");
        return "Greetings from Spring Boot";
    }

    @GetMapping("/props")
    public String getProps() {
        return "ip: " + props.getIp().toString() + " enabled: " + props.isEnabled() + " roles: " + props.getRoles();
    }

    /**
     * See HelloWorldControllerTest for usage example
     */
    @GetMapping("/props/reloadable")
    public String getReloadableProperties() {
        return myReloadableProperties.getMessage("reloadable.property", null, null);
    }

    @GetMapping("/props/remote")
    public String getRemoteProperty() {
        return remoteProperty;
    }

    /**
     * See {@link LongCalculationService} comment to info about caching. You can try calling this service with big numbers
     * 2 time in a row (e.g /math/100000000) to see that cache actually works
     */
    @GetMapping("/math/{numberOfRetries}")
    public double calculate(@PathVariable int numberOfRetries) {
        log.info("Math started: ");
        log.info(" from: " + Instant.now());
        double result = longCalculationService.compute(numberOfRetries);
        log.info("   to: " + Instant.now());

        return result;
    }

    /**
     * See {@link learn_to_code.springboot.rabbit} package-info for more info about rabbit
     * Note: you should configure rabbitMQ on your machine in order for it to work properly
     */
    @GetMapping("/message/{content}")
    public String sendMessage(@PathVariable String content) {
        rabbitSender.sendMessage(content);
        return "OK";
    }

    /**
     * See {@link learn_to_code.springboot.events.MyEvent}, {@link MyEventPublisher}, {@link learn_to_code.springboot.events.MyEventListener}
     * for more info.
     */
    @GetMapping("/event/{text}")
    public String sendEvent(@PathVariable String text) {
        log.info("Got event with text: {} from user", text);
        publisher.publishEvent(text);
        return text;
    }

}
