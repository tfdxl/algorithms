package com.tfdxl.graph;

import java.util.*;

/**
 * Created by tianfeng on 2017/7/30.
 * 深度优先搜索：总是对最近才发现的节点v的出发边进行搜索，直到该节点所有的出发边都被发现为止
 */
public class DFS {

    public void dfs(Graph graph) {

        //init
        for (Vertex vertex : graph.getVertexes()) {
            vertex.setColor(BFS.Color.WHITE);
            vertex.setParent(null);
        }
        //遍历每个节点
        int time = 0;
        for (Vertex vertex : graph.getVertexes()) {
            if (vertex.getColor() == BFS.Color.WHITE) {
                dfsVisit(graph, vertex, time);
            }
        }
    }

    private void dfsVisit(Graph graph, Vertex u, int time) {

        //white vertex u has been discovered
        time++;
        u.setFirstSearchedTime(time);
        u.setColor(BFS.Color.GRAY);

        for (Vertex v : graph.getAdjcents().get(u)) {
            if (v.getColor() == BFS.Color.WHITE) {
                v.setParent(u);
                dfsVisit(graph, v, time);
            }
        }

        //已经没有邻接节点，就返回
        u.setColor(BFS.Color.BLACK);
        time++;
        u.setAdjacentSearchedTime(time);
    }

    public static void dfsUsingStack(Graph graph) {

        //init
        for (Vertex vertex : graph.getVertexes()) {
            vertex.setColor(BFS.Color.WHITE);
            vertex.setParent(null);
        }

        Stack<Vertex> stack = new Stack<>();

        for (Vertex start : graph.getVertexes()) {
            if (start.getColor() == BFS.Color.WHITE) {
                stack.push(start);
                start.setColor(BFS.Color.GRAY);
                System.out.println(start.getName());
                start.setParent(null);
            }

            while (!stack.isEmpty()) {
                Vertex u = stack.pop();
                for (Vertex v : graph.getAdjcents().get(u)) {
                    if (v.getColor() == BFS.Color.WHITE) {
                        System.out.println(v.getName());
                        v.setColor(BFS.Color.GRAY);
                        v.setParent(u);
                        stack.push(v);
                    }
                }
                u.setColor(BFS.Color.BLACK);
            }
        }

    }

    public static void main(String[] args) {
        Vertex[] vertexes = new Vertex[8];
        vertexes[0] = new Vertex('s');
        vertexes[1] = new Vertex('z');
        vertexes[2] = new Vertex('y');
        vertexes[3] = new Vertex('x');

        vertexes[4] = new Vertex('w');
        vertexes[5] = new Vertex('v');
        vertexes[6] = new Vertex('u');
        vertexes[7] = new Vertex('t');

        Map<Vertex,Queue<Vertex>> map = new HashMap<>();
        Queue<Vertex> q1 = new LinkedList();
        q1.add(vertexes[1]);
        q1.add(vertexes[4]);
        map.put(vertexes[0],q1);
        Queue<Vertex> q2 = new LinkedList();
        q2.add(vertexes[2]);
        q2.add(vertexes[4]);
        map.put(vertexes[1],q2);
        Queue<Vertex> q3 = new LinkedList();
        q3.add(vertexes[3]);
        map.put(vertexes[2],q3);
        Queue<Vertex> q4 = new LinkedList();
        q4.add(vertexes[1]);
        map.put(vertexes[3],q4);

        Queue<Vertex> q5 = new LinkedList();
        q5.add(vertexes[3]);
        map.put(vertexes[4],q5);
        Queue<Vertex> q6 = new LinkedList();
        q6.add(vertexes[0]);
        q6.add(vertexes[4]);
        map.put(vertexes[5],q6);
        Queue<Vertex> q7 = new LinkedList();
        q7.add(vertexes[5]);
        q7.add(vertexes[7]);
        map.put(vertexes[6],q7);
        Queue<Vertex> q8 = new LinkedList();
        q8.add(vertexes[5]);
        q8.add(vertexes[6]);
        map.put(vertexes[7],q8);
        Graph g = new Graph(vertexes,map);
        dfsUsingStack(g);
    }
}
