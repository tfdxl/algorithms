package com.tfdxl.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianfeng on 2017/7/20.
 */
@Data
public class AMWGraph {

    private List<Vertex> vertexList;//存储点的链表
    private int[][] edges;//邻接矩阵，用来存储边
    private List<Edge> edgeList;
    private int numOfEdges;//边的数目


    public AMWGraph(ArrayList vertexList, int[][] edges, int numOfEdges) {
        this.vertexList = vertexList;
        this.edges = edges;
        this.numOfEdges = numOfEdges;
    }

    public AMWGraph(int n) {
        //init
        edges = new int[n][n];
        vertexList = new ArrayList(n);
        int index = 0;
        for (Vertex vertex : vertexList) {
            vertex.setParent(null);
            vertex.setIndex(index);
            vertex.setDesc(index + "");
            index++;
        }
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
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }


    //插入结点
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edgeList.add(new Edge(v1, v2));
        numOfEdges++;
    }

    //删除结点
    public void deleteEdge(int v1, int v2) {
        edges[v1][v2] = 0;
        numOfEdges--;
    }

    private Vertex findVertex(int index) {
        for (Vertex vertex : vertexList) {
            if (vertex.getIndex() == index) {
                return vertex;
            }
        }
        throw new IllegalArgumentException("cannot find vertex of the index" + index);
    }

    @Data
    private class Edge {
        private int srcIndex;
        private int destIndex;

        public Edge(int srcIndex, int destIndex) {
            this.srcIndex = srcIndex;
            this.destIndex = destIndex;
        }
    }

    @Data
    public class Vertex {

        private int index;
        private String desc;
        private Vertex parent;
        private int estimatedMaxDistanceFromSrc;

    }

    /**
     * init
     *
     * @param graph
     */
    private void initializeSingleSource(AMWGraph graph) {
        for (Vertex vertex : graph.getVertexList()) {
            vertex.setEstimatedMaxDistanceFromSrc(Integer.MAX_VALUE);
            vertex.setParent(null);
        }
    }

    /**
     * 松弛操作
     *
     * @param u
     * @param v
     * @param graph
     */
    private void relax(Vertex u, Vertex v, AMWGraph graph) {
        if (v.getEstimatedMaxDistanceFromSrc() > u.getEstimatedMaxDistanceFromSrc() + graph.getWeight(u.getIndex(), v.getIndex())) {
            v.setEstimatedMaxDistanceFromSrc(u.getEstimatedMaxDistanceFromSrc() + graph.getWeight(u.getIndex(), v.getIndex()));
            v.setParent(u);
        }
    }

    /**
     * bell-man ford
     *
     * @param graph
     * @param sourceIndex
     */
    public void bellManFord(AMWGraph graph, int sourceIndex) {
        initializeSingleSource(graph);
        Vertex vertex = findVertex(sourceIndex);
        vertex.setEstimatedMaxDistanceFromSrc(0);

        for (int i = 0; i < vertexList.size(); i++) {
            for (Edge edge : graph.edgeList) {
                Vertex u = findVertex(edge.srcIndex);
                Vertex v = findVertex(edge.destIndex);
                relax(u, v, graph);
            }
        }

        for (Edge edge : graph.edgeList) {
            if (findVertex(edge.destIndex).getEstimatedMaxDistanceFromSrc() > findVertex(edge.srcIndex).getEstimatedMaxDistanceFromSrc() + getWeight(edge.srcIndex, edge.destIndex)) {
                throw new RuntimeException("存在负数值得环路，无解");
            }
        }
    }
}
