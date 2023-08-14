package algorithms.chapter4.section4;

import algorithms.chapter2.section4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;
import java.util.Comparator;

public class Exercise34_MonotonicShortestPath {

    public static void main(String[] args) {
        EdgeWeightedDigraph edgeWeightedDigraph1 = new EdgeWeightedDigraph(8);
        edgeWeightedDigraph1.addEdge(new DirectedEdge(0, 1, 1));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(0, 4, 3));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 2, 2));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(2, 6, 2));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 5, 0));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(5, 0, 3));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 4, 1));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 3, 2));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 3, 0));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(2, 7, 1));

        MonotonicSP monotonicSP1 = new MonotonicSP(edgeWeightedDigraph1, 0);

        System.out.print("Monotonic shortest paths 1: ");

        for (int vertex = 0; vertex < edgeWeightedDigraph1.V(); vertex++) {
            System.out.print("\nPath from vertex 0 to vertex " + vertex + ": ");

            if (monotonicSP1.hasPathTo(vertex)) {
                for (DirectedEdge edge : monotonicSP1.pathTo(vertex)) {
                    System.out.print(edge.from() + "->" + edge.to() + " (" + edge.weight() + ") ");
                }
            } else {
                System.out.print("There is no monotonic path to vertex " + vertex);
            }
        }

        System.out.println("\n\nExpected monotonic paths");
        System.out.println("Path from vertex 0 to Vertex 0: ");
        System.out.println("Path from vertex 0 to Vertex 1: 0->1 (1.0)");
        System.out.println("Path from vertex 0 to Vertex 2: 0->1 (1.0) 1->2 (2.0)");
        System.out.println("Path from vertex 0 to Vertex 3: 0->1 (1.0) 1->3 (0.0)");
        System.out.println("Path from vertex 0 to Vertex 4: 0->4 (3.0)");
        System.out.println("Path from vertex 0 to Vertex 5: 0->1 (1.0) 1->5 (0.0)");
        System.out.println("Path from vertex 0 to Vertex 6: There is no monotonic path to vertex 6"); // There is a path but it is not monotonic
        System.out.println("Path from vertex 0 to Vertex 7: There is no monotonic path to vertex 7"); // There is a path but it is not monotonic


        EdgeWeightedDigraph edgeWeightedDigraph2 = new EdgeWeightedDigraph(3);
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 1, 1));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 1, 4));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(1, 2, 3));

        MonotonicSP monotonicSP2 = new MonotonicSP(edgeWeightedDigraph2, 0);

        System.out.print("\nMonotonic shortest paths 2: ");

        for (int vertex = 0; vertex < edgeWeightedDigraph2.V(); vertex++) {
            System.out.print("\nPath from vertex 0 to vertex " + vertex + ": ");

            if (monotonicSP2.hasPathTo(vertex)) {
                for (DirectedEdge edge : monotonicSP2.pathTo(vertex)) {
                    System.out.print(edge.from() + "->" + edge.to() + " (" + edge.weight() + ") ");
                }
            } else {
                System.out.print("There is no monotonic path to vertex " + vertex);
            }
        }

        System.out.println("\n\nExpected monotonic paths");
        System.out.println("Path from vertex 0 to Vertex 0: ");
        System.out.println("Path from vertex 0 to Vertex 1: 0->1 (1.0)");
        System.out.println("Path from vertex 0 to Vertex 2: 0->1 (1.0) 1->2 (3.0)");
    }

    private static class MonotonicSP {

        private DirectedEdge[] edgeTo;
        private double[] distTo;

        private DirectedEdge[] edgeToAscendingOrder;
        private double[] distToAscendingOrder;

        private DirectedEdge[] edgeToDescendingOrder;
        private double[] distToDescendingOrder;

        public MonotonicSP(EdgeWeightedDigraph graph, int s) {
            edgeTo = new DirectedEdge[graph.V()];
            distTo = new double[graph.V()];

            edgeToAscendingOrder = new DirectedEdge[graph.V()];
            edgeToDescendingOrder = new DirectedEdge[graph.V()];
            distToAscendingOrder = new double[graph.V()];
            distToDescendingOrder = new double[graph.V()];
            for (int v = 0; v < graph.V(); ++v) {
                distTo[v] = Double.POSITIVE_INFINITY;
                distToAscendingOrder[v] = Double.POSITIVE_INFINITY;
                distToDescendingOrder[v] = Double.POSITIVE_INFINITY;
            }

            distTo[s] = 0.0;
            distToAscendingOrder[s] = 0.0;
            distToDescendingOrder[s] = 0.0;

            int edgesCount = 0;
            for (DirectedEdge e : graph.edges()) {
                ++edgesCount;
            }

            Comparator<DirectedEdge> ascComparator = (o1, o2) -> {
                if (o1.weight() < o2.weight()) return -1;
                if (o1.weight() > o2.weight()) return 1;
                return 0;
            };
            relaxEdges(graph, edgesCount, ascComparator, distToAscendingOrder, edgeToAscendingOrder);

            Comparator<DirectedEdge> descComparator = (o1, o2) -> {
                if (o1.weight() > o2.weight()) return -1;
                if (o1.weight() < o2.weight()) return 1;
                return 0;
            };
            relaxEdges(graph, edgesCount, descComparator, distToDescendingOrder, edgeToDescendingOrder);

            // Then evaluate each best paths keeping the shortest
            for (int v = 0; v < graph.V(); ++v) {
                double weightAsc = distToAscendingOrder[v];
                double weightDesc = distToDescendingOrder[v];

                if (weightAsc < weightDesc) {
                    distTo[v] = weightAsc;
                    edgeTo[v] = edgeToAscendingOrder[v];
                } else {
                    distTo[v] = weightDesc;
                    edgeTo[v] = edgeToDescendingOrder[v];
                }
            }
        }

        private void relaxEdges(EdgeWeightedDigraph graph, int edgesCount, Comparator<DirectedEdge> comparator,
                                double[] distTo, DirectedEdge[] edgeTo) {

            DirectedEdge[] edges = new DirectedEdge[edgesCount];
            int count = 0;
            for (DirectedEdge e : graph.edges()) {
                edges[count++] = e;
            }

            Arrays.sort(edges, comparator);

            for (int i = 0; i < edges.length; ++i) {
                relax(edges[i], distTo, edgeTo);
            }
        }

        private void relax(DirectedEdge e, double[] distTo, DirectedEdge[] edgeTo) {
            int v = e.from();
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
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
