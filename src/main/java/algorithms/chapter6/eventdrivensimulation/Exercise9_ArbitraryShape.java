package algorithms.chapter6.eventdrivensimulation;

import algorithms.chapter2.section4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;
import java.awt.event.KeyEvent;

import static algorithms.chapter6.eventdrivensimulation.Particle.DEFAULT_RADIUS;

public class Exercise9_ArbitraryShape {

    public static class CollisionSystemArbitraryShape {

        public static final double VESSEL_HALF_WIDTH_DEFAULT = 0.1;
        public static final double VESSEL_HALF_HEIGHT_DEFAULT = 0.2;


        private class Event implements Comparable<Event> {

            private final double time;
            private final ParticleArbitraryShape a, b;
            private final int countA, countB;

            public Event(double t, ParticleArbitraryShape a, ParticleArbitraryShape b) {
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

        private static class ParticleArbitraryShape extends Particle {

            int index;

            public ParticleArbitraryShape(double px, double py,
                                        double vx, double vy,
                                        double r, double mass, int index) {

                super(px, py, vx, vy, r, mass);
                this.index = index;
            }

            public double timeToHitHorizontalWall() {
                double timeToHit;
                if (vy > 0) {
                    if (px + r >= topVessel.leftWall() && px - r <= topVessel.rightWall() && py + r <= topVessel.bottomWall()) {
                        timeToHit = (topVessel.bottomWall() - r - py) / vy;
                    } else {
                        timeToHit = (1 - r - py) / vy;
                    }
                } else if (vy < 0) {
                    if (px + r >= bottomVessel.leftWall() && px - r <= bottomVessel.rightWall() && py - r >= bottomVessel.topWall()) {
                        timeToHit = (r - py + bottomVessel.topWall()) / vy;
                    } else {
                        timeToHit = (r - py) / vy;
                    }
                } else {
                    timeToHit = Double.POSITIVE_INFINITY;
                }

                return timeToHit;
            }

            private boolean isInBottomVesselRange(double py, double r) {
                return py - r >= bottomVessel.bottomWall() && py - r <= bottomVessel.topWall();
            }

            private boolean isInTopVesselRange(double py, double r) {
                return py + r >= topVessel.bottomWall() && py + r <= topVessel.topWall();
            }

            public double timeToHitVerticalWall() {
                double timeToHit;
                if (vx > 0) {
                    // Hit from left
                    if (isInBottomVesselRange(py, r) && px + r <= bottomVessel.leftWall()) {
                        timeToHit = (bottomVessel.leftWall() - r - px) / vx;
                    } else if (isInTopVesselRange(py, r) && px + r <= topVessel.leftWall()) {
                        timeToHit = (topVessel.leftWall() - r - px) / vx;
                    } else {
                        timeToHit = (1 - r - px) / vx;
                    }
                } else if (vx < 0) {
                    // Hit from right
                    if (isInBottomVesselRange(py, r) && px + r >= bottomVessel.rightWall()) {
                        timeToHit = (r - px + bottomVessel.rightWall()) / vx;
                    } else if (isInTopVesselRange(py, r) && px + r >= topVessel.rightWall()) {
                        timeToHit = (r - px + topVessel.rightWall()) / vx;
                    } else {
                        timeToHit = (r - px) / vx;
                    }
                } else {
                    timeToHit = Double.POSITIVE_INFINITY;
                }

                return timeToHit;
            }
        }

        private static class VesselRectangle {
            double x;
            double y;
            double halfWidth;
            double halfHeight;

            VesselRectangle(double x, double y, double halfWidth, double halfHeight) {
                this.x = x;
                this.y = y;
                this.halfWidth = halfWidth;
                this.halfHeight = halfHeight;
            }

            double topWall() {
                return y + halfHeight;
            }

            double bottomWall() {
                return y - halfHeight;
            }

            double leftWall() {
                return x - halfWidth;
            }

            double rightWall() {
                return x + halfWidth;
            }

            void draw() {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
            }
        }

        private MinPQ<Event> pq;
        private double t = 0.0; // Simulation clock time
        private ParticleArbitraryShape[] particles;
        private static VesselRectangle topVessel = new VesselRectangle(0.5, 1.0 - VESSEL_HALF_HEIGHT_DEFAULT, VESSEL_HALF_WIDTH_DEFAULT, VESSEL_HALF_HEIGHT_DEFAULT);
        private static VesselRectangle bottomVessel = new VesselRectangle(0.5, VESSEL_HALF_HEIGHT_DEFAULT, VESSEL_HALF_WIDTH_DEFAULT, VESSEL_HALF_HEIGHT_DEFAULT);

        public CollisionSystemArbitraryShape(ParticleArbitraryShape[] particles) {
            this.particles = particles;
        }

        private void predictCollisions(ParticleArbitraryShape a, double limit) {
            if (a == null) return;

            // Predict hit with other particles
            for (int i = 0; i < particles.length; ++i) {
                ParticleArbitraryShape b = particles[i];
                double dt = a.timeToHit(b);
                if (t + dt <= limit) {
                    pq.insert(new Event(t + dt, a, b));
                }
            }

            // Predict hit with vertical walls
            double dtX = a.timeToHitVerticalWall();
            if (t + dtX <= limit) {
                pq.insert(new Event(t + dtX, a, null));
            }

            // Predict hit with horizontal walls
            double dtY = a.timeToHitHorizontalWall();
            if (t + dtY <= limit) {
                pq.insert(new Event(t + dtY, null, a));
            }
        }

        public void redraw(double limit, double hz) {
            StdDraw.clear();
            for (int i = 0; i < particles.length; ++i) {
                particles[i].draw();
            }

            topVessel.draw();
            bottomVessel.draw();

            StdDraw.show();
            StdDraw.pause(20);
            if (t < limit) {
                pq.insert(new Event(t + 1.0/hz, null, null));
            }
        }

        public void simulate(double limit, double hz) {
            pq = new MinPQ<>();
            for (int i = 0; i < particles.length; ++i) {
                predictCollisions(particles[i], limit);
            }

            pq.insert(new Event(0, null, null)); // Add redraw event

            while (!pq.isEmpty()) {
                Event event = pq.delMin();
                if (!event.isValid()) continue;

                for (int i = 0; i < particles.length; ++i) {
                    particles[i].move(event.time - t);
                }
                t = event.time;

                ParticleArbitraryShape a = event.a;
                ParticleArbitraryShape b = event.b;
                if      (a != null && b != null) a.bounceOff(b);
                else if (a != null && b == null) a.bounceOffVerticalWall();
                else if (a == null && b != null) b.bounceOffHorizontalWall();
                else if (a == null && b == null) redraw(limit, hz);

                predictCollisions(a, limit);
                predictCollisions(b, limit);
            }
        }

    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.enableDoubleBuffering();
//        int n = Integer.parseInt(args[0]);
        int n = 30;
        CollisionSystemArbitraryShape.ParticleArbitraryShape[] particles = new CollisionSystemArbitraryShape.ParticleArbitraryShape[n];
        for (int i = 0; i < n/2; ++i) {
            particles[i] = new CollisionSystemArbitraryShape.ParticleArbitraryShape(StdRandom.uniform(2*DEFAULT_RADIUS, 0.5 - CollisionSystemArbitraryShape.VESSEL_HALF_WIDTH_DEFAULT), StdRandom.uniform(2*DEFAULT_RADIUS, 1.0),
                                                                                    StdRandom.uniform(-0.002, 0.002), StdRandom.uniform(-0.002, 0.002),
                                                                                    DEFAULT_RADIUS, StdRandom.uniform(0.001, 0.002),
                                                                                    i);
        }

        for (int i = n/2; i < n; ++i) {
            particles[i] = new CollisionSystemArbitraryShape.ParticleArbitraryShape(StdRandom.uniform(0.5 + CollisionSystemArbitraryShape.VESSEL_HALF_WIDTH_DEFAULT, 1.0), StdRandom.uniform(2*DEFAULT_RADIUS, 1.0),
                                                                                    StdRandom.uniform(-0.002, 0.002), StdRandom.uniform(-0.002, 0.002),
                                                                                    DEFAULT_RADIUS, StdRandom.uniform(0.001, 0.002),
                                                                                    i);
        }

        CollisionSystemArbitraryShape system = new CollisionSystemArbitraryShape(particles);
        system.simulate(10_000, 0.5);
    }

}
