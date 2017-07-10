package learntocode.javaapi.io_and_nio.io.bytes_input;

import learntocode.javaapi.io_and_nio.FilesCreator;

import java.io.*;


/**
 * Data input/output streams are binary streams which allow you to read and write java primitive in files
 */
public class DataInputOutputStreamExample {
    public static void main(String[] args) throws IOException {
        File f = FilesCreator.createFilesIO()[0];

        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(f))) {

            dataOutputStream.writeInt(123);
            dataOutputStream.writeChar('H');
            dataOutputStream.writeDouble(532.213);
            dataOutputStream.writeBoolean(true);
        }

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(f))) {
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readChar());
            System.out.println(dataInputStream.readDouble());
            System.out.println(dataInputStream.readBoolean());
        }

    }
}
