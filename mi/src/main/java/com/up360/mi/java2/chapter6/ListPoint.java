package com.up360.mi.java2.chapter6;

//241
public class ListPoint {
    public ListPoint(Point point) {
        this.point = point;
        next = null;
    }

    public void setNext(ListPoint next) {
        this.next = next;
    }

    public ListPoint getNext() {
        return next;
    }

    public String toString() {
        return "(" + point + ")";
    }

    private ListPoint next;
    private Point point;
}
