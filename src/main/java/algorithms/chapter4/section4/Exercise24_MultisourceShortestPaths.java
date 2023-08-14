package algorithms.chapter4.section4;

import algorithms.chapter2.section4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.Set;

public class Exercise24_MultisourceShortestPaths {

    public static void main(String[] args) {
        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(8);
        edgeWeightedDigraph.addEdge(new DirectedEdge(4, 5, 0.35));
        edgeWeightedDigraph.addEdge(new DirectedEdge(5, 4, 0.35));
        edgeWeightedDigraph.addEdge(new DirectedEdge(4, 7, 0.37));
        edgeWeightedDigraph.addEdge(new DirectedEdge(5, 7, 0.28));
        edgeWeightedDigraph.addEdge(new DirectedEdge(7, 5, 0.28));
        edgeWeightedDigraph.addEdge(new DirectedEdge(5, 1, 0.32));
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 4, 0.38));
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 2, 0.26));
        edgeWeightedDigraph.addEdge(new DirectedEdge(7, 3, 0.39));
        edgeWeightedDigraph.addEdge(new DirectedEdge(1, 3, 0.29));
        edgeWeightedDigraph.addEdge(new DirectedEdge(2, 7, 0.34));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 2, 0.40));
        edgeWeightedDigraph.addEdge(new DirectedEdge(3, 6, 0.52));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 0, 0.58));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 4, 0.93));

        Set<Integer> sources = new HashSet<>();
        sources.add(0);
        sources.add(1);
        sources.add(7);

        DijkstraMultisourceSP dijkstraMultisourceSP = new DijkstraMultisourceSP(edgeWeightedDigraph, sources);

        System.out.println("Distance to 5: " + dijkstraMultisourceSP.distTo(5) + " Expected: 0.28");
        System.out.println("Has path to 5: " + dijkstraMultisourceSP.hasPathTo(5) + " Expected: true");

        System.out.print("Path to 5: ");

        for (DirectedEdge edge : dijkstraMultisourceSP.pathTo(5)) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }
        System.out.println("\nExpected:  7->5");

        System.out.println("\nDistance to 6: " + dijkstraMultisourceSP.distTo(6) + " Expected: 0.81");
        System.out.println("Has path to 6: " + dijkstraMultisourceSP.hasPathTo(6) + " Expected: true");

        System.out.print("Path to 6: ");

        for (DirectedEdge edge : dijkstraMultisourceSP.pathTo(6)) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }
        System.out.println("\nExpected:  1->3 3->6");
    }

    private static class DijkstraMultisourceSP {

        private DirectedEdge[] edgeTo;
        private double[] distTo;
        private IndexMinPQ<Double> pq;

        public DijkstraMultisourceSP(EdgeWeightedDigraph graph, Set<Integer> sources) {
            edgeTo = new DirectedEdge[graph.V()];
            distTo = new double[graph.V()];
            pq = new IndexMinPQ<>(graph.V());

            for (int v = 0; v < graph.V(); ++v) {
                distTo[v] = Double.POSITIVE_INFINITY;
            }

            for (int s : sources) {
                distTo[s] = 0.0;
                pq.insert(s, 0.0);
            }

            while (!pq.isEmpty()) {
                relax(graph, pq.delMin());
            }
        }

        private void relax(EdgeWeightedDigraph graph, int v) {
            for (DirectedEdge e : graph.adj(v)) {
                int w = e.to();
                if (distTo[w] > distTo[v] + e.weight()) {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    if (pq.contains(w)) {
                        pq.changeKey(w, distTo[w]);
                    } else {
                        pq.insert(w, distTo[w]);
                    }
                }
            }
        }

        public double distTo(int v) {
            return distTo[v];
        }

        public boolean hasPathTo(int v) {
            return distTo[v] < Double.POSITIVE_INFINITY;
        }

        public Iterable<DirectedEdge> pathTo(int v) {
            if (!hasPathTo(v)) return null;
            Stack<DirectedEdge> path = new Stack<>();
            for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
                path.push(e);
            }

            return path;
        }

    }

}
