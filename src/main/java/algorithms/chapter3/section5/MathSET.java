package algorithms.chapter3.section5;

import java.util.HashSet;
import java.util.Set;

/**
 * Finite mathematical set.
 *
 * @param <Key>
 */
public class MathSET<Key> {

    private Set<Key> universe = new HashSet<>();
    private Set<Key> set = new HashSet<>();

    public MathSET(Key[] universe) {
        for (Key key: universe) {
            this.universe.add(key);
        }
    }

    public void add(Key key) {
        if (universe.contains(key)) {
            set.add(key);
        }
    }

    public MathSET<Key> complement() {
        MathSET<Key> complement = new MathSET<>((Key[])universe.toArray());
        for (Key key: universe) {
            if (!set.contains(key)) {
                complement.add(key);
            }
        }

        return complement;
    }

    public void union(MathSET<Key> a) {
        this.set.addAll(a.set);
    }

    public void intersection(MathSET<Key> a) {
        Set<Key> keysToDelete = new HashSet<>(a.set.size());
        for (Key key: this.set) {
            if (!a.set.contains(key)) {
                keysToDelete.add(key);
            }
        }

        for (Key key: keysToDelete) {
            delete(key);
        }
    }

    public void delete(Key key) {
        set.remove(key);
    }

    public boolean contains(Key key) {
        return set.contains(key);
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public Iterable<Key> keys() {
        return set;
    }

}
