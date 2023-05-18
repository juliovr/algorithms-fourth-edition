package algorithms.chapter2.section4;

public class Exercise3_PQOrderedArray<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        String max;
        
        Exercise3_PQOrderedArray<String> pq = new Exercise3_PQOrderedArray<>(10);
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
    
    private Key[] pq;
    private int n = 0;
    
    public Exercise3_PQOrderedArray(int maxN) {
        pq = (Key[]) new Comparable[maxN];
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    public void insert(Key v) {
        int i = 0;
        while (i < n && less(pq[i], v)) ++i;

        int j = n-1;
        while (j >= i) {
            pq[j+1] = pq[j];
            --j;
        }
        
        pq[i] = v;
        ++n;
    }
    
    public Key delMax() {
        Key max = pq[--n];
        pq[n] = null; // Avoid loitering
        
        return max;
    }
    
    private boolean less(Key a, Key b) {
        return a.compareTo(b) < 0;
    }
    
    private void exchange(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }
    
    private void print() {
        for (int i = 0; i < size(); i++) {
            System.out.print(pq[i] + " ");
        }
        System.out.println();
    }
    
}
