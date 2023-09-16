package algorithms.chapter5.section3;

public class Exercise26_CyclicRotationCheck {

    public static void main(String[] args) {
        boolean check1 = isCyclicRotation("example", "ampleex");
        System.out.println("Check 1: " + check1 + " Expected: true");

        boolean check2 = isCyclicRotation("example", "ample");
        System.out.println("Check 2: " + check2 + " Expected: false");

        boolean check3 = isCyclicRotation("stackoverflow", "tackoverflows");
        System.out.println("Check 3: " + check3 + " Expected: true");

        boolean check4 = isCyclicRotation("stackoverflow", "ackoverflowst");
        System.out.println("Check 4: " + check4 + " Expected: true");

        boolean check5 = isCyclicRotation("stackoverflow", "overflowstack");
        System.out.println("Check 5: " + check5 + " Expected: true");

        boolean check6 = isCyclicRotation("stackoverflow", "stackoverflwo");
        System.out.println("Check 6: " + check6 + " Expected: false");
    }

    private static boolean isCyclicRotation(String original, String test) {
        if (original.length() != test.length()) {
            return false;
        }

        String concatenated = test + test;
        RabinKarp rabinKarp = new RabinKarp(original);
        return rabinKarp.search(concatenated) != concatenated.length();
    }

}
