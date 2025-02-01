package graphs.topological_sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortUsingDFS {

    //Using stack
    //TC: O(V+E)
    //SC: O(V)
    public List<Integer> topologicalSort(int[][] adj) {
        int V = adj.length;
        boolean[] visited = new boolean[V]; //To keep track of visited nodes
        Stack<Integer> stack = new Stack<>(); //To store the result

        //Topological sort on all unvisited nodes
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(adj, i, visited, stack);
            }
        }

        //Add contents of stack to result
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void topologicalSortUtil(int[][] adj, int u, boolean[] visited, Stack<Integer> stack) {
        //Mark the node as visited
        visited[u] = true;

        //Visit all unvisited neighbors
        for (int neighbor : adj[u]) {
            if (!visited[neighbor]) {
                topologicalSortUtil(adj, neighbor, visited, stack);
            }
        }
        //Add the node to stack
        stack.push(u);
    }

    public static void main(String[] args) {
        int[][] adj = new int[][] {
                {},
                {},
                {3},
                {1},
                {0, 1},
                {0, 2}
        };
        //o/p is [5, 4, 2, 3, 1, 0]
        TopologicalSortUsingDFS topologicalSortUsingDFS = new TopologicalSortUsingDFS();
        System.out.println(topologicalSortUsingDFS.topologicalSort(adj));
    }
}
