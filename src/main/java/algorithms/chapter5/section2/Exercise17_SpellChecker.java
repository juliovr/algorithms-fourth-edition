package algorithms.chapter5.section2;

import edu.princeton.cs.algs4.In;

public class Exercise17_SpellChecker {

    public static void main(String[] args) {
        In in = new In("test_data/dictionary.txt");
        String[] wordsInDictionary = in.readAllStrings();

        TST<Integer> tst = new TST<>();
        for (String word : wordsInDictionary) {
            tst.put(word, 0);
        }

        String linesFromInput = "this is a text example from standard input to test the dictionary";
        String[] splitted = linesFromInput.split(" ");
        System.out.println("Words that are not in dictionary:");
        for (String s : splitted) {
            if (!tst.contains(s)) {
                System.out.println(s);
            }
        }
    }

}
