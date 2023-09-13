package algorithms.chapter5.section3;

import java.util.ArrayList;
import java.util.List;

public class BoyerMoore {

    private int[] right;
    private String pat;

    public BoyerMoore(String pat) {
        this.pat = pat;
        int m = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0; c < R; c++) {
            right[c] = -1;              // -1 for chars not in pattern
        }

        for (int j = 0; j < m; j++) {   // Rightmost position for
            right[pat.charAt(j)] = j;   // chars in pattern
        }
    }

    public int search(String txt) {
        int n = txt.length();
        int m = pat.length();
        int skip;
        for (int i = 0; i <= n-m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = j - right[txt.charAt(i+j)];
                    if (skip < 1) {
                        skip = 1;
                    }
                    break;
                }
            }

            if (skip == 0) {
                return i; // Found
            }
        }

        return n; // Not found
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
        List<Integer> indices = new ArrayList<>();
        int n = txt.length();
        int m = pat.length();
        int skip;
        for (int i = 0; i <= n-m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = j - right[txt.charAt(i+j)];
                    if (skip < 1) {
                        skip = 1;
                    }
                    break;
                }
            }

            if (skip == 0) {
                indices.add(i);
                ++i;
            }
        }

        return indices;
    }

}
