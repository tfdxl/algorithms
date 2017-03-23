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
 */
public class RBTree<T> implements RBTreeOperations<T> {

    private transient int size;

    /**
     * 根节点
     */
    private transient Node root;

    /**
     * nil节点
     */
    private transient Node nil;

    //初始化
    private void init() {
        nil = new Node(Color.BLACK.getColor(), null, null, null, null);
        root = nil;
        size = 0;
    }

    public RBTree() {
        init();
    }

    public int size() {
        return size;
    }

    public boolean exists(WrappedData data) {
        Node node = this.root;
        while (node != null) {
            if (data.getKey() < node.getKey())
                node = node.left;
            else if (data.getKey() > node.getKey())
                node = node.right;
            else
                return true;
        }
        return false;
    }

    public boolean insert(WrappedData<T> data) {
        Node z = new Node(data);

        Node y = this.nil;
        Node x = this.root;
        while (x != this.nil) {
            y = x;
            if (z.getKey() < x.getKey()) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.setParent(y);
        if (y == this.nil) {
            this.root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = this.nil;
        z.right = this.nil;
        //暂时涂成红色
        z.color = Color.RED.getColor();
        //对节点进行着色并且旋转
        rbInsertFixup(z);
        this.size++;
        return true;
    }

    public WrappedData<T> delete(int key) {

        Node node = this.root;
        while (node != null) {
            if (key < node.getKey())
                node = node.left;
            else if (key > node.getKey())
                node = node.right;
            else
                return node.getData();
        }
        rbDelete(node);
        return node.getData();
    }

    public WrappedData<T> search(int key) {

        Node node = this.root;
        while (node != null) {
            if (key < node.getKey())
                node = node.left;
            else if (key > node.getKey())
                node = node.right;
            else
                return node.getData();
        }
        return null;
    }

    public boolean update(WrappedData<T> data) {
        Node node = this.root;
        while (node != null) {
            if (data.getKey() < node.getKey())
                node = node.left;
            else if (data.getKey() > node.getKey())
                node = node.right;
            else
                //just break
                break;
        }
        node.setData(data);
        return true;
    }

    public void printAllNodes() {

    }

    //rotate operation keeps balance
    private void leftRotate(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != nil) {
            y.getLeft().setParent(x);
        }
        //link x's parent to y
        y.setParent(x.getParent());
        if (x.getParent() == nil) {
            this.root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    private void rightRotate(Node y) {
        Node x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != this.nil) {
            x.getRight().setRight(y);
        }
        //link y's parent to x
        x.setParent(y.getParent());
        if (y.getParent() == nil) {
            nil = x;
        } else if (y == y.getParent().getLeft()) {
            y.getParent().setRight(x);
        } else {
            y.getParent().setLeft(x);
        }
        x.setRight(y);
        y.setParent(x);
    }


    /**
     * 插入的时候最多破坏的是性质2或者4
     *
     * @param z
     */
    private void rbInsertFixup(Node z) {
        //循环终止的条件是z.p的颜色是黑色的（若果z时根节点，那么z的父亲节点是nil，其颜色是黑）
        while (z.getParent().getColor() == Color.RED.getColor()) {
            //表示的是z的父节点为其祖父节点的左孩子
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                Node y = z.getParent().getParent().getRight();
                //case1:(z的叔节点y是红色的)
                if (y.getColor() == Color.RED.getColor()) {
                    //solution:将父亲和叔节染成黑色
                    z.getParent().setColor(Color.BLACK.getColor());
                    y.setColor(Color.BLACK.getColor());
                    //祖父节点染成红色
                    z.getParent().getParent().setColor(Color.RED.getColor());
                    //z置为祖父节点
                    z = z.getParent().getParent();
                    //case2:z的叔节点y是黑色的并且z是一个右孩子
                } else if (z == z.getParent().getRight()) {
                    z = z.getParent();
                    leftRotate(z);
                } else {
                    z.getParent().setColor(Color.BLACK.getColor());
                    z.getParent().getParent().setColor(Color.RED.getColor());
                    rightRotate(z.getParent().getParent());
                }
            } else { //symmetric
                Node y = z.getParent().getParent().getLeft();
                //case1:
                if (y.getColor() == Color.RED.getColor()) {
                    z.getParent().setColor(Color.BLACK.getColor());
                    y.setColor(Color.BLACK.getColor());
                    z.getParent().getParent().setColor(Color.RED.getColor());
                    z = z.getParent().getParent();
                } else if (z == z.getParent().getLeft()) {
                    z = z.getParent();
                    rightRotate(z);
                } else {
                    z.getParent().setColor(Color.BLACK.getColor());
                    z.getParent().getParent().setColor(Color.RED.getColor());
                    leftRotate(z.getParent().getParent());
                }
            }

        }
        this.root.setColor(Color.BLACK.getColor());
    }

    private void rbTransplant(Node u, Node v) {

        if (u.getParent() == this.nil) {
            this.root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    private void rbDeleteFixup(Node x) {
        while (x != this.root && x.getColor() == Color.BLACK.getColor()) {
            if (x == x.getParent().getLeft()) {
                Node w = x.getParent().getRight();
                if (w.getColor() == Color.RED.getColor()) {
                    w.setColor(Color.BLACK.getColor());
                    x.getParent().setColor(Color.RED.getColor());
                    leftRotate(x.getParent());
                    w = x.getParent().getRight();
                }

                if (w.getLeft().getColor() == Color.BLACK.getColor() && w.getRight().getColor() == Color.BLACK.getColor()) {

                    w.setColor(Color.RED.getColor());
                    x = x.getParent();
                } else if (w.getRight().getColor() == Color.BLACK.getColor()) {

                    w.getLeft().setColor(Color.BLACK.getColor());
                    w.setColor(Color.RED.getColor());
                    rightRotate(w);
                    w = x.getParent().getRight();
                }
                w.setColor(x.getParent().getColor());
                x.getParent().setColor(Color.BLACK.getColor());
                w.getRight().setColor(Color.BLACK.getColor());
                leftRotate(x.getParent());
                x = this.root;

            } else {
                Node w = x.getParent().getLeft();
                if (w.getColor() == Color.RED.getColor()) {
                    w.setColor(Color.BLACK.getColor());
                    x.getParent().setColor(Color.RED.getColor());
                    rightRotate(x.getParent());
                    w = x.getParent().getLeft();
                }

                if (w.getRight().getColor() == Color.BLACK.getColor() && w.getLeft().getColor() == Color.BLACK.getColor()) {
                    w.setColor(Color.RED.getColor());
                    x = x.getParent();
                } else if (w.getLeft().getColor() == Color.BLACK.getColor()) {

                    w.getRight().setColor(Color.BLACK.getColor());
                    w.setColor(Color.RED.getColor());
                    leftRotate(w);
                    w = x.getParent().getRight();
                }
                w.setColor(x.getParent().getColor());
                x.getParent().setColor(Color.BLACK.getColor());
                w.getLeft().setColor(Color.BLACK.getColor());
                rightRotate(x.getParent());
                x = this.root;
            }
        }
        x.setColor(Color.BLACK.getColor());
    }

    private void rbDelete(Node z) {
        Node y = z;
        Node x;
        int yOriginalColor = y.getColor();
        if (z.getLeft() == this.nil) {
            x = z.getRight();
            rbTransplant(z, z.getRight());
        } else if (z.getRight() == this.nil) {
            x = z.getLeft();
            rbTransplant(z, z.getLeft());
        } else {
            y = treeMinimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                rbTransplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }

            rbTransplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if (yOriginalColor == Color.BLACK.getColor()) {
            rbDeleteFixup(x);
        }
    }

    /**
     * 获取key最小的节点
     *
     * @param node
     * @return
     */
    private Node treeMinimum(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * 内部节点定义
     */
    @Data
    static final class Node {


        public Node() {
        }

        public Node(int color, Node left, Node right, Node parent, WrappedData data) {
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.data = data;
            if (data != null) {
                key = data.getKey();
            }
        }

        public Node(int key) {
            this.key = key;
        }

        public Node(WrappedData data) {
            if (data != null) {
                this.key = data.getKey();
            }
        }

        /**
         * 颜色
         *
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
