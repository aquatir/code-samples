package learn_to_code.frameworks.spring.annotation_bean_definition.beans;

public class HelloWorldDependency {
    private final HelloDependency helloDependency;
    private final WorldDependency worldDependency;

    public HelloWorldDependency(HelloDependency helloDependency, WorldDependency worldDependency) {
        this.helloDependency = helloDependency;
        this.worldDependency = worldDependency;
    }

    public String sayHello() {
        return helloDependency.sayHello() + " " + worldDependency.sayWorld();
    }
}
