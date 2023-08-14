package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class Exercise3_EdgeWeightedDigraphMatrix {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWD.txt");
//        In in = new In("test_data/mediumEWD.txt");
        EdgeWeightedDigraphMatrix graph = new EdgeWeightedDigraphMatrix(in);

        System.out.println(graph);
    }

}
