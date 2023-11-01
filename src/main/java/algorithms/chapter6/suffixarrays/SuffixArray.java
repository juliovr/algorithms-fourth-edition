package algorithms.chapter6.suffixarrays;

import java.util.Arrays;

public class SuffixArray {

    private final Suffix[] suffixes;
    private final int n;

    public SuffixArray(String text) {
        this.n = text.length();
        this.suffixes = new Suffix[n];
        for (int i = 0; i < n; ++i) {
            suffixes[i] = new Suffix(text, i);
        }

        Arrays.sort(suffixes);
    }

    private static class Suffix implements Comparable<Suffix> {

        private final String text;
        private final int index;

        private Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }

        private int length() {
            return text.length() - index;
        }

        private char charAt(int i) {
            return text.charAt(index + i);
        }

        public String toString() {
            return text.substring(index);
        }

        public int compareTo(Suffix that) {
            if (this == that) {
                return 0;
            }

            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; ++i) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }

            return this.length() - that.length();
        }

    }

    public int index(int i) {
        return suffixes[i].index;
    }

    public int length() {
        return suffixes.length;
    }

    public String select(int i) {
        return suffixes[i].toString();
    }

    public int lcp(int i) {
        if (i < 1 || i > (n-1)) throw new IllegalArgumentException("i must be between 1 and n-1");

        String prev = select(i-1);
        String curr = select(i);
        return lcp(prev, curr);
    }

    private static int lcp(String s1, String s2) {
        int n = Math.min(s1.length(), s2.length());
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return i;
            }
        }

        return n;
    }

    public int rank(String key) {
        int lo = 0;
        int hi = n-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            String midSuffix = suffixes[mid].toString();
            int cmp = key.compareTo(midSuffix);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }

        return lo;
    }

}
