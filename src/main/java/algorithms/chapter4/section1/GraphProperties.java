package algorithms.chapter4.section1;

public class GraphProperties {

    private int radius = Integer.MAX_VALUE;
    private int diameter = 0;
    private int wiener = 0;
    private int center;

    public GraphProperties(Graph graph) {
        for (int v = 0; v < graph.V(); ++v) {
            int maxDistancePath = 0;
            BreadthFirstPaths paths = new BreadthFirstPaths(graph, v);

            for (int w = 0; w < graph.V(); ++w) {
                if (v == w) continue;

                int distance = paths.distTo(w);
                if (distance > maxDistancePath) {
                    maxDistancePath = distance;
                    center = w;
                }

                wiener += distance;
            }

            if (maxDistancePath != 0) {
                if (maxDistancePath > diameter) {
                    diameter = maxDistancePath;
                }

                if (maxDistancePath < radius) {
                    radius = maxDistancePath;
                }
            }
        }
    }

    public int diameter() {
        return diameter;
    }

    public int radius() {
        return radius;
    }

    public int center() {
        return center;
    }

    public int wiener() {
        return wiener;
    }

}
