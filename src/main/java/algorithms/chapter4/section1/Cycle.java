package algorithms.chapter4.section1;

public class Cycle {

    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph graph) {
        marked = new boolean[graph.V()];
        for (int s = 0; s < graph.V(); ++s) {
            if (!marked[s]) {
                dfs(graph, -1, s);
            }
        }
    }

    private void dfs(Graph graph, int parent, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, v, w);
            } else if (parent != w) {
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

}
