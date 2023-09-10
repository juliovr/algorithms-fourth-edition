package algorithms.chapter5.section2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;
import java.util.Map;

public class Exercise19_RandomPhoneNumbers {

    public static void main(String[] args) {
        Map<Integer, Integer> areaCodes = parseAreaCodesFromFile("test_data/phone-na.csv");
        int n = 20; // "Random" count number to generate data

        TrieSTAreaCodes<String> trie = new TrieSTAreaCodes<>();
        for (int i = 0; i < n;) {
            int randomAreaCodeIndex = StdRandom.uniform(0, areaCodes.size() + 1);
            int areaCode = areaCodes.get(randomAreaCodeIndex);
            StringBuilder sb = new StringBuilder(areaCode + "");
            int numbersToGenerateMinusAreaCode = TrieSTAreaCodes.R - 3;
            for (int j = 0; j < numbersToGenerateMinusAreaCode; ++j) {
                int random = StdRandom.uniform(0, 10);
                sb.append(random);
            }

            String phoneNumberGenerated = sb.toString();
            String phoneNumberFormatted = "(" + areaCode + ") " + phoneNumberGenerated.substring(0, 3) + "-" + phoneNumberGenerated.substring(3, 7);
            if (trie.get(phoneNumberGenerated) == null) {
                trie.put(phoneNumberGenerated, phoneNumberFormatted);
                ++i; // it needs to be re-generated if the key exists
            }
        }

        for (String s : trie.keys()) {
            System.out.println(trie.get(s));
        }
    }

    private static Map<Integer, Integer> parseAreaCodesFromFile(String file) {
        Map<Integer, Integer> areaCodes = new HashMap<>();

        In in = new In(file);
        in.readLine(); // Skip headers
        while (in.hasNextLine()) {
            String[] lineElements = in.readLine().split(",");
            if (lineElements.length > 0) {
                int code = Integer.parseInt(lineElements[0]);
                areaCodes.put(areaCodes.size(), code);
            }
        }

        return areaCodes;
    }

    private static class TrieSTAreaCodes<Value> {

        private final static int R = 10;
        private Node root = new Node();
        private static int getCharacterValue(char c) {
            if (c >= '0' && c <= '9') {
                return (int)(c - '0');
            }

            return -1;
        }

        private static class Node {
            private Object val;
            private Node[] next = new Node[R];
        }

        public Value get(String key) {
            Node x = get(root, key, 0);
            if (x == null) {
                return null;
            }

            return (Value) x.val;
        }

        private Node get(Node x, String key, int d) {
            if (x == null) {
                return null;
            }

            if (d == key.length()) {
                return x;
            }

            char c = key.charAt(d);
            int charIndex = getCharacterValue(c);
            return get(x.next[charIndex], key, d+1);
        }

        public void put(String key, Value val) {
            root = put(root, key, val, 0);
        }

        private Node put(Node x, String key, Value val, int d) {
            if (x == null) {
                x = new Node();
            }

            if (d == key.length()) {
                x.val = val;
                return x;
            }

            char c = key.charAt(d);
            int charIndex = getCharacterValue(c);
            x.next[charIndex] = put(x.next[charIndex], key, val, d+1);
            return x;
        }

        public Iterable<String> keys() {
            return keysWithPrefix("");
        }

        public Iterable<String> keysWithPrefix(String prefix) {
            Queue<String> queue = new Queue<>();
            Node firstNodeMatchingPrefix = get(root, prefix, 0);
            collect(firstNodeMatchingPrefix, prefix, queue);
            return queue;
        }

        private void collect(Node x, String prefix, Queue<String> queue) {
            if (x == null) {
                return;
            }

            if (x.val != null) {
                queue.enqueue(prefix);
            }

            for (int c = 0; c < R; ++c) {
                collect(x.next[c], prefix + c, queue);
            }
        }
    }

}
