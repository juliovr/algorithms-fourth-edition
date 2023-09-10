package algorithms.chapter5.section2;

import java.util.StringJoiner;

public class Exercise9_ExtendedOperationsForTST {

    public static void main(String[] args) {
        TST<Integer> tstExtended = new TST<>();

        tstExtended.put("Rene", 0);
        tstExtended.put("Re", 1);
        tstExtended.put("Algorithms", 2);
        tstExtended.put("Algo", 3);
        tstExtended.put("Algor", 4);
        tstExtended.put("Tree", 5);
        tstExtended.put("Trie", 6);
        tstExtended.put("TST", 7);

        System.out.println("keys() test");
        StringJoiner initialKeys = new StringJoiner(" ");
        for (String key : tstExtended.keys()) {
            initialKeys.add(key);
        }
        System.out.println("Initial keys: " + initialKeys.toString());
        System.out.println("Expected:     Algo Algor Algorithms Re Rene TST Tree Trie");

        System.out.println();

        System.out.println("keysWithPrefix() test");
        StringJoiner keysWithPrefix1 = new StringJoiner(" ");
        for (String key : tstExtended.keysWithPrefix("A")) {
            keysWithPrefix1.add(key);
        }
        System.out.println("Keys with prefix A: " + keysWithPrefix1.toString());
        System.out.println("Expected:           Algo Algor Algorithms");

        StringJoiner keysWithPrefix2 = new StringJoiner(" ");
        for (String key : tstExtended.keysWithPrefix("R")) {
            keysWithPrefix2.add(key);
        }
        System.out.println("Keys with prefix R: " + keysWithPrefix2.toString());
        System.out.println("Expected:           Re Rene");

        StringJoiner keysWithPrefix3 = new StringJoiner(" ");
        for (String key : tstExtended.keysWithPrefix("Z")) {
            keysWithPrefix3.add(key);
        }
        System.out.println("Keys with prefix Z: " + keysWithPrefix3.toString());
        System.out.println("Expected: ");

        StringJoiner keysWithPrefix4 = new StringJoiner(" ");
        for (String key : tstExtended.keysWithPrefix("T")) {
            keysWithPrefix4.add(key);
        }
        System.out.println("Keys with prefix T: " + keysWithPrefix4.toString());
        System.out.println("Expected:           TST Tree Trie");

        System.out.println();

        System.out.println("keysThatMatch() test");
        StringJoiner keysThatMatch1 = new StringJoiner(" ");
        for (String key : tstExtended.keysThatMatch("Tr.e")) {
            keysThatMatch1.add(key);
        }
        System.out.println("Keys that match pattern Tr.e: " + keysThatMatch1.toString());
        System.out.println("Expected:                     Tree Trie");

        StringJoiner keysThatMatch2 = new StringJoiner(" ");
        for (String key : tstExtended.keysThatMatch("R.")) {
            keysThatMatch2.add(key);
        }
        System.out.println("Keys that match pattern R.: " + keysThatMatch2.toString());
        System.out.println("Expected:                   Re");

        System.out.println();

        System.out.println("longestPrefixOf() test");
        String longestPrefixOf1 = tstExtended.longestPrefixOf("Ren");
        System.out.println("Longest prefix of Ren: " + longestPrefixOf1);
        System.out.println("Expected:              Re");

        String longestPrefixOf2 = tstExtended.longestPrefixOf("Algor");
        System.out.println("Longest prefix of Algor: " + longestPrefixOf2);
        System.out.println("Expected:                Algor");

        String longestPrefixOf3 = tstExtended.longestPrefixOf("Quicksort");
        System.out.println("Longest prefix of Quicksort: " + longestPrefixOf3);
        System.out.println("Expected: ");
    }

}
