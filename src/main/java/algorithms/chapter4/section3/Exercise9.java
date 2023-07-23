package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.In;

public class Exercise9 {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWG.txt");
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);

        for (Edge e : graph.edges()) {
            System.out.println(e);
        }
    }

}
