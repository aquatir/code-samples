package patterns.factories.factoryMethod;

public class RunFactoryMethod {
    public static void main(String[] args) {
        Creator creatorOne = new ConcreteCreatorOne();
        Creator creatorTwo = new ConcreteCreatorTwo();

        Product productOne = creatorOne.factoryMethod();
        Product productTwo = creatorTwo.factoryMethod();
    }
}
