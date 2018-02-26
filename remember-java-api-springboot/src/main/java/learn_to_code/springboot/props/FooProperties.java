package learn_to_code.springboot.props;

import learn_to_code.springboot.controllers.HelloWorldController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.InetAddress;
import java.util.List;

/**
 * {@code @Getter} and {@code @Setter} is lombok annotations. You can read up on project lombok here: https://projectlombok.org/
 * Basically they create getters and setter for all your variables
 *
 * This class allows you to instantiate properties in type-safe manner. First we define this class using annotation {@code @ConfigurationProperties}.
 * Then we define the values for this properties in you application.yml file. We also need to register this configuration explicitly with
 * {@code @EnableConfigurationProperties(FooProperties.class) } in our main class {@link learn_to_code.springboot.SpringBootRunner}.
 *
 * After doing all that, we can make type-safe property calls as shown in {@link HelloWorldController#getProps()} call
 */
@Getter
@Setter
@ConfigurationProperties("foo")
public class FooProperties {
    private boolean enabled;
    private InetAddress ip;
    private List<String> roles;
}
