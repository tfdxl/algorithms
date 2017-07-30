package com.tfdxl.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tianfeng on 2017/1/26.
 * 广度优先遍历算法:算法需要发现所有距离原节点s为k的所有节点之后才会发现距离节点s为k+1的其他节点
 * 执行广度优先遍历之前构建一颗广度优先树
 */
public class BFS {

    public void bfs(Graph graph, Vertex sourceVertex) {


        //将所有的节点涂成白色，除了源节点
        for (Vertex vertex : graph.getVertexes()) {
            if (vertex != sourceVertex) {
                vertex.setColor(Color.WHITE);
                vertex.setDistanceFromSourceVertex(Double.MAX_VALUE);
                vertex.setParent(null);
            }
        }
        //改变源节点
        sourceVertex.setColor(Color.GRAY);
        sourceVertex.setParent(null);
        sourceVertex.setDistanceFromSourceVertex(0);

        Queue<Vertex> grayQueue = new LinkedList<>();
        grayQueue.add(sourceVertex);
        while (!grayQueue.isEmpty()) {
            Vertex vertex = grayQueue.remove();
            Queue<Vertex> queue = graph.getAdjcents().get(vertex);
            for (Vertex vertex1 : queue) {
                if (vertex1.getColor() == Color.WHITE) {

                    System.out.println(vertex1.getName() + vertex1.getColor().toString());

                    vertex1.setColor(Color.GRAY);
                    vertex1.setDistanceFromSourceVertex(vertex.getDistanceFromSourceVertex() + 1);
                    vertex1.setParent(vertex);
                    grayQueue.add(vertex1);
                }
            }
            vertex.setColor(Color.BLACK);
        }
    }


    public void printPath(Graph graph, Vertex start, Vertex end) {

        if (end == start) {
            System.out.println(start.getName());
        } else if (end.getParent() == null) {
            System.out.println("No path from start to end");
        } else {
            printPath(graph, start, end.getParent());
            System.out.println(end.getName());
        }
    }

    public enum Color {
        WHITE("表示没有被发现"),
        GRAY("灰色：已知和未知的边界"),
        BLACK("已经发现并且子节点已经被发现");
        private String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        Color(String desc) {
            this.desc = desc;
        }
    }

}
