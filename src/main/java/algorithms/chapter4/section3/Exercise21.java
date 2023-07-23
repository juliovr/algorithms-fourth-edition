package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.In;

public class Exercise21 {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWG.txt");
        EdgeWeightedGraph tinyEWG = new EdgeWeightedGraph(in);

        LazyPrimMST lazyPrimMST = new LazyPrimMST(tinyEWG);
        for (Edge e : lazyPrimMST.edges()) {
            System.out.println(e);
        }

        System.out.println();

        PrimMST primMST = new PrimMST(tinyEWG);
        for (Edge e : primMST.edges()) {
            System.out.println(e);
        }
    }

}
