package algorithms.chapter4.section4;

import algorithms.chapter2.section4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;
import java.util.Set;

public class Exercise25_ShortestPathBetweenTwoSubsets {

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

        Set<Integer> subsetS = new HashSet<>();
        subsetS.add(0);
        subsetS.add(1);

        HashSet<Integer> subsetT = new HashSet<>();
        subsetT.add(5);
        subsetT.add(6);

        DijkstraSPTwoSubsets dijkstraSPTwoSubsets = new DijkstraSPTwoSubsets(edgeWeightedDigraph, subsetS, subsetT);
        System.out.print("Shortest path from subset S to subset T: ");

        for (DirectedEdge edge : dijkstraSPTwoSubsets.getShortestPathFromStoT()) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }
        System.out.println("\nExpected: 0->4 4->5");
    }

    private static class DijkstraSPTwoSubsets {

        private DirectedEdge[] edgeTo;
        private double[] distTo;
        private IndexMinPQ<Double> pq;
        private Set<Integer> subsetT;

        public DijkstraSPTwoSubsets(EdgeWeightedDigraph graph, Set<Integer> subsetS, Set<Integer> subsetT) {
            edgeTo = new DirectedEdge[graph.V()];
            distTo = new double[graph.V()];
            pq = new IndexMinPQ<>(graph.V());
            this.subsetT = subsetT;

            for (int v = 0; v < graph.V(); ++v) {
                distTo[v] = Double.POSITIVE_INFINITY;
            }

            for (int s : subsetS) {
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

        public Iterable<DirectedEdge> getShortestPathFromStoT() {
            // Get the shortest path to a vertex in the target subset
            double pathWeight = Double.POSITIVE_INFINITY;
            int v = -1;
            for (int t : subsetT) {
                if (pathWeight > distTo[t]) {
                    pathWeight = distTo[t];
                    v = t;
                }
            }

            Stack<DirectedEdge> path = new Stack<>();
            if (v != -1) {
                for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
                    path.push(e);
                }
            }

            return path;
        }

        public boolean hasPathTo(int v) {
            return distTo[v] < Double.POSITIVE_INFINITY;
        }

    }
    
}
