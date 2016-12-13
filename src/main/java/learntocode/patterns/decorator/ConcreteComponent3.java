package learntocode.patterns.decorator;

public class ConcreteComponent3 implements Component {

    Component parrent;

    public ConcreteComponent3(Component parrent) {
        this.parrent = parrent;
    }

    @Override
    public void componentFunction() {
        parrent.componentFunction();
        System.out.println("+ Component 3");
    }
}
