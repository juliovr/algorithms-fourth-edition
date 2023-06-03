package algorithms.chapter3.section5;

import algorithms.chapter3.section4.SeparateChainingHashST;

public class Exercise26_LRUCache {

    public static void main(String[] args) {
        LRUCache<String> cache = new LRUCache<>();
        cache.access("A");
        cache.access("B");
        cache.access("C");

        cache.access("A");

        String removed = cache.remove();
        System.out.println("Removed = " + removed);

        System.out.println(cache);
    }

    private static class LRUCache<Item extends Comparable<Item>> {

        private Node first;
        private Node last;
        private int n;

        private SeparateChainingHashST<Item, Node> st = new SeparateChainingHashST<>();

        private class Node {
            private Item item;
            private Node prev;
            private Node next;

        }

        public void access(Item item) {
            if (st.contains(item)) {
                Node node = st.get(item);

                node.prev.next = node.next;
                if (node.next != null) node.next.prev = node.prev;

                if (node == last) {
                    last = node.prev;
                }

                node.prev = null;
                first.prev = node;
                node.next = first;

                first = node;
            } else {
                Node node = new Node();
                node.item = item;
                node.next = first;
                node.prev = null;
                first = node;

                ++n;
                if (n == 1) {
                    last = first;
                } else {
                    node.next.prev = node;
                }

                st.put(item, node);
            }
        }

        /**
         * Remove the lest recently accessed item (the last one).
         * @return
         */
        public Item remove() {
            if (n == 0) {
                return null;
            }

            Item item = last.item;

            if (n == 1) {
                --n;
                first = null;
                last = null;
            } else {
                last.prev.next = null;
            }

            return item;
        }

        public String toString() {
            if (first == null) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for (Node node = first; node != null; node = node.next) {
                sb.append(node.item);
                sb.append(", ");
            }

            sb.append("\b\b ]");

            return sb.toString();
        }

    }

}
