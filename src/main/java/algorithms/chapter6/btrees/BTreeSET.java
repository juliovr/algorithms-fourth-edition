package algorithms.chapter6.btrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;

public class BTreeSET<Key extends Comparable<Key>> {

    private static final int m = 6;

    private Page<Key> root = new Page<>(true, m);

    public BTreeSET(Key sentinel) {
        add(sentinel);
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    private boolean contains(Page<Key> page, Key key) {
        if (page.isExternal()) {
            return page.contains(key);
        }

        return contains(page.next(key), key);
    }

    public void add(Key key) {
        add(root, key);
        if (root.isFull()) {
            Page<Key> left = root;
            Page<Key> right = root.split();
            root = new Page<>(false, m);
            root.add(left);
            root.add(right);
        }
    }

    private void add(Page<Key> page, Key key) {
        if (page.isExternal()) {
            page.add(key);
            return;
        }

        Page<Key> next = page.next(key);
        add(next, key);
        if (next.isFull()) {
            page.add(next.split());
        }
        next.close();
    }

    public void draw() {
        StdDraw.setCanvasSize(1000, 1000);

        double length = 0.02;
        double startX = 0.5;
        double y = 0.7;
        double yStep = 0.15;
        int m = root.m();
        double pageLength = (length * m) + 0.2;

        Queue<Page<Key>> pagesToDraw = new Queue<>();
        Queue<Integer> childrenRemainingToDraw = new Queue<>();
        Queue<Double> itemsXPositions = new Queue<>(); // Keep parents x position

        pagesToDraw.enqueue(root);
        childrenRemainingToDraw.enqueue(1);

        while (!pagesToDraw.isEmpty()) {
            Page<Key> page = pagesToDraw.dequeue();
            childrenRemainingToDraw.enqueue(page.numberOfChildren());

            if (page.isExternal()) {
                StdDraw.setPenColor(Color.BLACK);
            } else {
                StdDraw.setPenColor(Color.RED);
            }

            double mp = startX - ((m - 1) * (length/2));

            // Draw page's items
            int i = 0;
            for (Key key : page.keys()) {
                double x = mp + (i * length * 2);
                StdDraw.rectangle(x, y, length, length);
                StdDraw.text(x, y, (String) key);
                itemsXPositions.enqueue(x);

                ++i;

                Page<Key> nextPage = page.next(key);
                if (nextPage != null) {
                    pagesToDraw.enqueue(nextPage);
                }
            }

            // Draw remaining blank-slots if page is not full
            for (; i < m; ++i) {
                double x = mp + (i * length * 2);
                StdDraw.rectangle(x, y, length, length);
                if (i == (m - 1)) {
                    StdDraw.line(x - length, y - length, x + length, y + length);
                }
            }

            // Draw line to parent page
            Integer numberOfChildren = childrenRemainingToDraw.peek();

            if (numberOfChildren > 1) {
                double x = itemsXPositions.dequeue();
                StdDraw.line(startX + 0.05, y + length, x, y + yStep - length);
            }

            numberOfChildren--;
            if (numberOfChildren == 0) {
                y -= yStep;
                childrenRemainingToDraw.dequeue();

                int newChildrenCount = childrenRemainingToDraw.peek();
                startX = newChildrenCount*pageLength / 2;
            } else {
                startX += pageLength;
            }

        }

        StdDraw.show();
    }

}
