package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.Queue;

public class Exercise33_Certification {

    public static void main(String[] args) {
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(5);
        edgeWeightedGraph.addEdge(new Edge(0, 1, 0.42));
        edgeWeightedGraph.addEdge(new Edge(0, 3, 0.5));
        edgeWeightedGraph.addEdge(new Edge(1, 2, 0.12));
        edgeWeightedGraph.addEdge(new Edge(1, 4, 0.91));
        edgeWeightedGraph.addEdge(new Edge(2, 3, 0.72));
        edgeWeightedGraph.addEdge(new Edge(3, 4, 0.8));
        edgeWeightedGraph.addEdge(new Edge(3, 4, 0.82));
        edgeWeightedGraph.addEdge(new Edge(4, 4, 0.1));

        Queue<Edge> proposal1 = new Queue<>();
        proposal1.enqueue(new Edge(0, 1, 0.42));
        proposal1.enqueue(new Edge(1, 2, 0.12));
        proposal1.enqueue(new Edge(0, 3, 0.5));
        proposal1.enqueue(new Edge(3, 4, 0.8));

        boolean check1 = check(edgeWeightedGraph, proposal1);
        System.out.println("Check 1: " + check1 + " Expected: true");

        // Fails on the cut optimality condition
        Queue<Edge> proposal2 = new Queue<>();
        proposal2.enqueue(new Edge(0, 1, 0.42));
        proposal2.enqueue(new Edge(1, 2, 0.12));
        proposal2.enqueue(new Edge(2, 3, 0.72));
        proposal2.enqueue(new Edge(3, 4, 0.8));

        boolean check2 = check(edgeWeightedGraph, proposal2);
        System.out.println("Check 2: " + check2 + " Expected: false");

        // Fails because it is not a spanning tree
        Queue<Edge> proposal3 = new Queue<>();
        proposal3.enqueue(new Edge(0, 1, 0.42));
        proposal3.enqueue(new Edge(0, 3, 0.5));
        proposal3.enqueue(new Edge(3, 4, 0.8));

        boolean check3 = check(edgeWeightedGraph, proposal3);
        System.out.println("Check 3: " + check3 + " Expected: false");
    }

    private static void dfs(boolean[] marked, Edge[] edgeTo, EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (!marked[w]) {
                edgeTo[w] = e;
                dfs(marked, edgeTo, graph, w);
            }
        }
    }

    private static boolean check(EdgeWeightedGraph graph, Iterable<Edge> mst) {
        boolean[] marked = new boolean[graph.V()];
        Edge[] edgeTo = new Edge[graph.V()];
        for (Edge e : mst) {
            for (int i = 0; i < graph.V(); ++i) {
                marked[i] = false;
                edgeTo[i] = null;
            }

            int v = e.either();
            int w = e.other(v);

            graph.deleteEdge(e);

            dfs(marked, edgeTo, graph, v);

            graph.addEdge(e);

            // There is no path
            if (!marked[w]) {
//                System.out.println("There is no other path from " + v + " to " + w);
                return false;
            }

            // If the edgeTo[w].weight() is less than e.weight() means that e was not part of the graph's MST, so the
            // check should fail.
            if (edgeTo[w].weight() < e.weight()) {
                return false;
            }
        }

        return true;
    }

}
