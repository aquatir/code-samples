package learn_to_code.frameworks.spring.annotation_bean_definition.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldDependency {

    @Autowired
    HelloDependency helloDependency;

    @Autowired
    WorldDependency worldDependency;

    public String sayHello() {
        return helloDependency.sayHello() + " great " + worldDependency.sayWorld();
    }
}
