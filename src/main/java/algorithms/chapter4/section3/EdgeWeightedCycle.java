package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.Stack;

import java.util.HashSet;

public class EdgeWeightedCycle {

    private boolean[] marked;
    private Edge[] edgeTo;
    private boolean[] onStack;
    private HashSet<Edge> visitedEdges; // To avoid check the same edge back
    private Stack<Edge> cycle;

    public EdgeWeightedCycle(EdgeWeightedGraph graph) {
        marked = new boolean[graph.V()];
        edgeTo = new Edge[graph.V()];
        onStack = new boolean[graph.V()];
        visitedEdges = new HashSet<>();

        for (int s = 0; s < graph.V(); ++s) {
            if (!marked[s]) {
                dfs(graph, s);
            }
        }
    }

    private void dfs(EdgeWeightedGraph graph, int v) {
        onStack[v] = true;

        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            if (visitedEdges.contains(e)) {
                continue;
            }

            visitedEdges.add(e);

            int w = e.other(v);

            if (hasCycle()) {
                return;
            } if (!marked[w]) {
                edgeTo[w] = e;
                dfs(graph, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();

                for (int currentV = v; currentV != w; currentV = edgeTo[currentV].other(currentV)) {
                    cycle.push(edgeTo[currentV]);
                }
                cycle.push(e);
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Edge> cycle() {
        return cycle;
    }

    public int count() {
        return cycle.size();
    }

}
