package algorithms.chapter5.section3;

import java.util.ArrayList;
import java.util.List;

public class KMP implements SubstringSearch {

    private String pat;
    private int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int m = pat.length();
        int R = 256;
        dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][x];      // Copy mismatch cases
            }

            dfa[pat.charAt(j)][j] = j+1;    // set match case
            x = dfa[pat.charAt(j)][x];      // Update restart state
        }
    }

    public int[][] dfa() {
        return dfa;
    }

    public int search(String txt) {
        int i, j, n = txt.length(), m = pat.length();
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }

        if (j == m) {
            return i - m;   // Found (hit end of pattern)
        } else {
            return n;       // Not found (hit end of text)
        }
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
        int i, j, n = txt.length(), m = pat.length();
        for (i = 0, j = 0; i < n; i++) {
            j = dfa[txt.charAt(i)][j];
            if (j == m) {
                indices.add(i - m + 1);
                j = 0;
            }
        }

        return indices;
    }

}
