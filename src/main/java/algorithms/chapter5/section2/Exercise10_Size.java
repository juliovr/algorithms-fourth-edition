package algorithms.chapter5.section2;

public class Exercise10_Size {

    public static void main(String[] args) {
        System.out.println("********Trie tests********");
        TrieSTWithSize<Integer> trieWithSize = new TrieSTWithSize<>();

        trieWithSize.put("Rene", 0);
        trieWithSize.put("Re", 1);
        trieWithSize.put("Algorithms", 2);
        trieWithSize.put("Algo", 3);
        trieWithSize.put("Algor", 4);
        trieWithSize.put("Tree", 5);
        trieWithSize.put("Trie", 6);
        trieWithSize.put("TST", 7);

        System.out.println("Size of Trie: " + trieWithSize.size());
        System.out.println("Expected: 8");

        System.out.println();
        printNodeSizes(trieWithSize.root, ' ');

        System.out.println("\nExpected:\n" +
                "Node character:   Size: 8\n" +
                "Node character: A Size: 3\n" +
                "Node character: l Size: 3\n" +
                "Node character: g Size: 3\n" +
                "Node character: o Size: 3\n" +
                "Node character: r Size: 2\n" +
                "Node character: i Size: 1\n" +
                "Node character: t Size: 1\n" +
                "Node character: h Size: 1\n" +
                "Node character: m Size: 1\n" +
                "Node character: s Size: 1\n" +
                "Node character: R Size: 2\n" +
                "Node character: e Size: 2\n" +
                "Node character: n Size: 1\n" +
                "Node character: e Size: 1\n" +
                "Node character: T Size: 3\n" +
                "Node character: S Size: 1\n" +
                "Node character: T Size: 1\n" +
                "Node character: r Size: 2\n" +
                "Node character: e Size: 1\n" +
                "Node character: e Size: 1\n" +
                "Node character: i Size: 1\n" +
                "Node character: e Size: 1");

        System.out.println("\nDeleted Trie key");
        trieWithSize.delete("Trie");

        printNodeSizes(trieWithSize.root, ' ');
        System.out.println("Expected: no 'i' and 'e' nodes after 'Tr'");

        System.out.println("\nSize of Trie: " + trieWithSize.size());
        System.out.println("Expected: 7");

        System.out.println("\nDeleted Re key");
        trieWithSize.delete("Re");

        printNodeSizes(trieWithSize.root, ' ');
        System.out.println("Expected: 'R' and 'e' with size 1");

        System.out.println("\nSize of Trie: " + trieWithSize.size());
        System.out.println("Expected: 6");

        System.out.println("\nDeleted Algo key");
        trieWithSize.delete("Algo");

        printNodeSizes(trieWithSize.root, ' ');
        System.out.println("Expected: 'A', 'l', 'g', 'o' and 'r' nodes with size 2");

        System.out.println("\nSize of Trie: " + trieWithSize.size());
        System.out.println("Expected: 5");

        System.out.println("\n********Ternary Search Trie tests********");
        TSTWithSize<Integer> tstWithSize = new TSTWithSize<>();

        tstWithSize.put("Rene", 0);
        tstWithSize.put("Re", 1);
        tstWithSize.put("Algorithms", 2);
        tstWithSize.put("Algo", 3);
        tstWithSize.put("Algor", 4);
        tstWithSize.put("Tree", 5);
        tstWithSize.put("Trie", 6);
        tstWithSize.put("TST", 7);

        System.out.println("Size of TST: " + tstWithSize.size());
        System.out.println("Expected: 8");

        System.out.println();
        printNodeSizes(tstWithSize.root);

        System.out.println("\nExpected:\n" +
                "Node character: A Size: 3\n" +
                "Node character: l Size: 3\n" +
                "Node character: g Size: 3\n" +
                "Node character: o Size: 3\n" +
                "Node character: r Size: 2\n" +
                "Node character: i Size: 1\n" +
                "Node character: t Size: 1\n" +
                "Node character: h Size: 1\n" +
                "Node character: m Size: 1\n" +
                "Node character: s Size: 1\n" +
                "Node character: R Size: 2\n" +
                "Node character: e Size: 2\n" +
                "Node character: n Size: 1\n" +
                "Node character: e Size: 1\n" +
                "Node character: T Size: 3\n" +
                "Node character: S Size: 1\n" +
                "Node character: T Size: 1\n" +
                "Node character: r Size: 2\n" +
                "Node character: e Size: 1\n" +
                "Node character: e Size: 1\n" +
                "Node character: i Size: 1\n" +
                "Node character: e Size: 1");

//        System.out.println("\nDeleted Trie key");
//        tstWithSize.delete("Trie");
//
//        printNodeSizes(tstWithSize.root);
//        System.out.println("Expected: no 'i' and 'e' nodes after 'Tr'");
//
//        System.out.println("\nSize of TST: " + tstWithSize.size());
//        System.out.println("Expected: 7");
//
//        System.out.println("\nDeleted Re key");
//        tstWithSize.delete("Re");
//
//        printNodeSizes(tstWithSize.root);
//        System.out.println("Expected: 'R' and 'e' with size 1");
//
//        System.out.println("\nSize of TST: " + tstWithSize.size());
//        System.out.println("Expected: 6");
//
//        System.out.println("\nDeleted Algo key");
//        tstWithSize.delete("Algo");
//
//        printNodeSizes(tstWithSize.root);
//        System.out.println("Expected: 'A', 'l', 'g', 'o' and 'r' nodes with size 2");
//
//        System.out.println("\nSize of TST: " + tstWithSize.size());
//        System.out.println("Expected: 5");
    }

    private static void printNodeSizes(TrieSTWithSize.NodeWithSize currentNode, char currentChar) {
        if (currentNode == null) {
            return;
        }

        System.out.println("Node character: " + currentChar + " Size: " + currentNode.size);

        for (char nextChar = 0; nextChar < TrieSTWithSize.R; nextChar++) {
            if (currentNode.next[nextChar] != null) {
                printNodeSizes(currentNode.next[nextChar], nextChar);
            }
        }
    }

    private static void printNodeSizes(TSTWithSize.NodeWithSize currentNode) {
        if (currentNode == null) {
            return;
        }

        printNodeSizes(currentNode.left);

        System.out.println("Node character: " + currentNode.c + " Size: " + currentNode.size);
        printNodeSizes(currentNode.mid);
        printNodeSizes(currentNode.right);
    }
    
}
