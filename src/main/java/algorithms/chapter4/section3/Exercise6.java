package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.In;

public class Exercise6 {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWG.txt");
        EdgeWeightedGraph tinyEWG = new EdgeWeightedGraph(in);

        EdgeWeightedGraph graph = new EdgeWeightedGraph(tinyEWG.V() - 1);
        for (Edge e : tinyEWG.edges()) {
            int v = e.either();
            int w = e.other(v);
            if (v == 7 || w == 7) {
                continue;
            } else {
                graph.addEdge(e);
            }
        }
        
        LazyPrimMST mst = new LazyPrimMST(graph);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
    }

}
