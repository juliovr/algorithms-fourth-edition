package algorithms.chapter4.section1;

import edu.princeton.cs.algs4.Stack;

public class Exercise39_ImageProcessing {

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1, 3},
                {1, 1, 2, 2},
                {4, 4, 5, 5}
        };

        floodFill(image);
    }

    private static int imageLength;
    private static void floodFill(int[][] image) {
        imageLength = image.length; // Both row and col should be same length
        Graph graph = new Graph(imageLength*imageLength);

        for (int row = 0; row < imageLength; ++row) {
            for (int col = 0; col < imageLength; ++col) {
                if ((col - 1) > 0           && image[row][col] == image[row][col - 1]) graph.addEdge(pixelId(row, col), pixelId(row, col - 1));  //left
                if ((col + 1) < imageLength && image[row][col] == image[row][col + 1]) graph.addEdge(pixelId(row, col), pixelId(row, col + 1));  // right
                if ((row - 1) > 0           && image[row][col] == image[row - 1][col]) graph.addEdge(pixelId(row, col), pixelId(row - 1, col)); // top
                if ((row + 1) < imageLength && image[row][col] == image[row + 1][col]) graph.addEdge(pixelId(row, col), pixelId(row + 1, col)); // bottom

            }
        }

        boolean[] marked = new boolean[graph.V()];
        int[] componentId = new int[graph.V()];
        dfs(graph, marked, componentId);

        System.out.println("Number of components: " + currentComponentId + " Expected: 4");
    }

    private static int currentComponentId = 0;

    private static void dfs(Graph graph, boolean[] marked, int[] componentId) {
        for (int s = 0; s < graph.V(); ++s) {
            if (!marked[s]) {
                ++currentComponentId;
                dfs(graph, marked, componentId, s);
            }
        }
    }

    private static void dfs(Graph graph, boolean[] marked, int[] componentId, int s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (marked[v]) continue;

            marked[v] = true;
            componentId[v] = currentComponentId;
            Stack<Integer> adj = new Stack<>();
            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    adj.push(w);
                }
            }

            while (!adj.isEmpty()) {
                stack.push(adj.pop());
            }
        }
    }

    private static int pixelId(int row, int col) {
        return row*imageLength + col;
    }

}
