package algorithms.chapter6.networkflow;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise41 {

    public static void main(String[] args) {
        FlowNetworkGenerator flowNetworkGenerator = new FlowNetworkGenerator();

        FlowNetwork[] randomUniformCapacityNetworks = flowNetworkGenerator.generateRandomUniformCapacityNetworks();
        FlowNetwork[] randomGaussianCapacityNetworks = flowNetworkGenerator.generateRandomGaussianCapacityNetworks();

        System.out.println("*** Random uniform capacity networks generated ***");
        printFlowNetworks(randomUniformCapacityNetworks);

        System.out.println("\n\n*** Random gaussian capacity networks generated ***");
        printFlowNetworks(randomGaussianCapacityNetworks);
    }

    private static void printFlowNetworks(FlowNetwork[] flowNetworks) {
        System.out.println("Number of flow networks: " + flowNetworks.length + "\n");

        for (int i = 0; i < flowNetworks.length; i++) {
            if (i > 0) {
                System.out.println();
            }

            System.out.println("Configuration " + (i + 1));
            System.out.println("Vertices: " + flowNetworks[i].V());
            System.out.println("Edges: " + flowNetworks[i].E());
        }
    }

    private static class FlowNetworkGenerator {

        private static int[] vertexValues = { 10, 100, 500, 1000 };
        private static int[] edgesValues = { 10, 100, 500, 1000 };

        public FlowNetwork[] generateRandomUniformCapacityNetworks() {
            RandomCapacity randomUniformCapacity = new RandomUniformCapacity();
            return generateFlowNetworks(randomUniformCapacity);
        }

        public FlowNetwork[] generateRandomGaussianCapacityNetworks() {
            RandomCapacity randomGaussianCapacity = new RandomGaussianCapacity();
            return generateFlowNetworks(randomGaussianCapacity);
        }

        private FlowNetwork[] generateFlowNetworks(RandomCapacity randomCapacity) {
            FlowNetwork[] networks = new FlowNetwork[vertexValues.length];
            for (int i = 0; i < vertexValues.length; ++i) {
                int V = vertexValues[i];
                int E = edgesValues[i];
                networks[i] = generateNetwork(V, E, randomCapacity);
            }

            return networks;
        }

        private FlowNetwork generateNetwork(int V, int E, RandomCapacity randomCapacity) {
            FlowNetwork flowNetwork = new FlowNetwork(V);
            int v;
            int w;
            int capacity;
            for (int i = 0; i < E; ++i) {
                do {
                    v = StdRandom.uniform(V);
                    w = StdRandom.uniform(V);
                } while (v == w);

                capacity = randomCapacity.generateCapacity();

                flowNetwork.addEdge(new FlowEdge(v, w, capacity));
            }

            return flowNetwork;
        }

    }

    private interface RandomCapacity {
        int generateCapacity();
    }

    private static final int MAX_VALUE = (int)Math.pow(2, 20);

    private static class RandomUniformCapacity implements RandomCapacity {

        @Override
        public int generateCapacity() {
            return StdRandom.uniform(MAX_VALUE);
        }

    }

    private static class RandomGaussianCapacity implements RandomCapacity {

        private static final int median = MAX_VALUE / 2;
        private static final double sigma = median / 6.0;

        @Override
        public int generateCapacity() {
            return (int)StdRandom.gaussian(median, sigma);
        }

    }
    
}
