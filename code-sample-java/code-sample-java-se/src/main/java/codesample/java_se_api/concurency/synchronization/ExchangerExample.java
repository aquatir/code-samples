package codesample.java_se_api.concurency.synchronization;

import java.util.concurrent.Exchanger;

/**
 * An example of Exchanger usage.
 *
 * We create 2 threads which will exchange their string via Exchanger. First thread will start with 'Hello' string, another
 * will start with 'World' string. After 1 second, threads will exchange their strings and print out the result.
 */
public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread exchangerHello = new Thread(new ExchangerUser(exchanger, "Hello", "Hello_exchanger"));
        Thread exchangerWorld = new Thread(new ExchangerUser(exchanger, "World", "World_exchanger"));

        exchangerHello.start();
        exchangerWorld.start();
    }
}

class ExchangerUser implements Runnable {

    Exchanger<String> exchanger;
    String exchangedString;
    String exchangerName;
    ExchangerUser(Exchanger<String> exchanger, String exchangedString, String exchangerName) {
        this.exchanger = exchanger;
        this.exchangedString = exchangedString;
        this.exchangerName = exchangerName;
    }
    @Override
    public void run() {
        try {
            System.out.println(exchangerName + " has string: " + exchangedString);
            Thread.sleep(2000);
            String strFromAnotherExchanger = exchanger.exchange(exchangedString);
            System.out.println(exchangerName + " got string from another exchanger: " + strFromAnotherExchanger);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}