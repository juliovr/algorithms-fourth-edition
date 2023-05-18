package algorithms.chapter2.section5;

public class Exercise19_KendallTau {

    public static void main(String[] args) {
        int[] rank1 = new int[] { 0, 3, 1, 6, 2, 5, 4 };
        int[] rank2 = new int[] { 1, 0, 3, 6, 4, 2, 5 };

        calculateKendallTau(rank1, rank2);
    }

    private static void calculateKendallTau(int[] rank1, int[] rank2) {
        print(rank1);
        print(rank2);

        int cont = 1;
        while (cont < rank1.length) {
            int n1 = rank1[cont - 1];

            for (int i = cont; i < rank1.length; ++i) {
                int n2 = rank1[i];

                int indexN1 = -1;
                int indexN2 = -1;
                for (int j = 0; j < rank2.length; ++j) {
                    if (rank2[j] == n1) indexN1 = j;
                    if (rank2[j] == n2) indexN2 = j;
                }

                if (indexN1 > indexN2) {
                    System.out.println(n1 + "-" + n2);
                }
            }
            ++cont;
        }
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
}
