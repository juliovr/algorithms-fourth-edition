package algorithms.chapter5.section1;

import algorithms.Common;
import edu.princeton.cs.algs4.Queue;

public class Exercise7 {

    private static class LSDQueueBased {

        private static void sort(String[] a, int w) {
            int n = a.length;
            int R = 256;
            Queue<String>[] count = new Queue[R+1];

            for (int i = 0; i < count.length; ++i) {
                count[i] = new Queue<>();
            }

            for (int d = w-1; d >= 0; d--) {
                // Enqueue words in the queue belonging to their sorted position
                for (int i = 0; i < n; ++i) {
                    count[a[i].charAt(d)].enqueue(a[i]);
                }

                // Copy back. There is no need to keep an aux[] array.
                int aIndex = 0;
                for (int i = 0; i < count.length; ++i) {
                    Queue<String> queue = count[i];
                    while (!queue.isEmpty()) {
                        a[aIndex++] = queue.dequeue();
                    }
                }
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
        LSDQueueBased.sort(input, w);

        for (String s : input) {
            System.out.println(s);
        }

        System.out.println("Is sorted? = " + Common.isSorted(input));
    }

}
