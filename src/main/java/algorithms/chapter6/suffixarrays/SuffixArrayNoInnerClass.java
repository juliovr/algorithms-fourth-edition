package algorithms.chapter6.suffixarrays;

public class SuffixArrayNoInnerClass {

    private final char[] textChars;
    private final int[] indices;

    public SuffixArrayNoInnerClass(String text) {
        this.textChars = text.toCharArray();
        this.indices = new int[this.textChars.length];
        for (int i = 0; i < this.indices.length; ++i) {
            this.indices[i] = i;
        }

        sortIndices(0, this.indices.length - 1);
    }

    private void sortIndices(int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo+1, gt = hi;
        int v = lo;
        while (i <= gt) {
            int cmp = compare(indices[i], indices[v]);
            if      (cmp < 0) swap(lt++, i++);
            else if (cmp > 0) swap(i, gt--);
            else i++;
        }

        sortIndices(lo, lt - 1);
        sortIndices(gt + 1, hi);
    }

    private void swap(int i, int j) {
        int temp = indices[i];
        indices[i] = indices[j];
        indices[j] = temp;
    }

    private int compare(int i, int j) {
        if (i == j) {
            return 0;
        }

        int iLength = suffixLength(i);
        int jLength = suffixLength(j);
        int n = Math.min(iLength, jLength);
        for (int index = 0; index < n; ++index) {
            if (textChars[i] < textChars[j]) return -1;
            if (textChars[i] > textChars[j]) return +1;
        }

        return iLength - jLength;
    }

    private int suffixLength(int i) {
        return textChars.length - i;
    }


    public int index(int i) {
        return indices[i];
    }

    public int length() {
        return textChars.length;
    }

    public String select(int i) {
        StringBuilder sb = new StringBuilder();
        int startIndex = indices[i];
        for (int j = startIndex; j < textChars.length; ++j) {
            sb.append(textChars[j]);
        }

        return sb.toString();
    }

    public int lcp(int i) {
        if (i < 1 || i > (textChars.length-1)) throw new IllegalArgumentException("i must be between 1 and n-1");

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
        int hi = textChars.length-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            String midSuffix = select(mid);
            int cmp = key.compareTo(midSuffix);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }

        return lo;
    }

}
