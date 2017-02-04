/**
* Breadth First Search for graphs.
*
*/

class BFS {

    public static void main(String[] args) throws IOException {        
        
        int n = 1234;       // number of nodes in graph
        
        // ArrayList for maintaing adj list of graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        // Array to mark which nodes are visited
        // so i dont visit them again
        // true = visited
        // false = not visited yet
        boolean[] vis = new boolean[n];
        
        // Queue for all the nodes to visit
        Queue<Integer> q = new LinkedList<Integer();
        q.add(0);       // Insert base node from where search will start
        vis[0];         // And mark it visited
        
        while(q.size() >0) {
            int a = q.remove();     // Remove the node to visit its neighbour nodes
            
            // Chech all the neighbours of a
            // and if they aren't visited mark them as visited
            // also add them to the queue, so that we can
            // visit its neighbours in future
            for(int i=0; i<adj.get(a).size(); i++) {
                int b = adj.get(a).get(i);
                if(!vis[b]) {
                    vis[b] = true;
                    q.add(b);
                }
            }
        }
        
    }
}