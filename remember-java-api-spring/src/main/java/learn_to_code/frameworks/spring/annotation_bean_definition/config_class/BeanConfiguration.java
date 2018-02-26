package learn_to_code.frameworks.spring.annotation_bean_definition.config_class.beans;

import learn_to_code.frameworks.spring.annotation_bean_definition.config_class.BeanCreatorAndLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration is getting picked up in {@link BeanCreatorAndLauncher}
 * Most of the time you would want Spring to scan for all classes annotated with {@code @Configuration, @Component, @Controller}, etc
 */
@Configuration
public class BeanConfiguration {

    @Bean
    HelloDependency helloDependency() {
        return new HelloDependency();
    }

    @Bean
    WorldDependency worldDependency() {
        return new WorldDependency();
    }

    @Bean
    HelloWorldDependency helloWorldDependency() {
        return new HelloWorldDependency(helloDependency(), worldDependency());
    }
}
