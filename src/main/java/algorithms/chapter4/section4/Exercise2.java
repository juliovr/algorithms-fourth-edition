package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.In;

public class Exercise2 {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWD.txt");
//        In in = new In("test_data/mediumEWD.txt");
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in);

        System.out.println(graph);
    }

}
