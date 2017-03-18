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
    private Node root;

    /**
     * nil节点
     */
    private Node nil ;

    private void init(){
        root = null;
        nil = new Node(Color.BLACK.getColor(),null,null,null,null);
        size = 0;
    }

    private RBTree(){
        init();
    }

    public int size() {
        return size;
    }

    public boolean exists(WrappedData data) {
        return false;
    }

    public boolean insert(WrappedData<T> data) {
        Node z = new Node(data);

        Node y = this.nil;
        Node x = this.root;
        while(x!=this.nil){
            y = x;
            if(z.getKey()<x.getKey()){
                x = x.left;
            }else {
                x = x.right;
            }
        }
        z.setParent(y);
        if(y == this.nil){
            this.root = z;
        }else if(z.key<y.key){
            y.left = z;
        }else{
            y.right = z;
        }
        z.left = this.nil;
        z.right = this.nil;
        //暂时涂成红色
        z.color = Color.RED.getColor();
        //对节点进行着色并且旋转
        rbInsertFixup(z);
        this.size ++;
        return true;
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
            y.getParent().setLeft(x);
        }
        x.setRight(y);
        y.setParent(x);
    }


    private void rbInsertFixup(Node z){
        while (z.getParent().getColor() == Color.RED.getColor()){

            //表示的是z的父节点为其祖父节点的左孩子
            if(z.getParent() == z.getParent().getParent().getLeft()){
                Node y = z.getParent().getParent().getRight();
                //case1:
                if(y.getColor() == Color.RED.getColor()){
                    z.getParent().setColor(Color.BLACK.getColor());
                    y.setColor(Color.BLACK.getColor());
                    z.getParent().getParent().setColor(Color.RED.getColor());
                    z = z.getParent().getParent();
                }else if(z == z.getParent().getRight()){
                    z = z.getParent();
                    leftRotate(z);
                }else {
                    z.getParent().setColor(Color.BLACK.getColor());
                    z.getParent().getParent().setColor(Color.RED.getColor());
                    rightRotate(z.getParent().getParent());
                }
            }else {
                Node y = z.getParent().getParent().getLeft();
                //case1:
                if(y.getColor() == Color.RED.getColor()){
                    z.getParent().setColor(Color.BLACK.getColor());
                    y.setColor(Color.BLACK.getColor());
                    z.getParent().getParent().setColor(Color.RED.getColor());
                    z = z.getParent().getParent();
                }else if(z == z.getParent().getLeft()){
                    z = z.getParent();
                    rightRotate(z);
                }else {
                    z.getParent().setColor(Color.BLACK.getColor());
                    z.getParent().getParent().setColor(Color.RED.getColor());
                    leftRotate(z.getParent().getParent());
                }
            }

        }
        this.root.setColor(Color.BLACK.getColor());
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

        public Node(WrappedData data){
            if(data != null){
                this.key = data.getKey();
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
