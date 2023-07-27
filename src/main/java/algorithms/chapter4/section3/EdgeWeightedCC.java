package algorithms.chapter4.section3;

import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedCC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public EdgeWeightedCC(EdgeWeightedGraph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        for (int s = 0; s < graph.V(); ++s) {
            if (!marked[s]) {
                dfs(graph, s);
                count++;
            }
        }
    }

    private void dfs(EdgeWeightedGraph graph, int s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (marked[v]) continue;

            marked[v] = true;
            id[v] = count;

            // Using adj as a temp stack to preserve the original order as in recursive calls
            Stack<Integer> adj = new Stack<>();
            for (Edge e : graph.adj(v)) {
                int w = e.other(v);
                if (!marked[w]) {
                    adj.push(w);
                }
            }

            while (!adj.isEmpty()) {
                stack.push(adj.pop());
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }


}
