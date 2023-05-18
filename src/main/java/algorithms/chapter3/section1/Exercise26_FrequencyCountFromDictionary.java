package algorithms.chapter3.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Exercise26_FrequencyCountFromDictionary {

    /* To use as input:
radical
radical
battlefield
news
radical
news
     */
    public static void main(String[] args) throws Exception {
        int minlen = Integer.parseInt(args[0]);
        BinarySearchST<String, Integer> st = new BinarySearchST<>(10);

        List<String> lines = Files.readAllLines(Paths.get("test_data/Exercise26_dictionary.txt"));
        ST<String, Integer> dictionary = new ST<>();
        for (int i = 0; i < lines.size(); ++i) {
            String word = lines.get(i);
            dictionary.put(word, i);
        }

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minlen) continue;

            if (dictionary.contains(key)) {
                if (st.contains(key)) {
                    st.put(key, st.get(key) + 1);
                } else {
                    st.put(key, 1);
                }
            }
        }

        BinarySearchST<Integer, WordsOrderedByFrequency> ordered = new BinarySearchST<>(10);
        for (String word : st.keys()) {
            WordsOrderedByFrequency asd = new WordsOrderedByFrequency();
            asd.word = word;
            asd.frequency = st.get(word);

            ordered.put(asd.frequency, asd);
        }

        for (int index : ordered.reverseKeys()) {
            WordsOrderedByFrequency word = ordered.get(index);
            StdOut.println(word.word + " = " + index);

        }
    }

    private static class WordsOrderedByFrequency implements Comparable<WordsOrderedByFrequency> {

        String word;
        int frequency;

        @Override
        public int compareTo(WordsOrderedByFrequency o) {
            if (this.frequency < o.frequency) return -1;
            if (this.frequency > o.frequency) return 1;

            return 0;
        }
    }

}
