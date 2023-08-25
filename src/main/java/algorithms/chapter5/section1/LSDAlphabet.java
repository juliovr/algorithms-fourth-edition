package algorithms.chapter5.section1;

import algorithms.Common;

public class LSDAlphabet {

    public static void sort(String[] a, int w, Alphabet alphabet) {
        int n = a.length;
        int R = alphabet.radix();
        String[] aux = new String[n];

        for (int d = w-1; d >= 0; d--) {
            int[] count = new int[R+1];

            // Compute frequency counts
            for (int i = 0; i < n; ++i) {
                int digit = alphabet.toIndex(a[i].charAt(d));
                count[digit + 1]++;
            }

            // Transform counts to indices. Every index in count[] has the number of elements behind it
            for (int r = 0; r < R; ++r) {
                count[r+1] += count[r];
            }

            // Distribute
            for (int i = 0; i < n; ++i) {
                int digit = alphabet.toIndex(a[i].charAt(d));
                aux[count[digit]++] = a[i];
            }

            // Copy back
            for (int i = 0; i < n; ++i) {
                a[i] = aux[i];
            }
        }
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

        int w = input[0].length();
        LSDAlphabet.sort(input, w, new Alphabet(Alphabet.ASCII));

        for (String s : input) {
            System.out.println(s);
        }

        System.out.println("Is sorted? = " + Common.isSorted(input));
    }

}
