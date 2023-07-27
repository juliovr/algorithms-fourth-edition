package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.In;

public class Exercise31_MSTWeights {

    public static void main(String[] args) {
        In in = new In("test_data/tinyEWG.txt");
        EdgeWeightedGraph tinyEWG = new EdgeWeightedGraph(in);

        LazyPrimMST lazyPrimMST = new LazyPrimMST(tinyEWG);
        System.out.println("LazyPrimMST = " + lazyPrimMST.weight());

        PrimMST primMST = new PrimMST(tinyEWG);
        System.out.println("PrimMST = " + primMST.weight());

        KruskalMST kruskalMST = new KruskalMST(tinyEWG);
        System.out.println("KruskalMST = " + kruskalMST.weight());
    }

}
