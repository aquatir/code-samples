package codesample.java_se_api.generics;

class CoordsPrinter {
    static void print2D(Coords<?> c) {
        System.out.println("x,y coords are:");
        for (int i = 0; i < c.coords.length; i++) {
            System.out.println("x: " + c.coords[i].x + " y: " + c.coords[i].y);
        }
        System.out.println();
    }

    static void print3D(Coords<? extends ThreeD> c) {
        System.out.println("x,y,z coords are:");
        for (int i = 0; i < c.coords.length; i++) {
            System.out.println("x: " + c.coords[i].x + " y: " + c.coords[i].y + " z: " + c.coords[i].z);
        }
        System.out.println();
    }

    static void print4D(Coords<? extends FourD> c) {
        System.out.println("x,y,z,t coords are:");
        for (int i = 0; i < c.coords.length; i++) {
            System.out.println("x: " + c.coords[i].x + " y: " + c.coords[i].y + " z: " + c.coords[i].z + " t: " + c.coords[i].t);
        }
        System.out.println();
    }
}
