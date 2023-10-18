package algorithms.chapter6.eventdrivensimulation;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;

public class Particle {

    public static double BOLTZMANN_CONSTANT = 1.380649e-23;
    public static final double DEFAULT_RADIUS = 0.03;

    public double px;
    public double py;
    public double vx;
    public double vy;
    public double r;
    public double mass;
    public int collisionsCount = 0;


    public Particle() {
        this.px = StdRandom.uniform(2*DEFAULT_RADIUS, 1.0);
        this.py = StdRandom.uniform(2*DEFAULT_RADIUS, 1.0);
        this.vx = StdRandom.uniform(-0.002, 0.002);
        this.vy = StdRandom.uniform(-0.002, 0.002);
        this.r = DEFAULT_RADIUS;
        this.mass = StdRandom.uniform(0.001, 0.002);
    }

    public Particle(double r) {
        this.px = StdRandom.uniform(2*DEFAULT_RADIUS, 1.0);
        this.py = StdRandom.uniform(2*DEFAULT_RADIUS, 1.0);
        this.vx = StdRandom.uniform(-0.002, 0.002);
        this.vy = StdRandom.uniform(-0.002, 0.002);
        this.r = r;
        this.mass = StdRandom.uniform(0.001, 0.002);
    }

    public Particle(double px, double py,
                    double vx, double vy,
                    double r, double mass) {

        this.px = px;
        this.py = py;
        this.vx = vx;
        this.vy = vy;
        this.r = r;
        this.mass = mass;
    }

    public void draw() {
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.filledCircle(px, py, r);
    }

    public void move(double dt) {
        px += vx*dt;
        py += vy*dt;
    }

    public int collisionsCount() {
        return collisionsCount;
    }

    private static double sq(double a) {
        return Math.pow(a, 2);
    }

    private static double sqrt(double a) {
        return Math.sqrt(a);
    }

    public double timeToHit(Particle b) {
        double result;

        double dvx = b.vx - this.vx;
        double dvy = b.vy - this.vy;
        double dx = b.px - this.px;
        double dy = b.py - this.py;
        double totalRadius = this.r + b.r;

        double dvdp = (dvx*dx) + (dvy*dy);
        double dvdv = sq(dvx) + sq(dvy);
        double dpdp = sq(dx) + sq(dy);
        double d = sq(dvdp) - ((dvdv)*(dpdp - sq(totalRadius)));
        double numerator = dvdp + sqrt(d);
        double denominator = dvdv;

        if (dvdp >= 0 || d < 0) {
            result = Double.POSITIVE_INFINITY;
        } else {
            result = -(numerator / denominator);
        }

        return result;
    }

    public double timeToHitHorizontalWall() {
        double timeToHit;
        if (vy > 0) {
            timeToHit = (1 - r - py) / vy;
        } else if (vy < 0) {
            timeToHit = (r - py) / vy;
        } else {
            timeToHit = Double.POSITIVE_INFINITY;
        }

        return timeToHit;
    }

    public double timeToHitVerticalWall() {
        double timeToHit;
        if (vx > 0) {
            timeToHit = (1 - r - px) / vx;
        } else if (vx < 0) {
            timeToHit = (r - px) / vx;
        } else {
            timeToHit = Double.POSITIVE_INFINITY;
        }

        return timeToHit;
    }

    public void bounceOff(Particle b) {
        double deltaVx = b.vx - this.vx;
        double deltaVy = b.vy - this.vy;
        double deltaPx = b.px - this.px;
        double deltaPy = b.py - this.py;
        double totalRadius = this.r + b.r;

        double deltaVTimesDeltaP = (deltaVx*deltaPx) + (deltaVy*deltaPy);
        double mi = this.mass;
        double mj = b.mass;
        double numerator = 2*mi*mj*(deltaVTimesDeltaP);
        double denominator = totalRadius*(mi + mj);
        double j = numerator / denominator;

        // Impulses
        double jx = (j*deltaPx) / totalRadius;
        double jy = (j*deltaPy) / totalRadius;

        // Update velocities applying Newton's second law
        vx = vx + (jx / mi);
        vy = vy + (jy / mi);

        b.vx = b.vx - (jx / mj);
        b.vy = b.vy - (jy / mj);

        collisionsCount++;
        b.collisionsCount++;
    }

    public void bounceOffHorizontalWall() {
        vy = -vy;
        collisionsCount++;
    }

    public void bounceOffVerticalWall() {
        vx = -vx;
        collisionsCount++;
    }

    public double temperature() {
        int dimensions = 2;
        double vMagnitude = sq(vx) + sq(vy);
        return (mass*sq(vMagnitude)) / (dimensions*BOLTZMANN_CONSTANT);
    }

}
