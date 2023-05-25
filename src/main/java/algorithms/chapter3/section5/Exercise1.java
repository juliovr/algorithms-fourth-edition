package algorithms.chapter3.section5;

import algorithms.chapter3.section1.SequentialSearchST;
import algorithms.chapter3.section3.RedBlackBST;
import algorithms.chapter3.section4.SeparateChainingHashST;

public class Exercise1 {

    public static void main(String[] args) {
        SET<String> set = new SET<>();
        set.put("C");
        set.put("B");
        set.put("Z");
        set.put("A");
        set.put("H");

        HashSET<String> hashSet = new HashSET<>();
        hashSet.put("C");
        hashSet.put("B");
        hashSet.put("Z");
        hashSet.put("A");
        hashSet.put("H");

        System.out.println("Done");
    }

    private static class SET<Key extends Comparable<Key>> {

        private RedBlackBST<Key, Integer> st = new RedBlackBST<>();

        public int size() {
            return st.size();
        }

        public void put(Key key) {
            st.put(key, 1);
        }

    }

    private static class HashSET<Key> {

        private SeparateChainingHashST<Key, Integer> st = new SeparateChainingHashST<>(10);

        public boolean contains(Key key) {
            for (Key item: st.keys()) {
                if (item.hashCode() == key.hashCode()) {
                    return true;
                }
            }

            return false;
        }

        public void put(Key key) {
            st.put(key, 1);
        }

        public void delete(Key key) {
            st.delete(key);
        }

    }

}
