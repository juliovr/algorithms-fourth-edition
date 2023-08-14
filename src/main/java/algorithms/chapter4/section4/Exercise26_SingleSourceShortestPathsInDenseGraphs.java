package algorithms.chapter4.section4;

import algorithms.chapter2.section4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class Exercise26_SingleSourceShortestPathsInDenseGraphs {

    public static void main(String[] args) {
        EdgeWeightedDigraphMatrix edgeWeightedDigraph = new EdgeWeightedDigraphMatrix(8);
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

        DijkstraSPDenseGraph dijkstraSPDenseGraph = new DijkstraSPDenseGraph(edgeWeightedDigraph, 0);
        System.out.println("Shortest-paths-tree\n");
        System.out.printf("%13s %10s\n", "edgeTo[]",  "distTo[]");

        for (int vertex = 0; vertex < edgeWeightedDigraph.V(); vertex++) {
            System.out.printf("%d %11s %9.2f\n", vertex, dijkstraSPDenseGraph.edgeTo(vertex), dijkstraSPDenseGraph.distTo(vertex));
        }

        System.out.print("\nPath from 0 to 6: ");

        for (DirectedEdge edge : dijkstraSPDenseGraph.pathTo(6)) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }
        System.out.println("\nExpected:         0->2 2->7 7->3 3->6");
    }

    private static class DijkstraSPDenseGraph {

        private DirectedEdge[] edgeTo;
        private double[] distTo;
        private IndexMinPQ<Double> pq;

        public DijkstraSPDenseGraph(EdgeWeightedDigraphMatrix graph, int s) {
            edgeTo = new DirectedEdge[graph.V()];
            distTo = new double[graph.V()];
            pq = new IndexMinPQ<>(graph.V());

            for (int v = 0; v < graph.V(); ++v) {
                distTo[v] = Double.POSITIVE_INFINITY;
            }

            distTo[s] = 0.0;
            pq.insert(s, 0.0);

            while (!pq.isEmpty()) {
                relax(graph, pq.delMin());
            }
        }

        private void relax(EdgeWeightedDigraphMatrix graph, int v) {
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

        public DirectedEdge edgeTo(int v) {
            return edgeTo[v];
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
