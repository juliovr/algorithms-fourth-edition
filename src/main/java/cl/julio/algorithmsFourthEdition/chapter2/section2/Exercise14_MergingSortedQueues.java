package cl.julio.algorithmsFourthEdition.chapter2.section2;

import cl.julio.algorithmsFourthEdition.chapter2.Common;
import edu.princeton.cs.algs4.Queue;

public class Exercise14_MergingSortedQueues {

    public static void main(String[] args) {
        Queue<Comparable> q1 = new Queue<>();
        Queue<Comparable> q2 = new Queue<>();
        
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                q1.enqueue(i);
            } else {
                q2.enqueue(i);
            }
        }
        
        Queue<Comparable> queue = mergeQueues(q1, q2);
        System.out.println(queue);
    }
    
    private static Queue<Comparable> mergeQueues(Queue<Comparable> q1, Queue<Comparable> q2) {
        Queue<Comparable> queue = new Queue<>();
        
        int n = q1.size() + q2.size();
        
        for (int i = 0; i < n; i++) {
            if (q1.isEmpty()) {
                queue.enqueue(q2.dequeue());
            } else if (q2.isEmpty()) {
                queue.enqueue(q1.dequeue());
            } else if (Common.less(q1.peek(), q2.peek())) {
                queue.enqueue(q1.dequeue());
            } else {
                queue.enqueue(q2.dequeue());
            }
        }
        
        return queue;
    }
    
}
