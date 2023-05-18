package algorithms.chapter2.section4;

public class HeapPrinter {

    public static void main(String[] args) {
        Comparable[] pq = new Comparable[] { 0, 0, 1, 2, 3, 4, 5};
        printHeap(pq);
    }
    
    /*
     * 
     *       0
     *      / \
     *     1   2
     * 
     *       0
     *      / \
     *     /   \
     *    1     2
     *   / \   / \
     *  3   4 5   6
     *  
     *             0
     *            / \
     *           /   \
     *          /     \
     *         /       \
     *        /         \
     *       1           2
     *      / \         / \
     *     /   \       /   \
     *    3     4     5     6
     *   / \   / \   / \   / \
     *  7   8 9   8 9   8 9   8
     *  
     *  
     *  
     *                         0
     *                        / \
     *                       /   \
     *                      /     \
     *                     /       \
     *                    /         \
     *                   /           \
     *                  /             \
     *                 /               \
     *                /                 \
     *               /                   \
     *              /                     \
     *             2                       2
     *            / \                     / \
     *           /   \                   /   \
     *          /     \                 /     \
     *         /       \               /       \
     *        /         \             /         \
     *       3           3           3           3
     *      / \         / \         / \         / \
     *     /   \       /   \       /   \       /   \
     *    5     5     5     5     5     5     5     5
     *   / \   / \   / \   / \   / \   / \   / \   / \
     *  7   8 9   8 9   8 9   8 7   8 9   8 9   8 9   8
     */
    public static void printHeap(Comparable[] pq) {
        int treeLevels = (int) Math.ceil(Math.log10(pq.length) / Math.log10(2));
        int maxNodesLastLevel = (int) Math.pow(2, treeLevels - 1);
        System.out.println(maxNodesLastLevel);
        
        for (int i = 0; i < treeLevels; i++) {
            int nodesPerLevel, index;
            nodesPerLevel = index = (int) Math.pow(2, i);
            
            for (int j = 0; index < pq.length && j < nodesPerLevel; j++) {
                System.out.print(pq[index++] + " ");
            }
            System.out.println();
        }
    }
    
}
