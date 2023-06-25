package edu.princeton.cs.algs4;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

    private Stack<Item> items = new Stack<>();

    public void add(Item item) {
        items.push(item);
    }

    public Item get() {
        return items.pop();
    }

    public int size() {
        return items.size();
    }

    public Iterator<Item> iterator() {
        return items.iterator();
    }

}
