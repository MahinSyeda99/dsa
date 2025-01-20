package graphs.bfs_and_dfs;

import java.util.*;

public class CloneUndirectedGraph {

    //O(n^2) TC solution
    //Because n - Number of nodes
    //Max number of edges for a node is n - 1. So, for n nodes, n (n - 1) edges
    //O(V + E) TC => O(n + n * (n - 1)) ~ O(n^2)
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        //Store the cloned nodes in hashmap
        Map<Node, Node> mp = new HashMap<>();
        //Add the source node to map
        mp.put(node, new Node(node.val));
        //Create a queue to do BFS
        Queue<Node> q = new LinkedList<>();
        q.add(node);

        //Do BFS
        while (!q.isEmpty()) {
            Node curr = q.poll();
            List<Node> neighbours = curr.neighbors;
            Node clonedCurr = mp.get(curr);
            for (Node neighbour : neighbours) {
                //Get cloned neighbour
                Node clonedNeighbour = mp.get(neighbour);
                //If the neighbour was not cloned earlier, then we clone it now and add it to map
                if (clonedNeighbour == null) {
                    clonedNeighbour = new Node(neighbour.val);
                    mp.put(neighbour, clonedNeighbour);
                    q.add(neighbour);//Add neighbour to queue as its not visited earlier
                }
                clonedCurr.neighbors.add(clonedNeighbour); //Add clonedNeighbour to neighbours list of clonedCurr
            }
        }
        return mp.get(node);
    }
}
