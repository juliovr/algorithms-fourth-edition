package algorithms.chapter4.section2;

import algorithms.chapter3.section4.SeparateChainingHashST;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdDraw;

public class Exercise38_EuclideanDigraphs {

    private static class EuclideanDigraph {

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
        private static final float ARROW_LENGTH = 0.01f;

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

                        float startX = x + x0Pad;
                        float startY = y + y0Pad;
                        float endX = adjX + x1Pad;
                        float endY = adjY + y1Pad;
                        StdDraw.line(startX, startY, endX, endY);

                        if (startX == endX) {
                            // Vertical line
                            float vLeftX = endX - ARROW_LENGTH;
                            float vLeftY = endY - ARROW_LENGTH;
                            float vMiddleX = endX;
                            float vMiddleY = endY;
                            float vRightX = endX + ARROW_LENGTH;
                            float vRightY = endY - ARROW_LENGTH;
                            drawTriangle(vLeftX, vLeftY, vMiddleX, vMiddleY, vRightX, vRightY);
                        } else if (startY == endY) {
                            // Horizontal line
                            float vTopX;
                            float vTopY = endY + ARROW_LENGTH;
                            float vMiddleX = endX;
                            float vMiddleY = endY;
                            float vBottomX;
                            float vBottomY = endY - ARROW_LENGTH;
                            if (endX < startX) {
                                // Point left
                                vTopX = endX + ARROW_LENGTH;
                                vBottomX = endX + ARROW_LENGTH;
                            } else {
                                // Point right
                                vTopX = endX - ARROW_LENGTH;
                                vBottomX = endX - ARROW_LENGTH;
                            }
                            drawTriangle(vTopX, vTopY, vMiddleX, vMiddleY, vBottomX, vBottomY);
                        }

                        // TODO: diagonal arrows
                    }
                }
            }

        }

        private void drawTriangle(double x0, double y0, double x1, double y1, double x2, double y2) {
            double[] x = new double[] { x0, x1, x2 };
            double[] y = new double[] { y0, y1, y2 };
            StdDraw.filledPolygon(x, y);
        }

        public static void main(String[] args) {
            EuclideanDigraph euclideanDigraph = new EuclideanDigraph();
            Vertex vertex0 = new Vertex(0, 6.1f, 1.3f);
            Vertex vertex1 = new Vertex(1, 7.2f, 2.5f);
            Vertex vertex2 = new Vertex(2, 8.4f, 1.3f);
            Vertex vertex3 = new Vertex(3, 8.4f, 15.3f);
            Vertex vertex4 = new Vertex(4, 6.1f, 15.3f);
            Vertex vertex5 = new Vertex(5, 7.2f, 5.2f);
            Vertex vertex6 = new Vertex(6, 7.2f, 8.4f);

            euclideanDigraph.addEdge(vertex0, vertex1);
            euclideanDigraph.addEdge(vertex2, vertex1);
            euclideanDigraph.addEdge(vertex0, vertex2);
            euclideanDigraph.addEdge(vertex3, vertex6);
            euclideanDigraph.addEdge(vertex4, vertex6);
            euclideanDigraph.addEdge(vertex3, vertex4);
            euclideanDigraph.addEdge(vertex1, vertex5);
            euclideanDigraph.addEdge(vertex5, vertex6);

            System.out.println(euclideanDigraph);
            euclideanDigraph.show();
        }

    }

}
