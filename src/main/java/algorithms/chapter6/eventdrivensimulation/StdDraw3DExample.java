package algorithms.chapter6.eventdrivensimulation;

import edu.princeton.cs.algs4.StdDraw3D;

public class StdDraw3DExample {

    public static void main(String[] args) {
        // set the scale of the drawing window
        StdDraw3D.setScale(-1,1);

        // draw a sphere of radius 1 centered at (0,0,0)
        StdDraw3D.sphere(0, 0, 0, 0.1);

        // render to the drawing window
        StdDraw3D.finished();
    }

}
