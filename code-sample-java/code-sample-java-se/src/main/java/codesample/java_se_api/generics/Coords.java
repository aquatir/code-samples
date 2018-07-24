package codesample.java_se_api.generics;

public class Coords<T extends TwoD> {

    T[] coords;

    public Coords(T[] coords) {
        this.coords = coords;
    }
}
