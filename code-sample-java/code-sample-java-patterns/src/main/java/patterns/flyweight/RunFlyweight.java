package patterns.flyweight;

class RunFlyweight {
    public static void main(String[] args) {

        FlyweightFactory factory = new FlyweightFactory();
        FlyweightObject obj1, obj2, obj3;

        obj1 = factory.getFlyweight(new InitParameters(5));
        obj2 = factory.getFlyweight(new InitParameters(3));

        /* this should print out Flyweight object already exists */
        obj3 = factory.getFlyweight(new InitParameters(5));

    }
}
