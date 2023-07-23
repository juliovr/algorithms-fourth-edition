package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class Exercise10_EdgeWeightedGraphAdjacencyMatrix {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWG.txt");
        Exercise10_EdgeWeightedGraphAdjacencyMatrix graph = new Exercise10_EdgeWeightedGraphAdjacencyMatrix(in);
        for (Edge e : graph.edges()) {
            System.out.println(e);
        }
    }

    private final int V;
    private int E;
    private double[][] adj;

    public Exercise10_EdgeWeightedGraphAdjacencyMatrix(int V) {
        this.V = V;
        this.E = 0;
        adj = new double[V][V];
        for (int v = 0; v < V; ++v) {
            for (int w = 0; w < V; ++w) {
                adj[v][w] = Double.POSITIVE_INFINITY;
            }
        }
    }

    public Exercise10_EdgeWeightedGraphAdjacencyMatrix(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();

            addEdge(v, w, weight);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w, double weight) {
        if (hasEdge(v, w)) {
            return;
        }

        adj[v][w] = weight;
        adj[w][v] = weight;
        ++E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        addEdge(v, w, e.weight());
    }

    public boolean hasEdge(int v, int w) {
        return adj[v][w] != Double.POSITIVE_INFINITY;
    }

    public Iterable<Edge> adj(int v) {
        Queue<Edge> queue = new Queue<>();
        for (int w = 0; w < adj[v].length; ++w) {
            if (adj[v][w] != Double.POSITIVE_INFINITY) {
                Edge e = new Edge(v, w, adj[v][w]);
                queue.enqueue(e);
            }
        }

        return queue;
    }

    public Iterable<Edge> edges() {
        Bag<Edge> bag = new Bag<>();
        for (int v = 0; v < V; ++v) {
            for (int w = 0; w < V; ++w) {
                if (w > v && adj[v][w] != Double.POSITIVE_INFINITY) {
                    Edge e = new Edge(v, w, adj[v][w]);
                    bag.add(e);
                }
            }
        }

        return bag;
    }

}
