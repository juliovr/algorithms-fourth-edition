package cl.julio.algorithmsFourthEdition.chapter2.section3;

public class Exercise15_NutsAndBolts {

    public static void main(String[] args) {
        int n = 100;
        int[] nuts = new int[n];
        int[] bolts = new int[n];

        for (int i = 0; i < n; i++) {
            nuts[i] = i;
            bolts[i] = i;
        }
        
        System.out.println("original");
        System.out.print("nuts  = ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d ", nuts[i]);
        }
        System.out.println();

        System.out.print("bolts = ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d ", bolts[i]);
        }
        System.out.println();
        System.out.println();
        

        solve(nuts, bolts);

        System.out.print("index = ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
        System.out.println("------------------------------------------------");

        System.out.print("nuts  = ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d ", nuts[i]);
        }
        System.out.println();

        System.out.print("bolts = ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d ", bolts[i]);
        }
        System.out.println();

        System.out.println("Is correct? = " + isCorrect(nuts, bolts));
    }

    private static boolean isCorrect(int[] nuts, int[] bolts) {
        for (int i = 0; i < bolts.length; i++) {
            if (nuts[i] != bolts[i]) {
//            if (nuts[i].compareTo(bolts[i]) != 0) {
                return false;
            }
        }

        return true;
    }

    private static void solve(int[] nuts, int[] bolts) {
        // O(n^2)
//        for (int i = 0; i < nuts.length; i++) {
//            for (int j = 0; j < bolts.length; j++) {
//                if (nuts[i].compareTo(bolts[j]) == 0) {
//                    int temp = bolts[j];
//                    bolts[j] = bolts[i];
//                    bolts[i] = temp;
//                }
//            }
//        }

        int lo = 0;
        int hi = nuts.length - 1;

        sort(nuts, bolts, lo, hi);
    }

    private static void sort(int[] nuts, int[] bolts, int lo, int hi) {
        if (hi <= lo)
            return;
        
        int pivot;
        
        pivot = bolts[lo];
        int j = partition(nuts, pivot, lo, hi);
        
        pivot = nuts[j];
        partition(bolts, pivot, lo, hi);

        sort(nuts, bolts, lo, j - 1); // Sort left
        sort(nuts, bolts, j + 1, hi); // Sort right
    }
    
    /*
     * a[hi] will store the matched value with pivot
     */
    private static int partition(int[] a, int pivot, int lo, int hi) {
        int i = lo - 1;
        int j = hi;
        
        while (true) {
            while (a[++i] <= pivot) {
                if (a[i] == pivot) {
                    exchange(a, i--, hi);
                }
                
                if (i == hi) break; // Scan from left to right. Stops when encounter an item greater than 'v'.
            }
            while (pivot <= a[--j]) {
                if (a[j] == pivot) {
                    exchange(a, j++, hi);
                }
                
                if (j == lo) break; // Scan from right to left. Stops when encounter an item less than 'v'.
            }

            if (i >= j) break; // Pointers cross to the other side
            
            exchange(a, i, j); // Leave item to the left (less than 'v') and to the right (greater than 'v').
        }
        
         exchange(a, j, hi); // Place pivot to the final position.
        
        return j;
    }
    
    private static void exchange(int[] a, int i, int j) {
        if (i == j) return;
        
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
