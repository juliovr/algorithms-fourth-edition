package algorithms.chapter5.section2;

public class Exercise16_DocumentSimilarity {

    public static void main(String[] args) {
        //
        // For now using raw hard-coded text instead of files
        //
        String file1 = "this is a file with some strings";
        String file2 = "this is a file with some nice strings";

        computeKSimilarity(1, file1, file2);
    }

    private static class KGramInformation {
        int frequencyCountFile1;
        int frequencyCountFile2;

        KGramInformation(int frequencyCountFile1, int frequencyCountFile2) {
            this.frequencyCountFile1 = frequencyCountFile1;
            this.frequencyCountFile2 = frequencyCountFile2;
        }
    }

    private static final int FILE_ID_1 = 1;
    private static final int FILE_ID_2 = 2;

    public static void computeKSimilarity(int k, String file1, String file2) {
        //
        // Using word-level k-grams. I want to match words, not characters.
        //
        String[] wordsFile1 = file1.split(" ");
        String[] wordsFile2 = file2.split(" ");

        TSTWithSize<KGramInformation> tst = new TSTWithSize<>();
        computeFrequencyCount(tst, wordsFile1, k, FILE_ID_1);
        computeFrequencyCount(tst, wordsFile2, k, FILE_ID_2);

        int totalKGram = tst.size();

        // Calculate Euclidean distance
        double sumDistances = 0.0;
        for (String key : tst.keys()) {
            KGramInformation information = tst.get(key);
            double frequency1 = (double)information.frequencyCountFile1 / (double)totalKGram;
            double frequency2 = (double)information.frequencyCountFile2 / (double)totalKGram;

            sumDistances += Math.pow(frequency2 - frequency1, 2); // (Qi - Pi)^2
        }

        System.out.println(Math.sqrt(sumDistances));
    }

    private static void computeFrequencyCount(TSTWithSize<KGramInformation> tst, String[] words, int k, int fileId) {
        for (int i = 0; i < words.length - k + 1; ++i) {
            for (int j = 0; j < k; ++j) {
                String word = words[i + j];
                KGramInformation information = tst.get(word);
                if (information == null) {
                    if (fileId == FILE_ID_1) {
                        tst.put(word, new KGramInformation(1, 0));
                    } else if (fileId == FILE_ID_2) {
                        tst.put(word, new KGramInformation(0, 1));
                    }
                } else {
                    if (fileId == FILE_ID_1) {
                        ++information.frequencyCountFile1;
                    } else if (fileId == FILE_ID_2) {
                        ++information.frequencyCountFile2;
                    }
                }
            }
        }
    }

}
