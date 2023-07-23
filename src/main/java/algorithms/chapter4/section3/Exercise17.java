package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.In;

public class Exercise17 {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWG.txt");
        EdgeWeightedGraph tinyEWG = new EdgeWeightedGraph(in);

        System.out.println(tinyEWG);
    }

}
