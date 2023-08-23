package algorithms.chapter5.section1;

import algorithms.Common;

public class Exercise9 {

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

        LSDVariableLength.sort(a);
        System.out.println("is sorted = " + Common.isSorted(a));


        String[] array1 = {"Rene", "Argento", "Arg", "Alg", "Algorithms", "LSD", "MSD", "3WayStringQuickSort",
                "Dijkstra", "Floyd", "Warshall", "Johnson", "Sedgewick", "Wayne", "Bellman", "Ford", "BFS", "DFS"};
        LSDVariableLength.sort(array1);
        System.out.println("is sorted = " + Common.isSorted(array1));

        String[] array2 = {"QuickSort", "MergeSort", "ShellSort", "InsertionSort", "BubbleSort", "SelectionSort"};
        LSDVariableLength.sort(array2);
        System.out.println("is sorted = " + Common.isSorted(array2));
    }

}
