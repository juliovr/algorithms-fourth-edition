package algorithms.chapter3.section5;

import algorithms.chapter3.section3.RedBlackBST;

public class Exercise24_IntervalSearch {

    public static void main(String[] args) {
        IntervalSearch.Interval[] intervals = new IntervalSearch.Interval[] {
                new IntervalSearch.Interval(1643, 2033),
                new IntervalSearch.Interval(5532, 7643),
                new IntervalSearch.Interval(8999, 10332),
                new IntervalSearch.Interval(5666653, 5669321)
        };
        IntervalSearch intervalSearch = new IntervalSearch(intervals);
        System.out.println("Find 9122 = " + intervalSearch.findInterval(9122) + ", expected = " + intervals[2]);
        System.out.println("Find 8122 = " + intervalSearch.findInterval(8122) + ", expected = null");
    }

    private static class IntervalSearch {

        private RedBlackBST<Integer, Interval> bst = new RedBlackBST<>();

        private static class Interval {
            private int min;
            private int max;
            public Interval(int min, int max) {
                this.min = min;
                this.max = max;
            }

            public String toString() {
                return "[min = " + min + ", max = " + max + "]";
            }

        }

        public IntervalSearch(Interval... intervals) {
            for (Interval interval: intervals) {
                bst.put(interval.min, interval);
            }
        }

        public Interval findInterval(int query) {
            int key = bst.floor(query);
            Interval interval = bst.get(key);
            if (interval != null && query < interval.max) {
                return interval;
            } else {
                return null;
            }
        }

    }

}
