package com.tfdxl.rbtree;

import lombok.Data;

/**
 * Created by tianfeng on 2017/3/18.
 * 红黑树(based on bst)的性质：
 * (1)每个节点的红色的，或者是黑色的；
 * (2)根节点是黑色的;
 * (3)每个叶子节点是就黑色的;
 * (4)如果一个节点是黑色的，那么他的两个子节点是黑色的；
 * (5)对于每个节点，从该节点到其所有的后代的节点的简单路径上，均包含相同数目的黑色节点
 *
 */
public class RBTree<T> implements RBTreeOperations<T>{

    private int size;

    /**
     * 根节点
     */
    private Node root = new Node();

    /**
     * nil节点
     */
    private Node nil = null;

    public int size() {
        return size;
    }

    public boolean exists(WrappedData data) {
        return false;
    }

    public boolean insert(WrappedData<T> data) {



        return false;
    }

    public WrappedData<T> delete(int key) {
        return null;
    }

    public WrappedData<T> search(int key) {
        return null;
    }

    public boolean update(WrappedData<T> data) {
        return false;
    }

    private void leftRotate(Node x){
        Node y = x.getRight();
        x.setRight(y.getLeft());
        if(y.getLeft() != nil){
            y.getLeft().setParent(x);
        }
        //link x's parent to y
        y.setParent(x.getParent());
        if(x.getParent()==nil){
            this.root = y;
        }else if(x == x.getParent().getLeft()){
            x.getParent().setLeft(y);
        }else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    private void rightRotate(Node y){
        Node x = y.getLeft();
        y.setLeft(x.getRight());
        if(x.getRight()!=this.nil){
            x.getRight().setRight(y);
        }
        //link y's parent to x
        x.setParent(y.getParent());
        if(y.getParent() == nil){
            nil = x;
        }else if(y == y.getParent().getLeft()){
            y.getParent().setRight(x);
        }else{
            y.getParent().setLeft(x
            );
        }
        x.setRight(y);
        y.setParent(x);
    }


    @Data
    public static class Node{


        public Node(){}

        public Node(int color,Node left,Node right,Node parent,WrappedData data){
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.data = data;
            if(data != null){
                key = data.getKey();
            }
        }

        /**
         * 颜色
         * @see Color
         */
        private int color;

        private Node left;

        private Node right;

        private Node parent;

        private int key;

        private WrappedData data;
    }
}
