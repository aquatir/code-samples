package learntocode.patterns.factories.factoryMethod;

public abstract class SuperFactory {

    private int concreteFactorySpecificValue;

    public int getConcreteFactorySpecificValue() {
        return concreteFactorySpecificValue;
    }

    public void setConcreteFactorySpecificValue( int value ) {
        this.concreteFactorySpecificValue = value;
    }

    public void concreteFactorySpecificMethod() {}

}
