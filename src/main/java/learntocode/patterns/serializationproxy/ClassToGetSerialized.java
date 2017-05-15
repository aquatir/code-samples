package learntocode.patterns.serializationproxy;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Serialization proxy pattern allows you to accomplish 2 tasks
 * <ol>
 *     <li>Put all your serialization logic (and logical representation of class) to another class </li>
 *     <li>Allow to use serialization where usually you would struggle to do so</li>
 * </ol>
 *
 * First item is self-explanatory. Second is based on the idea that usually serialization can happens
 * on no-parameters constructor. But sometimes you don't actually want to provide such a constructor. So instead
 * you prove a serialization proxy class which will handle all serialization work for you.
 */
class ClassToGetSerialized implements Serializable {

    ClassToGetSerialized(int intField, String stringField) {
        this.intField = intField;
        this.stringField = stringField;
    }

    private int intField;
    private String stringField;

    String getStringField() {
        return stringField;
    }

    int getIntField() {
        return intField;
    }

    /**
     * This method basically say: When you want to serialize this class
     * you should actually serialize another class instead. All the serialization logic
     * can go to that other proxy class.
     * <p>
     * When deserialization will occur (By calling readResolve method of Serialization proxy)
     * This proxy would return an object which can easily be cast to {@code ClassToGetSerialized}
     * (because it naturally returns {@code ClassToGetSerialized} but in a form of {@code Object} instance)
     *
     * @return Serialized object instance
     */
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    /**
     * We never want to deserialize {@code ClassToGetSerialized}. NEVER! Actually, we don't even
     * have a way to serialize it now, as each time we do, we create {@code SerializationProxy} instead.
     * But anyway, this may be useful if at some point you switched your serialization mechanism to serialization proxy
     * and don't want to even attempt to deserialize old class instances
     *
     * @param stream serializing object stream
     * @throws InvalidObjectException always thrown to protect from demoralizing {@code ClassToGetSerialized} instead of
     *                                {@code SerializationProxy}
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use Serialization Proxy instead.");
    }


    private static class SerializationProxy implements Serializable {
        private int intField;
        private String stringField;

        /**
         * This constructor is called when you serialize {@code ClassToGetSerialized} <br>
         *     method calls go like this <br>
         *         ClassToGetSerialized -> writeReplace (which return an instance of {@code SerializationProxy} -> this constructor
         * @param classToGetSerialized class to get serialized
         */
        SerializationProxy(ClassToGetSerialized classToGetSerialized) {
            this.intField = classToGetSerialized.getIntField();
            this.stringField = classToGetSerialized.getStringField();
        }

        private Object readResolve() {
            return new ClassToGetSerialized(intField, stringField);
        }
    }
}
