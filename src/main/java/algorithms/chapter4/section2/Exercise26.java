package algorithms.chapter4.section2;

public class Exercise26 {

    public static void main(String[] args) {
        Digraph digraph = new Digraph(10);
        digraph.addEdge(3, 7);
        digraph.addEdge(1, 4);
        digraph.addEdge(7, 8);
        digraph.addEdge(0, 5);
        digraph.addEdge(5, 2);
        digraph.addEdge(3, 8);
        digraph.addEdge(2, 9);
        digraph.addEdge(0, 6);
        digraph.addEdge(4, 9);
        digraph.addEdge(2, 6);
        digraph.addEdge(6, 4);

        TransitiveClosure transitiveClosure = new TransitiveClosure(digraph);
        System.out.println(transitiveClosure);
    }

}
