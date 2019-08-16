package com.up360.mi.java2.chapter6;

//p245
public class LinkedList {
    public LinkedList(){}

    public LinkedList(Object item){
        if(item != null){
            current = end = start = new ListItem(item);
        }
    }

    public LinkedList(Object[] items){
        if(items != null){
            for(int i=0; i<items.length; i++){
                addItem(items[i]);
            }
            current = start;
        }
    }
    public void addItem(Object item){
        ListItem newEnd = new ListItem(item);
        if(start == null){
            start = end = newEnd;
        } else {
            end.next = newEnd;
            end = newEnd;
        }
    }

    public Object getFirst(){
        current = start;
        return  start == null ? null : start.item;
    }

    public Object getNext(){
        if(current != null){
            current = current.next;
        }
        return  current == null ? null : current.item;
    }


    private ListItem start = null;
    private ListItem end = null;
    private ListItem current = null;

    private class ListItem {
        public ListItem(Object item){
            this.item = item;
            next = null;
        }

        public String toString(){
            return "ListItem "+item;
        }

        ListItem next;
        Object item;
    }
}
