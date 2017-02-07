package com.tfdxl.graph;

import java.util.Map;
import java.util.Queue;

/**
 * Created by tianfeng on 2017/1/26，整个图的数据结构
 */
public class Graph {

    //定点集合
    private Vertex []vertexes;

    private Map<Vertex,Queue<Vertex>> adjcents;

    public Vertex[] getVertexes() {
        return vertexes;
    }

    public void setVertexes(Vertex[] vertexes) {
        this.vertexes = vertexes;
    }

    public Map<Vertex, Queue<Vertex>> getAdjcents() {
        return adjcents;
    }

    public void setAdjcents(Map<Vertex, Queue<Vertex>> adjcents) {
        this.adjcents = adjcents;
    }

    public Graph(Vertex[] vertexes, Map<Vertex, Queue<Vertex>> adjcents) {

        this.vertexes = vertexes;
        this.adjcents = adjcents;
    }
}
