package cl.julio.algorithmsFourthEdition.chapter2.section4;

public class MinPQ<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        MinPQ<String> pq = new MinPQ<>(5);
        pq.insert("E");
        pq.insert("F");
        pq.insert("H");
        pq.insert("A");
        pq.insert("B");
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
    }
    
    private Key[] pq;  // Heap-ordered complete binary tree
    private int n = 0; // Size of the heap, going from 1 to n+1 ([0] leaves unused due to easy calculation of finding children nodes)
    
    public MinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    public void insert(Key v) {
        pq[++n] = v;
        swim(n);
    }

    public Key findMin() {
        return pq[1];
    }
    
    public Key delMin() {
        Key min = pq[1];
        
        exchange(1, n--);
        pq[n + 1] = null; // Avoid loitering
        sink(1);
        
        return min;
    }
    
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
    
    private void exchange(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }
    
    private void swim(int k) {
        while (k > 1 && less(k, k/2)) {
            exchange(k, k/2);
            k /= 2;
        }
    }
    
    private void sink(int k) {
        int j;
        while ((j = k*2) <= n) {
            if (j < n && less(j+1, j)) {
                j++;
            }
            
            if (!less(j, k)) break;
            
            exchange(j, k);
            k = j;
        }
    }
    
}
