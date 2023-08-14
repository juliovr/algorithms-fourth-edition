package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Queue;

public class BellmanFordSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQueue;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph graph, int s) {
        distTo = new double[graph.V()];
        edgeTo = new DirectedEdge[graph.V()];
        onQueue = new boolean[graph.V()];
        queue = new Queue<>();
        for (int v = 0; v < graph.V(); ++v) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(graph, v);
        }
    }

    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQueue[w]) {
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }

            if (cost++ % graph.V() == 0) {
                findNegativeCycle();
            }
        }
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; ++v) {
            if (edgeTo[v] != null) {
                spt.addEdge(edgeTo[v]);
            }
        }

        EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(spt);
        cycle = cf.cycle();
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    public double distTo(int v) {
        return distTo[v];
    }

}
