package algorithms.chapter4.section4;

import edu.princeton.cs.algs4.In;

public class CPM {

    public static void main(String[] args) {
        In in = new In("test_data/jobsPC.txt");
        int n = in.readInt();
        in.readLine();

        // 2*n vertices for start and end
        // + 2 for the source and target
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(2*n + 2);

        int source = 2*n;
        int target = 2*n + 1;

        // Every "process" is made of 2 vertices: start and end.
        // start -> end is the actual edge with duration as weight.
        // The clone vertex for end is the i+n
        // Additionally, every "process" is linked to a source and a target (with duration 0),
        // indicating the start and finish of the total schedule.
        for (int i = 0; i < n; ++i) {
            String[] a = in.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            int start = i;
            int end = i+n;
            graph.addEdge(new DirectedEdge(start, end, duration));
            graph.addEdge(new DirectedEdge(source, start, 0.0));
            graph.addEdge(new DirectedEdge(end, target, 0.0));
            for (int j = 1; j < a.length; ++j) {
                int successor = Integer.parseInt(a[j]);
                graph.addEdge(new DirectedEdge(end, successor, 0.0));
            }
        }

        AcyclicLP lp = new AcyclicLP(graph, source);
        System.out.println("Start times:");
        for (int i = 0; i < n; ++i) {
            System.out.printf("%4d: %5.1f\n", i, lp.distTo(i));
        }
        System.out.printf("Finish time: %5.1f\n", lp.distTo(target));
    }

}
