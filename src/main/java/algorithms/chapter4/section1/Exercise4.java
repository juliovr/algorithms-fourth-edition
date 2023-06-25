package algorithms.chapter4.section1;

public class Exercise4 {

    public static void main(String[] args) {
        Graph graph1 = new Graph(5);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(1, 4);
        graph1.addEdge(2, 0);
        graph1.addEdge(4, 3);

        System.out.println("Expected = true, Actual = " + graph1.hasEdge(0, 2));
        System.out.println("Expected = true, Actual = " + graph1.hasEdge(4, 1));
        System.out.println("Expected = true, Actual = " + graph1.hasEdge(3, 4));
        System.out.println("Expected = false, Actual = " + graph1.hasEdge(0, 4));
        System.out.println("Expected = false, Actual = " + graph1.hasEdge(2, 3));
        System.out.println("Expected = false, Actual = " + graph1.hasEdge(2, 1));
        System.out.println("end");
    }

}
