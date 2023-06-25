package algorithms.chapter4.section1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {

    private final int V;
    private int E = 0;
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = new Bag[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public Graph(Graph g) {
        this(g.V);
        this.E = g.E;

        for (int v = 0; v < g.adj.length; ++v) {
            for (Integer i : g.adj[v]) {
                adj[v].add(i);
            }
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        if (isParallelEdge(v, w)) return;

        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    private boolean isParallelEdge(int v, int w) {
        if (v >= V || w >= V) return false;

        for (Integer adjV : adj[v]) {
            if (adjV == w) return true;
        }

        return false;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public boolean hasEdge(int v, int w) {
        return isParallelEdge(v, w);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int v = 0; v < V; ++v) {
            sb.append(v + ": ");
            Bag<Integer> value = adj[v];
            for (Integer val : value) {
                sb.append(val + " ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

}
