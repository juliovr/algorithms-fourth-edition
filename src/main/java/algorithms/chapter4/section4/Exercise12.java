package algorithms.chapter4.section4;

public class Exercise12 {

    public static void main(String[] args) {
        EdgeWeightedDigraph edgeWeightedDigraphWithCycle = new EdgeWeightedDigraph(8);
        edgeWeightedDigraphWithCycle.addEdge(new DirectedEdge(0, 1, 0.35));
        edgeWeightedDigraphWithCycle.addEdge(new DirectedEdge(1, 2, 0.35));
        edgeWeightedDigraphWithCycle.addEdge(new DirectedEdge(2, 3, 0.37));
        edgeWeightedDigraphWithCycle.addEdge(new DirectedEdge(3, 4, 0.28));
        edgeWeightedDigraphWithCycle.addEdge(new DirectedEdge(4, 1, 0.28));
        edgeWeightedDigraphWithCycle.addEdge(new DirectedEdge(6, 7, 0.32));
        edgeWeightedDigraphWithCycle.addEdge(new DirectedEdge(7, 5, 0.38));

        EdgeWeightedDigraph edgeWeightedDAG = new EdgeWeightedDigraph(5);
        edgeWeightedDAG.addEdge(new DirectedEdge(0, 1, 0.35));
        edgeWeightedDAG.addEdge(new DirectedEdge(1, 2, 0.22));
        edgeWeightedDAG.addEdge(new DirectedEdge(3, 4, 0.31));
        edgeWeightedDAG.addEdge(new DirectedEdge(4, 0, 0.29));

        System.out.println("Cycle:");
        System.out.print("Actual:   ");
        EdgeWeightedDirectedCycle edgeWeightedDirectedCycle = new EdgeWeightedDirectedCycle(edgeWeightedDigraphWithCycle);
        for (DirectedEdge edge : edgeWeightedDirectedCycle.cycle()) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }
        System.out.println("\nExpected: 1->2 2->3 3->4 4->1 ");

        Topological topological = new Topological(edgeWeightedDAG);
        System.out.println("\nTopological order:");
        System.out.print("Actual:   ");
        for (int vertex : topological.order()) {
            System.out.print(vertex + " ");
        }
        System.out.println("\nExpected: 3 4 0 1 2");
    }
    
}
