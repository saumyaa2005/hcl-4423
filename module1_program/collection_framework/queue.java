package collection_framework;

import java.util.*;

public class queue {
    public static void main(String[] args) {
        // Queue<Integer> q = new LinkedList<>();

        // // insertion
        // q.offer(1);
        // q.offer(2);
        // q.offer(3);
        // q.offer(4);

        // System.out.println(q);

        // // deletion
        // System.out.println(q.poll());
        // System.out.println(q);
        // q.offer(1);
        // System.out.println(q);

        // arraydequeue-class
        // ArrayDeque<Integer> q = new ArrayDeque<>();
        // q.offer(1); // add first
        // q.offer(2);
        // q.offer(3);
        // q.offer(4);

        // // insertion
        // System.out.println(q);

        // // deletion- front/ head
        // System.out.println(q.poll());// removes first
        // System.out.println(q);
        // q.offer(1);// add first
        // System.out.println(q);

        // // deque
        // q.offerFirst(10);
        // q.offerLast(20);
        // System.out.println(q);
        // System.out.println(q.peekFirst());
        // System.out.println(q.peekLast());

        // priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(10);
        pq.offer(20);
        pq.offer(30);
        pq.offer(40);
        pq.offer(100);

        System.out.println(pq);
        System.out.println(pq.peek());

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

    }
}
