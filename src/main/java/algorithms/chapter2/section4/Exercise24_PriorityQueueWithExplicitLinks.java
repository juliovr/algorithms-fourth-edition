package algorithms.chapter2.section4;

public class Exercise24_PriorityQueueWithExplicitLinks<K extends Comparable<K>> {

    public static void main(String[] args) {
        Exercise24_PriorityQueueWithExplicitLinks<String> pq = new Exercise24_PriorityQueueWithExplicitLinks<>();
        pq.insert("A");
        pq.insert("B");
        pq.insert("C");
        pq.insert("C");
        pq.delMax();
        pq.insert("E");
//        pq.insert("F");
//        pq.insert("G");
//        
        System.out.println("fin");
    }
    
    private Node<K> root; // Heap-ordered complete binary tree
    private int n = 0;    // Size of the heap, going from 1 to n+1 ([0] leaves unused due to easy calculation of finding children nodes)
    
    private static class Node<K> {
        public K value;
        public Node<K> parent;
        public Node<K> left;
        public Node<K> right;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public int size() {
        return n;
    }
    
    public void insert(K v) {
        Node<K> node = new Node<>();
        node.value = v;
        
        ++n;

        if (isEmpty()) {
            root = node;
            return;
        }
        
        int[] path = findPathThroughNode(n);
        Node<K> lastParent = getNodeFromRootPath(path);
        
        node.parent = lastParent;
        
        if (lastParent.left == null) {
            lastParent.left = node;
        } else {
            lastParent.right = node;
        }
        
        swim(node);
    }
    
    /**
     * Find the path (top-down) through the node, from the root to the parent of the node at n position.
     * 
     * @param n
     * @return
     */
    private int[] findPathThroughNode(int n) {
        int numberOfParents = (int) (Math.log10(n) / Math.log10(2));
        int parentIndex = n / 2;
        
        int[] path = new int[numberOfParents];
        
        for (int i = numberOfParents - 1; i >= 0; --i) {
            path[i] = parentIndex;
            parentIndex /= 2;
        }
        
        return path;
    }
    
    private Node<K> getNodeFromRootPath(int[] path) {
        Node<K> node = root;

        for (int i = 0, heapIndex = 1; i < path.length; ++i) {
            if (path[i] == heapIndex * 2) {
                node = node.left;
                heapIndex = (heapIndex * 2);
            } else if (path[i] == (heapIndex * 2) + 1) {
                node = node.right;
                heapIndex = (heapIndex * 2) + 1;
            }
        }
        
        return node;
    }
    
    private void swim(Node<K> node) {
        while (node != null && node != root && less(node.parent.value, node.value)) {
            exchange(node, node.parent);
            
            node = node.parent;
        }
    }
    
    public K delMax() {
        K max = root.value;
        
        int[] path = findPathThroughNode(n);
        Node<K> lastParent = getNodeFromRootPath(path);
        
        --n;
        
        if (lastParent.right != null) {
            // right is the last node
            exchange(root, lastParent.right);
            
            lastParent.right.value = null;
            lastParent.right.parent = null;
            lastParent.right = null;
        } else {
            // left is the last node
            exchange(root, lastParent.left);
            
            lastParent.left.value = null;
            lastParent.left.parent = null;
            lastParent.left = null;
        }

        sink(root);
        
        System.out.println("Max = " + max);
        
        return max;
    }
    
    private boolean less(K i, K j) {
        return i.compareTo(j) < 0;
    }
    
    private void exchange(Node<K> i, Node<K> j) {
        K tmp = i.value;
        i.value = j.value;
        j.value = tmp;
    }
    
    
    private void sink(Node<K> node) {
        while (node != null && node.left != null) {
            Node<K> greaterChildren = node.left;
            if (node.right != null && less(node.left.value, node.right.value)) {
                greaterChildren = node.right;
            }
            
            if (!less(node.value, greaterChildren.value)) break;
            
            exchange(node, greaterChildren);
            node = greaterChildren;
        }
        
//        int j;
//        while ((j = k*2) <= n) {
//            if (j < n && less(j, j+1)) {
//                j++;
//            }
//            
//            if (!less(k, j)) break;
//            
//            exchange(k, j);
//            k = j;
//        }
    }
    
    // Old: iterate over the node replacing the pointers to make de correct exchange.
    // Replaced for the new version because it only exchange the values instead of all pointers (so
    // much less expensive).
//    private void swim(Node<K> node) {
//        while (node != null && node != root && less(node.parent.value, node.value)) {
//            Node<K> oldParent = node.parent;
//            Node<K> newParent = oldParent.parent;
//            
//            node.parent = newParent;
//            
//            Node<K> tmpLeft = node.left;
//            Node<K> tmpRight = node.right;
//            
//            if (tmpLeft != null) tmpLeft.parent = oldParent;
//            if (tmpRight != null) tmpRight.parent = oldParent;
//            
//            if (oldParent.left == node) {
//                node.left = oldParent;
//                node.right = oldParent.right;
//                if (node.right != null) node.right.parent = node;
//            } else {
//                node.right = oldParent;
//                node.left = oldParent.left;
//                if (node.left != null) node.left.parent = node;
//            }
//            
//            if (newParent == null) {
//                root = node;
//            } else {
//                if (newParent.left == oldParent) {
//                    newParent.left = node;
//                } else {
//                    newParent.right = node;
//                }
//            }
//            
//            oldParent.parent = node;
//            oldParent.left = tmpLeft;
//            oldParent.right = tmpRight;
//        }
//    }
    
}
