package algorithms.chapter6.eventdrivensimulation;

import edu.princeton.cs.algs4.StdDraw;

public class Exercise7_Temperature {

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
//        int n = Integer.parseInt(args[0]);
        int n = 20;
        Particle[] particles = new Particle[n];
        for (int i = 0; i < n; ++i) {
            particles[i] = new Particle();
        }

        CollisionSystemEntropy system = new CollisionSystemEntropy(particles, true);
        system.simulate(1000, 0.5);
    }

}
