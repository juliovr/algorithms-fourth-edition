package algorithms.chapter4.section3;

import java.util.HashSet;
import java.util.Set;

public class Exercise26_CriticalEdges {

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(7);
        graph.addEdge(new Edge(0, 1, 0.5));
        graph.addEdge(new Edge(1, 2, 0.5));
        graph.addEdge(new Edge(1, 3, 0.3));
        graph.addEdge(new Edge(1, 4, 0.4));
        graph.addEdge(new Edge(2, 3, 0.5));
        graph.addEdge(new Edge(3, 4, 0.4));
        graph.addEdge(new Edge(2, 6, 0.2));
        graph.addEdge(new Edge(3, 6, 0.5));
        graph.addEdge(new Edge(4, 5, 0.5));
        graph.addEdge(new Edge(6, 5, 0.1));

        Set<Double> distinctWeights = new HashSet<>();
        for (Edge e : graph.edges()) {
            distinctWeights.add(e.weight());
        }

        Set<Edge> criticalEdges = new HashSet<>();
        for (double weight : distinctWeights) {
            EdgeWeightedGraph subgraph = new EdgeWeightedGraph(graph.V());
            for (Edge e : graph.edges()) {
                if (e.weight() <= weight) {
                    subgraph.addEdge(e);
                }
            }
            TwoEdgeConnectivity twoEdgeConnectivity = new TwoEdgeConnectivity(subgraph);
            for (Edge critical : twoEdgeConnectivity.criticalEdges()) {
                criticalEdges.add(critical);
            }
        }

        System.out.println(criticalEdges);
    }

    private static class TwoEdgeConnectivity {
        private boolean[] marked;
        private int[] low;
        private int[] time;
        private EdgeWeightedGraph graph;
        private int entryTime = 1;
        private Set<Edge> criticalEdges = new HashSet<>();

        public TwoEdgeConnectivity(EdgeWeightedGraph graph) {
            this.marked = new boolean[graph.V()];
            this.low = new int[graph.V()];
            this.time = new int[graph.V()];
            this.graph = graph;

            findCriticalEdges();
        }

        private void findCriticalEdges() {
            for (int s = 0; s < graph.V(); ++s) {
                if (!marked[s]) {
                    dfs(s, s);
                }
            }
        }

        private void dfs(int v, int parent) {
            marked[v] = true;
            time[v] = low[v] = entryTime++;

            for (Edge e : graph.adj(v)) {
                int w = e.other(v);
                if (!marked[w]) {
                    dfs(w, v);
                    low[v] = Math.min(low[v], low[w]);
                    if (low[w] > time[v]) {
                        criticalEdges.add(e);
                    }
                } else if (parent != w) {
                    // cycle
                    low[v] = Math.min(low[v], time[w]);
                }
            }
        }

        public Iterable<Edge> criticalEdges() {
            return criticalEdges;
        }

    }

}
