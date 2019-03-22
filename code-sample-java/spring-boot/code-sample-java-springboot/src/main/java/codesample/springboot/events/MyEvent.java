package codesample.springboot.events;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  From Spring Framework 4.1 you dont even need to extend {@link org.springframework.context.ApplicationEvent}. You can
 *  just publish arbitrary objects. Remember, that those object WILL however get wrapped into
 *  {@link org.springframework.context.PayloadApplicationEvent}
 */
@Data
@AllArgsConstructor
public class MyEvent {

    private String value;

}
