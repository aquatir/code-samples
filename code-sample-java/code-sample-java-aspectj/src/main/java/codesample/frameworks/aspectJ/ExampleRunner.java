package codesample.frameworks.aspectJ;

public class ExampleRunner {

    public static void printStuff() {
        System.out.println("I'm printing stuff!");
        printMoreStuff();
    }

    public static void printMoreStuff() {
        System.out.println("I'm printing more stuff");
    }

    public static void main(String[] args) {
        printStuff();
    }
}