package com.javamaster.b2c.cloud.test.learn.java.thinking;

import java.util.Arrays;

public class AdjMatricx {
    public static final int INFINITY = -1;
    private int vertexNum, edgeNum;
    private String[] vertex;
    private int[][] edge;

    public AdjMatricx() {
        super();
        vertexNum = 5;
        edgeNum = 6;
        vertex = new String[vertexNum];
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = "v" + i;
        }
        edge = new int[vertexNum][vertexNum];
        for (int i = 0; i < edge.length; i++) {
            for (int j = 0; j < edge[i].length; j++) {
                edge[i][j] = INFINITY;
            }
            edge[i][i] = 0;
        }
        edge[0][3] = 23;
        edge[1][0] = 12;
        edge[2][0] = 26;
        edge[1][2] = 66;
        edge[2][1] = 11;
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("顶点数组:\n" + Arrays.toString(vertex) + "\n");
        sBuilder.append("边数组:\n");
        for (int i = 0; i < edge.length; i++) {
            sBuilder.append(Arrays.toString(edge[i]) + "\n");
        }
        return sBuilder.toString();
    }

    private boolean[] visited = new boolean[5];

    {
        for (int i = 0; i < 5; i++) {
            visited[i] = false;
        }
    }

    public void DFS(int i) {
        System.out.println(vertex[i]);
        visited[i] = true;
        for (int j = 0; j < vertex.length; j++) {
            if (edge[i][j] != -1 && !visited[j]) {
                DFS(j);
            }
        }
    }

}

class AdjMatricxTest {
    public static void main(String[] args) {
        AdjMatricx adjMatricx = new AdjMatricx();
        System.out.println(adjMatricx);
        // for (int i = 0; i < adjMatricx.vertexNum; i++) {
        // if (!adjMatricx.visited[i]) {
        // adjMatricx.DFS(i);
        // }
        // }
        adjMatricx.DFS(0);
    }
}