package algorithms.chapter4.section2;

import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

public class Exercise28_DirectedEulerianCycle {

    public static void main(String[] args) {
        Digraph digraphWithDirectedEulerPath1 = new Digraph(4);
        digraphWithDirectedEulerPath1.addEdge(0, 1);
        digraphWithDirectedEulerPath1.addEdge(1, 2);
        digraphWithDirectedEulerPath1.addEdge(2, 3);
        digraphWithDirectedEulerPath1.addEdge(3, 0);
        digraphWithDirectedEulerPath1.addEdge(3, 2);

        Stack<Integer> eulerCycle1 = findEulerianCycle(digraphWithDirectedEulerPath1);

        System.out.print("Actual:   ");
        if (eulerCycle1 != null) {
            printCycle(eulerCycle1);
        } else {
            System.out.println("There is no directed Eulerian cycle");
        }
        System.out.println("Expected: There is no directed Eulerian cycle");

        System.out.println();

        Digraph digraphWithDirectedEulerCycle1 = new Digraph(4);
        digraphWithDirectedEulerCycle1.addEdge(0, 1);
        digraphWithDirectedEulerCycle1.addEdge(1, 2);
        digraphWithDirectedEulerCycle1.addEdge(2, 3);
        digraphWithDirectedEulerCycle1.addEdge(3, 0);

        Stack<Integer> eulerCycle2 = findEulerianCycle(digraphWithDirectedEulerCycle1);

        System.out.print("Actual:   ");
        if (eulerCycle2 != null) {
            printCycle(eulerCycle2);
        } else {
            System.out.println("There is no directed Eulerian cycle");
        }
        System.out.println("Expected: 0->1 1->2 2->3 3->0");

        System.out.println();

        Digraph digraphWithDirectedEulerCycle2 = new Digraph(6);
        digraphWithDirectedEulerCycle2.addEdge(0, 1);
        digraphWithDirectedEulerCycle2.addEdge(1, 2);
        digraphWithDirectedEulerCycle2.addEdge(1, 3);
        digraphWithDirectedEulerCycle2.addEdge(3, 1);
        digraphWithDirectedEulerCycle2.addEdge(3, 2);
        digraphWithDirectedEulerCycle2.addEdge(2, 4);
        digraphWithDirectedEulerCycle2.addEdge(4, 3);
        digraphWithDirectedEulerCycle2.addEdge(2, 0);

        Stack<Integer> eulerCycle3 = findEulerianCycle(digraphWithDirectedEulerCycle2);

        System.out.print("Actual:   ");
        if (eulerCycle3 != null) {
            printCycle(eulerCycle3);
        } else {
            System.out.println("There is no directed Eulerian cycle");
        }
        System.out.println("Expected: 0->1 1->3 3->2 2->4 4->3 3->1 1->2 2->0");

        System.out.println();

        Digraph digraphWithDirectedEulerPath2 = new Digraph(4);
        digraphWithDirectedEulerPath2.addEdge(0, 1);
        digraphWithDirectedEulerPath2.addEdge(1, 2);
        digraphWithDirectedEulerPath2.addEdge(2, 3);
        digraphWithDirectedEulerPath2.addEdge(3, 0);
        digraphWithDirectedEulerPath2.addEdge(3, 1);

        Stack<Integer> eulerCycle4 = findEulerianCycle(digraphWithDirectedEulerPath2);

        System.out.print("Actual:   ");
        if (eulerCycle4 != null) {
            printCycle(eulerCycle4);
        } else {
            System.out.println("There is no directed Eulerian cycle");
        }
        System.out.println("Expected: There is no directed Eulerian cycle");

        System.out.println();

        Digraph digraphWithDirectedEulerPath3 = new Digraph(4);
        digraphWithDirectedEulerPath3.addEdge(0, 1);
        digraphWithDirectedEulerPath3.addEdge(1, 2);
        digraphWithDirectedEulerPath3.addEdge(2, 3);

        Stack<Integer> eulerCycle5 = findEulerianCycle(digraphWithDirectedEulerPath3);

        System.out.print("Actual:   ");
        if (eulerCycle5 != null) {
            printCycle(eulerCycle5);
        } else {
            System.out.println("There is no directed Eulerian cycle");
        }
        System.out.println("Expected: There is no directed Eulerian cycle");
    }

    private static Stack<Integer> findEulerianCycle(Digraph digraph) {
        Degrees degrees = new Degrees(digraph);

        for (int v = 0; v < digraph.V(); ++v) {
            if (degrees.indegree(v) != degrees.outdegree(v)) {
                return null;
            }
        }

        Iterator<Integer>[] iterators = new Iterator[digraph.V()];
        for (int v = 0; v < iterators.length; ++v) {
            iterators[v] = digraph.adj(v).iterator();
        }

        Stack<Integer> eulerianStack = new Stack<>();

        dfs(iterators, 0, eulerianStack);

        if (eulerianStack.size() - 1 != digraph.E()) {
            return null;
        }

        return eulerianStack;
    }

    private static void dfs(Iterator<Integer>[] iterators, int v, Stack<Integer> eulerianStack) {
        while (iterators[v].hasNext()) {
            int w = iterators[v].next();
            dfs(iterators, w, eulerianStack);
        }

        eulerianStack.push(v);
    }

    public static void printCycle(Stack<Integer> cycle) {
        StringBuilder sb = new StringBuilder();
        int v = 0;
        if (!cycle.isEmpty()) {
            v = cycle.pop();
        }
        while (!cycle.isEmpty()) {
            int w = cycle.pop();
            sb.append(v + "->" + w + " ");
            v = w;
        }

        System.out.println(sb);
    }

}
