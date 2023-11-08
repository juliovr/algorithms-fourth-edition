package algorithms.chapter6.networkflow;

public class Exercise37 {

    public static void main(String[] args) {
        FlowNetwork flowNetwork = new FlowNetwork(7);

        // Path 1 from source to target
        flowNetwork.addEdge(new FlowEdge(0, 1, 5));
        flowNetwork.addEdge(new FlowEdge(1, 4, 4));
        flowNetwork.addEdge(new FlowEdge(4, 6, 3));

        // Path 2 from source to target
        flowNetwork.addEdge(new FlowEdge(0, 2, 8));
        flowNetwork.addEdge(new FlowEdge(2, 5, 10));
        flowNetwork.addEdge(new FlowEdge(5, 6, 7));

        // Path 3 from source to target
        flowNetwork.addEdge(new FlowEdge(0, 3, 2));
        flowNetwork.addEdge(new FlowEdge(3, 6, 2));

        int source = 0;
        int target = 6;
        double maxflow = getMaxFlow(flowNetwork, source, target);
        System.out.println("Max flow: " + maxflow);
        System.out.println("Expected: 14.0");
    }

    private static double getMaxFlow(FlowNetwork G, int source, int target) {
        double maxflow = 0.0;
        for (FlowEdge e : G.adj(source)) {
            int w = e.to();
            if (w != target) {
                double minCapacity = e.capacity();
                maxflow += dfs(G, w, target, minCapacity);
            }
        }

        return maxflow;
    }

    private static double dfs(FlowNetwork G, int v, int target, double minCapacity) {
        for (FlowEdge e : G.adj(v)) {
            if (v == e.from()) {
                int w = e.to();
                if (w == target) {
                    continue;
                }

                if (e.capacity() < minCapacity) {
                    minCapacity = e.capacity();
                }

                return dfs(G, w, target, minCapacity);
            }
        }

        return minCapacity;
    }

}
