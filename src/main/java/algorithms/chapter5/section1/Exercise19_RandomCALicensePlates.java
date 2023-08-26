package algorithms.chapter5.section1;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise19_RandomCALicensePlates {

    /**
     * d: digit
     * c: character
     * Plates format: dcccddd
     * Example: 4PGC938
     */
    private static final char[] PLATES_STRUCTURE = new char[] {
            'd', 'c', 'c', 'c', 'd', 'd', 'd'
    };

    public static void main(String[] args) {
        int n = 10;
        String[] plates = randomPlatesCA(n);
        for (String s : plates) {
            System.out.println(s);
        }
    }

    public static String[] randomPlatesCA(int n) {
        String[] plates = new String[n];

        for (int i = 0; i < n; ++i) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < PLATES_STRUCTURE.length; ++j) {
                char type = PLATES_STRUCTURE[j];
                switch (type) {
                    case 'd':
                        int digit = StdRandom.uniform(0, 10);
                        sb.append(digit);
                        break;
                    case 'c':
                        int character = StdRandom.uniform('A', 'Z'+1);
                        sb.append((char)character);
                        break;
                    default:
                        throw new RuntimeException(String.format("Type %c in plate is not defined", type));
                }
            }

            plates[i] = sb.toString();
        }

        return plates;
    }

}
