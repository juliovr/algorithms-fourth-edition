package algorithms.chapter2.section4;

public class Exercise3_PQUnorderedLinkedList<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        String max;
        
        Exercise3_PQUnorderedLinkedList<String> pq = new Exercise3_PQUnorderedLinkedList<>();
        pq.insert("P");
        pq.insert("Q");
        pq.insert("E");
        
        pq.print();
        
        max = pq.delMax();
        System.out.println("Max = " + max);

        pq.print();
        
        pq.insert("X");
        pq.insert("A");
        pq.insert("M");
        
        pq.print();
        
        max = pq.delMax();
        System.out.println("Max = " + max);

        pq.print();
        
        pq.insert("P");
        pq.insert("L");
        pq.insert("E");
        
        pq.print();
        
        max = pq.delMax();
        System.out.println("Max = " + max);
        
        pq.print();
    }
    
    private Node<Key> first;
    private Node<Key> last;
    private int n = 0;
    
    private static class Node<Key> {
        public Key value;
        public Node<Key> next;
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    public void insert(Key v) {
        Node<Key> oldLast = last;
        
        last = new Node<>();
        last.value = v;
        
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        
        ++n;
    }
    
    public Key delMax() {
        Node<Key> max = first;
        
        for (Node<Key> node = first; node != null; node = node.next) {
            if (less(max.value, node.value)) {
                max = node;
            }
        }
        
        
        Node<Key> previous = null;
        for (Node<Key> node = first; !equals(node.value, max.value); node = node.next) {
            previous = node;
        }
        
        --n;
        
        // max == first
        if (previous == null) {
            first = first.next;
            return max.value;
        }
        
        previous.next = max.next;
        max.next = null;
        
        return max.value;
    }
    
    private boolean less(Key a, Key b) {
        return a.compareTo(b) < 0;
    }
    
    private boolean equals(Key a, Key b) {
        return a.compareTo(b) == 0;
    }
    
    private void print() {
        for (Node<Key> node = first; node != null; node = node.next) {
            System.out.print(node.value + " ");
        }
        System.out.println();
    }
    
}
