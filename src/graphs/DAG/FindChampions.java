package graphs.DAG;

import java.util.HashSet;
import java.util.Set;

public class FindChampions {

    //Solution 2//Finding in-degrees
    //O(n + m) TC and O(n) SC(To store in-degrees in array)
    public int findChampion2(int n, int[][] edges) {
        //Find the in-degree of the nodes
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            indegree[edge[1]]++;
        }
        boolean found = false;
        int node = 0;
        //Iterate through all nodes
        for (int i = 0; i <= n - 1; i++) {
            //If in-degree is zero, then it is strong node
            if (indegree[i] == 0) {
                //If already found, then not unique
                if (found) {
                    return -1;
                }
                //Mark result found
                found = true;
                node = i;
            }
        }
        return found ? node : -1;
    }

    //Solution 1
    //O(n + m) TC and O(n) SC(To store nodes in set)
    public int findChampion(int n, int[][] edges) {
        //Find all weaker nodes, where edge ends
        Set<Integer> weaker = new HashSet<>();
        for (int i = 0; i < edges.length; i++) {
            weaker.add(edges[i][1]);
        }
        //Result
        int result = -1;
        //Iterate through all nodes
        for (int i = 0; i <= n - 1; i++) {
            //If the node is not in weaker node
            if (!weaker.contains(i)) {
                //If result as already set, then the result is not unique, return -1
                if (result != -1) {
                    return -1;
                }
                //Else, assign the current node to result
                result = i;
            }
        }
        return result;
    }
}
