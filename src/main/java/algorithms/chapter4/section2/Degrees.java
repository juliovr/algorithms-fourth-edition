package algorithms.chapter4.section2;

import edu.princeton.cs.algs4.Bag;

public class Degrees {

    private int[] indegree;
    private int[] outdegree;
    private Bag<Integer> sources = new Bag<>();
    private Bag<Integer> sinks = new Bag<>();
    private boolean isMap = true;

    public Degrees(Digraph digraph) {
        this.indegree = new int[digraph.V()];
        this.outdegree = new int[digraph.V()];

        for (int v = 0; v < digraph.V(); ++v) {
            for (int w : digraph.adj(v)) {
                outdegree[v]++;
                indegree[w]++;
            }
        }

        for (int v = 0; v < digraph.V(); ++v) {
            if (indegree[v] == 0) {
                sources.add(v);
            }

            if (outdegree[v] == 0) {
                sinks.add(v);
            }

            if (outdegree[v] != 1) {
                isMap = false;
            }
        }
    }

    public int indegree(int v) {
        return indegree[v];
    }

    public int outdegree(int v) {
        return outdegree[v];
    }

    public Iterable<Integer> sources() {
        return sources;
    }

    public Iterable<Integer> sinks() {
        return sinks;
    }

    public boolean isMap() {
        return isMap;
    }

}
