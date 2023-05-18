package algorithms.chapter2.section5;

import algorithms.chapter2.section4.MaxPQ;
import algorithms.chapter2.section4.MinPQ;

public class Exercise22_StockMarketTrading {

    public static void main(String[] args) {
        StockMarketTrading market = new StockMarketTrading();
        
        market.addOffer(new Offer(OfferType.BUY, 10, 5));
        market.addOffer(new Offer(OfferType.BUY, 8, 12));
        
        market.addOffer(new Offer(OfferType.SELL, 11, 10));
        market.addOffer(new Offer(OfferType.SELL, 12, 5));
        market.addOffer(new Offer(OfferType.SELL, 8, 5));
        market.addOffer(new Offer(OfferType.SELL, 8, 5));
        market.addOffer(new Offer(OfferType.SELL, 8, 5));
    }


    /**
     * Time is not considering to make the trades, only the prices.
     */
    private static class StockMarketTrading {

        // PQ doesn't resize. Leave fixed size only for testing!!
        private MaxPQ<Offer> buyers = new MaxPQ<>(30);  // Max price to buy
        private MinPQ<Offer> sellers = new MinPQ<>(30); // Min price to sell

        public void addOffer(Offer offer) {
            switch (offer.type) {
            case BUY:
                buyers.insert(offer);
                break;
            case SELL:
                sellers.insert(offer);
                break;
            }

            // Make trading automatically to match up buyers and sellers.
            trade();
        }

        private void trade() {
            if (buyers.isEmpty() || sellers.isEmpty()) return;

            System.out.println("Trading...");

            Offer buy = buyers.findMax();
            Offer sell = sellers.findMin();
            if (buy.price >= sell.price) {
                System.out.println("Offers matched, making trade.");
                System.out.println("Buy offer: { price = " + buy.price + ", quantity = " + buy.quantity + " }");
                System.out.println("Sell offer: { price = " + sell.price + ", quantity = " + sell.quantity + " }");

                if (buy.quantity > sell.quantity) {
                    buy.quantity -= sell.quantity;
                    sellers.delMin();
                } else if (buy.quantity < sell.quantity) {
                    sell.quantity -= buy.quantity;
                    buyers.delMax();
                } else {
                    buyers.delMax();
                    sellers.delMin();
                }

                System.out.println("Trade done");
            }
        }

    }

    private static class Offer implements Comparable<Offer> {

        public OfferType type;
        public int price;
        public int quantity;

        public Offer(OfferType type, int price, int quantity) {
            this.type = type;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public int compareTo(Offer o) {
            if (this.price < o.price) return -1;
            if (this.price > o.price) return 1;
            return 0;
        }

    }

    private static enum OfferType {
        BUY, SELL;
    }

}
