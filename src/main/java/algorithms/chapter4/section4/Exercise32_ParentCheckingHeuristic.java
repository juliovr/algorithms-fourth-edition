package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Exercise32_ParentCheckingHeuristic {

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
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 2, -1.20));
        edgeWeightedDigraph.addEdge(new DirectedEdge(3, 6, 0.52));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 0, -1.40));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 4, -1.25));

        BellmanFordParentCheckingHeuristic bellmanFordParentCheckingHeuristic = new BellmanFordParentCheckingHeuristic(edgeWeightedDigraph, 0);
        System.out.println("Shortest-paths-tree\n");
        System.out.printf("%13s %10s\n", "edgeTo[]",  "distTo[]");

        for (int vertex = 0; vertex < edgeWeightedDigraph.V(); vertex++) {
            System.out.printf("%d %11s %9.2f\n", vertex, bellmanFordParentCheckingHeuristic.edgeTo(vertex),
                    bellmanFordParentCheckingHeuristic.distTo(vertex));
        }

        System.out.println("\nExpected:\n" +
                "     edgeTo[]   distTo[]\n" +
                "0        null      0.00\n" +
                "1   5->1 0.32      0.93\n" +
                "2   0->2 0.26      0.26\n" +
                "3   7->3 0.39      0.99\n" +
                "4  6->4 -1.25      0.26\n" +
                "5   4->5 0.35      0.61\n" +
                "6   3->6 0.52      1.51\n" +
                "7   2->7 0.34      0.60");

        System.out.print("\nPath from 0 to 1: ");

        for (DirectedEdge edge : bellmanFordParentCheckingHeuristic.pathTo(1)) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }
        System.out.println("\nExpected:         0->2 2->7 7->3 3->6 6->4 4->5 5->1");

        // Edge weighted digraph in which the parent-checking heuristic improves performance
        EdgeWeightedDigraph edgeWeightedDigraph2 = new EdgeWeightedDigraph(4);
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 3, 4));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 1, 2));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(1, 2, 2));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(3, 1, -3));

        BellmanFordParentCheckingHeuristic bellmanFordParentCheckingHeuristic2 = new BellmanFordParentCheckingHeuristic(edgeWeightedDigraph2, 0);
        System.out.println("\nShortest-paths-tree 2\n");
        System.out.printf("%13s %10s\n", "edgeTo[]",  "distTo[]");

        for (int vertex = 0; vertex < edgeWeightedDigraph2.V(); vertex++) {
            System.out.printf("%d %11s %9.2f\n", vertex, bellmanFordParentCheckingHeuristic2.edgeTo(vertex),
                    bellmanFordParentCheckingHeuristic2.distTo(vertex));
        }

        System.out.println("\nExpected:\n" +
                "     edgeTo[]   distTo[]\n" +
                "0        null      0.00\n" +
                "1  3->1 -3.00      1.00\n" +
                "2   1->2 2.00      3.00\n" +
                "3   0->3 4.00      4.00");
    }

    private static class BellmanFordParentCheckingHeuristic {

        private double[] distTo;
        private DirectedEdge[] edgeTo;
        private boolean[] onQueue;
        private Queue<Integer> queue;
        private int cost;
        private Iterable<DirectedEdge> cycle;

        public BellmanFordParentCheckingHeuristic(EdgeWeightedDigraph graph, int s) {
            distTo = new double[graph.V()];
            edgeTo = new DirectedEdge[graph.V()];
            onQueue = new boolean[graph.V()];
            queue = new Queue<>();
            for (int v = 0; v < graph.V(); ++v) {
                distTo[v] = Double.POSITIVE_INFINITY;
            }
            distTo[s] = 0.0;
            queue.enqueue(s);
            onQueue[s] = true;
            while (!queue.isEmpty() && !hasNegativeCycle()) {
                int v = queue.dequeue();
                onQueue[v] = false;

                if (edgeTo[v] != null) {
                    int parent = edgeTo[v].from();
                    if (onQueue[parent]) {
                        continue;
                    }
                }

                relax(graph, v);
            }
        }

        private void relax(EdgeWeightedDigraph graph, int v) {
            for (DirectedEdge e : graph.adj(v)) {
                int w = e.to();
                if (distTo[w] > distTo[v] + e.weight()) {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    if (!onQueue[w]) {
                        queue.enqueue(w);
                        onQueue[w] = true;
                    }
                }

                if (cost++ % graph.V() == 0) {
                    findNegativeCycle();
                }
            }
        }

        private void findNegativeCycle() {
            int V = edgeTo.length;
            EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
            for (int v = 0; v < V; ++v) {
                if (edgeTo[v] != null) {
                    spt.addEdge(edgeTo[v]);
                }
            }

            EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(spt);
            cycle = cf.cycle();
        }

        public boolean hasNegativeCycle() {
            return cycle != null;
        }

        public Iterable<DirectedEdge> negativeCycle() {
            return cycle;
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
