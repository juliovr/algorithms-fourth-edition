package algorithms.chapter4.section3;

import algorithms.chapter2.section4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalMSF {

    private Queue<Edge>[] msf;
    private int msfSize;
    private EdgeWeightedCC cc;

    public KruskalMSF(EdgeWeightedGraph graph) {
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : graph.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(graph.V());
        cc = new EdgeWeightedCC(graph);

        msf = new Queue[cc.count()];
        for (int i = 0; i < msf.length; ++i) {
            msf[i] = new Queue<>();
        }
        msfSize = 0;

        while (!pq.isEmpty() && msfSize < graph.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) continue; // Ignore ineligible edges

            uf.union(v, w); // Merge the vertices into one component
            msf[cc.id(v)].enqueue(e);
            ++msfSize;
        }
    }

    public Iterable<Edge>[] edges() {
        return msf;
    }

//    public Iterable<Edge> edges() {
//        return mst;
//    }

}
