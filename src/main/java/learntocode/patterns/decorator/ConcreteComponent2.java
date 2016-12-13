package learntocode.patterns.decorator;

public class ConcreteComponent2 implements Component {

    Component parrent;

    public ConcreteComponent2(Component parrent) {
        this.parrent = parrent;
    }

    @Override
    public void componentFunction() {
        parrent.componentFunction();
        System.out.println("+ Component 2");
    }
}
