package codesample.frameworks.aspectJ;

public class ExampleRunner {

    private static void printStuff() {
        System.out.println("I'm printing stuff!");
        printMoreStuff();
    }

    private static void printMoreStuff() {
        System.out.println("I'm printing more stuff");
    }

    public static void main(String[] args) {
        printStuff();
    }
}