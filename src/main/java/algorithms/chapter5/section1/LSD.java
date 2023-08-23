package algorithms.chapter5.section1;

import algorithms.Common;

public class LSD {

    public static void sort(String[] a, int w) {
        int n = a.length;
        int R = 256;
        String[] aux = new String[n];

        for (int d = w-1; d >= 0; d--) {
            int[] count = new int[R+1];

            // Compute frequency counts
            for (int i = 0; i < n; ++i) {
                count[a[i].charAt(d) + 1]++;
            }

            // Transform counts to indices. Every index in count[] has the number of elements behind it
            for (int r = 0; r < R; ++r) {
                count[r+1] += count[r];
            }

            // Distribute
            for (int i = 0; i < n; ++i) {
                aux[count[a[i].charAt(d)]++] = a[i];
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
        LSD.sort(input, w);

        for (String s : input) {
            System.out.println(s);
        }

        System.out.println("Is sorted? = " + Common.isSorted(input));
    }

}
