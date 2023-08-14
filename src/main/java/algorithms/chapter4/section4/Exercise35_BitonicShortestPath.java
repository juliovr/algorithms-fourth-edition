package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Exercise35_BitonicShortestPath {

    public static void main(String[] args) {
        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(13);
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 1, 4));
        edgeWeightedDigraph.addEdge(new DirectedEdge(1, 2, 5));
        edgeWeightedDigraph.addEdge(new DirectedEdge(2, 3, 4));
        edgeWeightedDigraph.addEdge(new DirectedEdge(3, 4, 1));
        edgeWeightedDigraph.addEdge(new DirectedEdge(1, 5, 5));
        edgeWeightedDigraph.addEdge(new DirectedEdge(5, 6, 3));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 7, 8));
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 8, 2));
        edgeWeightedDigraph.addEdge(new DirectedEdge(8, 9, 1));
        edgeWeightedDigraph.addEdge(new DirectedEdge(1, 2, 4));

        // With the following 3 edges, there is now a shortest monotonic ascending path from 0 to 3:
        // 0->1 (1.0) 1->2 (2.0) 2->3 (3.0)
        // but it is not bitonic, so it should not be selected
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 1, 1));
        edgeWeightedDigraph.addEdge(new DirectedEdge(1, 2, 2));
        edgeWeightedDigraph.addEdge(new DirectedEdge(2, 3, 3));

        // Edge case: a bitonic path consisting of 2 edges of the same weight
        // 0->10 (3.0) 10->11 (3.0)
        // Should be in the final solution
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 10, 3));
        edgeWeightedDigraph.addEdge(new DirectedEdge(10, 11, 3));

        // Not an edge case: 3 edges of the same weight in the path
        // 0->10 (3.0) 10->11 (3.0) 11->12 (3.0)
        // Should not be in the final solution
        edgeWeightedDigraph.addEdge(new DirectedEdge(11, 12, 3));

        BitonicSP bitonicSP = new BitonicSP(edgeWeightedDigraph, 0);

        System.out.print("Bitonic shortest paths: ");

        for (int vertex = 0; vertex < edgeWeightedDigraph.V(); vertex++) {
            System.out.print("\nPath from vertex 0 to vertex " + vertex + ": ");

            if (bitonicSP.hasPathTo(vertex)) {
                for (DirectedEdge edge : bitonicSP.pathTo(vertex)) {
                    System.out.print(edge.from() + "->" + edge.to() + " (" + edge.weight() + ") ");
                }
            } else {
                System.out.print("There is no bitonic path to vertex " + vertex);
            }
        }

        System.out.println("\n\nExpected bitonic paths");
        System.out.println("Path from vertex 0 to Vertex 0: There is no bitonic path to vertex 0"); // There is a path but it is not bitonic
        System.out.println("Path from vertex 0 to Vertex 1: There is no bitonic path to vertex 1"); // There is a path but it is not bitonic
        System.out.println("Path from vertex 0 to Vertex 2: 0->1 (4.0) 1->2 (2.0)");
        System.out.println("Path from vertex 0 to Vertex 3: 0->1 (1.0) 1->2 (4.0) 2->3 (3.0)");
        System.out.println("Path from vertex 0 to Vertex 4: 0->1 (1.0) 1->2 (2.0) 2->3 (3.0) 3->4 (1.0)");
        System.out.println("Path from vertex 0 to Vertex 5: There is no bitonic path to vertex 5"); // There is a path but it is not bitonic
        System.out.println("Path from vertex 0 to Vertex 6: 0->1 (1.0) 1->5 (5.0) 5->6 (3.0)");
        System.out.println("Path from vertex 0 to Vertex 7: There is no bitonic path to vertex 7"); // There is a path but it is not bitonic
        System.out.println("Path from vertex 0 to Vertex 8: There is no bitonic path to vertex 8"); // There is a path but it is not bitonic
        System.out.println("Path from vertex 0 to Vertex 9: 0->8 (2.0) 8->9 (1.0)");
        System.out.println("Path from vertex 0 to Vertex 10: There is no bitonic path to vertex 10"); // There is a path but it is not bitonic
        System.out.println("Path from vertex 0 to Vertex 11: 0->10 (3.0) 10->11 (3.0)"); // An edge case
        System.out.println("Path from vertex 0 to Vertex 12: There is no bitonic path to vertex 12"); // There is a path but it is not bitonic

        double[] expectedDistances = {
                Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 6, 8, 7, Double.POSITIVE_INFINITY,
                9, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 3, Double.POSITIVE_INFINITY,
                6, Double.POSITIVE_INFINITY
        };

        for (int vertex = 0; vertex < edgeWeightedDigraph.V(); vertex++) {
            System.out.print("\nDistance to vertex " + vertex + ": " + bitonicSP.distTo(vertex)
                    + " Expected: " + expectedDistances[vertex]);
        }
    }

    private static class BitonicSP {

        private DirectedEdge[] edgeTo;
        private double[] distTo;

        public BitonicSP(EdgeWeightedDigraph graph, int s) {
            edgeTo = new DirectedEdge[graph.V()];
            distTo = new double[graph.V()];

            DirectedEdge[] edgeToAscendingOrder = new DirectedEdge[graph.V()];
            double[] distToAscendingOrder = new double[graph.V()];

            DirectedEdge[] edgeToDescendingOrder = new DirectedEdge[graph.V()];
            double[] distToDescendingOrder = new double[graph.V()];

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

            // TODO: starting from the s vertex in asc order, compare the distToDescendingOrder from s + 1 to t - 1
            // looking for the shortest path.
            DirectedEdge[] edgesAsc = new DirectedEdge[edgesCount];
            int countAsc = 0;
            for (DirectedEdge e : graph.edges()) {
                edgesAsc[countAsc++] = e;
            }
            Arrays.sort(edgesAsc, ascComparator);

            // TODO: delete desc order and use only ascending order (when desc order is needed, just do the reverse for loop
            DirectedEdge[] edgesDesc = new DirectedEdge[edgesCount];
            int countDesc = 0;
            for (DirectedEdge e : graph.edges()) {
                edgesDesc[countDesc++] = e;
            }
            Arrays.sort(edgesDesc, descComparator);


            List<DirectedEdge> sources = new ArrayList<>();
            for (int i = 0; i < edgesAsc.length; ++i) {
                DirectedEdge e = edgesAsc[i];
                if (e.from() == s) {
                    sources.add(e);
                }
            }

            System.out.println(graph);

            for (int i = 0; i < edgesAsc.length; ++i) {

                for (int j = 0; j < edgesDesc.length; ++j) {

                }
            }
            for (DirectedEdge source : sources) {

            }



//            for (int i = 0; i < distToAscendingOrder.length; ++i) {
//                double currentWeight = distToAscendingOrder[i];
//                DirectedEdge currentEdge = edgeToAscendingOrder[i];
//
//                for (int j = 0; j < distToDescendingOrder.length; ++j) {
//                    double weight = distToDescendingOrder[i];
//                    DirectedEdge edge = edgeToDescendingOrder[i];
//
//                    if (currentWeight > weight) {
//                        distTo[j] = weight;
//                        edgeTo[j] = edge;
//                    }
//                }
//            }
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
