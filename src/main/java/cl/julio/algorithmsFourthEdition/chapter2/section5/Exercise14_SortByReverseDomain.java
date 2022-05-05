package cl.julio.algorithmsFourthEdition.chapter2.section5;

import java.util.Arrays;

public class Exercise14_SortByReverseDomain {

    public static void main(String[] args) {
        Domain[] domains = new Domain[] {
            new Domain("cs.princeton.edu"),
            new Domain("google.com"),
            new Domain("youtube.com"),
            new Domain("www.wikipedia.org"),
        };

        Arrays.sort(domains);

        for (int i = 0; i < domains.length; ++i) {
            System.out.println(domains[i].reverseDomain);
        }
        
    }

    private static class Domain implements Comparable<Domain> {

        public String reverseDomain;

        public Domain(String url) {
            this.reverseDomain = getReverseDomain(url);
        }

        private static String getReverseDomain(String domain) {
            String endProtocol = "//";
            String url = domain;
            if (url.contains(endProtocol)) {
                url = url.substring(url.indexOf(endProtocol) + endProtocol.length(), url.length());
            }
            
            String[] tokens = url.split("\\.");
            String reverseDomain = "";
            for (int i = tokens.length - 1; i >= 0; --i) {
                reverseDomain += tokens[i];
                if (i != 0) {
                    reverseDomain += ".";
                }
            }

            return reverseDomain;
        }

        @Override
        public int compareTo(Domain o) {
            return this.reverseDomain.compareTo(o.reverseDomain);
        }
    }
    
}
