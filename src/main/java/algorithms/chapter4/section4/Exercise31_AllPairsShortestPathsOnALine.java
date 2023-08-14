package algorithms.chapter4.section4;

import algorithms.chapter4.section3.Edge;
import algorithms.chapter4.section3.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Queue;

public class Exercise31_AllPairsShortestPathsOnALine {

    public static void main(String[] args) {
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(5);
        edgeWeightedGraph.addEdge(new Edge(0, 1, 2));
        edgeWeightedGraph.addEdge(new Edge(1, 2, 3));
        edgeWeightedGraph.addEdge(new Edge(2, 3, 4));
        edgeWeightedGraph.addEdge(new Edge(3, 4, 1));

        AllPairsShortestPathsOnALine allPairsShortestPathsOnALine = new AllPairsShortestPathsOnALine(edgeWeightedGraph);

        double[][] expectedDistances = {
                {0, 2, 5, 9, 10},
                {2, 0, 3, 7, 8},
                {5, 3, 0, 4, 5},
                {9, 7, 4, 0, 1},
                {10, 8, 5, 1, 0}
        };

        for (int s = 0; s < edgeWeightedGraph.V(); s++) {
            for (int t = 0; t < edgeWeightedGraph.V(); t++) {
                System.out.println("Distance from " + s + " to " + t + ": " +
                        allPairsShortestPathsOnALine.dist(s, t)
                        + " Expected: " + expectedDistances[s][t]);
            }
        }
    }

    private static class AllPairsShortestPathsOnALine {

        private boolean[] marked;
        private double[] distTo;

        public AllPairsShortestPathsOnALine(EdgeWeightedGraph graph) {
            marked = new boolean[graph.V()];
            distTo = new double[graph.V()];

            for (int v = 0; v < graph.V(); ++v) {
                distTo[v] = Double.POSITIVE_INFINITY;
            }

            boolean isLineGraph = true;
            int source = -1;
            int target = -1;
            int verticesDegree1 = 0;

            for (int v = 0; v < graph.V(); ++v) {
                int outdegree = 0;
                for (Edge e : graph.adj(v)) {
                    ++outdegree;
                }

                if (outdegree == 1) {
                    if (source == -1) {
                        source = v;
                    }

                    ++verticesDegree1;
                } else if (outdegree != 2) {
                    isLineGraph = false;
                    break;
                }
            }

            if (verticesDegree1 != 2) {
                isLineGraph = false;
            }

            if (!isLineGraph) {
                throw new IllegalArgumentException("Supplied graph is not a line graph");
            }

            bfs(graph, source);
        }

        private void bfs(EdgeWeightedGraph graph, int s) {
            Queue<Integer> queue = new Queue<>();
            queue.enqueue(s);
            distTo[s] = 0.0;
            while (!queue.isEmpty()) {
                int v = queue.dequeue();
                for (Edge e : graph.adj(v)) {
                    int w = e.other(v);
                    if (distTo[w] > distTo[v] + e.weight()) {
                        distTo[w] = distTo[v] + e.weight();
                        queue.enqueue(w);
                    }
                }
            }
        }

        public double dist(int s, int t) {
            return Math.abs(distTo[t] - distTo[s]);
        }

    }

}
