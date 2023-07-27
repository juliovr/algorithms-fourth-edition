package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercise24_ReverseDelete {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWG.txt");
        EdgeWeightedGraph tinyEWG = new EdgeWeightedGraph(in);

        PrimMST primMST = new PrimMST(tinyEWG);
        printEdgesInOrder(primMST.edges());

        System.out.println();

        EdgeWeightedGraph mstGraph = new EdgeWeightedGraph(tinyEWG.V());
        for (Edge e : tinyEWG.edges()) {
            mstGraph.addEdge(e);
        }

        EdgeWeightedCC cc = new EdgeWeightedCC(mstGraph);
        List<Edge> edgesSorted = edgesDecreasingOrder(mstGraph);

        for (Edge e : edgesSorted) {
            mstGraph.deleteEdge(e);
            EdgeWeightedCC stillConnected = new EdgeWeightedCC(mstGraph);
            if (stillConnected.count() > cc.count()) {
                mstGraph.addEdge(e);
            }
        }

        printEdgesInOrder(mstGraph.edges());
    }

    private static List<Edge> edgesDecreasingOrder(EdgeWeightedGraph graph) {
        List<Edge> edges = new ArrayList<>();
        for (Edge e : graph.edges()) {
            edges.add(e);
        }

        Collections.sort(edges, (o1, o2) -> {
            if (o1.compareTo(o2) < 0) return 1;
            if (o2.compareTo(o1) < 0) return -1;
            return 0;
        });

        return edges;
    }

    private static void printEdgesInOrder(Iterable<Edge> edges) {
        List<Edge> sorted = new ArrayList<>();
        for (Edge e : edges) {
            sorted.add(e);
        }

        Collections.sort(sorted);

        for (Edge e : sorted) {
            System.out.println(e);
        }
    }

}
