package algorithms.chapter5.section1;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise20_RandomFixedLengthWords {

    private static final char[] LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        int n = 10;
        int w = 5;
        String[] keys = randomFixedLengthWords(n, w);
        for (String s : keys) {
            System.out.println(s);
        }
    }

    public static String[] randomFixedLengthWords(int n, int w) {
        String[] keys = new String[n];
        for (int i = 0; i < n; ++i) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < w; ++j) {
                int letterIndex = StdRandom.uniform(0, LETTERS.length);
                sb.append(LETTERS[letterIndex]);
            }

            keys[i] = sb.toString();
        }

        return keys;
    }

}
