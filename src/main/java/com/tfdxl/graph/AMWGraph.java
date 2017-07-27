package com.tfdxl.graph;

import java.util.ArrayList;

/**
 * Created by tianfeng on 2017/7/20.
 */
public class AMWGraph {

    private ArrayList vertexList;//存储点的链表
    private int[][] edges;//邻接矩阵，用来存储边
    private int numOfEdges;//边的数目

    public AMWGraph(ArrayList vertexList, int[][] edges, int numOfEdges) {
        this.vertexList = vertexList;
        this.edges = edges;
        this.numOfEdges = numOfEdges;
    }

    public AMWGraph(int n){
        //init
        edges = new int[n][n];
        vertexList = new ArrayList(n);
    }

    //得到结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点i的数据
    public Object getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1,v2的权值
    public int getWeight(int v1,int v2) {
        return edges[v1][v2];
    }

    //插入结点
    public void insertVertex(Object vertex) {
        vertexList.add(vertexList.size(),vertex);
    }

    //插入结点
    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2]=weight;
        numOfEdges++;
    }

    //删除结点
    public void deleteEdge(int v1,int v2) {
        edges[v1][v2]=0;
        numOfEdges--;
    }

}
