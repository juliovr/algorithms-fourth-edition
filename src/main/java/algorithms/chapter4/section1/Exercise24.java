package algorithms.chapter4.section1;

public class Exercise24 {

    public static void main(String[] args) {
        SymbolGraph symbolGraph = new SymbolGraph("test_data/movies.txt", "/");
        Graph graph = symbolGraph.graph();
        CC cc = new CC(graph);
        System.out.println("Connected components = " + cc.count());

        int[] sizes = new int[cc.count()];
        for (int v = 0; v < graph.V(); ++v) {
            sizes[cc.id(v)]++;
        }

        int maxSizeComponent = 0;
        int minSizeComponent = Integer.MAX_VALUE;
        int componentsLessThan10 = 0;
        int idLargestComponent = 0;
        for (int i = 0; i < sizes.length; ++i) {
            if (sizes[i] < minSizeComponent) {
                minSizeComponent = sizes[i];
            }

            if (sizes[i] > maxSizeComponent) {
                maxSizeComponent = sizes[i];
                idLargestComponent = i;
            }

            if (sizes[i] < 10) {
                componentsLessThan10++;
            }
        }

        System.out.println("Min size component = " + minSizeComponent);
        System.out.println("Max size component = " + maxSizeComponent);
        System.out.println("Components less than 10 = " + componentsLessThan10);
        System.out.println("ID largest component = " + idLargestComponent);

        int kevinBaconIndex = symbolGraph.indexOf("Bacon, Kevin");
        int kevinBaconComponent = cc.id(kevinBaconIndex);
        System.out.println("Does the largest component contain Kevin Bacon? -> " + ((kevinBaconComponent == idLargestComponent) ? "Yes" : "No"));



        Graph largestComponentGraph = new Graph(graph.V());
        for (int v = 0; v < graph.V(); ++v) {
            if (cc.id(v) == idLargestComponent) {
                for (int w : graph.adj(v)) {
                    largestComponentGraph.addEdge(v, w);
                }
            }
        }

        System.out.println("Computing properties...");
        GraphProperties graphProperties = new GraphProperties(largestComponentGraph);
        System.out.println("Largest component diameter = " + graphProperties.diameter());
        System.out.println("Largest component radius = " + graphProperties.radius());
        System.out.println("Largest component center = " + graphProperties.center());
    }

}

