package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {

    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(In in) {
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
        adj[e.from()].add(e);
        ++E;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; ++v) {
            for (DirectedEdge e : adj[v]) {
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
