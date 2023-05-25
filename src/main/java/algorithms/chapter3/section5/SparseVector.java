package algorithms.chapter3.section5;

import algorithms.chapter3.section4.SeparateChainingHashST;

public class SparseVector {

    private SeparateChainingHashST<Integer, Double> st = new SeparateChainingHashST<>();

    public int size() {
        return st.size();
    }

    public int maxKey() {
        int max = 0;
        for (int i: st.keys()) {
            if (i > max) {
                max = i;
            }
        }

        return max;
    }

    public void put(int i, double x) {
        st.put(i, x);
    }

    public double get(int i) {
        if (!st.contains(i)) {
            return 0.0;
        } else {
            return st.get(i);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i: st.keys()) {
            sb.append("[" + i + "] = " + get(i) + "\n");
        }

        return sb.toString();
    }

    public double dot(double[] that) {
        double sum = 0.0;
        for (int i: st.keys()) {
            sum += that[i]*this.get(i);
        }

        return sum;
    }

    public SparseVector sum(SparseVector that) {
        SparseVector result = new SparseVector();
        int n = Math.max(this.maxKey(), that.maxKey());

        for (int i = 0; i <= n; ++i) {
            double a = this.get(i);
            double b = that.get(i);
            double c = a + b;
            if (c != 0.0) {
                result.put(i, c);
            }
        }

        return result;
    }

}
