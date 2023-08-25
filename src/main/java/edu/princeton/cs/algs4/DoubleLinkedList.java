package edu.princeton.cs.algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class DoubleLinkedList<I> implements Iterable<I> {

    private Node<I> first;
    private Node<I> last;
    private int n;
    
    public static class Node<I> {
        public I value;
        public Node<I> next;
        public Node<I> prev;
    }

    public Node<I> first() {
        return first;
    }

    public Node<I> last() {
        return last;
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    public void insert(I value) {
        Node<I> newNode = new Node<>();
        newNode.value = value;
        
        if (isEmpty()) {
            this.first = newNode;
            this.last = newNode;
        } else {
            newNode.prev = this.last;
            this.last.next = newNode;
            this.last = newNode;
        }
        n++;
    }
    
    @Override
    public Iterator<I> iterator() {
        return new LinkedIterator(first);
    }
    
    private class LinkedIterator implements Iterator<I> {
        private Node<I> current;

        public LinkedIterator(Node<I> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public I next() {
            if (!hasNext())
                throw new NoSuchElementException();
            I item = current.value;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.insert("a");
        list.insert("b");
        list.insert("c");
        list.insert("d");
        list.insert("e");

        for (String value : list) {
            System.out.println(value);
        }

        System.out.println("prev:");
        for (Node node = list.last; node != null; node = node.prev) {
            System.out.println(node.value);
        }
    }

}
