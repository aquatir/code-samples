package learn_to_code.java_api.generics;

public class Coords<T extends TwoD> {

    T[] coords;

    public Coords(T[] coords) {
        this.coords = coords;
    }
}
