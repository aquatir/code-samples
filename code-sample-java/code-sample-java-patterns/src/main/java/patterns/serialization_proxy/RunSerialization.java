package patterns.serialization_proxy;

import java.io.*;

public class RunSerialization {

    public static void main(String[] args) {

        String serFilePath = "/tmp/serialization_check.ser";

        ClassToGetSerialized serialize = new ClassToGetSerialized(10, "foo");

        /* serialize with try-with-resources */
        try (FileOutputStream fileOut =
                     new FileOutputStream(serFilePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut)
        ) {
            objectOutputStream.writeObject(serialize);

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* deserialize. Also deleting serialized file. Can't use try-with-resources, because File doesn't' implement Autoclosable */
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

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
