package algorithms.chapter6.suffixarrays;

public class Exercise33_CircularStringLinearization {

    public static void main(String[] args) {
        System.out.println("Circular Suffix Array method");

        String string1 = "rene";
        CircularSuffixArray circularSuffixArray1 = new CircularSuffixArray(string1);
        String smallestCyclicRotation1 = circularSuffixArray1.smallestCyclicRotation();
        System.out.println("Smallest Cyclic Rotation: " + smallestCyclicRotation1);
        System.out.println("Expected:                 ener");

        String string2 = "mississippi";
        CircularSuffixArray circularSuffixArray2 = new CircularSuffixArray(string2);
        String smallestCyclicRotation2 = circularSuffixArray2.smallestCyclicRotation();
        System.out.println("Smallest Cyclic Rotation: " + smallestCyclicRotation2);
        System.out.println("Expected:                 imississipp");

        String string3 = "barcelona";
        CircularSuffixArray circularSuffixArray3 = new CircularSuffixArray(string3);
        String smallestCyclicRotation3 = circularSuffixArray3.smallestCyclicRotation();
        System.out.println("Smallest Cyclic Rotation: " + smallestCyclicRotation3);
        System.out.println("Expected:                 abarcelon");
    }

}
