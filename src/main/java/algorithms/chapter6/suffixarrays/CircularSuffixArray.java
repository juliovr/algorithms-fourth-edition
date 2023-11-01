package algorithms.chapter6.suffixarrays;

import java.util.Arrays;

public class CircularSuffixArray {

    private final CircularSuffix[] circularSuffixes;
    private final int n;

    public CircularSuffixArray(String text) {
        n = text.length();
        this.circularSuffixes = new CircularSuffix[n];
        for (int i = 0; i < n; ++i) {
            circularSuffixes[i] = new CircularSuffix(text, i);
        }

        Arrays.sort(circularSuffixes);
    }

    private static class CircularSuffix implements Comparable<CircularSuffix> {

        private final String text;
        private final int index;

        private CircularSuffix(String text, int index) {
            this.text = text;
            this.index = index;
        }

        private int length() {
            return text.length();
        }

        private char charAt(int i) {
            int circularIndex = ((index + i) % text.length());
            return text.charAt(circularIndex);
        }

        public String toString() {
            String lastCharacters = text.substring(index);
            String firstCharacters = text.substring(0, index);
            return lastCharacters + firstCharacters;
        }

        public int compareTo(CircularSuffix that) {
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
        return circularSuffixes[i].index;
    }

    public int length() {
        return circularSuffixes.length;
    }

    public String select(int i) {
        return circularSuffixes[i].toString();
    }

    public String smallestCyclicRotation() {
        return select(0);
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
            String midSuffix = circularSuffixes[mid].toString();
            int cmp = key.compareTo(midSuffix);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }

        return lo;
    }

}
