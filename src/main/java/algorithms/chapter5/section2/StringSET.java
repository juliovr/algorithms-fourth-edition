package algorithms.chapter5.section2;

public class StringSET {

    private int n = 0;
    private TrieST<Integer> trie = new TrieST<>();

    public StringSET() {

    }

    public void add(String key) {
        if (contains(key)) {
            return;
        }

        trie.put(key, n++);
    }

    public void delete(String key) {
        trie.delete(key);
        --n;
    }

    public boolean contains(String key) {
        return trie.get(key) != null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : trie.keys()) {
            sb.append(s);
            sb.append('\n');
        }

        return sb.toString();
    }

    public boolean containsPrefix(String prefix) {
        for (String s : trie.keysWithPrefix(prefix)) {
            return true;
        }

        return false;
    }

}
