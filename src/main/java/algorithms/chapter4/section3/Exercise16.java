package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class Exercise16 {

    public static void main(String[] args) {
        // Graph diagram in page 604
        In in = new In("test_data/tinyEWG.txt");
        EdgeWeightedGraph tinyEWG = new EdgeWeightedGraph(in);
        LazyPrimMST mst = new LazyPrimMST(tinyEWG);

        // The edge exists in the original graph, but it is not part of the MST
        Edge newEdge = new Edge(0, 4, 0.1); // Less than 4-5 (weight 0.35)
        rangeWeights(mst.edges(), newEdge);

    }

    private static void rangeWeights(Queue<Edge> mst, Edge newEdge) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(mst.size() + 1);
        for (Edge edgeInMst : mst) {
            graph.addEdge(edgeInMst);
        }
        graph.addEdge(newEdge);

        EdgeWeightedCycle cycle = new EdgeWeightedCycle(graph);
        if (cycle.hasCycle()) {
            double minWeight = Double.MAX_VALUE;
            double maxWeight = Double.MIN_VALUE;
            for (Edge edge : cycle.cycle()) {
                if (edge.weight() > maxWeight) {
                    maxWeight = edge.weight();
                }

                if (edge.weight() < minWeight) {
                    minWeight = edge.weight();
                }
            }

            System.out.println("Min = " + minWeight + ", Max = " + maxWeight);
        }
    }

}
