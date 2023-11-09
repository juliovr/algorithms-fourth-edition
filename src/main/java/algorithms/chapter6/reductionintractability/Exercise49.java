package algorithms.chapter6.reductionintractability;

public class Exercise49 {

    public static void main(String[] args) {
        int a = 37703491;
        int factor = findNontrivialFactor(a);
        if (factor != -1) {
            System.out.println("Nontrivial factor = " + factor);
        } else {
            System.out.println("The number " + a + " does not have nontrivial factors");
        }
    }

    private static int findNontrivialFactor(int a) {
        int n = a / 2;
        for (int i = 2; i < n; ++i) {
            if (a % i == 0) {
                return i;
            }
        }

        return -1;
    }

}
