package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class Exercise3_EdgeWeightedDigraphMatrix {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWD.txt");
//        In in = new In("test_data/mediumEWD.txt");
        Exercise3_EdgeWeightedDigraphMatrix graph = new Exercise3_EdgeWeightedDigraphMatrix(in);

        System.out.println(graph);
    }

    private final int V;
    private int E;
    private double[][] adj;

    public Exercise3_EdgeWeightedDigraphMatrix(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new double[V][V];
        for (int v = 0; v < V; ++v) {
            for (int w = 0; w < V; ++w) {
                adj[v][w] = Double.POSITIVE_INFINITY;
            }
        }
    }

    public Exercise3_EdgeWeightedDigraphMatrix(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();

            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()][e.to()] = e.weight();
        ++E;
    }

    public Iterable<DirectedEdge> adj(int v) {
        Queue<DirectedEdge> queue = new Queue<>();
        for (int w = 0; w < adj[v].length; ++w) {
            if (adj[v][w] != Double.POSITIVE_INFINITY) {
                DirectedEdge e = new DirectedEdge(v, w, adj[v][w]);
                queue.enqueue(e);
            }
        }

        return queue;
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; ++v) {
            for (DirectedEdge e : adj(v)) {
                bag.add(e);
            }
        }

        return bag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int v = 0; v < V; ++v) {
            sb.append(v + ": ");
            for (DirectedEdge e : adj(v)) {
                sb.append("(");
                sb.append(e);
                sb.append(")");
                sb.append(" ");
            }
            sb.append('\n');
        }

        return sb.toString();
    }

}
