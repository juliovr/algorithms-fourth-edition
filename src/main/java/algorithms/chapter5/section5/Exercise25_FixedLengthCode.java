package algorithms.chapter5.section5;

import algorithms.chapter5.section1.Alphabet;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.ArrayList;
import java.util.List;

public class Exercise25_FixedLengthCode {

    /**
     * Compress: java -classpath "D:\projects\algorithms-fourth-edition\target\classes" algorithms.chapter5.section5.Exercise25_FixedLengthCode$RLE - < ..\..\test_data\5.5_test.txt | java -classpath "D:\projects\algorithms-fourth-edition\target\classes" algorithms.chapter5.section5.HexDump
     * Compress and Expand: java -classpath "D:\projects\algorithms-fourth-edition\target\classes" algorithms.chapter5.section5.Exercise25_FixedLengthCode$RLE - < ..\..\test_data\5.5_test.txt | java -classpath "D:\projects\algorithms-fourth-edition\target\classes" algorithms.chapter5.section5.Exercise25_FixedLengthCode$RLE +
     */
    private static class RLE {

        private static final char EOF = 0xFF;

        public static void expand() {
            // Read Alphabet
            int alphabetLength = BinaryStdIn.readInt(8);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < alphabetLength; ++i) {
                char c = BinaryStdIn.readChar();
                sb.append(c);
            }

            Alphabet alphabet = new Alphabet(sb.toString());

            // Read data
            int index;
            while ((index = BinaryStdIn.readInt(8)) != EOF) {
                int count = BinaryStdIn.readInt(8);
                char c = alphabet.toChar(index);
                for (int i = 0; i < count; ++i) {
                    BinaryStdOut.write(c);
                }
            }

            BinaryStdOut.close();
        }

        private static String extractDifferentCharacters(String s) {
            List<Character> characters = new ArrayList<>();
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (!characters.contains(c)) {
                    characters.add(c);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (char c : characters) {
                sb.append(c);
            }

            return sb.toString();
        }

        public static void compress() {
            String s = BinaryStdIn.readString();
            String alpha = extractDifferentCharacters(s);
            Alphabet alphabet = new Alphabet(alpha);

            // Write alphabet
            BinaryStdOut.write(alpha.length(), 8);
            for (int i = 0; i < alpha.length(); ++i) {
                BinaryStdOut.write(alpha.charAt(i), 8);
            }

            // Write data
            for (int i = 0; i < s.length();) {
                char c = s.charAt(i);
                int index = alphabet.toIndex(c);
                int count = 0;
                for (int j = i; j < s.length() && s.charAt(j) == c; ++j) {
                    ++count;

                    if (count == 255) {
                        break;
                    }
                }

                i += count;

                BinaryStdOut.write(index, 8);
                BinaryStdOut.write(count, 8);
            }

            BinaryStdOut.write(EOF, 8);
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

}
