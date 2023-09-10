package algorithms.chapter5.section2;

public class Exercise8_OrderedOperationsForTries {

    public static void main(String[] args) {
        System.out.println("********Trie tests********");
        TrieST<Integer> trieST = new TrieST<>();
        trieST.put("Rene", 0);
        trieST.put("Re", 1);
        trieST.put("Algorithms", 2);
        trieST.put("Algo", 3);
        trieST.put("Algor", 4);
        trieST.put("Tree", 5);
        trieST.put("Trie", 6);
        trieST.put("TST", 7);
        trieST.put("Trie123", 8);

        System.out.println("Floor of Re: " + trieST.floor("Re"));
        System.out.println("Expected: Re");
        System.out.println("Floor of Algori: " + trieST.floor("Algori"));
        System.out.println("Expected: Algor");
        System.out.println("Floor of Ball: " + trieST.floor("Ball"));
        System.out.println("Expected: Algorithms");
        System.out.println("Floor of Tarjan: " + trieST.floor("Tarjan"));
        System.out.println("Expected: TST");
        System.out.println("Floor of AA: " + trieST.floor("AA"));
        System.out.println("Expected: null");
        System.out.println("Floor of Zoom: " + trieST.floor("Zoom"));
        System.out.println("Expected: Trie123");
        System.out.println("Floor of TAB: " + trieST.floor("TAB"));
        System.out.println("Expected: Rene");
        System.out.println("Floor of TSA: " + trieST.floor("TSA"));
        System.out.println("Expected: Rene");

        System.out.println("\nCeiling of Re: " + trieST.ceiling("Re"));
        System.out.println("Expected: Re");
        System.out.println("Ceiling of Algori: " + trieST.ceiling("Algori"));
        System.out.println("Expected: Algorithms");
        System.out.println("Ceiling of Ball: " + trieST.ceiling("Ball"));
        System.out.println("Expected: Re");
        System.out.println("Ceiling of Tarjan: " + trieST.ceiling("Tarjan"));
        System.out.println("Expected: Tree");
        System.out.println("Ceiling of AA: " + trieST.ceiling("AA"));
        System.out.println("Expected: Algo");
        System.out.println("Ceiling of Zoom: " + trieST.ceiling("Zoom"));
        System.out.println("Expected: null");
        System.out.println("Ceiling of Ruby: " + trieST.ceiling("Ruby"));
        System.out.println("Expected: TST");

        System.out.println("\nSelect 0: " + trieST.select(0));
        System.out.println("Expected: Algo");
        System.out.println("Select 3: " + trieST.select(3));
        System.out.println("Expected: Re");
        System.out.println("Select 2: " + trieST.select(2));
        System.out.println("Expected: Algorithms");
        System.out.println("Select 5: " + trieST.select(5));
        System.out.println("Expected: TST");
        System.out.println("Select 8: " + trieST.select(8));
        System.out.println("Expected: Trie123");

        System.out.println("\nRank of R: " + trieST.rank("R"));
        System.out.println("Expected: 3");
        System.out.println("Rank of Re: " + trieST.rank("Re"));
        System.out.println("Expected: 3");
        System.out.println("Rank of A: " + trieST.rank("A"));
        System.out.println("Expected: 0");
        System.out.println("Rank of Algo: " + trieST.rank("Algo"));
        System.out.println("Expected: 0");
        System.out.println("Rank of Algori: " + trieST.rank("Algori"));
        System.out.println("Expected: 2");
        System.out.println("Rank of Algorithms: " + trieST.rank("Algorithms"));
        System.out.println("Expected: 2");
        System.out.println("Rank of Tarjan: " + trieST.rank("Tarjan"));
        System.out.println("Expected: 6");
        System.out.println("Rank of Trie123: " + trieST.rank("Trie123"));
        System.out.println("Expected: 8");
        System.out.println("Rank of Zoom: " + trieST.rank("Zoom"));
        System.out.println("Expected: 9");
    }

}
