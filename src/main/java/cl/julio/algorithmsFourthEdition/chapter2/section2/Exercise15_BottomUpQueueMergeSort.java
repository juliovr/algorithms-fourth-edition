package cl.julio.algorithmsFourthEdition.chapter2.section2;

import cl.julio.algorithmsFourthEdition.chapter2.Common;
import edu.princeton.cs.algs4.Queue;

public class Exercise15_BottomUpQueueMergeSort {

    public static void main(String[] args) {
        int n = 1000;
        Queue<Queue<Comparable>> queues = new Queue<>();
        
        Comparable[] data = Common.generateRandomData(n);
        for (int i = 0; i < n; i++) {
            Queue<Comparable> q = new Queue<>();
            q.enqueue(data[i]);
            
            queues.enqueue(q);
        }
        
        mergeSortQueue(queues);
        System.out.println(queues);
    }
    
    public static void mergeSortQueue(Queue<Queue<Comparable>> queues) {
        while (queues.size() > 1) {
            Queue q = mergeQueues(queues.dequeue(), queues.dequeue());
            queues.enqueue(q);
        }
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
