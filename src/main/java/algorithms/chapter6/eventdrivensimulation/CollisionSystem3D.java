package algorithms.chapter6.eventdrivensimulation;

import algorithms.chapter2.section4.MinPQ;
import edu.princeton.cs.algs4.StdDraw3D;

public class CollisionSystem3D {

    private static class Event implements Comparable<Event> {

        private final double time;
        private final Particle3D a, b;
        private final int countA, countB;
        private final boolean zWall;

        public Event(double t, Particle3D a, Particle3D b, boolean zWall) {
            this.time = t;
            this.a = a;
            this.b = b;
            this.zWall = zWall;
            if (a != null) countA = a.collisionsCount(); else countA = -1;
            if (b != null) countB = b.collisionsCount(); else countB = -1;
        }

        public int compareTo(Event that) {
            if      (this.time < that.time) return -1;
            else if (this.time > that.time) return 1;
            return 0;
        }

        public boolean isValid() {
            if (a != null && a.collisionsCount() != countA) return false;
            if (b != null && b.collisionsCount() != countB) return false;
            return true;
        }

    }

    private MinPQ<Event> pq;
    private double t = 0.0; // Simulation clock time
    private Particle3D[] particles;

    public CollisionSystem3D(Particle3D[] particles) {
        this.particles = particles;
    }

    private void predictCollisions(Particle3D a, double limit) {
        if (a == null) return;

        // Predict hit with other particles
        for (int i = 0; i < particles.length; ++i) {
            Particle3D b = particles[i];
            double dt = a.timeToHit(b);
            if (t + dt <= limit) {
                pq.insert(new Event(t + dt, a, b, false));
            }
        }

        // Predict hit with vertical walls
        double dtX = a.timeToHitVerticalWall();
        if (t + dtX <= limit) {
            pq.insert(new Event(t + dtX, a, null, false));
        }

        // Predict hit with horizontal walls
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtY <= limit) {
            pq.insert(new Event(t + dtY, null, a, false));
        }

        // Predict hit with z walls
        double dtZ = a.timeToHitZWall();
        if (t + dtZ <= limit) {
            pq.insert(new Event(t + dtZ, a, null, true));
        }
    }

    public void redraw(double limit, double hz) {
        StdDraw3D.clear();
        for (int i = 0; i < particles.length; ++i) {
            particles[i].draw();
        }
        StdDraw3D.show();
        StdDraw3D.pause(20);
        if (t < limit) {
            pq.insert(new Event(t + 1.0/hz, null, null, false));
        }
    }

    public void simulate(double limit, double hz) {
        pq = new MinPQ<>();
        for (int i = 0; i < particles.length; ++i) {
            predictCollisions(particles[i], limit);
        }

        pq.insert(new Event(0, null, null, false)); // Add redraw event

        while (!pq.isEmpty()) {
            Event event = pq.delMin();
            if (!event.isValid()) continue;

            for (int i = 0; i < particles.length; ++i) {
                particles[i].move(event.time - t);
            }
            t = event.time;

            Particle3D a = event.a;
            Particle3D b = event.b;
            if      (a != null && b != null) a.bounceOff(b);
            else if (a != null && b == null) {
                if (event.zWall) {
                    a.bounceOffZWall();
                } else {
                    a.bounceOffVerticalWall();
                }
            }
            else if (a == null && b != null) b.bounceOffHorizontalWall();
            else if (a == null && b == null) redraw(limit, hz);

            predictCollisions(a, limit);
            predictCollisions(b, limit);
        }
    }

    public static void main(String[] args) {
        StdDraw3D.setCanvasSize(1000, 1000);
        StdDraw3D.setScale(-1, 1);
//        int n = Integer.parseInt(args[0]);
        int n = 20;
        Particle3D[] particles = new Particle3D[n];
        for (int i = 0; i < n; ++i) {
            particles[i] = new Particle3D();
        }

        CollisionSystem3D system = new CollisionSystem3D(particles);
        system.simulate(10_000, 0.5);
        StdDraw3D.finished();
    }

}
