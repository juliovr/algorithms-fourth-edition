package algorithms.chapter4.section1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class MazeGenerator {

    private final int n;
    private boolean[][] north;
    private boolean[][] south;
    private boolean[][] east;
    private boolean[][] west;

    private boolean[][] visited;


    public MazeGenerator(int n) {
        this.n = n;
        north = new boolean[n + 2][n + 2];
        south = new boolean[n + 2][n + 2];
        east = new boolean[n + 2][n + 2];
        west = new boolean[n + 2][n + 2];

        visited = new boolean[n + 2][n + 2];

        // Mark border as visited
        for (int col = 0; col < (n + 2); ++col) {
            visited[0][col] = true;
            visited[n+1][col] = true;
        }
        for (int row = 0; row < (n + 2); ++row) {
            visited[row][0] = true;
            visited[row][n+1] = true;
        }

        for (int row = 0; row < (n + 2); ++row) {
            for (int col = 0; col < (n + 2); ++col) {
                north[row][col] = true;
                south[row][col] = true;
                east[row][col] = true;
                west[row][col] = true;
            }
        }

        generate(1, 1);
    }

    private static final int NORTH = 0;
    private static final int SOUTH = 1;
    private static final int EAST = 2;
    private static final int WEST = 3;

    private void generate(int x, int y) {
        visited[y][x] = true;

        while (!visited[y+1][x] || !visited[y-1][x] || !visited[y][x-1] || !visited[y][x+1]) {
            while (true) {
                int randomNeighborCell = StdRandom.uniform(4);

                if (randomNeighborCell == NORTH && !visited[y+1][x]) {
                    north[y][x] = south[y+1][x] = false;
                    generate(x, y+1);
                    break;
                } else if (randomNeighborCell == SOUTH && !visited[y-1][x]) {
                    south[y][x] = north[y-1][x] = false;
                    generate(x, y-1);
                    break;
                } else if (randomNeighborCell == EAST && !visited[y][x+1]) {
                    east[y][x] = west[y][x+1] = false;
                    generate(x+1, y);
                    break;
                } else if (randomNeighborCell == WEST && !visited[y][x-1]) {
                    west[y][x] = east[y][x-1] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }

    public void draw() {
        StdDraw.setCanvasSize(2000, 2000);
        StdDraw.setPenColor(StdDraw.BLACK);

        float lineSize = 0.85f / (float)n;
        float size = lineSize / 2;

        for (int y = 1; y <= n; ++y) {
            for (int x = 1; x <= n; ++x) {
                if (north[y][x]) StdDraw.line(lineSize*x - size, lineSize*y + size, lineSize*x + size, lineSize*y + size);
                if (south[y][x]) StdDraw.line(lineSize*x - size, lineSize*y - size, lineSize*x + size, lineSize*y - size);
                if (east[y][x]) StdDraw.line(lineSize*x + size, lineSize*y + size, lineSize*x + size, lineSize*y - size);
                if (west[y][x]) StdDraw.line(lineSize*x - size, lineSize*y + size, lineSize*x - size, lineSize*y - size);
            }
        }

        StdDraw.show();
    }

    public static void main(String[] args) {
        int n = 10;

        MazeGenerator mazeGenerator = new MazeGenerator(n);
        mazeGenerator.draw();
    }

}
