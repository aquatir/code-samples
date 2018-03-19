package learn_to_code.springboot.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Here we show both eventListener and a little bit of SpEL.
 *
 * Please read javadocs to {@link MyEvent} and {@link MyEventPublisher} first.
 *
 * We define 2 listeners for our event in this class. The event class
 * is specified as argument to listening method. One of the listeners will listen to any
 * {@link MyEvent} published in context. The second will ONLY listen to event with {@link MyEvent#value} property
 * being equal to 'Hello'. Note that we do string comparison with simple == and that our condition starts with # sign.
 * This is Spring Expression Language (SpEL). Read the dock for more info (Dont forget to refer to your version of
 * spring): https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#expressions
 */
@Component
@Slf4j
public class MyEventListener {

    @EventListener
    public String handleEvent(MyEvent myEvent) {
        log.info("Got event: {}. Returning string value", myEvent);
        return myEvent.getValue();
    }

    @EventListener(condition = "#myEvent.value == 'Hello'")
    public String handleHelloString(MyEvent myEvent) {
        log.info("Got event: {}. Returning string value. I'm very special event", myEvent);
        return myEvent.getValue();
    }
}
