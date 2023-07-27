package algorithms.chapter4.section3;

import algorithms.chapter2.section4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PrimMSF {

    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;
    private EdgeWeightedCC cc;

    public PrimMSF(EdgeWeightedGraph graph) {
        edgeTo = new Edge[graph.V()];
        distTo = new double[graph.V()];
        marked = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); ++v) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(graph.V());

        cc = new EdgeWeightedCC(graph);
        Integer[] firstVertexFoundInComponents = new Integer[cc.count()];
        for (int v = 0; v < graph.V(); ++v) {
            int component = cc.id(v);
            if (firstVertexFoundInComponents[component] == null) {
                firstVertexFoundInComponents[component] = v;
            }
        }

        for (int v : firstVertexFoundInComponents) {
            distTo[v] = 0.0;
            pq.insert(v, 0.0);
            while (!pq.isEmpty()) {
                visit(graph, pq.delMin());
            }
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

    public Iterable<Edge>[] edges() {
        Queue<Edge>[] mstPerComponent = new Queue[cc.count()];
        for (int i = 0; i < mstPerComponent.length; ++i) {
            mstPerComponent[i] = new Queue<>();
        }

        for (int v = 0; v < edgeTo.length; ++v) {
            if (edgeTo[v] != null) {
                int component = cc.id(v);
                mstPerComponent[component].enqueue(edgeTo[v]);
            }
        }

        return mstPerComponent;
    }

//    public Iterable<Edge> edges() {
//        Queue<Edge> mst = new Queue<>();
//        for (int v = 1; v < edgeTo.length; ++v) {
//            if (edgeTo[v] != null) {
//                mst.enqueue(edgeTo[v]);
//            }
//        }
//
//        return mst;
//    }

}
