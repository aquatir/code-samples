package learn_to_code.springboot.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * This publisher can publish {@link MyEvent}. All you need to do in order to publish an event is to get an instance
 * of {@link ApplicationEventPublisher} and call publishEvent method with published object.
 *
 * Note: your event does not need to extend {@link org.springframework.context.ApplicationEvent} class anymore as of
 * spring 4.2. HOWEVER, you event WILL be wrapped in {@link org.springframework.context.PayloadApplicationEvent} if
 * it does not subclass ApplicationEvent
 */
@Component
public class MyEventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishEvent(String text) {
        publisher.publishEvent(new MyEvent(text));
    }
}
