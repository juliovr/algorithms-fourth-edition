package cl.julio.algorithmsFourthEdition.chapter2.section4;

public class Exercise3_PQOrderedLinkedList<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        String max;
        
        Exercise3_PQOrderedLinkedList<String> pq = new Exercise3_PQOrderedLinkedList<>();
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
        public Node<Key> prev;
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    public void insert(Key v) {
        Node<Key> newNode = new Node<>();
        newNode.value = v;
        
        if (isEmpty()) {
            first = newNode;
            last = first;
            ++n;
            
            return;
        }
        
        Node<Key> node;
        for (node = first; node != null && less(node.value, v); node = node.next) {
            
        }
        
        if (node == first) {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        } else if (node == null) { // last element
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        } else {
            Node<Key> old = node.prev;
            newNode.next = old.next;
            newNode.prev = old;
            old.next.prev = newNode;
            old.next = newNode;
        }
        
        ++n;
    }
    
    public Key delMax() {
        Node<Key> max = last;
        
        last = last.prev;
        last.next = null;
        --n;
        
        if (n == 1) {
            first = last;
        }
        
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
