package algorithms.chapter5.section1;

import algorithms.Common;
import edu.princeton.cs.algs4.Queue;

public class Exercise11_QueueSort {

    public static void main(String[] args) {
        String[] a = new String[] {
                "now",
                "is",
                "the",
                "time",
                "for",
                "all",
                "good",
                "people",
                "to",
                "come",
                "zzbb",
                "zzaa",
                "to",
                "the",
                "aid",
                "of"
        };

        MSDQueueSort.sort(a);
        System.out.println("is sorted = " + Common.isSorted(a));


        String[] array1 = {"Rene", "Argento", "Arg", "Alg", "Algorithms", "LSD", "MSD", "3WayStringQuickSort",
                "Dijkstra", "Floyd", "Warshall", "Johnson", "Sedgewick", "Wayne", "Bellman", "Ford", "BFS", "DFS"};
        MSDQueueSort.sort(array1);
        System.out.println("is sorted = " + Common.isSorted(array1));

        String[] array2 = {"QuickSort", "MergeSort", "ShellSort", "InsertionSort", "BubbleSort", "SelectionSort"};
        MSDQueueSort.sort(array2);
        System.out.println("is sorted = " + Common.isSorted(array2));
    }

    private static class MSDQueueSort {
        private static int R = 256;
        private static final int CUTOFF = 15;

        private static int charAt(String s, int d) {
            if (d < s.length()) {
                return s.charAt(d);
            } else {
                return 0;
            }
        }

        public static void sort(String[] a) {
            int n = a.length;
            sort(a, 0, n-1, 0);
        }

        private static void sort(String[] a, int lo, int hi, int d) {
            if (hi <= lo + CUTOFF) {
                insertion(a, lo, hi, d);
                return;
            }

            // Create the bins
            Queue<String>[] buckets = new Queue[R+1];
            for (int i = 0; i < buckets.length; ++i) {
                buckets[i] = new Queue<>();
            }

            // Distribute in appropriate buckets
            for (int i = lo; i <= hi; ++i) {
                buckets[charAt(a[i], d)].enqueue(a[i]);
            }

            // Sort each sublist
            for (int i = 0; i < buckets.length; ++i) {
                sortBucket(buckets[i]);
            }

            // Copy back
            int aIndex = 0;
            for (int i = 0; i < buckets.length; ++i) {
                while (!buckets[i].isEmpty()) {
                    a[aIndex++] = buckets[i].dequeue();
                }
            }
        }

        private static void sortBucket(Queue<String> queue) {
            if (queue.isEmpty()) {
                return;
            }

            String[] a = new String[queue.size()];
            int i = 0;
            while (!queue.isEmpty()) {
                a[i++] = queue.dequeue();
            }

            insertion(a, 0, a.length-1, 0);

            for (String s : a) {
                queue.enqueue(s);
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
    }

}
