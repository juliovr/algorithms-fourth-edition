package edu.princeton.cs.algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedList<I> implements Iterable<I> {

    private Node<I> node;
    private int n;
    
    private static class Node<I> {
        private I value;
        private Node<I> next;
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
            this.node = newNode;
        } else {
            this.node.next = newNode;
        }
        n++;
    }
    
    @Override
    public Iterator<I> iterator() {
        return new LinkedIterator(node);
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
        LinkedList<String> list = new LinkedList<>();
        list.insert("a");
        list.insert("b");
        list.insert("c");
        
        for (String value : list) {
            System.out.println(value);
        }
    }

}
