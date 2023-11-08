package algorithms.chapter6.networkflow;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise44_ProductDistribution {

    public static void main(String[] args) {
        int V = 30;
        int E = 100;
        int days = 5;
        int maxRoadCapacity = 200;

        FlowNetwork citiesMap = generateCitiesMap(V, E, maxRoadCapacity);

        for (int day = 0; day < days; day++) {
            boolean possibleToDistribute = generateDailyOrder(citiesMap, V, day + 1);

            if (!possibleToDistribute) {
                day--;
                continue;
            }

            if (day != days - 1) {
                System.out.println();
            }
        }
    }

    private static FlowNetwork generateCitiesMap(int V, int E, int maxCapacity) {
        FlowNetwork flowNetwork = new FlowNetwork(V);
        int v, w, capacity;
        for (int i = 0; i < E; ++i) {
            do {
                v = StdRandom.uniform(V);
                w = StdRandom.uniform(V);
            } while (v == w);

            capacity = StdRandom.uniform(maxCapacity);

            flowNetwork.addEdge(new FlowEdge(v, w, capacity));
        }

        return flowNetwork;
    }

    private static boolean generateDailyOrder(FlowNetwork citiesMap, int V, int day) {
        int s;
        int t;

        do {
            s = StdRandom.uniform(V);
            t = StdRandom.uniform(V);
        } while (s == t);

        FordFulkerson maxflow = new FordFulkerson(citiesMap, s, t);
        if (maxflow.value() < 0.0) {
            return false;
        }


        System.out.println("**** Day " + day + " ****");
        System.out.println("Max flow from " + s + " to " + t);
        for (int v = 0; v < citiesMap.V(); ++v) {
            for (FlowEdge e : citiesMap.adj(v)) {
                if ((v == e.from()) && e.flow() > 0) {
                    System.out.println("  " + e);
                }
            }
        }

        System.out.println("Max flow value = " + maxflow.value());

        return true;
    }

}
