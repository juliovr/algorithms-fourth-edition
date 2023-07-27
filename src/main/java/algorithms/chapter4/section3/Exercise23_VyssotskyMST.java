package algorithms.chapter4.section3;

import algorithms.chapter2.section4.MaxPQ;
import edu.princeton.cs.algs4.In;

public class Exercise23_VyssotskyMST {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWG.txt");
        EdgeWeightedGraph tinyEWG = new EdgeWeightedGraph(in);

        PrimMST primMST = new PrimMST(tinyEWG);
        for (Edge e : primMST.edges()) {
            System.out.println(e);
        }

        System.out.println();

        EdgeWeightedGraph mstGraph = new EdgeWeightedGraph(tinyEWG.V());
        for (Edge e : tinyEWG.edges()) {
            mstGraph.addEdge(e);

            EdgeWeightedCycle cycle = new EdgeWeightedCycle(mstGraph);
            if (cycle.hasCycle()) {
                Edge max = findMaxEdgeInCycle(cycle);
                mstGraph.deleteEdge(max);
            }
        }

        for (Edge e : mstGraph.edges()) {
            System.out.println(e);
        }
    }

    private static Edge findMaxEdgeInCycle(EdgeWeightedCycle cycle) {
        MaxPQ<Edge> pq = new MaxPQ<>(cycle.count());
        for (Edge e : cycle.cycle()) {
            pq.insert(e);
        }

        return pq.max();
    }

}
