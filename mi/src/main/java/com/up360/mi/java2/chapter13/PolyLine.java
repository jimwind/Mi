package com.up360.mi.java2.chapter13;

import com.up360.mi.java2.chapter6.Point;

public class PolyLine {
    public PolyLine(double[][] coords){
        Point[] points = new Point[coords.length];
        for(int i=0; i<coords.length; i++){
            points[i] = new Point(coords[i][0], coords[i][1]);
        }
        polyline = new LinkedList<Point>(points);
    }

    public PolyLine(Point[] points){
        polyline = new LinkedList<Point>(points);
    }

    public void addPoint(Point point){
        polyline.addItem(point);
    }

    public void addPoint(double x, double y){
        polyline.addItem(new Point(x, y));
    }

    public String toString(){
        StringBuffer str = new StringBuffer("Polyline: ");
        Point point = polyline.getFirst();
        while (point != null){
            str.append(" ("+point+")");
            point = polyline.getNext();
        }
        return str.toString();
    }

    private LinkedList<Point> polyline;

}
