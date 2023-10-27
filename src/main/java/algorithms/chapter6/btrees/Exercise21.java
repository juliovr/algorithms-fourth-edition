package algorithms.chapter6.btrees;

import edu.princeton.cs.algs4.StdRandom;

public class Exercise21 {

    private static class BTreeSETExternalPageCounter<Key extends Comparable<Key>> {

        private Page<Key> root;
        private int externalPagesCount = 1;
        private int m;

        public BTreeSETExternalPageCounter(Key sentinel, int m) {
            this.m = m;
            root = new Page<>(true, m);
            add(sentinel);
        }

        public boolean contains(Key key) {
            return contains(root, key);
        }

        private boolean contains(Page<Key> page, Key key) {
            if (page.isExternal()) {
                return page.contains(key);
            }

            return contains(page.next(key), key);
        }

        public void add(Key key) {
            add(root, key);
            if (root.isFull()) {
                if (root.isExternal()) {
                    ++externalPagesCount;
                }

                Page<Key> left = root;
                Page<Key> right = root.split();
                root = new Page<>(false, m);
                root.add(left);
                root.add(right);
            }
        }

        private void add(Page<Key> page, Key key) {
            if (page.isExternal()) {
                page.add(key);
                return;
            }

            Page<Key> next = page.next(key);
            add(next, key);
            if (next.isFull()) {
                Page<Key> newPage = next.split();
                if (newPage.isExternal()) {
                    ++externalPagesCount;
                }

                page.add(newPage);
            }
            next.close();
        }

    }

    public static void main(String[] args) {
        int[] mArray = new int[] { 4, 8, 16, 32, 64, 128, 256, 512 };
        int[] nArray = new int[] { 100, 1_000, 10_000, 100_000 };
        int numberOfExperiments = 10;

        System.out.printf("%8s%4s%10s\n", "n", "m", "avg");
        for (int i = 0; i < mArray.length; ++i) {
            int m = mArray[i];

            int totalExternalPages = 0;
            for (int j = 0; j < nArray.length; ++j) {
                int n = nArray[j];

                for (int k = 0; k < numberOfExperiments; ++k) {
                    BTreeSETExternalPageCounter<Integer> btree = new BTreeSETExternalPageCounter<>(Integer.MIN_VALUE, m);
                    for (int itemIndex = 0; itemIndex < n; ++itemIndex) {
                        int key = StdRandom.uniform(Integer.MAX_VALUE);
                        btree.add(key);
                    }

                    totalExternalPages += btree.externalPagesCount;
                }

                int avg = (totalExternalPages / numberOfExperiments);
                System.out.printf("%8d%4d%10d\n", n, m, avg);
            }
        }
    }

}
