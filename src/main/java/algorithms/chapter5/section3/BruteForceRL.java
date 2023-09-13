package algorithms.chapter5.section3;

import java.util.ArrayList;
import java.util.List;

public class BruteForceRL {

    private String pat;

    public BruteForceRL(String pat) {
        this.pat = pat;
    }

    public int search(String txt) {
        int n = txt.length();
        int m = pat.length();
        for (int i = 0; i <= n - m; ++i) {
            int j;
            for (j = m-1; j >= 0; j--) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }

            if (j == 0) {
                return i;
            }
        }

        return n;
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

    private List<Integer> getMatchesIndices(String txt) {
        int n = txt.length();
        int m = pat.length();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i <= n - m; ++i) {
            int j;
            for (j = 0; j < m; ++j) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }

            if (j == m) {
                indices.add(i);
            }
        }

        return indices;
    }

}
