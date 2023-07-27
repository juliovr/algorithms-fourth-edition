package algorithms.chapter4.section3;

import algorithms.chapter2.section4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {

    private Queue<Edge> mst;
    private double totalWeight;

    public KruskalMST(EdgeWeightedGraph graph) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : graph.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(graph.V());

        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) continue; // Ignore ineligible edges

            uf.union(v, w); // Merge the vertices into one component
            mst.enqueue(e);
            totalWeight += e.weight();
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

//    public double weight() {
//        double weight = 0.0;
//        for (Edge e : edges()) {
//            weight += e.weight();
//        }
//
//        return weight;
//    }

    public double weight() {
        return totalWeight;
    }

}
