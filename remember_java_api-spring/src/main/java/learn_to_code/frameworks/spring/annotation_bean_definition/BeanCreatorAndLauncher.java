package learn_to_code.frameworks.spring.annotation_bean_definition;

import learn_to_code.frameworks.spring.annotation_bean_definition.beans.HelloDependency;
import learn_to_code.frameworks.spring.annotation_bean_definition.beans.HelloWorldDependency;
import learn_to_code.frameworks.spring.annotation_bean_definition.beans.WorldDependency;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanCreatorAndLauncher {

    private static final Logger log = LogManager.getLogger(BeanCreatorAndLauncher.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DependenciesHolder.class);
        HelloDependency dependencyHello = context.getBean(HelloDependency.class);
        WorldDependency dependencyWorld = context.getBean(WorldDependency.class);
        HelloWorldDependency dependencyHelloGreatWorld = context.getBean(HelloWorldDependency.class);

        log.info(dependencyHello.sayHello() + ", " + dependencyWorld.sayWorld());

        log.info(dependencyHelloGreatWorld.sayHello());
    }
}
