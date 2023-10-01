package algorithms.chapter5.section5;

import edu.princeton.cs.algs4.BinaryStdIn;

public class BinaryDump {

    public static void main(String[] args) {
        int bitsPerLine = 16;
        if (args.length == 1) {
            bitsPerLine = Integer.parseInt(args[0]);
        }

        int count;
        for (count = 0; !BinaryStdIn.isEmpty(); ++count) {
            if (bitsPerLine == 0) {
                BinaryStdIn.readBoolean();
                continue;
            }

            if (count != 0 && count % bitsPerLine == 0) {
                System.out.println();
            }

            if (BinaryStdIn.readBoolean()) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }

        if (bitsPerLine != 0) {
            System.out.println();
        }

        System.out.println(count + " bits");
    }

}
