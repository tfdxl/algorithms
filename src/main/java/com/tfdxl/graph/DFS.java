package com.tfdxl.graph;

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

        u.setColor(BFS.Color.WHITE);
        time++;
        u.setAdjacentSearchedTime(time);
    }
}
