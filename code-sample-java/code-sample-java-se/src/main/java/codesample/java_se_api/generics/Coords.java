package codesample.java_se_api.generics;

class Coords<T extends TwoD> {

    final T[] coords;

    public Coords(T[] coords) {
        this.coords = coords;
    }
}
