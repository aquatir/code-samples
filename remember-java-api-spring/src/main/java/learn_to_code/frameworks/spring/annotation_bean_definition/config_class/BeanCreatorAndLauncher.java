package learn_to_code.frameworks.spring.annotation_bean_definition.config_class;


import learn_to_code.frameworks.spring.annotation_bean_definition.config_class.beans.HelloDependency;
import learn_to_code.frameworks.spring.annotation_bean_definition.config_class.beans.HelloWorldDependency;
import learn_to_code.frameworks.spring.annotation_bean_definition.config_class.beans.WorldDependency;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This example shows how to configure Spring beans in most straight-forward fashion. This was okay around spring 2-3.
 * Now you probably want to stick to bean definition using {@code @ComponentScan} now
 */
public class BeanCreatorAndLauncher {

    private static final Logger log = LogManager.getLogger(BeanCreatorAndLauncher.class);

    public static void main(String[] args) {
        /* We configure context from configuration class. This class defines 3 beans, so we can now
        * query those beans and use them */
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        HelloDependency dependencyHello = context.getBean(HelloDependency.class);
        WorldDependency dependencyWorld = context.getBean(WorldDependency.class);
        HelloWorldDependency dependencyHelloGreatWorld = context.getBean(HelloWorldDependency.class);

        log.info(dependencyHello.sayHello() + ", " + dependencyWorld.sayWorld());

        log.info(dependencyHelloGreatWorld.sayHello());
    }
}
