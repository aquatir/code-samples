package learntocode.patterns.decorator;

public class ConcreteComponent1 implements Component {

    Component parrent;

    public ConcreteComponent1(Component parrent) {
        this.parrent = parrent;
    }

    @Override
    public void componentFunction() {
        parrent.componentFunction();
        System.out.println("+ Component 1");
    }
}
