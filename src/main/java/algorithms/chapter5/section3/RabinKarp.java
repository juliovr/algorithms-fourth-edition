package algorithms.chapter5.section3;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp implements SubstringSearch {

    private String pat;
    private long patHash;
    private int m;
    private long q; // a large prime
    private int R = 256;
    private long RM; // R^(m-1) % q

    public RabinKarp(String pat) {
        this.pat = pat;
        m = pat.length();
        q = longRandomPrime();
        RM = 1;
        for (int i = 1; i <= m-1; i++) {
            RM = (R * RM) % q;
        }

        patHash = hash(pat, m);
    }

    private long longRandomPrime() {
        return 997;
    }

    public boolean check(int i) {
        return true;
    }

    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++) {
            h = (R * h + key.charAt(j)) % q;
        }

        return h;
    }

    public int search(String txt) {
        int n = txt.length();
        long txtHash = hash(txt, m);
        if (patHash == txtHash && check(0)) {
            return 0; // Match
        }

        for (int i = m; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match
            txtHash = (txtHash + q - RM*txt.charAt(i-m) % q) % q;
            txtHash = (txtHash*R + txt.charAt(i)) % q;
            if (patHash == txtHash) {
                if (check(i - m + 1)) {
                    return i - m + 1; // Match
                }
            }
        }

        return n; // No match
    }

    public void searchAll(String txt) {
        List<Integer> indices = getMatchesIndices(txt);

        if (indices.isEmpty()) {
            System.out.println("No occurrences");
        } else {
            boolean first = true;
            for (int i : indices) {
                if (first) {
                    System.out.print(i);
                    first = false;
                } else {
                    System.out.print(", " + i);
                }
            }
            System.out.println();
        }
    }

    public int count(String txt) {
        List<Integer> indices = getMatchesIndices(txt);
        return indices.size();
    }

    @Override
    public Iterable<Integer> findAll(String txt) {
        return getMatchesIndices(txt);
    }

    private List<Integer> getMatchesIndices(String txt) {
        List<Integer> indices = new ArrayList<>();
        int n = txt.length();
        long txtHash = hash(txt, m);
        if (patHash == txtHash && check(0)) {
            indices.add(0);
        }

        for (int i = m; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match
            txtHash = (txtHash + q - RM*txt.charAt(i-m) % q) % q;
            txtHash = (txtHash*R + txt.charAt(i)) % q;
            if (patHash == txtHash) {
                if (check(i - m + 1)) {
                    indices.add(i - m + 1);
                }
            }
        }

        return indices;
    }

}
