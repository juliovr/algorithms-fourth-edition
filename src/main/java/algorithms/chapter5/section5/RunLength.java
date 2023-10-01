package algorithms.chapter5.section5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class RunLength {

    public static void expand() {
        boolean b = false;
        while (!BinaryStdIn.isEmpty()) {
            char count = BinaryStdIn.readChar();
            for (int i = 0; i < count; ++i) {
                BinaryStdOut.write(b);
            }
            b = !b;
        }
        BinaryStdOut.close();
    }

    public static void compress() {
        char count = 0;
        boolean b;
        boolean old = false;
        while (!BinaryStdIn.isEmpty()) {
            b = BinaryStdIn.readBoolean();
            if (b != old) {
                BinaryStdOut.write(count, 8);
                count = 0;
                old = !old;
            } else {
                if (count == 255) {
                    BinaryStdOut.write(count, 8);
                    count = 0;
                    BinaryStdOut.write(count, 8);
                }
            }

            ++count;
        }

        BinaryStdOut.write(count);
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
