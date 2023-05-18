package algorithms.chapter2.section4;

public class Exercise3_PQUnorderedArray<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        String max;
        
        Exercise3_PQUnorderedArray<String> pq = new Exercise3_PQUnorderedArray<>(10);
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
    
    public Exercise3_PQUnorderedArray(int maxN) {
        pq = (Key[]) new Comparable[maxN];
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    public void insert(Key v) {
        pq[n++] = v;
    }
    
    public Key delMax() {
        int maxItemIndex = 0;
        
        for (int i = 1; i < n; ++i) {
            if (less(maxItemIndex, i)) {
                maxItemIndex = i;
            }
        }
        
        Key max = pq[maxItemIndex];
        
        exchange(maxItemIndex, --n);
        pq[n] = null; // Avoid loitering
        
        return max;
    }
    
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
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
