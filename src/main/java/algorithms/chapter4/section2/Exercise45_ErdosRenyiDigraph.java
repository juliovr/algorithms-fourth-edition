package algorithms.chapter4.section2;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise45_ErdosRenyiDigraph {

    public static void main(String[] args) {
        int V = 10;
        int E = 20;

        Digraph digraph = new Digraph(V);
        for (int i = 0; i < E; ++i) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            digraph.addEdge(v, w);
        }

        System.out.println(digraph);
    }

}
