package learntocode.javaapi.generics;

public class RunCoords {
    public static void main(String[] args) {
        TwoD[] two = {
                new TwoD(0, 5),
                new TwoD(1, 2),
                new TwoD(10, 19)
        };

        ThreeD[] threeD = {
                new ThreeD(1, 1, 3),
                new ThreeD(15, 25, 19),
                new ThreeD(3, 4, 5)
        };

        FourD[] fourD = {
                new FourD(0, 1, 2, 3),
                new FourD(32, 54, 12, 31),
                new FourD(12, 21, 43, 12)
        };

        Coords<TwoD> coordsTwoD = new Coords<>(two);
        Coords<ThreeD> coordsThreeD = new Coords<>(threeD);
        Coords<FourD> coordsFourD = new Coords<>(fourD);

        /* this will only print x and y of threeD*/
        CoordsPrinter.print2D(coordsThreeD);

        /* this can only take Coords<FourD> as input. */
        CoordsPrinter.print4D(coordsFourD);


        /* this can only print x,y,z of Coords<FourD> */
        CoordsPrinter.print3D(coordsFourD);
    }


}
