package algorithms.chapter3.section5;

import algorithms.chapter3.section3.RedBlackBST;

import java.time.LocalTime;

public class Exercise25_RegistrarScheduling {

    public static void main(String[] args) {

        Exercise25_RegistrarScheduling.Scheduling.Interval[] intervals = new Exercise25_RegistrarScheduling.Scheduling.Interval[] {
                new Exercise25_RegistrarScheduling.Scheduling.Interval(LocalTime.of(9, 0), LocalTime.of(9, 50)),
                new Exercise25_RegistrarScheduling.Scheduling.Interval(LocalTime.of(10, 0), LocalTime.of(10, 50)),
                new Exercise25_RegistrarScheduling.Scheduling.Interval(LocalTime.of(11, 0), LocalTime.of(11, 50)),
                new Exercise25_RegistrarScheduling.Scheduling.Interval(LocalTime.of(13, 0), LocalTime.of(13, 50)),
                new Exercise25_RegistrarScheduling.Scheduling.Interval(LocalTime.of(14, 0), LocalTime.of(14, 50)),
                new Exercise25_RegistrarScheduling.Scheduling.Interval(LocalTime.of(15, 0), LocalTime.of(15, 50)),
        };
        Exercise25_RegistrarScheduling.Scheduling intervalSearch = new Exercise25_RegistrarScheduling.Scheduling(intervals);
        System.out.println("Find 9:00 = " + intervalSearch.take(LocalTime.of(9, 0)) + ", expected = " + intervals[0]);
        System.out.println("Find 12:00 = " + intervalSearch.take(LocalTime.of(12, 0)) + ", expected = null");
    }

    private static class Scheduling {

        private RedBlackBST<LocalTime, Interval> bst = new RedBlackBST<>();

        private static class Interval {
            private LocalTime start;
            private LocalTime end;
            private boolean taken;
            public Interval(LocalTime start, LocalTime end) {
                this.start = start;
                this.end = end;
            }

            public void take() {
                this.taken = true;
            }

            public String toString() {
                return "[start = " + start + ", end = " + end + ", taken = " + taken + "]";
            }

        }

        public Scheduling(Interval... intervals) {
            for (Interval interval: intervals) {
                bst.put(interval.start, interval);
            }
        }

        public Interval take(LocalTime query) {
            Interval interval = findInterval(query);
            if (interval != null) {
                interval.take();
            }

            return interval;
        }

        public Interval findInterval(LocalTime query) {
            LocalTime key = bst.floor(query);
            Interval interval = bst.get(key);
            if (interval != null && query.isBefore(interval.end)) {
                return interval;
            } else {
                return null;
            }
        }

    }
    
}
