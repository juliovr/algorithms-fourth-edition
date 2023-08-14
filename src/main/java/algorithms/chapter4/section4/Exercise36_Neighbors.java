package algorithms.chapter4.section4;

import algorithms.chapter2.section4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.Set;

public class Exercise36_Neighbors {

    public static void main(String[] args) {
        EdgeWeightedDigraph edgeWeightedDigraph1 = new EdgeWeightedDigraph(4);
        edgeWeightedDigraph1.addEdge(new DirectedEdge(0, 1, 20));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(0, 2, 5));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(2, 1, 1));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 3, 10));

        int maxDistance1 = 20;

        DijkstraSPMaxDistance dijkstraSPMaxDistance1 = new DijkstraSPMaxDistance(edgeWeightedDigraph1, 0, maxDistance1);

        System.out.println("Vertices within distance 20 from digraph 1:");
        System.out.print("Actual:   ");
        for (int vertex : dijkstraSPMaxDistance1.verticesWithinMaxDistance()) {
            System.out.print(vertex + " ");
        }
        System.out.println("\nExpected: 0 1 2 3");

        EdgeWeightedDigraph edgeWeightedDigraph2 = new EdgeWeightedDigraph(8);
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 1, 5));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(1, 3, 2));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(3, 5, 3));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(5, 6, 1));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 2, 9));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(2, 3, 2));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(2, 4, 3));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(4, 7, 1));

        int maxDistance2 = 10;
        DijkstraSPMaxDistance dijkstraSPMaxDistance2 = new DijkstraSPMaxDistance(edgeWeightedDigraph2, 0, maxDistance2);

        System.out.println("\nVertices within distance 10 from digraph 2:");
        System.out.print("Actual:   ");
        for (int vertex : dijkstraSPMaxDistance2.verticesWithinMaxDistance()) {
            System.out.print(vertex + " ");
        }
        System.out.println("\nExpected: 0 1 2 3 5");
    }

    private static class DijkstraSPMaxDistance {

        private DirectedEdge[] edgeTo;
        private double[] distTo;
        private IndexMinPQ<Double> pq;
        private double maxDistance;

        public DijkstraSPMaxDistance(EdgeWeightedDigraph graph, int s, double maxDistance) {
            edgeTo = new DirectedEdge[graph.V()];
            distTo = new double[graph.V()];
            pq = new IndexMinPQ<>(graph.V());
            this.maxDistance = maxDistance;

            for (int v = 0; v < graph.V(); ++v) {
                distTo[v] = Double.POSITIVE_INFINITY;
            }

            distTo[s] = 0.0;
            pq.insert(s, 0.0);

            while (!pq.isEmpty()) {
                relax(graph, pq.delMin());
            }
        }

        private void relax(EdgeWeightedDigraph graph, int v) {
            for (DirectedEdge e : graph.adj(v)) {
                int w = e.to();
                double testedWeight = distTo[v] + e.weight();
                if (testedWeight > maxDistance) continue;

                if (distTo[w] > testedWeight) {
                    distTo[w] = testedWeight;
                    edgeTo[w] = e;
                    if (pq.contains(w)) {
                        pq.changeKey(w, distTo[w]);
                    } else {
                        pq.insert(w, distTo[w]);
                    }
                }
            }
        }

        public Iterable<Integer> verticesWithinMaxDistance() {
            Set<Integer> vertices = new HashSet<>();
            for (int v = 0; v < distTo.length; ++v) {
                if (hasPathTo(v)) {
                    vertices.add(v);
                }
            }

            return vertices;
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
