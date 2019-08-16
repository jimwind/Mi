package com.up360.mi.java2.chapter6;

public class Point {
    public Point(double xVal, double yVal){
        x = xVal;
        y = yVal;
    }
    public Point(Point point){
        x = point.x;
        y = point.y;
    }

    public String toString(){
        return x + "," + y;
    }

    protected double x;
    protected double y;
}
