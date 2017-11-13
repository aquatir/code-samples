package learn_to_code.frameworks.spring.annotation_bean_definition.beans;

public class HelloWorldMegaBean {
    private HelloDependency hello;
    private WorldDependency world;

    HelloWorldMegaBean (HelloDependency hello, WorldDependency world) {
        this.hello = hello;
        this.world = world;
    }

    public String sayWorld() {
        return hello.sayHello() + " ," + world.sayWorld();
    }
}
