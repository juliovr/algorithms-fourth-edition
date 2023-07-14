package algorithms.chapter4.section2;

import edu.princeton.cs.algs4.Bag;

public class Digraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<>();
        }
    }

    public Digraph(Digraph o) {
        this(o.V);
        for (int v = 0; v < V; ++v) {
            for (int w : o.adj(v)) {
                addEdge(v, w);
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
        adj[v].add(w);
        ++E;
    }

    public boolean hasEdge(int v, int w) {
        for (int a : adj(v)) {
            if (a == w) {
                return true;
            }
        }

        return false;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph r = new Digraph(V);
        for (int v = 0; v < V; ++v) {
            for (int w : adj(v)) {
                r.addEdge(w, v);
            }
        }

        return r;
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
