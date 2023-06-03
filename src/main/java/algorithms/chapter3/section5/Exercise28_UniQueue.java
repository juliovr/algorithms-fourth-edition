package algorithms.chapter3.section5;

import edu.princeton.cs.algs4.Queue;

import java.util.HashSet;
import java.util.Set;

public class Exercise28_UniQueue {

    public static void main(String[] args) {
        UniQueue<String> queue = new UniQueue<>();
        queue.enqueue("C");
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("Z");
        queue.enqueue("A");
        queue.enqueue("A");
        queue.enqueue("H");
        queue.enqueue("Z");

        System.out.println(queue);
    }

    private static class UniQueue<Item> extends Queue<Item> {

        private Set<Item> allElements = new HashSet<>();

        @Override
        public void enqueue(Item item) {
            if (allElements.contains(item)) {
                return;
            }

            super.enqueue(item);
            allElements.add(item);
        }

    }

}
