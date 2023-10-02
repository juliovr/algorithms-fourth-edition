package algorithms.chapter5.section5;

import edu.princeton.cs.algs4.BinaryStdIn;

public class HexDump {

    public static void main(String[] args) {
        int bytesPerLine = 16;
        if (args.length == 1) {
            bytesPerLine = Integer.parseInt(args[0]);
        }

        char[] charsPerLine = new char[bytesPerLine];
        int charIndex = 0;

        int bytesCount;
        for (bytesCount = 0; !BinaryStdIn.isEmpty(); ++bytesCount) {
            if (bytesPerLine == 0) {
                BinaryStdIn.readChar();
                continue;
            }

            if (bytesCount == 0) {
                // Don't print anything in the first pass
            } else if (bytesCount % bytesPerLine == 0) {
//                // Print ascii representation of the hex values
//                System.out.print("\t\t");
//                for (int i = 0; i < charsPerLine.length; ++i) {
//                    System.out.printf("%c ", charsPerLine[i]);
//                }

                System.out.println();
            } else {
                System.out.print(" ");
            }

            char c = BinaryStdIn.readChar();
            System.out.printf("%02x", (c & 0xFF));
            charsPerLine[charIndex++] = c;
            if (charIndex >= charsPerLine.length) {
                charIndex = 0;
            }
        }

        if (bytesPerLine != 0) {
//            System.out.print("\t\t");
//            for (int i = 0; i < charsPerLine.length; ++i) {
//                System.out.printf("%c ", charsPerLine[i]);
//            }

            System.out.println();
        }

        System.out.println(bytesCount + " bytes");
        System.out.println((bytesCount*8) + " bits");
    }

}
