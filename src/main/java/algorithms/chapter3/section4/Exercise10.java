package algorithms.chapter3.section4;

public class Exercise10 {

    public static void main(String[] args) {
        char[] letters = "EASYQUTION".toCharArray();
        LinearProbingHashST st = new LinearProbingHashST(16);
        for (int i = 0; i < letters.length; ++i) {
            st.put(letters[i], i);
        }

        System.out.println(st);
    }

    private static int alphabetValue(char c) {
        return ((int)c - 'A' + 1);
    }

    public static class LinearProbingHashST {

        private int n;
        private int m;
        private Character[] keys;
        private int[] values;

        public LinearProbingHashST() {
            this(16);
        }

        public LinearProbingHashST(int m) {
            this.m = m;
            keys = new Character[m];
            values = new int[m];
        }

        private int hash(char c) {
            return (11*alphabetValue(c)) % m;
        }

        private void resize(int cap) {
            LinearProbingHashST t = new LinearProbingHashST(cap);
            for (int i = 0; i < m; ++i) {
                if (keys[i] != null) {
                    t.put(keys[i], values[i]);
                }
            }

            // Assign the new arrays into this object
            keys = t.keys;
            values = t.values;
            m = t.m;
        }

        public void put(Character key, int value) {
            if (n >= m/2) resize(2*m);

            int i;
            for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
                if (keys[i].equals(key)) {
                    values[i] = value;
                    return;
                }
            }

            keys[i] = key;
            values[i] = value;
            ++n;
        }

    }

}
