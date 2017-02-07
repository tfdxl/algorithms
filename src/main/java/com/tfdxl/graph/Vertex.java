package com.tfdxl.graph;

/**
 * Created by tianfeng on 2017/1/26.
 */
public class Vertex {


    //表示名字
    private char name;
    //定点的颜色
    private BFS.Color color;
    //该节点的前驱节点
    private Vertex parent;
    //该节点到源节点距离
    private double distanceFromSourceVertex;

    public Vertex(char name, BFS.Color color, Vertex parent, double distanceFromSourceVertex) {
        this.name = name;
        this.color = color;
        this.parent = parent;
        this.distanceFromSourceVertex = distanceFromSourceVertex;
    }

    public double getDistanceFromSourceVertex() {
        return distanceFromSourceVertex;
    }

    public void setDistanceFromSourceVertex(double distanceFromSourceVertex) {
        this.distanceFromSourceVertex = distanceFromSourceVertex;
    }

    public Vertex(char name, BFS.Color color, Vertex parent) {
        this.name = name;
        this.color = color;
        this.parent = parent;
    }

    public Vertex(char name) {

        this.name = name;
    }


    public Vertex(BFS.Color color, Vertex parent) {
        this.color = color;
        this.parent = parent;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public BFS.Color getColor() {
        return color;
    }

    public void setColor(BFS.Color color) {
        this.color = color;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }
}
