package algorithms.chapter5.section1;

import edu.princeton.cs.algs4.DoubleLinkedList;

public class Exercise16_LinkedListSort {

    public static void main(String[] args) {
        DoubleLinkedList<String> input = new DoubleLinkedList<>();
        input.insert("4PGC938");
        input.insert("2IYE230");
        input.insert("3CIO720");
        input.insert("1ICK750");
        input.insert("1OHV845");
        input.insert("4JZY524");
        input.insert("1ICK750");
        input.insert("3CIO720");
        input.insert("1OHV845");
        input.insert("1OHV845");
        input.insert("2RLA629");
        input.insert("2RLA629");
        input.insert("3ATW723");

        String[] expected = new String[input.size()];
        int i = 0;
        for (String s : input) {
            expected[i++] = s;
        }
        Quick3string.sort(expected);

        System.out.println("Expected:");
        for (String s : expected) {
            System.out.println(s);
        }
        System.out.println(expected);


        sort(input);
        System.out.println("Actual:");
        for (String s : input) {
            System.out.println(s);
        }

        System.out.print("is sorted? = ");
        if (input.size() != expected.length) {
            System.out.println("false");
        } else {
            int j = 0;
            boolean equals = false;
            for (String s : input) {
                String expect = expected[j++];
                if (s.equals(expect)) {
                    equals = true;
                }
            }

            System.out.println(equals);
        }
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    public static void sort(DoubleLinkedList<String> a) {
        if (a.isEmpty()) return;

        sort(a.first(), a.last(), 0, a.size()-1, 0);
    }

    private static void sort(DoubleLinkedList.Node<String> low, DoubleLinkedList.Node<String> high, int lo, int hi, int d) {
        if (hi <= lo) return;

        int lt = lo;
        int gt = hi;

        DoubleLinkedList.Node<String> nodeLt = low;
        DoubleLinkedList.Node<String> nodeLo = low;
        DoubleLinkedList.Node<String> nodeGt = high;
        int v = charAt(nodeLo.value, d);
        int i = lo + 1;
        DoubleLinkedList.Node<String> nodeI = nodeLo.next;
        while (i <= gt) {
            int t = charAt(nodeI.value, d);
            if (t < v) {
                exch(nodeLt, nodeI);
                nodeLt = nodeLt.next;
                nodeI = nodeI.next;
                lt++;
                i++;
            } else if (t > v) {
                exch(nodeI, nodeGt);
                nodeGt = nodeGt.prev;
                gt--;
            } else {
                i++;
                nodeI = nodeI.next;
            }
        }

        sort(nodeLo, nodeLt.prev, lo, lt-1, d);
        if (v >= 0) {
            sort(nodeLt, nodeGt, lt, gt, d+1);
        }
        sort(nodeGt.next, high, gt+1, hi, d);
    }

    private static void exch(DoubleLinkedList.Node<String> a, DoubleLinkedList.Node<String> b) {
        String temp = a.value;
        a.value = b.value;
        b.value = temp;
    }

}
