package algorithms.chapter3.section5;

public class Exercise23 {

    public static void main(String[] args) {
        int[][] a = new int[][] {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        int[][] b = new int[][] {
                { 10, 10, 10 },
                { 10, -5, 10 },
                { 10, 10, 10 }
        };

        SparseMatrix matrixA = new SparseMatrix(a);
        SparseMatrix matrixB = new SparseMatrix(b);

        System.out.println("MatrixA =");
        System.out.println(matrixA);
        System.out.println("MatrixB = ");
        System.out.println(matrixB);

        System.out.println("MatrixA + MatrixB = ");
        SparseMatrix matrixC = matrixA.sum(matrixB);
        System.out.println(matrixC);
    }

}
