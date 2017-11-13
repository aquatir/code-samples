package learn_to_code.frameworks.spring.annotation_bean_definition;


import learn_to_code.frameworks.spring.annotation_bean_definition.beans.HelloDependency;
import learn_to_code.frameworks.spring.annotation_bean_definition.beans.WorldDependency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependenciesHolder {
    @Bean
    HelloDependency helloDependency() {
        return new HelloDependency();
    }

    @Bean
    WorldDependency worldDependency() {
        return new WorldDependency();
    }
}
