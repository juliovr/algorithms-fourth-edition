package algorithms.chapter5.section2;

public class StringSETNonRecursive {

    private int n = 0;
    private TSTNonRecursive<Integer> tst = new TSTNonRecursive<>();

    public StringSETNonRecursive() {

    }

    public void add(String key) {
        if (contains(key)) {
            return;
        }

        tst.put(key, n++);
    }

    public void delete(String key) {
        tst.delete(key);
        --n;
    }

    public boolean contains(String key) {
        return tst.get(key) != null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : tst.keys()) {
            sb.append(s);
            sb.append('\n');
        }

        return sb.toString();
    }

}
