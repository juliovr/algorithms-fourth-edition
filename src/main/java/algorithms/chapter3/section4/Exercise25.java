package algorithms.chapter3.section4;

import java.util.Date;

public class Exercise25 {

    public static void main(String[] args) {

    }

    private class Transaction {
        private final String who;
        private final Date when;
        private final double amount;
        private final int hash;

        public Transaction(String who, Date when, double amount) {
            this.who = who;
            this.when = when;
            this.amount = amount;
            this.hash = computeHashCode();
        }

        private int computeHashCode() {
            int hash = 1;
            hash = 31 * hash + who.hashCode();
            hash = 31 * hash + when.hashCode();
            hash = 31 * hash + ((Double) amount).hashCode();
            return hash;
        }

        public int hashCode() {
            return hash;
        }
    }

}
