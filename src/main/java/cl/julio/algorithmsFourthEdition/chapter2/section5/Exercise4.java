package cl.julio.algorithmsFourthEdition.chapter2.section5;

import java.util.Arrays;

public class Exercise4 {

    public static void main(String[] args) {
        String[] a = new String[] {
            "aa", "aa",
            "bb", "cc",
            "dd", "dd",
            "ee", "ef",
        };

        String[] b = dedup(a);
        for (int i = 0; i < b.length; ++i) {
            System.out.println(b[i]);
        }
    }

    /**
     * @return a new String[] in sorted order with no duplicates.
     */
    public static String[] dedup(String[] a) {
        Arrays.sort(a);
        int newLength = 0;
        for (int i = 0; i < a.length; ++i) {
            if (i == 0 || !a[i-1].equals(a[i])) {
                ++newLength;
            }
        }

        String[] result = new String[newLength];
        for (int i = 0, j = 0; i < a.length; ++i) {
            if (i == 0 || !a[i-1].equals(a[i])) {
                result[j++] = a[i];
            }
        }

        return result;
    }

}
