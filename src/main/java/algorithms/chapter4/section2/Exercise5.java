package algorithms.chapter4.section2;

public class Exercise5 {

    public static void main(String[] args) {
        Digraph digraph1 = new Digraph(4);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(1, 2);
        digraph1.addEdge(2, 3);
        digraph1.addEdge(3, 0);
        digraph1.addEdge(0, 2);
        digraph1.addEdge(2, 0);
        digraph1.addEdge(2, 0);

        System.out.println(digraph1);
    }

}
