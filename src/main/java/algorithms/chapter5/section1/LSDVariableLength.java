package algorithms.chapter5.section1;

public class LSDVariableLength {

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return 0;
        }
    }

    public static void sort(String[] a) {
        int n = a.length;
        int R = 256;
        String[] aux = new String[n];

        int w = getMaxStringWidth(a);

        for (int d = w-1; d >= 0; d--) {
            int[] count = new int[R+1];

            // Compute frequency counts
            for (int i = 0; i < n; ++i) {
                int digit = charAt(a[i], d);
                count[digit + 1]++;
            }

            // Transform counts to indices. Every index in count[] has the number of elements behind it
            for (int r = 0; r < R; ++r) {
                count[r+1] += count[r];
            }

            // Distribute
            for (int i = 0; i < n; ++i) {
                int digit = charAt(a[i], d);
                aux[count[digit]++] = a[i];
            }

            // Copy back
            for (int i = 0; i < n; ++i) {
                if (aux[i] != null) {
                    a[i] = aux[i];
                }
            }
        }
    }

    private static int getMaxStringWidth(String[] a) {
        int w = 0;
        for (int i = 0; i < a.length; ++i) {
            String s = a[i];
            if (s.length() > w) {
                w = s.length();
            }
        }

        return w;
    }

}
