/**
* Problem :
* https://www.hackerrank.com/challenges/dynamic-array
*
*/

import java.io.*;
import java.util.*;

class DynamicArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int q = Integer.parseInt(str[1]);
        int t = n;
        
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        
        while(t-- >0) {         // Create 'n' empty sequences in 'list'
            list.add(new ArrayList<Integer>());
        }
        
        int lastAns =0;
        while(q-- >0) {
            str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int x = Integer.parseInt(str[1]);
            int y = Integer.parseInt(str[2]);
            
            if(a==1) {
                // Query 1 : append y to the specified sequence
                list.get((x^lastAns)%n).add(y); 
                
            } else if(a==2) {
                // Query 2 : get the y%Size-th element from the specified sequence
                int s = list.get((x^lastAns)%n).size();     //get size
                
                lastAns = list.get((x^lastAns)%n).get(y%s); // get element
                System.out.println(lastAns);
                
            }
        }
    }
}
