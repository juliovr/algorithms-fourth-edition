package cl.julio.algorithmsFourthEdition.chapter2.section4;

public class Exercise29_MinMaxPriorityQueue<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        MinMaxPQ<String> pq = new MinMaxPQ<>(7);
        pq.insert("E");
        pq.insert("C");
        pq.insert("B");
        pq.insert("H");
        pq.insert("C");
        pq.insert("D");
        pq.insert("A");
        System.out.println(pq.delMax());
        System.out.println(pq.delMin());
        System.out.println(pq.findMax());
        System.out.println(pq.findMin());
    }

    private static class Node<Key extends Comparable<Key>> implements Comparable<Node<Key>> {

        public Key value;
        public int maxIndex;
        public int minIndex;
        
        @Override
        public int compareTo(Node<Key> o) {
            return this.value.compareTo(o.value);
        }
        
    }
    
    private static class MinMaxPQ<Key extends Comparable<Key>> {

        private Node<Key>[] maxPQ;
        private Node<Key>[] minPQ;
        private int n = 0;
        
        public MinMaxPQ(int maxN) {
            this.maxPQ = new Node[maxN + 1];
            this.minPQ = new Node[maxN + 1];
        }

        public void insert(Key v) {
            Node node = new Node();
            node.value = v;

            ++n;
            
            insertMax(node);
            insertMin(node);
        }
        
        public Key delMax() {
            Node<Key> maxNode = maxPQ[1];

            exchange(maxPQ, 1, n--);
            maxPQ[n + 1] = null;
            sinkMax(1);

            // Delete max key in minPQ
            exchange(minPQ, maxNode.minIndex, n + 1);
            minPQ[n + 1] = null;

            return maxNode.value;
        }
        
        public Key delMin() {
            Node<Key> minNode = minPQ[1];

            exchange(minPQ, 1, n--);
            minPQ[n + 1] = null;
            sinkMin(1);

            // Delete min key in maxPQ
            exchange(maxPQ, minNode.maxIndex, n + 1);
            maxPQ[n + 1] = null;

            --n;

            return minNode.value;
        }
        
        public Key findMax() {
            return maxPQ[1].value;
        }
        
        public Key findMin() {
            return minPQ[1].value;
        }

        private void insertMax(Node v) {
            maxPQ[n] = v;
            swimMax(n);
        }

        private void swimMax(int k) {
            while (k > 1 && less(maxPQ, k/2, k)) {
                exchange(maxPQ, k/2, k);
                maxPQ[k].maxIndex = k;
                
                k /= 2;
            }

            maxPQ[k].maxIndex = k;
        }

        
        private void insertMin(Node v) {
            minPQ[n] = v;
            swimMin(n);
        }

        private void swimMin(int k) {
            while (k > 1 && less(minPQ, k, k/2)) {
                exchange(minPQ, k, k/2);
                minPQ[k].minIndex = k;
                
                k /= 2;
            }

            minPQ[k].minIndex = k;
        }
        
        private boolean less(Node[] pq, int i, int j) {
            return pq[i].compareTo(pq[j]) < 0;
        }
    
        private void exchange(Node[] pq, int i, int j) {
            Node tmp = pq[i];
            pq[i] = pq[j];
            pq[j] = tmp;
        }

        private void sinkMax(int k) {
            int j;
            while ((j = k*2) <= n) {
                if (j < n && less(maxPQ, j, j+1)) {
                    j++;
                }
            
                if (!less(maxPQ, k, j)) break;
            
                exchange(maxPQ, k, j);
                k = j;
            }
        }

        private void sinkMin(int k) {
            int j;
            while ((j = k*2) <= n) {
                if (j < n && less(minPQ, j+1, j)) {
                    j++;
                }
            
                if (!less(minPQ, j, k)) break;
            
                exchange(minPQ, j, k);
                k = j;
            }
        }


    }
    
}
