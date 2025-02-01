package graphs.union_find;

/**
 * https://leetcode.com/problems/redundant-connection/description
 */
public class RedundantConnection {

    //TC: O(n) using union find
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (unionFind.merge(edge[0] - 1, edge[1] - 1)) {
                //Found a cycle, return the edge
                return edge;
            }
        }
        return new int[0];
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] size;

        public UnionFind(int V) {
            parent = new int[V];
            size = new int[V];
            for (int i = 0; i < V; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int u) {
            if (parent[u] == u) {
                return u;
            }
            parent[u] = find(parent[u]);
            return parent[u];
        }

        public boolean merge(int i, int j) {
            int iRep = find(i);
            int jRep = find(j);
            if (iRep == jRep) {
                return true; //If the representatives are same, then they are already part of same set. There is a cycle
            }
            if (size[i] < size[j]) {
                parent[iRep] = jRep;
                size[jRep] = size[jRep] + size[iRep];
            } else {
                parent[jRep] = iRep;
                size[iRep] = size[iRep] + size[jRep];
            }
            return false;
        }
    }
}
