package algorithms.chapter4.section4;

public class Exercise37_CriticalEdges {

    public static void main(String[] args) {
        EdgeWeightedDigraph edgeWeightedDigraph1 = new EdgeWeightedDigraph(8);
        // Shortest path
        edgeWeightedDigraph1.addEdge(new DirectedEdge(0, 1, 1));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 2, 2));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(2, 3, 1));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(3, 4, 1));
        // Path to take when edge 0->1 is removed
        edgeWeightedDigraph1.addEdge(new DirectedEdge(0, 1, 1));
        // Path to take when edge 3->4 is removed
        edgeWeightedDigraph1.addEdge(new DirectedEdge(3, 5, 2));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(5, 4, 1));
        // Possible path to take when edge 2->3 is removed. Won't be taken because there is a shorter path from 0 to 4
        edgeWeightedDigraph1.addEdge(new DirectedEdge(2, 6, 3));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(6, 4, 3));
        // Path to take when edge 1->2 is removed
        edgeWeightedDigraph1.addEdge(new DirectedEdge(1, 7, 3));
        edgeWeightedDigraph1.addEdge(new DirectedEdge(7, 4, 4));

        int source1 = 0;
        int target1 = 4;

        System.out.print("Critical edge from " + source1 + " to " + target1 + ": ");
        DirectedEdge criticalEdge1 = getCriticalEdge(edgeWeightedDigraph1, source1, target1);
        if (criticalEdge1 == null) {
            System.out.println("There is no path from " + source1 + " to " + target1);
        } else {
            System.out.println(criticalEdge1);
        }
        System.out.println("Expected: 1->2 2.00");

        int source2 = 7;
        int target2 = 3;

        System.out.print("\nCritical edge " + source2 + " to " + target2 + ": ");
        DirectedEdge criticalEdge2 = getCriticalEdge(edgeWeightedDigraph1, source2, target2);
        if (criticalEdge2 == null) {
            System.out.println("There is no path from " + source2 + " to " + target2);
        } else {
            System.out.println(criticalEdge2);
        }
        System.out.println("Expected: There is no path from 7 to 3");

        EdgeWeightedDigraph edgeWeightedDigraph2 = new EdgeWeightedDigraph(9);
        // Shortest path
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 1, 1));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(1, 2, 1));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(2, 3, 1));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(3, 4, 1));
        // Path to take when edge 2->3 is removed
        edgeWeightedDigraph2.addEdge(new DirectedEdge(2, 5, 2));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(5, 4, 2));
        // Path to take when edge 0->1 is removed
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 6, 4));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(6, 4, 3));
        // Another path from 0 to 4 when edge 0->1 is removed - but this path is longer and won't be taken
        edgeWeightedDigraph2.addEdge(new DirectedEdge(0, 7, 4));
        edgeWeightedDigraph2.addEdge(new DirectedEdge(7, 4, 5));
        // Adding a cycle just for fun
        edgeWeightedDigraph2.addEdge(new DirectedEdge(2, 1, 2));
        // Random edge
        edgeWeightedDigraph2.addEdge(new DirectedEdge(8, 2, 3));

        int source3 = 0;
        int target3 = 4;

        System.out.print("\nCritical edge " + source3 + " to " + target3 + ": ");
        DirectedEdge criticalEdge3 = getCriticalEdge(edgeWeightedDigraph2, source3, target3);
        if (criticalEdge3 == null) {
            System.out.println("There is no path from " + source3 + " to " + target3);
        } else {
            System.out.println(criticalEdge3);
        }
        System.out.println("Expected: 0->1 1.00");

        // Digraph with a bridge: 2->3 is a bridge which disconnects source and target vertices when removed
        EdgeWeightedDigraph edgeWeightedDigraph3 = new EdgeWeightedDigraph(9);
        // Shortest path
        edgeWeightedDigraph3.addEdge(new DirectedEdge(0, 1, 1));
        edgeWeightedDigraph3.addEdge(new DirectedEdge(1, 2, 1));
        edgeWeightedDigraph3.addEdge(new DirectedEdge(2, 3, 3));
        edgeWeightedDigraph3.addEdge(new DirectedEdge(3, 4, 1));
        edgeWeightedDigraph3.addEdge(new DirectedEdge(4, 5, 1));
        // Other paths
        edgeWeightedDigraph3.addEdge(new DirectedEdge(0, 1, 2));
        edgeWeightedDigraph3.addEdge(new DirectedEdge(1, 2, 2));
        edgeWeightedDigraph3.addEdge(new DirectedEdge(3, 4, 2));
        edgeWeightedDigraph3.addEdge(new DirectedEdge(4, 5, 2));

        int source4 = 0;
        int target4 = 5;

        System.out.print("\nCritical edge " + source4 + " to " + target4 + ": ");
        DirectedEdge criticalEdge4 = getCriticalEdge(edgeWeightedDigraph3, source4, target4);
        if (criticalEdge4 == null) {
            System.out.println("There is no path from " + source4 + " to " + target4);
        } else {
            System.out.println(criticalEdge4);
        }
        System.out.println("Expected: 0->1 1.00");
    }

    private static DirectedEdge getCriticalEdge(EdgeWeightedDigraph graph, int s, int t) {
        DijkstraSP sp = new DijkstraSP(graph, s);
        if (!sp.hasPathTo(t)) {
            return null;
        }

        double weight = sp.distTo(t);

        DirectedEdge[] edgesInSP = sp.edges();
        for (int i = 0; i < edgesInSP.length; ++i) {
            if (i == s) continue;

            DirectedEdge e = edgesInSP[i];
            graph.deleteEdge(e);
            DijkstraSP testSP = new DijkstraSP(graph, s);
            if (sp.hasPathTo(t)) {
                if (testSP.distTo(t) > weight) {
                    return e;
                }
            }
            graph.addEdge(e);
        }

        return null;
    }
    
}
