package algorithms.chapter4.section3;

public class Exercise22_MinimumSpanningForest {

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(6);
        graph.addEdge(new Edge(0, 1, 0.1));
        graph.addEdge(new Edge(1, 2, 0.2));
        graph.addEdge(new Edge(3, 4, 0.3));
        graph.addEdge(new Edge(4, 5, 0.4));

//        EdgeWeightedCC cc = new EdgeWeightedCC(graph);
//        System.out.println(cc.count());
//        System.out.println(cc.connected(0, 1) + " Expected = true");
//        System.out.println(cc.connected(3, 4) + " Expected = true");
//        System.out.println(cc.connected(0, 2) + " Expected = true");
//        System.out.println(cc.connected(1, 5) + " Expected = false");

        System.out.println("PrimMSF");
        PrimMSF primMSF = new PrimMSF(graph);
        for (int c = 0; c < primMSF.edges().length; ++c) {
            Iterable<Edge> mst = primMSF.edges()[c];
            System.out.println("Component " + c);
            for (Edge e : mst) {
                System.out.println("\t" + e);
            }
        }

        System.out.println("KruskalMSF");
        KruskalMSF kruskalMSF = new KruskalMSF(graph);
        for (int c = 0; c < kruskalMSF.edges().length; ++c) {
            Iterable<Edge> mst = kruskalMSF.edges()[c];
            System.out.println("Component " + c);
            for (Edge e : mst) {
                System.out.println("\t" + e);
            }
        }
    }

}
