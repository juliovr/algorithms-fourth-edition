package algorithms.chapter4.section1;

import edu.princeton.cs.algs4.StdIn;

public class DegreesOfSeparation {

    public static void main(String[] args) {
        String stream = args[0];
        String separator = args[1];

        SymbolGraph sg = new SymbolGraph(stream, separator);

        String source = args[2];
        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        Graph graph = sg.graph();
        int s = sg.indexOf(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, s);
        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.indexOf(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        System.out.println("    " + sg.nameOf(v));
                    }
                } else {
                    System.out.println("not connected");
                }
            } else {
                System.out.println("not in database");
            }
        }
    }

}
