package algorithms.chapter3.section5;

import edu.princeton.cs.algs4.Queue;

public class SequentialSearchSET<Key> {

    private Node first;
    private int size;

    private class Node {
        private Key key;
        private Node next;

        public Node(Key key, Node next) {
            this.key = key;
            this.next = next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Key get(Key key) {
        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                return node.key;
            }
        }

        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key) {
        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                // Search hit: nothing to do
                return;
            }
        }

        first = new Node(key, first);
        ++size;
    }

    public void delete(Key key) {
        if (isEmpty()) return;

        if (key.equals(first.key)) {
            first = first.next;
            --size;
            return;
        }

        for (Node node = first; node != null; node = node.next) {
            if (node.next != null) {
                if (node.next.key.equals(key)) {
                    node.next = node.next.next;
                    --size;
                }
            }
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node node = first; node != null; node = node.next) {
            queue.enqueue(node.key);
        }

        return queue;
    }

}
