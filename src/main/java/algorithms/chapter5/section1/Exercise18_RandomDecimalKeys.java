package algorithms.chapter5.section1;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise18_RandomDecimalKeys {

    public static void main(String[] args) {
        int n = 10;
        int w = 5;
        String[] keys = randomDecimalKeys(n, w);
        for (String s : keys) {
            System.out.println(s);
        }
    }

    public static String[] randomDecimalKeys(int n, int w) {
        String[] keys = new String[n];
        for (int i = 0; i < n; ++i) {
            StringBuilder sb = new StringBuilder();
            int decimalPointIndex = StdRandom.uniform(1, w);
            boolean decimalPointInserted = false;
            for (int j = 0; j < w; ++j) {
                if (j == decimalPointIndex) {
                    sb.append('.');
                    decimalPointInserted = true;
                }

                int digit;
                if (decimalPointInserted) {
                    // Avoid inserting leading zeros before the decimal point, giving numbers like 04.12
                    digit = StdRandom.uniform(0, 10);
                } else {
                    digit = StdRandom.uniform(1, 10);
                }

                sb.append(digit);
            }

            keys[i] = sb.toString();
        }

        return keys;
    }

}
