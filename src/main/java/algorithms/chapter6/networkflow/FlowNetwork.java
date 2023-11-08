package algorithms.chapter6.networkflow;

import algorithms.chapter4.section3.Edge;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class FlowNetwork {

    private final int V;
    private int E = 0;
    private Bag<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        this.V = V;
        this.adj = new Bag[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<>();
        }
    }

    public FlowNetwork(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            double capacity = in.readDouble();
            addEdge(new FlowEdge(v, w, capacity));
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        adj[w].add(e);
        ++E;
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<FlowEdge> edges() {
        Bag<FlowEdge> bag = new Bag<>();
        for (int v = 0; v < V; ++v) {
            for (FlowEdge e : adj[v]) {
                int w = e.to();
                if (w > v) {
                    bag.add(e);
                }
            }
        }

        return bag;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int v = 0; v < V; ++v) {
            sb.append(v + ": ");
            for (FlowEdge e : adj(v)) {
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
