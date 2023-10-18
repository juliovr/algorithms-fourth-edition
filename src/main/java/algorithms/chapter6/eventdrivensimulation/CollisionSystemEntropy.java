package algorithms.chapter6.eventdrivensimulation;

import algorithms.MathUtil;
import algorithms.chapter2.section4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;

public class CollisionSystemEntropy {

    private static class Event implements Comparable<Event> {

        private final double time;
        private final Particle a, b;
        private final int countA, countB;

        public Event(double t, Particle a, Particle b) {
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


    private static final int ROW_SIZE = 4;
    private static final int COL_SIZE = 4;

    private MinPQ<Event> pq;
    private double t = 0.0; // Simulation clock time
    private Particle[] particles;
    private double rowSize = 1.0 / ROW_SIZE;
    private double colSize = 1.0 / COL_SIZE;
    private boolean plotTemperature;

    public CollisionSystemEntropy(Particle[] particles, boolean plotTemperature) {
        this.particles = particles;
        this.plotTemperature = plotTemperature;
    }

    private void predictCollisions(Particle a, double limit) {
        if (a == null) return;

        // Predict hit with other particles
        for (int i = 0; i < particles.length; ++i) {
            Particle b = particles[i];
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
        StdDraw.show();
        StdDraw.pause(20);
        if (t < limit) {
            pq.insert(new Event(t + 1.0/hz, null, null));
        }
    }

    private double temperature() {
        double temperature = 0.0;
        for (int i = 0; i < particles.length; ++i) {
            temperature += particles[i].temperature();
        }

        return (temperature / particles.length);
    }

    public void simulate(double limit, double hz) {
        pq = new MinPQ<>();
        for (int i = 0; i < particles.length; ++i) {
            predictCollisions(particles[i], limit);
        }

        pq.insert(new Event(0, null, null)); // Add redraw event

        double initialBoltzmannEntropy = calculateBoltzmannEntropy();

        while (!pq.isEmpty()) {
            Event event = pq.delMin();
            if (!event.isValid()) continue;

            for (int i = 0; i < particles.length; ++i) {
                particles[i].move(event.time - t);
            }
            t = event.time;

            Particle a = event.a;
            Particle b = event.b;
            if      (a != null && b != null) a.bounceOff(b);
            else if (a != null && b == null) a.bounceOffVerticalWall();
            else if (a == null && b != null) b.bounceOffHorizontalWall();
            else if (a == null && b == null) redraw(limit, hz);

            if (plotTemperature) {
                System.out.println("Temperature = " + temperature());
            }

            predictCollisions(a, limit);
            predictCollisions(b, limit);
        }

        double finalBoltzmannEntropy = calculateBoltzmannEntropy();

        System.out.println("initialBoltzmannEntropy = " + initialBoltzmannEntropy);
        System.out.println("finalBoltzmannEntropy = " + finalBoltzmannEntropy);

        if (finalBoltzmannEntropy > initialBoltzmannEntropy) {
            System.out.println("Entropy increases -> Correct");
        } else {
            System.out.println("Entropy decreases -> Tend not to be");
        }
    }

    private int getParticleCellId(Particle p) {
        int row = (int)(p.py / rowSize);
        int col = (int)(p.px / colSize);

        return (row * ROW_SIZE) + col;
    }

    /*
    Number of microstates in a given distribution

    W = N! / (n1! * n2! * ... * nl!)
    where:
        W: number of ways of distributing N total particles into bins
        N: total particles
        n: number of particles distributed in bins (in this exercise, the cells are the bins in which the particles are distributed into)

    Boltzmann entropy's formula: S = kb*ln(W)
     */
    private double calculateBoltzmannEntropy() {
        int[] particlesPerCell = new int[ROW_SIZE*COL_SIZE];
        for (Particle p : particles) {
            int cellId = getParticleCellId(p);
            ++particlesPerCell[cellId];
        }

        // Calculate denominator of distribution
        int nl = 1;
        for (int particlesInCell : particlesPerCell) {
            nl *= MathUtil.factorial(particlesInCell);
        }

        long N = particles.length;

        double W = (double)MathUtil.factorial(N) / (double)nl;
        double S = Particle.BOLTZMANN_CONSTANT*Math.log(W);
        return S;
    }

    /*
    To calculate W (microstates) we convert the continuous space into a discrete one by dividing the space into cells.
    This way, each cell corresponds to a microstate.
    References:
    - Continuous space to discrete: https://web.stanford.edu/~peastman/statmech/statisticaldescription.html
    - Entropy as measuring a system's multiplicity: http://hyperphysics.phy-astr.gsu.edu/hbasees/Therm/entrop2.html
    - The Boltzmann Distribution and the Statistical Definition of Entropy : https://chem.libretexts.org/Courses/Western_Washington_University/Biophysical_Chemistry_(Smirnov_and_McCarty)/01%3A_Biochemical_Thermodynamics/1.05%3A_The_Boltzmann_Distribution_and_the_Statistical_Definition_of_Entropy
     */
    public static void main(String[] args) {
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.enableDoubleBuffering();
//        int n = Integer.parseInt(args[0]);
        int n = 20;
        Particle[] particles = new Particle[n];
        for (int i = 0; i < n; ++i) {
            particles[i] = new Particle();
        }

        CollisionSystemEntropy system = new CollisionSystemEntropy(particles, false);
        system.simulate(1000, 0.5);
    }

}
