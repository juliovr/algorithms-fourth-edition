package algorithms.chapter2.section4;

public class Exercise25_ComputationalNumberTheory {

    public static void main(String[] args) {
        int n = 3;
        MinPQ<Item> pq = new MinPQ<>(n);
        
        for (int i = 0; i < n; ++i) {
            pq.insert(new Item(cube(i), i, 0));
        }
        
        while (!pq.isEmpty()) {
            Item min = pq.delMin();
            System.out.println(min);
            
            if (min.j < n-1) {
                min.j++;
                min.sum = cube(min.i) + cube(min.j);
                pq.insert(min);
            }
        }
        
    }
    
    private static int cube(int a) {
        return a*a*a;
    }
    
    private static class Item implements Comparable<Item> {
        
        public int sum;
        public int i;
        public int j;
        
        public Item(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Item o) {
            if (this.sum < o.sum) return -1;
            if (this.sum > o.sum) return 1;
            return 0;
        }

        @Override
        public String toString() {
            return "Item [sum=" + sum + ", i=" + i + ", j=" + j + "]";
        }
        
    }
    
    private static class MinPQ<Key extends Comparable<Key>> {
        
        private Key[] pq;  // Heap-ordered complete binary tree
        private int n = 0; // Size of the heap, going from 1 to n+1 ([0] leaves unused due to easy calculation of finding children nodes)
        
        public MinPQ(int maxN) {
            pq = (Key[]) new Comparable[maxN + 1];
        }
        
        public void print() {
            for (int i = 1; i <= n; ++i) {
                System.out.println(pq[i]);
            }
            System.out.println();
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
    
}
