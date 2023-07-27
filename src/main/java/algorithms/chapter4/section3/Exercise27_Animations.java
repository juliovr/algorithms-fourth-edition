package algorithms.chapter4.section3;

import algorithms.chapter3.section4.SeparateChainingHashST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class Exercise27_Animations {

    public static void main(String[] args) {
        In in = new In("test_data/mediumEWG-euclidean.txt");
        int V = in.readInt();
        SeparateChainingHashST<Integer, Point> points = new SeparateChainingHashST<>(V);

        for (int i = 0; i < V; ++i) {
            if (in.hasNextLine()) {
                Point p = new Point();
                p.x = in.readDouble();
                p.y = in.readDouble();
                points.put(points.size(), p);
            }
        }

        EdgeWeightedGraph graph = new EdgeWeightedGraph(V);

        int E = in.readInt();
        for (int i = 0; i < E; ++i) {
            if (in.hasNextLine()) {
                int v = in.readInt();
                int w = in.readInt();
                Point p1 = points.get(v);
                Point p2 = points.get(w);
                Edge edge = new Edge(v, w, distance(p1, p2));
                graph.addEdge(edge);
            }
        }

//        LazyPrimMST mst = new LazyPrimMST(graph);
//        PrimMST mst = new PrimMST(graph);
        KruskalMST mst = new KruskalMST(graph);

        draw(graph, points, mst.edges());
    }

    private static void draw(EdgeWeightedGraph graph, SeparateChainingHashST<Integer, Point> points, Iterable<Edge> mst) {
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.GRAY);

        for (Edge e : graph.edges()) {
            int v = e.either();
            int w = e.other(v);
            Point p1 = points.get(v);
            Point p2 = points.get(w);

            StdDraw.line(p1.x, p1.y, p2.x, p2.y);
        }

        StdDraw.setPenRadius(StdDraw.getPenRadius()*3);

        StdDraw.setPenColor(StdDraw.BLACK);

        for (Edge e : mst) {
            int v = e.either();
            int w = e.other(v);
            Point p1 = points.get(v);
            Point p2 = points.get(w);

            StdDraw.line(p1.x, p1.y, p2.x, p2.y);
            StdDraw.pause(10);
        }

    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(sq(p2.x - p1.x) + sq(p2.y - p1.y));
    }

    private static double sq(double a) {
        return a*a;
    }

    private static class Point {
        private double x;
        private double y;

        @Override
        public String toString() {
            return "(x = " + x + ", y = " + y + ")";
        }
    }

}
