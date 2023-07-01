package algorithms.chapter4.section1;

import java.util.ArrayList;
import java.util.List;

public class Exercise36_TwoEdgeConnectivity {

    public static void main(String[] args) {
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);

        List<Edge> bridges1 = new TwoEdgeConnectivity(graph1).findBridges();

        if (bridges1.size() == 0) {
            System.out.println("Graph is two-edge connected");
        } else {
            System.out.println("Bridges");

            for (Edge edge : bridges1) {
                System.out.println(edge.v1 + "-" + edge.v2);
            }
        }
        System.out.println("Expected: Graph is two-edge connected\n");

        Graph graph2 = new Graph(6);
        graph2.addEdge(0, 1);
        graph2.addEdge(2, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(3, 5);
        graph2.addEdge(4, 5);
        graph2.addEdge(3, 4);
        graph2.addEdge(1, 5);

        List<Edge> bridges2 = new TwoEdgeConnectivity(graph2).findBridges();

        if (bridges2.size() == 0) {
            System.out.println("Graph is two-edge connected");
        } else {
            System.out.println("Bridges");

            for (Edge edge : bridges2) {
                System.out.println(edge.v1 + "-" + edge.v2);
            }
        }
        System.out.println("Expected: 1-5\n");

        Graph graph3 = new Graph(7);
        graph3.addEdge(0, 1);
        graph3.addEdge(2, 1);
        graph3.addEdge(0, 2);
        graph3.addEdge(3, 6);
        graph3.addEdge(4, 6);
        graph3.addEdge(3, 4);
        graph3.addEdge(1, 5);
        graph3.addEdge(5, 6);

        List<Edge> bridges3 = new TwoEdgeConnectivity(graph3).findBridges();

        if (bridges3.size() == 0) {
            System.out.println("Graph is two-edge connected");
        } else {
            System.out.println("Bridges");

            for (Edge edge : bridges3) {
                System.out.println(edge.v1 + "-" + edge.v2);
            }
        }
        System.out.println("Expected: 5-6");
        System.out.println("Expected: 1-5");
    }

    private static class Edge {
        private int v1;
        private int v2;

        public Edge(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    private static class TwoEdgeConnectivity {
        private boolean[] marked;
        private int[] low;
        private int[] time;
        private Graph graph;
        private int entryTime = 1;

        public TwoEdgeConnectivity(Graph graph) {
            this.marked = new boolean[graph.V()];
            this.low = new int[graph.V()];
            this.time = new int[graph.V()];
            this.graph = graph;
        }

        public List<Edge> findBridges() {
            List<Edge> bridges = new ArrayList<>();
            dfs(bridges, 0, 0);

            return bridges;
        }


        private void dfs(List<Edge> bridges, int v, int parent) {
            marked[v] = true;
            time[v] = low[v] = entryTime++;

            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    dfs(bridges, w, v);
                    low[v] = Math.min(low[v], low[w]);
                    if (low[w] > time[v]) {
                        bridges.add(new Edge(v, w));
                    }
                } else if (parent != w) {
                    // cycle
                    low[v] = Math.min(low[v], time[w]);
                }
            }
        }

    }

}
