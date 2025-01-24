package graphs.cycle_detection;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-eventual-safe-states/description/
 */
public class FindSafeStates {

    //Solution 2
    //TC: O(V+E), SC: O(V)
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        int n = graph.length;
        //To keep track of node, whether a cycle has already been detected, i.e. Memoization using DP
        Boolean[] hasCycle = new Boolean[n];
        boolean[] recStack = new boolean[n];
        //Safe node is a node whose every possible path starting from that node leads to a terminal node(there are no outgoing edges)
        //This means, all those nodes with cycles are not safe nodes. Because, if a node has a cyclic path, then it will never lead to a terminal node.
        //So, we will find all those nodes that have cycles
        //To detect cycles, we will use detect cycle using DFS approach
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!findCyclicNodes2(graph, i, hasCycle, recStack)) {
                result.add(i);//safe node
            }
        }
        return result;
    }

    private boolean findCyclicNodes2(int[][] graph, int node, Boolean[] hasCycle, boolean[] recStack) {
        //If the node is already processed, return the value found
        if (hasCycle[node] != null) {
            return hasCycle[node];
        }

        //Assume there is no cycle initially
        hasCycle[node] = false;
        //Add the node to recursive stack
        recStack[node] = true;
        //Visit neighbours
        for (int neighbour : graph[node]) {
            //If its part of recursive stack, then there is a cycle
            if (recStack[neighbour]) {
                return hasCycle[node] = true; //return true
            } else if (findCyclicNodes2(graph, neighbour, hasCycle, recStack)) {
                //If it was not part of recursive stack, then we find if it's a cyclic node
                return hasCycle[node] = true;
            }
        }

        recStack[node] = false; //Remove the node from recursive stack
        return hasCycle[node]; //Return value
    }

    //Solution 1
    //TC: O(V+E), SC: O(V)
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        //To keep track of node, whether a cycle has already been detected, i.e. Memoization using DP
        Boolean[] hasCycle = new Boolean[n];
        boolean[] recStack = new boolean[n];
        //Safe node is a node whose every possible path starting from that node leads to a terminal node(there are no outgoing edges)
        //This means, all those nodes with cycles are not safe nodes. Because, if a node has a cyclic path, then it will never lead to a terminal node.
        //So, we will find all those nodes that have cycles
        //To detect cycles, we will use detect cycle using DFS approach
        for (int i = 0; i < n; i++) {
            if (hasCycle[i] == null) {
                findCyclicNodes(graph, i, hasCycle, recStack);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!hasCycle[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean findCyclicNodes(int[][] graph, int node, Boolean[] hasCycle, boolean[] recStack) {
        //If the node is already processed, return the value found
        if (hasCycle[node] != null) {
            return hasCycle[node];
        }

        //Assume there is no cycle initially
        hasCycle[node] = false;
        //Add the node to recursive stack
        recStack[node] = true;
        //Visit neighbours
        for (int neighbour : graph[node]) {
            //If its part of recursive stack, then there is a cycle
            if (recStack[neighbour]) {
                hasCycle[node] = true; //We are not returning true here, as we want to find all the nodes that are part of cycle
            } else if (findCyclicNodes(graph, neighbour, hasCycle, recStack)) {
                //If it was not part of recursive stack, then we find if it's a cyclic node
                hasCycle[node] = true;
            }
        }

        recStack[node] = false; //Remove the node from recursive stack
        return hasCycle[node]; //Return value
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {1, 2},
                {2, 3},
                {5},
                {0},
                {5},
                {},
                {}
        };
        FindSafeStates findSafeStates = new FindSafeStates();
        //o/p is [2, 4, 5, 6]
        System.out.println(findSafeStates.eventualSafeNodes(graph));
    }
}
