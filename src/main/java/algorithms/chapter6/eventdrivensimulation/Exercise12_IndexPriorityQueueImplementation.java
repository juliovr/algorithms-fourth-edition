package algorithms.chapter6.eventdrivensimulation;

import algorithms.chapter2.section4.IndexMinPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Exercise12_IndexPriorityQueueImplementation {

    private static class CollisionSystemIndexPriorityQueue {

        private static class Event implements Comparable<Event> {

            private final double time;
            private final ParticleIndex a, b;
            private final int countA, countB;

            public Event(double t, ParticleIndex a, ParticleIndex b) {
                this.time = t;
                this.a = a;
                this.b = b;
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

        private static class ParticleIndex extends Particle {

            int id;

            public ParticleIndex(int id) {
                super();
                this.id = id;
            }

        }

        private IndexMinPQ<Event> pq;
        private double t = 0.0; // Simulation clock time
        private ParticleIndex[] particles;
        private int redrawIndex;

        public CollisionSystemIndexPriorityQueue(ParticleIndex[] particles) {
            this.particles = particles;
            this.redrawIndex = particles.length;
        }

        private void predictCollisions(ParticleIndex a, double limit) {
            if (a == null) return;

            // Each event is stored using the same particle id (a.id), so timeToHitHorizontalWall will override timeToHitVerticalWall.
            // We check whether it is smaller than the previously stored.
            double smallestTime = Double.POSITIVE_INFINITY;

            // Predict hit with other particles
            for (int i = 0; i < particles.length; ++i) {
                ParticleIndex b = particles[i];
                double dt = a.timeToHit(b);
                double eventTime = t + dt;
                if (eventTime <= limit && eventTime <= smallestTime) {
                    if (pq.contains(a.id)) pq.delete(a.id);
                    pq.insert(a.id, new Event(eventTime, a, b));
                    smallestTime = eventTime;
                }
            }

            // Predict hit with vertical walls
            double dtX = a.timeToHitVerticalWall();
            double deltaTimeVerticalWall = t + dtX;
            if (deltaTimeVerticalWall <= limit && deltaTimeVerticalWall <= smallestTime) {
                if (pq.contains(a.id)) pq.delete(a.id);
                pq.insert(a.id, new Event(deltaTimeVerticalWall, a, null));
                smallestTime = deltaTimeVerticalWall;
            }

            // Predict hit with horizontal walls
            double dtY = a.timeToHitHorizontalWall();
            double deltaTimeHorizontalWall = t + dtY;
            if (deltaTimeHorizontalWall <= limit && deltaTimeHorizontalWall <= smallestTime) {
                if (pq.contains(a.id)) pq.delete(a.id);
                pq.insert(a.id, new Event(deltaTimeHorizontalWall, null, a));
            }
        }

        public void redraw(double limit, double hz) {
            StdDraw.clear();
            for (int i = 0; i < particles.length; ++i) {
                particles[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(20);
            if (t < limit) {
                pq.insert(redrawIndex, new Event(t + 1.0/hz, null, null));
            }
        }

        public void simulate(double limit, double hz) {
            pq = new IndexMinPQ<>(particles.length + 1);
            for (int i = 0; i < particles.length; ++i) {
                predictCollisions(particles[i], limit);
            }

            pq.insert(redrawIndex, new Event(0, null, null)); // Add redraw event

            while (!pq.isEmpty()) {
                Event event = pq.minKey();
                pq.delMin();

                if (!event.isValid()) continue;

                for (int i = 0; i < particles.length; ++i) {
                    particles[i].move(event.time - t);
                }
                t = event.time;

                ParticleIndex a = event.a;
                ParticleIndex b = event.b;
                if      (a != null && b != null) a.bounceOff(b);
                else if (a != null && b == null) a.bounceOffVerticalWall();
                else if (a == null && b != null) b.bounceOffHorizontalWall();
                else if (a == null && b == null) redraw(limit, hz);

                predictCollisions(a, limit);
                predictCollisions(b, limit);
            }
        }

        public static void main(String[] args) {
            StdDraw.setCanvasSize(1000, 1000);
            StdDraw.enableDoubleBuffering();
//        int n = Integer.parseInt(args[0]);
            int n = 20;
            ParticleIndex[] particles = new ParticleIndex[n];
            for (int i = 0; i < n; ++i) {
                particles[i] = new ParticleIndex(i);
            }

            CollisionSystemIndexPriorityQueue system = new CollisionSystemIndexPriorityQueue(particles);
            system.simulate(10_000, 0.5);
        }

    }

}
