package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedDirectedCycle {

    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;
    private boolean[] onStack;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph digraph) {
        onStack = new boolean[digraph.V()];
        edgeTo = new DirectedEdge[digraph.V()];
        marked = new boolean[digraph.V()];

        for (int v = 0; v < digraph.V(); ++v) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph digraph, int v) {
        onStack[v] = true;

        marked[v] = true;
        for (DirectedEdge e : digraph.adj(v)) {
            int w = e.to();
            if (hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();

                DirectedEdge edgeInCycle = e;
                cycle.push(edgeInCycle);

                while (edgeInCycle.from() != w) {
                    // Add everything except for the first edge in the cycle (prevents to add the edge used to reach
                    // the first node in the cycle, previous to enter the cycle)
                    edgeInCycle = edgeTo[edgeInCycle.from()];
                    cycle.push(edgeInCycle);
                }
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

}
