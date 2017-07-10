package learntocode.javaapi.io_and_nio.io.bytes_input;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayInputStreamsExample {

    public static void main (String[] args) throws IOException {

        /*  ------------------------------
            ------------------------------
                Print letters of alphabet from 10th letter to 20th.
            ------------------------------
            ------------------------------
        */
        /* Alphabet */
        System.out.println("Example 1:");
        String abc = "abcdefghijklmnopqrstuvwzyx";
        byte[] arr = abc.getBytes();

        /* This will contain letters from 10th to 20th (The inputs are: start from here and then include 10 more bytes to input stream */
        ByteArrayInputStream inputStream = new ByteArrayInputStream(arr, 10,10);
        int b;
        while ((b = inputStream.read()) != -1) {
            System.out.print((char) b + " ");
        }
        System.out.println("\n");

        /*  ------------------------------
            ------------------------------
                Mark after 5 bytes, mark, continue reading, than reset to mark and output in upper register
            ------------------------------
            ------------------------------
        */
        System.out.println("Example 2:");
        inputStream = new ByteArrayInputStream(arr, 10,10);

        int i =0;
        while ((b = inputStream.read()) != -1) {
            ++i;
            System.out.print((char) b + " ");
            if (i == 5) {
                inputStream.mark(i);
            }
        }
        System.out.println();
        inputStream.reset();
        while ((b = inputStream.read()) != -1) {
            System.out.print(Character.toUpperCase((char) b) + " ");
        }
        System.out.println("\n");


        /* closing stream */
        inputStream.close();
    }
}
