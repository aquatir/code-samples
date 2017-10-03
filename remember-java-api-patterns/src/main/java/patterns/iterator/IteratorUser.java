package patterns.iterator;

public class IteratorUser {

    Integer[] superUsefullData;

    public IteratorUser(Integer[] array) {

        superUsefullData = new Integer[array.length];
        superUsefullData = array.clone();
    }


    public Iterator<Integer> getIterator() {
        return new IntegerArrayIterator(superUsefullData);
    }

    public int getLength() {
        return superUsefullData.length;
    }

    public int getById(int id) {
        return superUsefullData[id];
    }
}
