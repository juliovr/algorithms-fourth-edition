package algorithms.chapter4.section2;

public class Exercise7 {

    public static void main(String[] args) {
        Digraph digraph1 = new Digraph(6);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(2, 1);
        digraph1.addEdge(2, 3);
        digraph1.addEdge(3, 3);
        digraph1.addEdge(4, 3);

        System.out.println("Test 1\n");

        Degrees degrees1 = new Degrees(digraph1);
        System.out.println("Indegree of vertex 0: " + degrees1.indegree(0) + " Expected: 0");
        System.out.println("Indegree of vertex 1: " + degrees1.indegree(1) + " Expected: 2");
        System.out.println("Indegree of vertex 2: " + degrees1.indegree(2) + " Expected: 0");
        System.out.println("Indegree of vertex 3: " + degrees1.indegree(3) + " Expected: 3");
        System.out.println("Indegree of vertex 4: " + degrees1.indegree(4) + " Expected: 0");
        System.out.println("Indegree of vertex 5: " + degrees1.indegree(5) + " Expected: 0");

        System.out.println();

        System.out.println("Outdegree of vertex 0: " + degrees1.outdegree(0) + " Expected: 1");
        System.out.println("Outdegree of vertex 1: " + degrees1.outdegree(1) + " Expected: 0");
        System.out.println("Outdegree of vertex 2: " + degrees1.outdegree(2) + " Expected: 2");
        System.out.println("Outdegree of vertex 3: " + degrees1.outdegree(3) + " Expected: 1");
        System.out.println("Outdegree of vertex 4: " + degrees1.outdegree(4) + " Expected: 1");
        System.out.println("Outdegree of vertex 5: " + degrees1.outdegree(5) + " Expected: 0");

        System.out.println();

        System.out.print("Sources:  ");
        for (int source : degrees1.sources()) {
            System.out.print(source + " ");
        }
        System.out.print("\nExpected: 5 4 2 0");

        System.out.print("\n\nSinks:    ");
        for (int sink : degrees1.sinks()) {
            System.out.print(sink + " ");
        }
        System.out.print("\nExpected: 5 1");

        System.out.println("\nIs map: " + degrees1.isMap() + " Expected: false");

        System.out.println("\nTest 2\n");

        Digraph digraph2 = new Digraph(6);
        digraph2.addEdge(0, 1);
        digraph2.addEdge(1, 5);
        digraph2.addEdge(2, 3);
        digraph2.addEdge(3, 3);
        digraph2.addEdge(4, 2);
        digraph2.addEdge(5, 0);

        Degrees degrees2 = new Degrees(digraph2);

        System.out.println("Indegree of vertex 0: " + degrees2.indegree(0) + " Expected: 1");
        System.out.println("Indegree of vertex 1: " + degrees2.indegree(1) + " Expected: 1");
        System.out.println("Indegree of vertex 2: " + degrees2.indegree(2) + " Expected: 1");
        System.out.println("Indegree of vertex 3: " + degrees2.indegree(3) + " Expected: 2");
        System.out.println("Indegree of vertex 4: " + degrees2.indegree(4) + " Expected: 0");
        System.out.println("Indegree of vertex 5: " + degrees2.indegree(5) + " Expected: 1");

        System.out.println();

        System.out.println("Outdegree of vertex 0: " + degrees2.outdegree(0) + " Expected: 1");
        System.out.println("Outdegree of vertex 1: " + degrees2.outdegree(1) + " Expected: 1");
        System.out.println("Outdegree of vertex 2: " + degrees2.outdegree(2) + " Expected: 1");
        System.out.println("Outdegree of vertex 3: " + degrees2.outdegree(3) + " Expected: 1");
        System.out.println("Outdegree of vertex 4: " + degrees2.outdegree(4) + " Expected: 1");
        System.out.println("Outdegree of vertex 5: " + degrees2.outdegree(5) + " Expected: 1");

        System.out.println();

        System.out.print("Sources:  ");
        for (int source : degrees2.sources()) {
            System.out.print(source + " ");
        }
        System.out.print("\nExpected: 4");

        System.out.print("\n\nSinks:    ");
        for (int sink : degrees2.sinks()) {
            System.out.print(sink + " ");
        }
        System.out.print("\nExpected: ");

        System.out.println("\nIs map: " + degrees2.isMap() + " Expected: true");
    }

}
