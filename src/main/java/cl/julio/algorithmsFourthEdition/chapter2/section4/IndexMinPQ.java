package cl.julio.algorithmsFourthEdition.chapter2.section4;

public class IndexMinPQ<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        // Multiway merge priority-queue client
        
        String[][] strings = new String[][] {
            new String[] { "A", "B", "C", "F", "G", "I", "I", "Z" },
            new String[] { "B", "D", "H", "P", "Q", "Q" },
            new String[] { "A", "B", "E", "F", "J", "N" }
        };

        int n = strings.length;
        int[] currentIndexes = new int[n];

        for (int i = 0; i < n; ++i) {
            currentIndexes[i] = 0;
        }
        
        IndexMinPQ<String> pq = new IndexMinPQ<>(n);

        for (int i = 0; i < n; ++i) {
            if (currentIndexes[i] < strings[i].length) {
                pq.insert(i, strings[i][currentIndexes[i]++]);
            }
        }
        
        pq.delete(2);

        while (!pq.isEmpty()) {
            System.out.println(pq.minKey());
            int i = pq.delMin();
            if (currentIndexes[i] < strings[i].length) {
                pq.insert(i, strings[i][currentIndexes[i]++]);
            }
        }
    }

    private int[] pq;   // binary heap using 1-based indexing for keys
    private int[] qp;   // inverse: qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys; // items with priorities
    private int n = 0;  // Size of the heap, going from 1 to n+1 ([0] leaves unused due to easy calculation of finding children nodes)
    
    public IndexMinPQ(int maxN) {
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];

        for (int i = 0; i <= maxN; ++i) {
            qp[i] = -1;
        }
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }
    
    public void insert(int i, Key key) {
        ++n;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public void changeKey(int i, Key key) {
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public void delete(int i) {
        int index = qp[i];
        exchange(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    public Key minKey() {
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    // Return key associated with index i
    public Key keyOf(int i) {
        return keys[pq[qp[i]]];
    }
    
    public int delMin() {
        int indexMin = pq[1];
        exchange(1, n--);
        sink(1);
        keys[pq[n+1]] = null;
        qp[pq[n+1]] = -1;
        
        return indexMin;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exchange(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;

        qp[pq[i]] = i;
        qp[pq[j]] = j;
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
