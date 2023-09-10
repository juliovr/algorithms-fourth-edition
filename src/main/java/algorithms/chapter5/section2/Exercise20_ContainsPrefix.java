package algorithms.chapter5.section2;

public class Exercise20_ContainsPrefix {

    public static void main(String[] args) {
        StringSET stringSET = new StringSET();

        stringSET.add("Rene");
        stringSET.add("Re");
        stringSET.add("Algorithms");
        stringSET.add("Algo");
        stringSET.add("Algor");
        stringSET.add("Tree");
        stringSET.add("Trie");
        stringSET.add("TST");
        stringSET.add("Trie123");
        stringSET.add("Z-Function");

        System.out.println("Contains prefix Alg: " + stringSET.containsPrefix("Alg"));
        System.out.println("Expected: true");

        System.out.println("\nContains prefix Trie123: " + stringSET.containsPrefix("Trie123"));
        System.out.println("Expected: true");

        System.out.println("\nContains prefix T: " + stringSET.containsPrefix("T"));
        System.out.println("Expected: true");

        System.out.println("\nContains prefix R: " + stringSET.containsPrefix("R"));
        System.out.println("Expected: true");

        System.out.println("\nContains prefix Algar: " + stringSET.containsPrefix("Algar"));
        System.out.println("Expected: false");

        System.out.println("\nContains prefix ZZZ: " + stringSET.containsPrefix("ZZZ"));
        System.out.println("Expected: false");
    }


}
