package algorithms.chapter4.section1;

import algorithms.chapter3.section4.SeparateChainingHashST;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdDraw;

public class Exercise37 {

    private static class EuclideanGraph {

        private static class Vertex {
            private int id;
            private float x;
            private float y;

            public Vertex(int id, float x, float y) {
                this.id = id;
                this.x = x;
                this.y = y;
            }
        }

        public int nVertices;
        public int nEdges;
        private SeparateChainingHashST<Integer, Vertex> vertices = new SeparateChainingHashST<>();
        private SeparateChainingHashST<Integer, Bag<Vertex>> adj = new SeparateChainingHashST<>();

        public void addEdge(Vertex v1, Vertex v2) {
            if (!vertices.contains(v1.id)) {
                vertices.put(v1.id, v1);
            }

            if (!vertices.contains(v2.id)) {
                vertices.put(v2.id, v2);
            }

            if (!adj.contains(v1.id)) {
                adj.put(v1.id, new Bag<>());
                ++nVertices;
            }

            if (!adj.contains(v2.id)) {
                adj.put(v2.id, new Bag<>());
                ++nVertices;
            }

            adj.get(v1.id).add(v2);
            adj.get(v2.id).add(v1);
            ++nEdges;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int v = 0; v < nVertices; ++v) {
                sb.append(v + ": ");
                Bag<Vertex> value = adj.get(v);
                for (Vertex vertex : value) {
                    sb.append(vertex.id + " ");
                }

                sb.append("\n");
            }

            return sb.toString();
        }

        private static final float RADIUS = 0.02f;

        public void show() {
            boolean[] showed = new boolean[nVertices];

            float maxX = 0;
            float maxY = 0;
            for (Integer v : vertices.keys()) {
                Vertex vertex = vertices.get(v);
                if (vertex.x > maxX) {
                    maxX = vertex.x;
                }
                if (vertex.y > maxY) {
                    maxY = vertex.y;
                }
            }

            maxX += (RADIUS + 2);
            maxY += (RADIUS + 2);

            StdDraw.setCanvasSize(1000, 1000);
            StdDraw.setPenColor(StdDraw.BLACK);

            for (Integer v : vertices.keys()) {
                Vertex vertex = vertices.get(v);
                float x = RADIUS + (vertex.x / maxX);
                float y = RADIUS + (vertex.y / maxY);

                if (!showed[v]) {
                    StdDraw.circle(x, y, RADIUS);
                    StdDraw.text(x, y, String.valueOf(vertex.id));
                    showed[vertex.id] = true;
                }

                for (Vertex adjVertex : adj.get(v)) {
                    if (!showed[adjVertex.id]) {
                        float adjX = RADIUS + (adjVertex.x / maxX);
                        float adjY = RADIUS + (adjVertex.y / maxY);

                        float x0Pad;
                        float x1Pad;
                        if (x == adjX) {
                            x0Pad = 0;
                            x1Pad = 0;
                        } else if (x < adjX) {
                            x0Pad = RADIUS;
                            x1Pad = -RADIUS;
                        } else {
                            x0Pad = -RADIUS;
                            x1Pad = RADIUS;
                        }

                        float y0Pad;
                        float y1Pad;
                        if (y == adjY) {
                            y0Pad = 0;
                            y1Pad = 0;
                        } else if (y < adjY) {
                            y0Pad = RADIUS;
                            y1Pad = -RADIUS;
                        } else {
                            y0Pad = -RADIUS;
                            y1Pad = RADIUS;
                        }

                        if (x == adjX && y < adjY) {
                            x0Pad = 0;
                            x1Pad = 0;
                            y0Pad = RADIUS;
                            y1Pad = -RADIUS;
                        }

                        StdDraw.line(x + x0Pad, y + y0Pad, adjX + x1Pad, adjY + y1Pad);
                    }
                }
            }

        }

        public static void main(String[] args) {
            EuclideanGraph euclideanGraph = new EuclideanGraph();
            Vertex vertex0 = new Vertex(0, 6.1f, 1.3f);
            Vertex vertex1 = new Vertex(1, 7.2f, 2.5f);
            Vertex vertex2 = new Vertex(2, 8.4f, 1.3f);
            Vertex vertex3 = new Vertex(3, 8.4f, 15.3f);
            Vertex vertex4 = new Vertex(4, 6.1f, 15.3f);
            Vertex vertex5 = new Vertex(5, 7.2f, 5.2f);
            Vertex vertex6 = new Vertex(6, 7.2f, 8.4f);

            euclideanGraph.addEdge(vertex0, vertex1);
            euclideanGraph.addEdge(vertex2, vertex1);
            euclideanGraph.addEdge(vertex0, vertex2);
            euclideanGraph.addEdge(vertex3, vertex6);
            euclideanGraph.addEdge(vertex4, vertex6);
            euclideanGraph.addEdge(vertex3, vertex4);
            euclideanGraph.addEdge(vertex1, vertex5);
            euclideanGraph.addEdge(vertex5, vertex6);

            System.out.println(euclideanGraph);
            euclideanGraph.show();
        }

    }

}
