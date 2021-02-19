package com.javamaster.b2c.cloud.test.learn.java.thinking;

/**
 * @author yu
 */
public class AdjList {
    private int vertexNum, edgeNum;
    private Vertex[] vertex;

    public AdjList() {
        vertexNum = 4;
        edgeNum = 5;
        vertex = new Vertex[vertexNum];
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = new Vertex("v" + i, null, null);
        }
        for (int i = 0; i < edgeNum; i++) {

        }
        vertex[0].firstOutEdge = new EdgeNode(3, null);
        vertex[0].firstInEdge = new EdgeNode(1, new EdgeNode(2, null));
        vertex[1].firstOutEdge = new EdgeNode(0, new EdgeNode(2, null));
        vertex[1].firstInEdge = new EdgeNode(2, null);
        vertex[2].firstOutEdge = new EdgeNode(0, new EdgeNode(1, null));
        vertex[2].firstInEdge = new EdgeNode(1, null);
        vertex[3].firstInEdge = new EdgeNode(0, null);
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < vertex.length; i++) {
            sBuilder.append("顶点" + vertex[i].data + "的出度邻接顶点有:");
            if (vertex[i].firstOutEdge != null) {
                EdgeNode temp = vertex[i].firstOutEdge;
                while (temp != null) {
                    sBuilder.append(vertex[temp.adjvex].data + " ");
                    temp = temp.next;
                }
            }
            sBuilder.append("\n");
            sBuilder.append("顶点" + vertex[i].data + "的入度邻接顶点有:");
            if (vertex[i].firstInEdge != null) {
                EdgeNode temp = vertex[i].firstInEdge;
                while (temp != null) {
                    sBuilder.append(vertex[temp.adjvex].data + " ");
                    temp = temp.next;
                }
                sBuilder.append("\n");
            }
        }
        return sBuilder.toString();
    }

    private static class Vertex {
        private String data;
        private EdgeNode firstOutEdge;
        private EdgeNode firstInEdge;

        public Vertex(String data, EdgeNode firstOutEdge, EdgeNode firstInEdge) {
            this.data = data;
            this.firstOutEdge = firstOutEdge;
            this.firstInEdge = firstInEdge;
        }

    }

    private static class EdgeNode {
        private int adjvex;
        private EdgeNode next;

        public EdgeNode(int adjvex, EdgeNode next) {
            this.adjvex = adjvex;
            this.next = next;
        }

    }
}

class AdjListTest {
    public static void main(String[] args) {
        AdjList adjList = new AdjList();
        System.out.println(adjList);
    }
}