package algorithms.chapter5.section3;

import edu.princeton.cs.algs4.In;

public class Exercise39_Timings {

    public static void main(String[] args) {
        String pattern = "it is a far far better thing that i do than i have ever done";
        String txt = new In("test_data/tale.txt").readAll();
        System.out.println("txt length = " + txt.length());

        runExperiments(10_000, pattern, txt);
    }

    private static void runExperiments(int n, String pattern, String txt) {
        System.out.println("Brute force:");
        Statistics brute = run(new Brute(pattern), txt, n);
        System.out.println(brute);

        System.out.println("KMP:");
        Statistics kmp = run(new KMP(pattern), txt, n);
        System.out.println(kmp);

        System.out.println("Boyer Moore:");
        Statistics boyerMoore = run(new BoyerMoore(pattern), txt, n);
        System.out.println(boyerMoore);

        System.out.println("Rabin Karp:");
        Statistics rabinKarp = run(new RabinKarp(pattern), txt, n);
        System.out.println(rabinKarp);
    }

    private static class Statistics {
        long min;
        long max;
        long avg;
        long total;

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\tmin = " + min + " ms\n");
            sb.append("\tmax = " + max + " ms\n");
            sb.append("\tavg = " + avg + " ms\n");
            sb.append("\ttotal = " + total + " ms\n");

            return sb.toString();
        }
    }

    private static Statistics run(SubstringSearch searchAlgorithm, String txt, int n) {
        Statistics statistics = new Statistics();
        statistics.min = Long.MAX_VALUE;
        statistics.max = 0;
        statistics.avg = 0;

        for (int i = 0; i < n; ++i) {
            long start = System.currentTimeMillis();
            searchAlgorithm.search(txt);
            long elapsedTime = System.currentTimeMillis() - start;

            statistics.total += elapsedTime;

            if (elapsedTime < statistics.min) {
                statistics.min = elapsedTime;
            }

            if (elapsedTime > statistics.max) {
                statistics.max = elapsedTime;
            }
        }

        statistics.avg = statistics.total / n;

        return statistics;
    }

}
