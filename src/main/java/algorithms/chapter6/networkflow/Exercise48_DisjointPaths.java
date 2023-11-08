package algorithms.chapter6.networkflow;

import algorithms.chapter4.section1.Graph;

public class Exercise48_DisjointPaths {

    public static void main(String[] args) {
        Graph graph1 = new Graph(5);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(0, 3);
        graph1.addEdge(1, 4);
        graph1.addEdge(2, 4);
        graph1.addEdge(3, 4);

        int numberOfDisjointPaths1 = getNumberOfDisjointPaths(graph1, 0, 4);
        System.out.println("Number of disjoint paths 1: " + numberOfDisjointPaths1 + " Expected: 3");

        int numberOfDisjointPaths2 = getNumberOfDisjointPaths(graph1, 4, 0);
        System.out.println("Number of disjoint paths 2: " + numberOfDisjointPaths2 + " Expected: 3");

        int numberOfDisjointPaths3 = getNumberOfDisjointPaths(graph1, 1, 4);
        System.out.println("Number of disjoint paths 3: " + numberOfDisjointPaths3 + " Expected: 2");

        Graph graph2 = new Graph(5);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(0, 3);
        graph2.addEdge(1, 4);
        graph2.addEdge(2, 1);
        graph2.addEdge(3, 4);

        int numberOfDisjointPaths4 = getNumberOfDisjointPaths(graph2, 0, 4);
        System.out.println("Number of disjoint paths 4: " + numberOfDisjointPaths4 + " Expected: 2");

        int numberOfDisjointPaths5 = getNumberOfDisjointPaths(graph2, 4, 0);
        System.out.println("Number of disjoint paths 5: " + numberOfDisjointPaths5 + " Expected: 2");
    }

    private static int getNumberOfDisjointPaths(Graph graph, int s, int t) {
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
