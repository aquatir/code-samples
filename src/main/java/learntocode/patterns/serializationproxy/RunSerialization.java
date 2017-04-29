package learntocode.patterns.serializationproxy;

import java.io.*;

public class RunSerialization {

    public static void main(String[] args) {

        String serFilePath = "/tmp/serialization_check.ser";

        ClassToGetSerialized serialize = new ClassToGetSerialized(10, "foo");

        /* serialize */
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(serFilePath);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut);

            objectOutputStream.writeObject(serialize);
            objectOutputStream.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* deserialize. Also deleting serialized file */
        try {
            File file = new File(serFilePath);
            FileInputStream fileIn =
                    new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);

            ClassToGetSerialized deserialize = (ClassToGetSerialized) objectInputStream.readObject();

            objectInputStream.close();
            fileIn.close();
            file.delete();

            System.out.println("Deserialize int field: " + deserialize.getIntField() + " string field: " + deserialize.getStringField());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
