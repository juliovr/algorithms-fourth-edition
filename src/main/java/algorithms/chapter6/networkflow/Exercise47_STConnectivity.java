package algorithms.chapter6.networkflow;

import algorithms.chapter4.section1.Graph;

public class Exercise47_STConnectivity {

    public static void main(String[] args) {
        Graph graph1 = new Graph(5);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(0, 3);
        graph1.addEdge(1, 4);
        graph1.addEdge(2, 4);
        graph1.addEdge(3, 4);

        int numberOfEdgesToDisconnect1 = getMinNumberOfEdgesToDisconnect(graph1, 0, 4);
        System.out.println("Number of edges to disconnect graph 1: " + numberOfEdgesToDisconnect1 + " Expected: 3");

        int numberOfEdgesToDisconnect2 = getMinNumberOfEdgesToDisconnect(graph1, 4, 0);
        System.out.println("Number of edges to disconnect graph 2: " + numberOfEdgesToDisconnect2 + " Expected: 3");

        int numberOfEdgesToDisconnect3 = getMinNumberOfEdgesToDisconnect(graph1, 1, 4);
        System.out.println("Number of edges to disconnect graph 3: " + numberOfEdgesToDisconnect3 + " Expected: 2");

        Graph graph2 = new Graph(5);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(0, 3);
        graph2.addEdge(1, 4);
        graph2.addEdge(2, 1);
        graph2.addEdge(3, 4);

        int numberOfEdgesToDisconnect4 = getMinNumberOfEdgesToDisconnect(graph2, 0, 4);
        System.out.println("Number of edges to disconnect graph 4: " + numberOfEdgesToDisconnect4 + " Expected: 2");

        int numberOfEdgesToDisconnect5 = getMinNumberOfEdgesToDisconnect(graph2, 4, 0);
        System.out.println("Number of edges to disconnect graph 5: " + numberOfEdgesToDisconnect5 + " Expected: 2");
    }

    private static int getMinNumberOfEdgesToDisconnect(Graph graph, int s, int t) {
        FlowNetwork flowNetwork = new FlowNetwork(graph.V());
        for (int v = 0; v < graph.V(); ++v) {
            for (int w : graph.adj(v)) {
                flowNetwork.addEdge(new FlowEdge(v, w, 1));
            }
        }

        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, s, t);
        return (int)fordFulkerson.value();
    }

}
