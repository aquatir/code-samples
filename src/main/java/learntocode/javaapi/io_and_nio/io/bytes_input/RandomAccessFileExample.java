package learntocode.javaapi.io_and_nio.io.bytes_input;

import learntocode.javaapi.io_and_nio.FilesCreator;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile allows you to jump with file pointer anywhere in file.
 */
public class RandomAccessFileExample {
    public static void main(String[] args) throws IOException {
        File f = FilesCreator.createFilesIO()[0];

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(f, "rw")) {
            randomAccessFile.writeChars("Hello, world!");

            /* seek put a pointer to byte, specified as argument. Char is 16 bits in Java, so you should multiply
            * number of chars skipped by 2 in this example (This may not be true for different encodings */
            randomAccessFile.seek(7 * 2);
            for (int i =0; i < 6; i++) {
                System.out.print(randomAccessFile.readChar() + " ");
            }
        }
    }
}
