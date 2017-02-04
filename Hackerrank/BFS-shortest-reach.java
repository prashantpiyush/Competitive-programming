/**
* Problem :
* https://www.hackerrank.com/challenges/bfsshortreach
*
*/

import java.io.*;
import java.util.*;

class ShortReach {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine().trim());
        while(t-- >0) {
            String[] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            for(int i=0; i<n; i++) {
                list.add(new ArrayList<Integer>());
            }
            // edge input
            while(m-- >0) {
                str = br.readLine().split(" ");
                int a = Integer.parseInt(str[0]);
                int b = Integer.parseInt(str[1]);
                list.get(a-1).add(b-1);
                list.get(b-1).add(a-1);
            }
            Queue<Integer> q = new LinkedList<Integer>();
            int[] level = new int[n];
            Arrays.fill(level, -1);
            int s = Integer.parseInt(br.readLine().trim());
            // bfs
            q.add(s-1);
            level[s-1] = 0;
            while(q.size() >0) {
                int a = q.remove();
                ArrayList<Integer> adj = list.get(a);
                for(int i=0; i<adj.size(); i++) {
                    int b = adj.get(i);
                    if(level[b] == -1) {
                        level[b] = level[a] + 6;
                        q.add(b);
                    }
                }
            }
            for(int i=0; i<n; i++) {
                if(i != s-1) {
                    System.out.print(level[i] + " ");
                }
            }
            System.out.println();
        }
        
    }
}
