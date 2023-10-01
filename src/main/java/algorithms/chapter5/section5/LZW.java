package algorithms.chapter5.section5;

import algorithms.chapter5.section2.TST;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * Compress command: java -classpath "D:\projects\algorithms-fourth-edition\target\classes" algorithms.chapter5.section5.LZW - < ..\..\test_data\5.5_test.txt | java -classpath "D:\projects\algorithms-fourth-edition\target\classes" algorithms.chapter5.section5.HexDump
 */
public class LZW {

    private static final int R = 128;
    private static final int L = 4096;
    private static final int WIDTH = 8;

    public static void compress() {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<>();

        for (int i = 0; i < R; ++i) {
            st.put("" + (char)i, i);
        }

        int code = R+1; // R is codeword for EOF

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);
            BinaryStdOut.write(st.get(s), WIDTH);
            int t = s.length();
            if (t < input.length() && code < L) {
                st.put(input.substring(0, t + 1), code++);
            }

            input = input.substring(t);
        }

        BinaryStdOut.write(R, WIDTH); // EOF
        BinaryStdOut.close();
    }

    public static void expand() {
        String[] st = new String[L];
        int i; // next available codeword value

        for (i = 0; i < R; ++i) {
            st[i] = "" + (char)i;
        }

        st[i++] = " "; // (unused) lookahead for EOF

        int codeword = BinaryStdIn.readInt(WIDTH);
        String val = st[codeword];
        while (true) {
            BinaryStdOut.write(val); // Write current substring
            codeword = BinaryStdIn.readInt(WIDTH);
            if (codeword == R) break;
            String s = st[codeword];        // Get next codeword
            if (i == codeword) {            // If lookahead is invalid,
                s = val + val.charAt(0);    //     make codeword from last one
            }
            if (i < L) {
                st[i++] = val + s.charAt(0); // Add new entry to code table
            }

            val = s; // Update current codeword
        }

        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        String usage = "[USAGE]: '+' for expand or '-' for compress";
        if (args.length == 0) {
            throw new IllegalArgumentException(usage);
        }

        if      ("+".equals(args[0])) expand();
        else if ("-".equals(args[0])) compress();
        else throw new IllegalArgumentException(usage);
    }

}
