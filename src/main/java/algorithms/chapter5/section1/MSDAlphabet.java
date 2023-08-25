package algorithms.chapter5.section1;

import algorithms.Common;

public class MSDAlphabet {

    private static final int CUTOFF = 15;

    private static int charAt(String s, int d, Alphabet alphabet) {
        if (d < s.length()) {
            return alphabet.toIndex(s.charAt(d));
        } else {
            return -1;
        }
    }

    public static void sort(String[] a, Alphabet alphabet) {
        int n = a.length;
        String[] aux = new String[n];
        sort(a, 0, n-1, 0, aux, alphabet);
    }

    private static void sort(String[] a, int lo, int hi, int d, String[] aux, Alphabet alphabet) {
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        int R = alphabet.radix();

        // Compute frequency counts
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; ++i) {
            count[charAt(a[i], d, alphabet) + 2]++;
        }

        // Transform counts to indices
        for (int r = 0; r < R+1; ++r) {
            count[r+1] += count[r];
        }

        // Distribute
        for (int  i = lo; i <= hi; ++i) {
            aux[count[charAt(a[i], d, alphabet) + 1]++] = a[i];
        }

        // Copy back
        for (int i = lo; i <= hi; ++i) {
            a[i] = aux[i - lo];
        }

        //Recursively sort for each character value (excluding -1)
        for (int r = 0; r < R; ++r) {
            sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux, alphabet);
        }
    }

    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; ++i) {
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--) {
                exch(a, j, j-1);
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }

        return v.length() < w.length();
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        String[] input = new String[] {
                "4PGC938",
                "2IYE230",
                "3CIO720",
                "1ICK750",
                "1OHV845",
                "4JZY524",
                "1ICK750",
                "3CIO720",
                "1OHV845",
                "1OHV845",
                "2RLA629",
                "2RLA629",
                "3ATW723"
        };

        MSDAlphabet.sort(input, new Alphabet(Alphabet.ASCII));

        for (String s : input) {
            System.out.println(s);
        }

        System.out.println("Is sorted? = " + Common.isSorted(input));
    }

}
