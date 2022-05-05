package cl.julio.algorithmsFourthEdition.chapter2.section5;

import java.util.Arrays;

import cl.julio.algorithmsFourthEdition.chapter2.section4.MaxPQ;

public class Exercise8 {

    public static void main(String[] args) {
        Comparable<String>[] a = new Comparable[] {
            "a", "d",
            "d", "b", "a", "a",
            "a", "b",
            "c",
            "e",
        };

        System.out.println("Expected:");
        System.out.println("a: 4 times");
        System.out.println("b: 2 times");
        System.out.println("d: 2 times");
        System.out.println("c: 1 times");
        System.out.println("e: 1 times");

        System.out.println("Result:");
        printFrequencies(a);
    }

    private static void printFrequencies(Comparable[] a) {
        Arrays.sort(a);

        MaxPQ<Item> pq = new MaxPQ<>(a.length);
        Comparable current = a[0];
        int count = 1;
        
        for (int i = 1; i < a.length; ++i) {
            if (current.compareTo(a[i]) == 0) {
                ++count;
            } else {
                Item item = new Item();
                item.value = current;
                item.count = count;
                
                pq.insert(item);
                
                current = a[i];
                count = 1;

                if (i == a.length - 1) {
                    Item lastItem = new Item();
                    lastItem.value = current;
                    lastItem.count = count;
                    pq.insert(lastItem);
                }
            }
        }

        while (pq.size() > 0) {
            Item item = pq.delMax();
            System.out.println(item.value + ": " + item.count + " times");
        }
    }

    private static class Item implements Comparable<Item> {

        public Comparable value;
        public int count;

        @Override
        public int compareTo(Item o) {
            if (this.count < o.count) return -1;
            if (this.count > o.count) return 1;
            return 0;
        }

    }
    
}
