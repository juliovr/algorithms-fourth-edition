package algorithms.chapter4.section3;

import algorithms.chapter2.section4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimMST {

    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph graph) {
        edgeTo = new Edge[graph.V()];
        distTo = new double[graph.V()];
        marked = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); ++v) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(graph.V());
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(graph, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue; // v-w is ineligible
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<>();
        for (int v = 1; v < edgeTo.length; ++v) {
            mst.enqueue(edgeTo[v]);
        }

        return mst;
    }

}
