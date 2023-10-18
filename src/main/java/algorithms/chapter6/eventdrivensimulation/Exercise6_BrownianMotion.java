package algorithms.chapter6.eventdrivensimulation;

import edu.princeton.cs.algs4.StdDraw;

public class Exercise6_BrownianMotion {

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
//        int n = Integer.parseInt(args[0]);
        int n = 600;
        Particle[] particles = new Particle[n];
        for (int i = 0; i < n-1; ++i) {
            particles[i] = new Particle(Particle.DEFAULT_RADIUS/4);
        }

        particles[n-1] = new Particle(Particle.DEFAULT_RADIUS*2);

        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(10_000, 0.5);
    }

}
