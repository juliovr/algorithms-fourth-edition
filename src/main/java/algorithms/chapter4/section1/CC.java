package algorithms.chapter4.section1;

import edu.princeton.cs.algs4.Stack;

public class CC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        for (int s = 0; s < graph.V(); ++s) {
            if (!marked[s]) {
//                System.out.println(s);
                dfs(graph, s);
                count++;
            }
        }
    }

//    private void dfs(Graph graph, int v) {
//        marked[v] = true;
//        id[v] = count;
//        for (int w : graph.adj(v)) {
//            if (!marked[w]) {
//                System.out.println(w);
//                dfs(graph, w);
//            }
//        }
//    }

    private void dfs(Graph graph, int v) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (marked[vertex]) {
                continue;
            }

            marked[vertex] = true;
            id[vertex] = count;

            // Using adj as a temp stack to preserve the original order as in recursive calls
            Stack<Integer> adj = new Stack<>();
            for (int w : graph.adj(vertex)) {
                if (!marked[w]) {
                    adj.push(w);
                }
            }

            while (!adj.isEmpty()) {
                stack.push(adj.pop());
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(13);
        graph.addEdge(0, 5);
        graph.addEdge(4, 3);
        graph.addEdge(0, 1);
        graph.addEdge(9, 12);
        graph.addEdge(6, 4);
        graph.addEdge(5, 4);
        graph.addEdge(0, 2);
        graph.addEdge(11, 12);
        graph.addEdge(9, 10);
        graph.addEdge(0, 6);
        graph.addEdge(7, 8);
        graph.addEdge(9, 11);
        graph.addEdge(5, 3);

        CC cc = new CC(graph);
        System.out.println(cc.count());

        System.out.println(graph);

        for (int a : cc.id) {
            System.out.print(a + " ");
        }
    }

}
