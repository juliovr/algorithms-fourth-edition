package algorithms.chapter4.section3;

import algorithms.chapter2.section4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {

    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        pq = new MinPQ<>();
        marked = new boolean[graph.V()];
        mst = new Queue<>();

        visit(graph, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) continue;

            mst.enqueue(e);

            if (!marked[v]) visit(graph, v);
            if (!marked[w]) visit(graph, w);
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (!marked[w]) {
                pq.insert(e);
            }
        }
    }

    public Queue<Edge> edges() {
        return mst;
    }

}
