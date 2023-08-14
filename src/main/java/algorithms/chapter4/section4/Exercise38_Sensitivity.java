package algorithms.chapter4.section4;

import java.util.ArrayList;
import java.util.List;

public class Exercise38_Sensitivity {

    public static void main(String[] args) {
        EdgeWeightedDigraph edgeWeightedDigraph1 = new EdgeWeightedDigraph(6);
        edgeWeightedDigraph1.addEdge(new DirectedEdge(0, 1, 2));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 2, 1));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(2, 3, 1));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(3, 4, 1));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(5, 0, 3));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 0, 2));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 3, 2));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(0, 2, 3));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(3, 4, 3));

        boolean[][] sensitivity1 = computeSensitivity(edgeWeightedDigraph1);

        System.out.print("Actual:   ");
        for (int i = 0; i < sensitivity1.length; i++) {
            for (int j = 0; j < sensitivity1[0].length; j++) {
                if (!sensitivity1[i][j]) {
                    System.out.print(i + "->" + j + " ");
                }
            }
        }

        System.out.println("\nExpected: 0->1 1->0 1->2 2->3 3->4 5->0");

        EdgeWeightedDigraph edgeWeightedDigraph2 = new EdgeWeightedDigraph(5);
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 1, 2));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(1, 2, 1));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(2, 3, 1)); // this is a bridge
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 4, 1));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(4, 1, 1));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(1, 2, 1));

        boolean[][] sensitivity2 = computeSensitivity(edgeWeightedDigraph2);

        System.out.print("Actual:   ");
        for (int i = 0; i < sensitivity2.length; i++) {
            for (int j = 0; j < sensitivity2[0].length; j++) {
                if (!sensitivity2[i][j]) {
                    System.out.print(i + "->" + j + " ");
                }
            }
        }
        System.out.println("\nExpected: 0->4 2->3 4->1");
    }

    public static boolean[][] computeSensitivity(EdgeWeightedDigraph graph) {
        boolean[][] sensitivity = new boolean[graph.V()][graph.V()];

        for (int v = 0; v < sensitivity.length; ++v) {
            for (int w = 0; w < sensitivity[0].length; ++w) {
                if (graph.containsEdge(v, w)) {
                    List<DirectedEdge> criticalEdges = getCriticalEdges(graph, v, w);
                    if (criticalEdges.isEmpty()) {
                        sensitivity[v][w] =  true;
                    } else {
                        for (DirectedEdge e : criticalEdges) {
                            sensitivity[v][w] = false;
                        }
                    }
                } else {
                    sensitivity[v][w] =  true;
                }
            }
        }

        return sensitivity;
    }

    private static List<DirectedEdge> getCriticalEdges(EdgeWeightedDigraph graph, int s, int t) {
        List<DirectedEdge> criticalEdges = new ArrayList<>();

        DijkstraSP sp = new DijkstraSP(graph, s);
        if (!sp.hasPathTo(t)) {
            return criticalEdges;
        }

        double weight = sp.distTo(t);

        DirectedEdge[] edgesInSP = sp.edges();
        for (int i = 0; i < edgesInSP.length; ++i) {
            if (i == s) continue;

            DirectedEdge e = edgesInSP[i];
            if (e == null) continue;

            graph.deleteEdge(e);
            DijkstraSP testSP = new DijkstraSP(graph, s);
            if (sp.hasPathTo(t)) {
                if (testSP.distTo(t) > weight) {
                    criticalEdges.add(e);
                }
            }
            graph.addEdge(e);
        }

        return criticalEdges;
    }

}
