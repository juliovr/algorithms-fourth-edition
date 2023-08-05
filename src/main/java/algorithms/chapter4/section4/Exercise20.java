package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.In;

import java.util.Scanner;

public class Exercise20 {

    public static void main(String[] args) {
        String rates =
                "3\n" +
                "CLP 1        0.00117  0.00107\n" +
                "USD 853.99   1        0.9135\n" +
                "EUR 934.85   1.09468  1";
        Scanner sc = new Scanner(rates);
        In in = new In(sc);
        int V = in.readInt();
        String[] name = new String[V];
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; ++v) {
            name[v] = in.readString();
            for (int w = 0; w < V; ++w) {
                double rate = in.readDouble();
                DirectedEdge e = new DirectedEdge(v, w, -Math.log(rate));
                graph.addEdge(e);
            }
        }

        BellmanFordSP spt = new BellmanFordSP(graph, 0);
        if (spt.hasNegativeCycle()) {
            double stake = 1000.0;
            for (DirectedEdge e : spt.negativeCycle()) {
                System.out.printf("%10.5f %s ", stake, name[e.from()]);
                stake *= Math.exp(-e.weight());
                System.out.printf("= %10.5f %s\n", stake, name[e.to()]);
            }
        }

        sc.close();
    }

}
