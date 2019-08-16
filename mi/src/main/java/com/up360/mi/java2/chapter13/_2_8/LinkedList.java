package com.up360.mi.java2.chapter13._2_8;

import java.io.Serializable;

/**
 * 类型参数限制
 *
 * 关键字extends 与类型参数限制是类还是接口无关，这种用法只针对泛型中的类型参数限制
 *
 * 如果泛型本身实现的是接口，就使用implements，如果扩展了类，就使用extends
 *
 * 另外，内部类也必须实现接口Serializable
 *
 * @param <T>
 */
public class LinkedList<T extends Serializable> implements Serializable {
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

    private class ListItem implements Serializable{
        public ListItem(T item){
            this.item = item;
            next = null;
        }

        @Override
        public String toString(){
            return "ListItem "+item;
        }

        ListItem next;
        T item;
    }
}
