package algorithms.chapter4.section1;

public class Exercise41_RandomGraphs {

    public static void main(String[] args) {
        int V = 10;
        int E = 20;

        Graph graph = new Graph(V);
        for (int i = 0; i < E; ++i) {
            int v1 = (int)(Math.random() * V);
            int v2 = (int)(Math.random() * V);
            graph.addEdge(v1, v2);
        }

        System.out.println(graph);
    }

}
