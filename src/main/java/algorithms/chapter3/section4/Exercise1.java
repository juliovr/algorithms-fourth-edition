package algorithms.chapter3.section4;

public class Exercise1 {

    public static void main(String[] args) {
        SeparateChainingHashSTLetters st = new SeparateChainingHashSTLetters(5);
        st.put('E');
        st.put('A');
        st.put('S');
        st.put('Y');
        st.put('Q');
        st.put('U');
        st.put('T');
        st.put('I');
        st.put('O');
        st.put('N');
    }

    private static class SeparateChainingHashSTLetters extends SeparateChainingHashST<Character, Integer> {

        public SeparateChainingHashSTLetters(int m) {
            super(m);
        }

        private int hashLetter(char letter) {
            return (11*letter) % getM();
        }

        public void put(Character letter) {
            super.put(letter, hashLetter(letter));
        }

    }

}
