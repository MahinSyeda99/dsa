package graphs.bfs_and_dfs;

import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule-iv
 */
public class CourseScheduleIV {

    //Solution 2 - Using topological sort
    //TC: O(V*(V + E)) where V + E is TC of bfs/Kahn's and V * (V + E) is adding pre-requisites
    //SC: O(V^2), for isReachable matrix
    public List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {
        //ai is prerequisite of bi => there is an edge from ai to bi
        //ai is prerequisite of bi and bi is prerequisite of ci => there is an edge from ai to bi and from bi to ci => there is a path from ai to ci
        //So, we need to find if there is a path from a node u to node v. Then, we can say node us is prerequisite of node v
        //So we find all those nodes reachable from u. And for all those nodes u is a prerequisite
        //We can use Topological sort, Kahn's algo to traverse in order, where first courses with no pre-requisites are traversed

        Map<Integer, Set<Integer>> nodesPrerequisites = topologicalSort(prerequisites, numCourses);

        List<Boolean> result = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            result.add(nodesPrerequisites.getOrDefault(query[1], Collections.emptySet()).contains(query[0]));
        }
        return result;
    }

    public Map<Integer, Set<Integer>> topologicalSort(int[][] prerequisites, int numCourses) {
        //Create adj list from prerequisite
        List<List<Integer>> adjList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses]; //Calculate in-degree of each node
        for (int[] prerequisite : prerequisites) {
            adjList.get(prerequisite[0]).add(prerequisite[1]);
            inDegree[prerequisite[1]]++;
        }

        Map<Integer, Set<Integer>> nodesPrerequisites = new HashMap<>(); //Keep track of all prerequisites of a node

        Queue<Integer> q = new LinkedList<>();
        //Add all nodes with in-degree 0 to queue
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int curr = q.poll();
            for (int adj : adjList.get(curr)) {
                inDegree[adj]--;
                if (inDegree[adj] == 0) {
                    q.add(adj);
                }
                nodesPrerequisites.computeIfAbsent(adj, k -> new HashSet<>()).add(curr);
                nodesPrerequisites.get(adj).addAll(nodesPrerequisites.getOrDefault(curr, Collections.emptySet()));
            }
        }
        return nodesPrerequisites;
    }


    //Solution 1
    //TC: O(V*(V + E)) where V + E is TC of bfs and V * (V + E) is for all V vertices
    //SC: O(V^2), for isReachable matrix
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        //ai is prerequisite of bi => there is an edge from ai to bi
        //ai is prerequisite of bi and bi is prerequisite of ci => there is an edge from ai to bi and from bi to ci => there is a path from ai to ci
        //So, we need to find if there is a path from a node u to node v. Then, we can say node us is prerequisite of node v
        //So we find all those nodes reachable from u. ANd for all those nodes u is a prerequisite
        Boolean[][] isReachable = new Boolean[numCourses][numCourses];
        //Create adj list from prerequisite
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            adjList.get(prerequisite[0]).add(prerequisite[1]);
        }

        for (int i = 0; i < numCourses; i++) {
            //Bfs with i as source
            bfs(numCourses, i, adjList, isReachable);
        }

        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            if (isReachable[query[0]][query[1]] == null || !isReachable[query[0]][query[1]]) {
                result.add(false);
            } else {
                result.add(true);
            }
        }
        return result;
    }

    public void bfs(int numCourses, int u, List<List<Integer>> adjList, Boolean[][] isReachable) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[numCourses];
        q.add(u);
        visited[u] = true;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int adj : adjList.get(curr)) {
                if (!visited[adj]) {
                    q.add(adj);
                    visited[adj] = true;
                    isReachable[u][adj] = true; //Mark adj node as reachable from node u
                }
            }
        }
    }

    public static void main(String[] args) {
        CourseScheduleIV courseScheduleIV = new CourseScheduleIV();
        int numCourses = 3;
        int[][] queries = new int[][]{
                {1, 0},
                {1, 2}
        };
        int[][] prerequisite = new int[][]{
                {1, 2},
                {1, 0},
                {2, 0}
        };
        //o/p is [true,true]
        System.out.println(courseScheduleIV.checkIfPrerequisite(numCourses, prerequisite, queries));
        numCourses = 2;
        queries = new int[][]{
                {1, 0},
                {0, 1}
        };
        prerequisite = new int[0][0];
        //o/p is [false, false]
        System.out.println(courseScheduleIV.checkIfPrerequisite(numCourses, prerequisite, queries));
        queries = new int[][]{
                {0, 1},
                {1, 0}
        };
        prerequisite = new int[][]{
                {1, 0}
        };
        //o/p is [false, true]
        System.out.println(courseScheduleIV.checkIfPrerequisite(numCourses, prerequisite, queries));
    }
}
