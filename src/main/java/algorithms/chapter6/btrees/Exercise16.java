package algorithms.chapter6.btrees;

import java.util.StringJoiner;

public class Exercise16 {

    public static void main(String[] args) {
        BTreeST<String, Integer> bTreeST = new BTreeST<>("*");

        // Test add()
        bTreeST.add("Rene", 0);
        bTreeST.add("Sedgewick", 1);
        bTreeST.add("Wayne", 2);
        bTreeST.add("B-tree", 3);
        bTreeST.add("Binary tree", 4);
        bTreeST.add("Red-black tree", 5);
        bTreeST.add("AVL tree", 6);
        bTreeST.add("Segment tree", 7);
        bTreeST.add("Fenwick tree", 8);

        // Test size()
        System.out.println("*** Size tests ***");
        System.out.println("BTreeST size: " + bTreeST.size() + " Expected: 9");

        // Test size() with range
        System.out.println("\n*** Size with range tests ***");

        int sizeWithRange1 = bTreeST.size("Red-black tree", "Segment tree");
        System.out.println("BTreeST size between Red-black tree and Segment tree: " + sizeWithRange1);
        System.out.println("Expected: 4");

        int sizeWithRange2 = bTreeST.size("A*", "B-tree");
        System.out.println("BTreeST size between A* and B-tree: " + sizeWithRange2);
        System.out.println("Expected: 2");

        int sizeWithRange3 = bTreeST.size("Rene", "Z-Function");
        System.out.println("BTreeST size between Rene and Z-Function: " + sizeWithRange3);
        System.out.println("Expected: 4");

        // Test keys()
        System.out.println("\n*** Keys tests ***");
        StringJoiner allKeys = new StringJoiner(" - ");
        for (String key : bTreeST.keys()) {
            allKeys.add(key);
        }
        System.out.println("          " + allKeys.toString());
        System.out.println("Expected: AVL tree - B-tree - Binary tree - Fenwick tree - Red-black tree - Rene - " +
                "Sedgewick - Segment tree - Wayne");

        // Test keys() with range
        System.out.println("\n*** Keys with range tests ***");

        System.out.println("Keys between Red-black tree and Segment tree:");
        StringJoiner keysWithRange1 = new StringJoiner(" - ");
        for (String key : bTreeST.keys("Red-black tree", "Segment tree")) {
            keysWithRange1.add(key);
        }
        System.out.println("          " + keysWithRange1.toString());
        System.out.println("Expected: Red-black tree - Rene - Sedgewick - Segment tree");

        System.out.println("\nKeys between A* and B-tree");
        StringJoiner keysWithRange2 = new StringJoiner(" - ");
        for (String key : bTreeST.keys("A*", "B-tree")) {
            keysWithRange2.add(key);
        }
        System.out.println("          " + keysWithRange2.toString());
        System.out.println("Expected: AVL tree - B-tree");

        System.out.println("\nKeys between Rene and Z-Function");
        StringJoiner keysWithRange3 = new StringJoiner(" - ");
        for (String key : bTreeST.keys("Rene", "Z-Function")) {
            keysWithRange3.add(key);
        }
        System.out.println("          " + keysWithRange3.toString());
        System.out.println("Expected: Rene - Sedgewick - Segment tree - Wayne");

        // Test contains()
        System.out.println("\n*** Contains tests ***");
        System.out.println("Contains Binary tree: " + bTreeST.contains("Binary tree") + " Expected: true");
        System.out.println("Contains Red-black tree: " + bTreeST.contains("Red-black tree") + " Expected: true");
        System.out.println("Contains B-tree: " + bTreeST.contains("B-tree") + " Expected: true");
        System.out.println("Contains Rene: " + bTreeST.contains("Rene") + " Expected: true");
        System.out.println("Contains Binary trees: " + bTreeST.contains("Binary trees") + " Expected: false");
        System.out.println("Contains Binary tre: " + bTreeST.contains("Binary tre") + " Expected: false");
        System.out.println("Contains Binary : " + bTreeST.contains("Binary ") + " Expected: false");

        // Test get()
        System.out.println("\n*** Get tests ***");
        System.out.println("Get Rene: " + bTreeST.get("Rene") + " Expected: 0");
        System.out.println("Get Fenwick tree: " + bTreeST.get("Fenwick tree") + " Expected: 8");
        System.out.println("Get Wayne: " + bTreeST.get("Wayne") + " Expected: 2");
        System.out.println("Get AVL tree: " + bTreeST.get("AVL tree") + " Expected: 6");
        System.out.println("Get Splay tree: " + bTreeST.get("Splay tree") + " Expected: null");
        System.out.println("Get AAA: " + bTreeST.get("AAA") + " Expected: null");
        System.out.println("Get ZZZ: " + bTreeST.get("ZZZ") + " Expected: null");

        System.out.println("\n*** Get with range tests ***");

        System.out.println("Get values for keys between Red-black tree and Segment tree:");
        StringJoiner getWithRange1 = new StringJoiner(" - ");
        for (int value : bTreeST.get("Red-black tree", "Segment tree")) {
            getWithRange1.add(String.valueOf(value));
        }
        System.out.println("          " + getWithRange1.toString());
        System.out.println("Expected: 5 - 0 - 1 - 7");

        System.out.println("\nGet values for keys between A* and B-tree");
        StringJoiner getWithRange2 = new StringJoiner(" - ");
        for (int value : bTreeST.get("A*", "B-tree")) {
            getWithRange2.add(String.valueOf(value));
        }
        System.out.println("          " + getWithRange2.toString());
        System.out.println("Expected: 6 - 3");

        System.out.println("\nGet values for keys between Rene and Z-Function");
        StringJoiner getWithRange3 = new StringJoiner(" - ");
        for (int value : bTreeST.get("Rene", "Z-Function")) {
            getWithRange3.add(String.valueOf(value));
        }
        System.out.println("          " + getWithRange3.toString());
        System.out.println("Expected: 0 - 1 - 7 - 2");

        // Test min()
        System.out.println("\n*** Min and max tests ***");
        System.out.println("Min: " + bTreeST.min() + " Expected: AVL tree");
        System.out.println("Max: " + bTreeST.max() + " Expected: Wayne");

        // Test floor()
        System.out.println("\n*** Floor tests ***");
        System.out.println("Floor AVL tree: " + bTreeST.floor("AVL tree") + " Expected: AVL tree");
        System.out.println("Floor B-tree: " + bTreeST.floor("B-tree") + " Expected: B-tree");
        System.out.println("Floor Red-black tree: " + bTreeST.floor("Red-black tree") + " Expected: Red-black tree");
        System.out.println("Floor Wayne: " + bTreeST.floor("Wayne") + " Expected: Wayne");
        System.out.println("Floor AAA: " + bTreeST.floor("AAA") + " Expected: null");
        System.out.println("Floor Binary zebra: " + bTreeST.floor("Binary zebra") + " Expected: Binary tree");
        System.out.println("Floor Ball: " + bTreeST.floor("Ball") + " Expected: B-tree");
        System.out.println("Floor ZZZ: " + bTreeST.floor("ZZZ") + " Expected: Wayne");

        // Test ceiling()
        System.out.println("\n*** Ceiling tests ***");
        System.out.println("Ceiling AVL tree: " + bTreeST.ceiling("AVL tree") + " Expected: AVL tree");
        System.out.println("Ceiling B-tree: " + bTreeST.ceiling("B-tree") + " Expected: B-tree");
        System.out.println("Ceiling Red-black tree: " + bTreeST.ceiling("Red-black tree") + " Expected: Red-black tree");
        System.out.println("Ceiling Wayne: " + bTreeST.ceiling("Wayne") + " Expected: Wayne");
        System.out.println("Ceiling AAA: " + bTreeST.ceiling("AAA") + " Expected: AVL tree");
        System.out.println("Ceiling Binary zebra: " + bTreeST.ceiling("Binary zebra") + " Expected: Fenwick tree");
        System.out.println("Ceiling Quicksort: " + bTreeST.ceiling("Quicksort") + " Expected: Red-black tree");
        System.out.println("Ceiling ZZZ: " + bTreeST.ceiling("ZZZ") + " Expected: null");

        // Test select()
        System.out.println("\n*** Select tests ***");
        System.out.println("Select 0: " + bTreeST.select(0) + " Expected: AVL tree");
        System.out.println("Select 1: " + bTreeST.select(1) + " Expected: B-tree");
        System.out.println("Select 2: " + bTreeST.select(2) + " Expected: Binary tree");
        System.out.println("Select 3: " + bTreeST.select(3) + " Expected: Fenwick tree");
        System.out.println("Select 4: " + bTreeST.select(4) + " Expected: Red-black tree");
        System.out.println("Select 5: " + bTreeST.select(5) + " Expected: Rene");
        System.out.println("Select 6: " + bTreeST.select(6) + " Expected: Sedgewick");
        System.out.println("Select 7: " + bTreeST.select(7) + " Expected: Segment tree");
        System.out.println("Select 8: " + bTreeST.select(8) + " Expected: Wayne");

        // Test rank()
        System.out.println("\n*** Rank tests ***");
        System.out.println("Rank AVL tree: " + bTreeST.rank("AVL tree") + " Expected: 0");
        System.out.println("Rank B-tree: " + bTreeST.rank("B-tree") + " Expected: 1");
        System.out.println("Rank Binary tree: " + bTreeST.rank("Binary tree") + " Expected: 2");
        System.out.println("Rank Fenwick tree: " + bTreeST.rank("Fenwick tree") + " Expected: 3");
        System.out.println("Rank Red-black tree: " + bTreeST.rank("Red-black tree") + " Expected: 4");
        System.out.println("Rank Rene: " + bTreeST.rank("Rene") + " Expected: 5");
        System.out.println("Rank Sedgewick: " + bTreeST.rank("Sedgewick") + " Expected: 6");
        System.out.println("Rank Segment tree: " + bTreeST.rank("Segment tree") + " Expected: 7");
        System.out.println("Rank Wayne: " + bTreeST.rank("Wayne") + " Expected: 8");
        System.out.println("Rank AAA: " + bTreeST.rank("AAA") + " Expected: 0");
        System.out.println("Rank ZZZ: " + bTreeST.rank("ZZZ") + " Expected: 9");

        // Test delete()
        System.out.println("\n*** Delete tests ***");

        System.out.println("Delete B-tree");
        bTreeST.delete("B-tree");
        printKeys(bTreeST);
        System.out.println("Expected: AVL tree - Binary tree - Fenwick tree - Red-black tree - Rene - " +
                "Sedgewick - Segment tree - Wayne");
        System.out.println("Size: " + bTreeST.size() + " Expected: 8");

        System.out.println("\nDelete Red-black tree");
        bTreeST.delete("Red-black tree");
        printKeys(bTreeST);
        System.out.println("Expected: AVL tree - Binary tree - Fenwick tree - Rene - Sedgewick - Segment tree - Wayne");
        System.out.println("Size: " + bTreeST.size() + " Expected: 7");

        System.out.println("\nDelete Segment tree");
        bTreeST.delete("Segment tree");
        printKeys(bTreeST);
        System.out.println("Expected: AVL tree - Binary tree - Fenwick tree - Rene - Sedgewick - Wayne");
        System.out.println("Size: " + bTreeST.size() + " Expected: 6");

        System.out.println("\nDelete Wayne");
        bTreeST.delete("Wayne");
        printKeys(bTreeST);
        System.out.println("Expected: AVL tree - Binary tree - Fenwick tree - Rene - Sedgewick");
        System.out.println("Size: " + bTreeST.size() + " Expected: 5");

        System.out.println("\nDelete Binary tree");
        bTreeST.delete("Binary tree");
        printKeys(bTreeST);
        System.out.println("Expected: AVL tree - Fenwick tree - Rene - Sedgewick");
        System.out.println("Size: " + bTreeST.size() + " Expected: 4");

        // Test deleteMin()
        System.out.println("\n*** Delete min tests ***");
        System.out.println("\nDelete min 1");
        bTreeST.deleteMin();
        printKeys(bTreeST);
        System.out.println("Expected: Fenwick tree - Rene - Sedgewick");
        System.out.println("Size: " + bTreeST.size() + " Expected: 3");

        System.out.println("\nDelete min 2");
        bTreeST.deleteMin();
        printKeys(bTreeST);
        System.out.println("Expected: Rene - Sedgewick");
        System.out.println("Size: " + bTreeST.size() + " Expected: 2");

        // Test deleteMax()
        System.out.println("\n*** Delete max tests ***");
        System.out.println("\nDelete max 1");
        bTreeST.deleteMax();
        printKeys(bTreeST);
        System.out.println("Expected: Rene");
        System.out.println("Size: " + bTreeST.size() + " Expected: 1");

        System.out.println("\nDelete max 2");
        bTreeST.deleteMax();
        if (!bTreeST.isEmpty()) {
            printKeys(bTreeST);
        } else {
            System.out.println(" ");
        }
        System.out.println("Expected: ");
        System.out.println("Size: " + bTreeST.size() + " Expected: 0");

        System.out.println("IsEmpty: " + bTreeST.isEmpty() + " Expected: true");
    }

    private static void printKeys(BTreeST<String, Integer> bTreeST) {
        StringJoiner allKeys = new StringJoiner(" - ");
        for (String key : bTreeST.keys()) {
            allKeys.add(key);
        }
        System.out.println("          " + allKeys.toString());
    }
    
}
