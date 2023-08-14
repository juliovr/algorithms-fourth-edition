package algorithms.chapter4.section4;

public class Exercise33_ShortestPathInAGrid {

    public static void main(String[] args) {
        double[][] matrix1 = {
                {0, 1},
                {3, 1}
        };

        System.out.print("Path:     ");
        Iterable<DirectedEdge> shortestPath1 = shortestPathInGrid(matrix1);
        for (DirectedEdge edge : shortestPath1) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }
        System.out.println("\nExpected: 0->1 1->3");

        double[][] matrix2 = {
                {0, 2, 1},
                {1, 3, 2},
                {4, 2, 5}
        };

        System.out.print("\nPath:     ");
        Iterable<DirectedEdge> shortestPath2 = shortestPathInGrid(matrix2);
        for (DirectedEdge edge : shortestPath2) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }
        System.out.println("\nExpected: 0->1 1->2 2->5 5->8");

        double[][] matrix3 = {
                {0,   4, 10, 10, 10},
                {1,   8,  1,  1,  1},
                {1,   8,  1, 10,  1},
                {1,   1,  1, 10,  1},
                {10, 10, 10, 10,  2}
        };

        System.out.print("\nPath:     ");
        Iterable<DirectedEdge> shortestPath3 = shortestPathInGrid(matrix3);
        for (DirectedEdge edge : shortestPath3) {
            System.out.print(edge.from() + "->" + edge.to() + " ");
        }
        System.out.println("\nExpected: 0->5 5->10 10->15 15->16 16->17 17->12 12->7 7->8 8->9 9->14 14->19 19->24");
    }

    private static Iterable<DirectedEdge> shortestPathInGrid(double[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        int[] rowPaddings = {  0, -1,  1,  0 };
        int[] colPaddings = { -1,  0,  0,  1 };

        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(rowCount * colCount);

        for (int row = 0; row < rowCount; ++row) {
            for (int col = 0; col < colCount; ++col) {
                int v = getCellIndex(rowCount, row, col);

                for (int i = 0; i < rowPaddings.length; ++i) {
                    int rowPadding = rowPaddings[i];
                    int colPadding = colPaddings[i];

                    int r = row + rowPadding;
                    int c = col + colPadding;

                    if (isValidCell(rowCount, colCount, r, c)) {
                        int w = getCellIndex(rowCount, r, c);
                        double weight = matrix[row][col] + matrix[r][c];
                        graph.addEdge(new DirectedEdge(v, w, weight));
                    }
                }
            }
        }

        DijkstraSP sp = new DijkstraSP(graph, 0);
        int lastV = rowCount - 1;
        int lastW = colCount - 1;
        return sp.pathTo(getCellIndex(rowCount, lastV, lastW));
    }

    private static int getCellIndex(int rowCount, int row, int col) {
        return rowCount*row + col;
    }

    private static boolean isValidCell(int rowCount, int colCount, int row, int col) {
        return ((row >= 0 && row < rowCount) &&
                (col >= 0 && col < colCount));
    }
    
}
