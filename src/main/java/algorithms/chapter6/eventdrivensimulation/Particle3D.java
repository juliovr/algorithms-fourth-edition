package algorithms.chapter6.eventdrivensimulation;

import edu.princeton.cs.algs4.StdDraw3D;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;

public class Particle3D {

    private static final double DEFAULT_RADIUS = 0.1;

    private double px;
    private double py;
    private double pz;
    private double vx;
    private double vy;
    private double vz;
    private double r;
    private double mass;
    private int collisionsCount = 0;


    public Particle3D() {
        this.px = StdRandom.uniform(2*DEFAULT_RADIUS, 1.0);
        this.py = StdRandom.uniform(2*DEFAULT_RADIUS, 1.0);
        this.pz = StdRandom.uniform(2*DEFAULT_RADIUS, 1.0);
        this.vx = StdRandom.uniform(-0.002, 0.002);
        this.vy = StdRandom.uniform(-0.002, 0.002);
        this.vz = StdRandom.uniform(-0.002, 0.002);
        this.r = DEFAULT_RADIUS;
        this.mass = StdRandom.uniform(0.001, 0.002);
    }

    public Particle3D(double px, double py, double pz,
                      double vx, double vy, double vz,
                      double r, double mass) {

        this.px = px;
        this.py = py;
        this.pz = pz;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.r = r;
        this.mass = mass;
    }

    public void draw() {
        StdDraw3D.setPenColor(Color.GRAY);
        StdDraw3D.sphere(px, py, pz, DEFAULT_RADIUS);
    }

    public void move(double dt) {
        px += vx*dt;
        py += vy*dt;
        pz += vz*dt;
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

    public double timeToHit(Particle3D b) {
        double result;

        double dvx = b.vx - this.vx;
        double dvy = b.vy - this.vy;
        double dvz = b.vz - this.vz;
        double dx = b.px - this.px;
        double dy = b.py - this.py;
        double dz = b.pz - this.pz;
        double totalRadius = this.r + b.r;

        double dvdp = (dvx*dx) + (dvy*dy) + (dvz*dz);
        double dvdv = sq(dvx) + sq(dvy) + sq(dvz);
        double dpdp = sq(dx) + sq(dy) + sq(dz);
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

    public double timeToHitZWall() {
        double timeToHit;
        if (vz > 0) {
            timeToHit = (1 - r - pz) / vz;
        } else if (vz < 0) {
            timeToHit = (r - pz) / vz;
        } else {
            timeToHit = Double.POSITIVE_INFINITY;
        }

        return timeToHit;
    }

    public void bounceOff(Particle3D b) {
        System.out.println("[DEBUG] bounce off");
        double deltaVx = b.vx - this.vx;
        double deltaVy = b.vy - this.vy;
        double deltaVz = b.vz - this.vz;
        double deltaPx = b.px - this.px;
        double deltaPy = b.py - this.py;
        double deltaPz = b.pz - this.pz;
        double totalRadius = this.r + b.r;

        double deltaVTimesDeltaP = (deltaVx*deltaPx) + (deltaVy*deltaPy) + (deltaVz*deltaPz);
        double mi = this.mass;
        double mj = b.mass;
        double numerator = 2*mi*mj*(deltaVTimesDeltaP);
        double denominator = totalRadius*(mi + mj);
        double j = numerator / denominator;

        // Impulses
        double jx = (j*deltaPx) / totalRadius;
        double jy = (j*deltaPy) / totalRadius;
        double jz = (j*deltaPz) / totalRadius;

        // Update velocities applying Newton's second law
        vx = vx + (jx / mi);
        vy = vy + (jy / mi);
        vz = vz + (jz / mi);

        b.vx = b.vx - (jx / mj);
        b.vy = b.vy - (jy / mj);
        b.vz = b.vz - (jz / mj);

        collisionsCount++;
        b.collisionsCount++;
    }

    public void bounceOffHorizontalWall() {
        System.out.println("[DEBUG] bounce off horizontal wall");
        vy = -vy;
        collisionsCount++;
    }

    public void bounceOffVerticalWall() {
        System.out.println("[DEBUG] bounce off vertical wall");
        vx = -vx;
        collisionsCount++;
    }

    public void bounceOffZWall() {
        System.out.println("[DEBUG] bounce off z wall");
        vz = -vz;
        collisionsCount++;
    }

}
