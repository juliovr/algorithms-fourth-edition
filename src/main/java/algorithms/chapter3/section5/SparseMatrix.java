package algorithms.chapter3.section5;

public class SparseMatrix {

    private SparseVector[] rows;

    public SparseMatrix(int[][] source) {
        rows = new SparseVector[source.length];

        for (int row = 0; row < source.length; ++row) {
            rows[row] = new SparseVector();

            for (int col = 0; col < source[row].length; ++col) {
                rows[row].put(col, source[row][col]);
            }
        }
    }

    public SparseMatrix(SparseMatrix that) {
        this.rows = new SparseVector[that.rows.length];

        System.arraycopy(that.rows, 0, this.rows, 0, that.rows.length);
    }

    public SparseMatrix(int n) {
        rows = new SparseVector[n];
        for (int i = 0; i < n; ++i) {
            rows[i] = new SparseVector();
        }
    }

    public SparseMatrix sum(SparseMatrix that) {
        int maxRows = Math.max(this.rows.length, that.rows.length);
        SparseMatrix result = new SparseMatrix(maxRows);

        for (int i = 0; i < result.rows.length; ++i) {
            int maxCol = Math.max(this.rows[i].maxKey(), that.rows[i].maxKey());
            for (int j = 0; j <= maxCol; ++j) {
                double a = this.rows[i].get(j);
                double b = that.rows[i].get(j);
                double c = a + b;
                if (c != 0.0) {
                    result.rows[i].put(j, c);
                }
            }
        }

        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows.length; ++i) {
            sb.append("[" + i + "] -> \n");
            sb.append(rows[i]);
            sb.append("\n");
        }

        return sb.toString();
    }

}
