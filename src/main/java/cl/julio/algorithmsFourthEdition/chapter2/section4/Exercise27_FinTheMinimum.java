package cl.julio.algorithmsFourthEdition.chapter2.section4;

public class Exercise27_FinTheMinimum<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>(5);
        pq.insert("E");
        pq.insert("B");
        pq.insert("C");
        pq.insert("D");
        pq.insert("A");
        System.out.println(pq.min());
    }
    
    
    private static class MaxPQ<Key extends Comparable<Key>> {
    
        private Key[] pq;  // Heap-ordered complete binary tree
        private int n = 0; // Size of the heap, going from 1 to n+1 ([0] leaves unused due to easy calculation of finding children nodes)
        private Key min;
        
        public MaxPQ(int maxN) {
            pq = (Key[]) new Comparable[maxN + 1];
        }
        
        public boolean isEmpty() {
            return n == 0;
        }
        
        public int size() {
            return n;
        }
        
        public Key min() {
            return min;
        }
        
        public void insert(Key v) {
            pq[++n] = v;
            
            if (min == null || less(v, min)) {
                min = v;
            }
            
            swim(n);
        }
        
        public Key delMax() {
            Key max = pq[1];
            
            exchange(1, n--);
            pq[n + 1] = null; // Avoid loitering
            sink(1);
            
            return max;
        }
        
        private boolean less(int i, int j) {
            return pq[i].compareTo(pq[j]) < 0;
        }
        
        private boolean less(Key a, Key b) {
            return a.compareTo(b) < 0;
        }
        
        private void exchange(int i, int j) {
            Key tmp = pq[i];
            pq[i] = pq[j];
            pq[j] = tmp;
        }
        
        private void swim(int k) {
            while (k > 1 && less(k/2, k)) {
                exchange(k/2, k);
                k /= 2;
            }
        }
        
        private void sink(int k) {
            int j;
            while ((j = k*2) <= n) {
                if (j < n && less(j, j+1)) {
                    j++;
                }
                
                if (!less(k, j)) break;
                
                exchange(k, j);
                k = j;
            }
        }
    }
    
}
