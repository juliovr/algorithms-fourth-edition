package algorithms.chapter5.section3;

public class KMPTandemRepeat {

    private String pat;
    private int[][] dfa;

    public KMPTandemRepeat(String pattern) {
        this.pat = pattern + pattern; // Duplicate because should be consecutive
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

    public int search(String txt) {
        int i, j, n = txt.length(), m = pat.length();
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }

        if (j == m) {
            return i - m;   // Found (hit end of pattern)
        } else {
            return -1;       // Not found (hit end of text)
        }
    }

    public int findTandemRepeat(String txt) {
        return search(txt);
    }

}
