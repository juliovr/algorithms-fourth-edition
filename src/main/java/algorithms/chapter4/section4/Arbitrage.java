package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.In;

public class Arbitrage {

    public static void main(String[] args) {
        In in = new In("test_data/rates.txt");
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
    }

}
