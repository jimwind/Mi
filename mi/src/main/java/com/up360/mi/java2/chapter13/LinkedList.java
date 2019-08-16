package com.up360.mi.java2.chapter13;

//p427
public class LinkedList<T> {
    public LinkedList(){}

    public LinkedList(T item){
        if(item != null){
            current = end = start = new ListItem(item);
        }
    }

    public LinkedList(T[] items){
        if(items != null){
            for(int i=0; i<items.length; i++){
                addItem(items[i]);
            }
            current = start;
        }
    }
    public void addItem(T item){
        ListItem newEnd = new ListItem(item);
        if(start == null){
            start = end = newEnd;
        } else {
            end.next = newEnd;
            end = newEnd;
        }
    }

    public T getFirst(){
        current = start;
        return  start == null ? null : start.item;
    }

    public T getNext(){
        if(current != null){
            current = current.next;
        }
        return  current == null ? null : current.item;
    }


    private ListItem start = null;
    private ListItem end = null;
    private ListItem current = null;

    private class ListItem {
        public ListItem(T item){
            this.item = item;
            next = null;
        }

        public String toString(){
            return "ListItem "+item;
        }

        ListItem next;
        T item;
    }
}
