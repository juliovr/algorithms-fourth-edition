package algorithms.chapter4.section1;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.s = s;
        dfs(graph, s);
    }

//    private void dfs(Graph graph, int v) {
//        marked[v] = true;
//        for (int w : graph.adj(v)) {
//            if (!marked[w]) {
//                edgeTo[w] = v;
//                dfs(graph, w);
//            }
//        }
//    }

    private void dfs(Graph graph, int v) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (marked[vertex]) {
                continue;
            }

            marked[vertex] = true;
            Stack<Integer> adj = new Stack<>();
            for (int w : graph.adj(vertex)) {
                if (!marked[w]) {
                    edgeTo[w] = vertex;
                    adj.push(w);
                }
            }

            while (!adj.isEmpty()) {
                stack.push(adj.pop());
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);

        return path;
    }

}
