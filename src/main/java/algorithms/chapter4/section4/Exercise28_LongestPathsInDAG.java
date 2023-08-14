package algorithms.chapter4.section4;

public class Exercise28_LongestPathsInDAG {

    public static void main(String[] args) {
        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(9);
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 1, 1));
        edgeWeightedDigraph.addEdge(new DirectedEdge(0, 2, 2));
        edgeWeightedDigraph.addEdge(new DirectedEdge(1, 3, 3));
        edgeWeightedDigraph.addEdge(new DirectedEdge(3, 4, -3));
        edgeWeightedDigraph.addEdge(new DirectedEdge(3, 5, 4));
        edgeWeightedDigraph.addEdge(new DirectedEdge(4, 6, 1));
        edgeWeightedDigraph.addEdge(new DirectedEdge(5, 6, 2));
        edgeWeightedDigraph.addEdge(new DirectedEdge(6, 7, 2));

        AcyclicLP acyclicLP = new AcyclicLP(edgeWeightedDigraph, 0);

        int furthestVertex = -1;
        double longestDistance = Double.NEGATIVE_INFINITY;

        for (int vertex = 0; vertex < edgeWeightedDigraph.V(); vertex++) {
            if (acyclicLP.distTo(vertex) > longestDistance) {
                longestDistance = acyclicLP.distTo(vertex);
                furthestVertex = vertex;
            }
        }

        System.out.println("Dist to 1: " + acyclicLP.distTo(1) + " Expected: 1.0");
        System.out.println("Dist to 8: " + acyclicLP.distTo(8) + " Expected: -Infinity");

        System.out.print("\nLongest path: ");

        for (DirectedEdge edge : acyclicLP.pathTo(furthestVertex)) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }

        System.out.println("\nExpected:     0->1 1->3 3->5 5->6 6->7");
    }

}
