package algorithms.chapter5.section2;

public class Exercise6 {

    public static void main(String[] args) {
        StringSET stringSET = new StringSET();
//        StringSETNonRecursive stringSET = new StringSETNonRecursive();

        System.out.println("Is string set empty: " + stringSET.isEmpty());
        System.out.println("Expected: true");

        System.out.println("\nSize: " + stringSET.size());
        System.out.println("Expected: 0");

        System.out.println("\nToString:\n" + stringSET);

        stringSET.add("Rene");
        stringSET.add("Re");
        stringSET.add("Algorithms");
        stringSET.add("Algo");
        stringSET.add("Algor");
        stringSET.add("Tree");
        stringSET.add("Trie");
        stringSET.add("TST");
        stringSET.add("Trie123");

        System.out.println("\nIs string set empty: " + stringSET.isEmpty());
        System.out.println("Expected: false");

        System.out.println("\nSize: " + stringSET.size());
        System.out.println("Expected: 9");

        System.out.println("\nToString:\n" + stringSET);

        // Adding a key that already exists
        stringSET.add("Algorithms");

        System.out.println("\nSize after adding key that already exists: " + stringSET.size());
        System.out.println("Expected: 9");

        System.out.println("\nContains key Sedgewick: " + stringSET.contains("Sedgewick"));
        System.out.println("Expected: false");

        System.out.println("\nContains key Rene: " + stringSET.contains("Rene"));
        System.out.println("Expected: true");

        System.out.println("\nContains key Z-Function: " + stringSET.contains("Z-Function"));
        System.out.println("Expected: false");

        System.out.println("\nContains key Algorithms: " + stringSET.contains("Algorithms"));
        System.out.println("Expected: true");

        stringSET.delete("Algorithms");

        System.out.println("\nContains key Algorithms (after delete): " + stringSET.contains("Algorithms"));
        System.out.println("Expected: false");

        stringSET.delete("Re");
        System.out.println("\nSize after deletes: " + stringSET.size());
        System.out.println("Expected: 7");

        System.out.println("\nToString:\n" + stringSET);
    }

}
