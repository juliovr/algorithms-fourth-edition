package algorithms.chapter3.section4;

public class Exercise34_HashCost {

    public static void main(String[] args) {
        Experiment compareToExperiment = new CompareToExperiment();
        Experiment hashExperiment = new HashExperiment();

        System.out.print("Generating strings...");
        int times = 10_000_000;
        String[] as = generateRandomStrings(times);
        String[] bs = generateRandomStrings(times);
        System.out.println(" Done");

        float elapsedCompareTo = runExperiments(compareToExperiment, as, bs);
        float elapsedHash = runExperiments(hashExperiment, as, bs);

        System.out.println("Time elapsed compareTo = " + elapsedCompareTo);
        System.out.println("Time elapsed hash = " + elapsedHash);
        System.out.println("Ratio: compareTo is " + (elapsedHash/elapsedCompareTo) + " faster than hash");
    }

    private static String[] generateRandomStrings(int times) {
        String[] strings = new String[times];
        for (int i = 0; i < times; ++i) {
            int length = Math.max(1, (int)(50*Math.random()));
            strings[i] = generateRandomString(length);
        }

        return strings;
    }

    private static String generateRandomString(int length) {
        String s = "";
        for (int i = 0; i < length; ++i) {
            s += (char)('A' + (Math.random()*('z' - 'A')));
        }

        return s;
    }

    private static long runExperiments(Experiment experiment, String[] as, String[] bs) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < as.length; ++i) {
            String a = as[i];
            String b = bs[i];
            experiment.run(a, b);
        }

        return System.currentTimeMillis() - start;
    }

    private interface Experiment {
        boolean run(String a, String b);
    }

    private static class CompareToExperiment implements Experiment {
        @Override
        public boolean run(String a, String b) {
            return a.compareTo(b) == 0;
        }
    }

    private static class HashExperiment implements Experiment {
        @Override
        public boolean run(String a, String b) {
            return a.hashCode() == b.hashCode();
        }
    }

}
