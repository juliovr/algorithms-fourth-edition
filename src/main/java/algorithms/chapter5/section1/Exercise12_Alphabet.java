package algorithms.chapter5.section1;

public class Exercise12_Alphabet {

    public static void main(String[] args) {
        System.out.println("Binary");
        Alphabet binary = new Alphabet(Alphabet.BINARY);
        System.out.println(binary.radix() + ", expected = 2");
        System.out.println(binary.lgR() + ", expected = 1");
        System.out.println(binary.toChar(0) + ", expected = 0");
        System.out.println(binary.toChar(1) + ", expected = 1");
        System.out.println(binary.toIndex('0') + ", expected = 0");
        System.out.println(binary.toIndex('1') + ", expected = 1");
        System.out.println(binary.contains('1') + ", expected = true");
        System.out.println(binary.contains('2') + ", expected = false");
        int[] binaryIndices = binary.toIndices("012");
        int[] expectedBinaryIndices = new int[] { 0, 1, -1 };
        System.out.println("indices:");
        for (int i = 0; i < binaryIndices.length; ++i) {
            System.out.println("\t" + binaryIndices[i] + "\texpected = " + expectedBinaryIndices[i]);
        }

        System.out.println(binary.toChars(binaryIndices) + ", expected = 01");

        System.out.println("DNA");
        Alphabet dna = new Alphabet(Alphabet.DNA);
        System.out.println(dna.radix() + ", expected = 4");
        System.out.println(dna.lgR() + ", expected = 2");
        System.out.println(dna.toChar(0) + ", expected = A");
        System.out.println(dna.toChar(3) + ", expected = T");
        System.out.println(dna.toIndex('A') + ", expected = 0");
        System.out.println(dna.toIndex('2') + ", expected = -1");
        System.out.println(dna.contains('C') + ", expected = true");
        System.out.println(dna.contains('2') + ", expected = false");
        int[] dnaIndices = dna.toIndices("ATCN");
        int[] expectedDnaIndices = new int[] { 0, 3, 1, -1 };
        System.out.println("indices:");
        for (int i = 0; i < dnaIndices.length; ++i) {
            System.out.println("\t" + dnaIndices[i] + "\texpected = " + expectedDnaIndices[i]);
        }

        System.out.println(dna.toChars(dnaIndices) + ", expected = ATC");
    }

}
