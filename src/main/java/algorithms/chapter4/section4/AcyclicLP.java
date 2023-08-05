package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Stack;

public class AcyclicLP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicLP(EdgeWeightedDigraph graph, int s) {
        edgeTo = new DirectedEdge[graph.V()];
        distTo = new double[graph.V()];

        for (int v = 0; v < graph.V(); ++v) {
            distTo[v] = Double.NEGATIVE_INFINITY;
        }

        distTo[s] = 0.0;

        Topological topological = new Topological(graph);

        for (int v : topological.order()) {
            relax(graph, v);
        }
    }

    private void relax(EdgeWeightedDigraph graph, int v) {
        for (DirectedEdge e : graph.adj(v)) {
            int w = e.to();
            if (distTo[w] < distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
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
