package com.up360.mi.java2.chapter13._3;

public class BinaryTree<T extends Comparable<? super T>> {

    public void add(T value){
        if(root == null){
            root = new Node(value);
        } else {
            add(value, root);
        }
    }
    private void add(T value, Node node){
        int comparison = node.obj.compareTo(value);
        if(comparison == 0){
            ++node.count;
            return;
        }
        if(comparison > 0){
            if(node.left == null){
                node.left = new Node(value);
            } else {
                add(value, node.left);
            }
        } else {
            if(node.right == null){
                node.right = new Node(value);
            } else {
                add(value, node.right);
            }
        }

    }

    public LinkedList<T> sort() {
        values = new LinkedList<T>();
        treeSort(root);
        return values;
    }

    private void treeSort(Node node){
        if(node != null){
            treeSort(node.left);
            for(int i=0; i<node.count; i++){
                values.addItem(node.obj);
            }
            treeSort(node.right);

        }

    }

    LinkedList<T> values;
    private Node root;


    private class Node {
        Node(T value){
            obj = value;
            count = 1;
        }
        T obj;
        int count;
        Node left;
        Node right;
    }
}
