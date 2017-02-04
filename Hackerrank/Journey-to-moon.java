/**
* Problem:
* https://www.hackerrank.com/challenges/journey-to-the-moon
* 
* Journey to the moon
* I have solved this problem using ArrayList and HashMap
*/

import java.io.*;
import java.util.*;

class journey {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int t = Integer.parseInt(str[1]);
        
        // array to allot each astronaut its country number
        // if there is only one astronaut from a country 
        // anot[] will be zero
        int[] anot = new int[n];
        
        // Next country number to be alloted
        int next = 1;
        
        while(t-- >0) {
            str = br.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            
            if(anot[a]==0 && anot[b]==0) {
                anot[a] = next;
                anot[b] = next;
                next++;
            } else {
                if(anot[a] !=0 && anot[b] == 0) {
                    anot[b]=anot[a];
                    
                } else if(anot[a] ==0 && anot[b] != 0){
                    anot[a] = anot[b];
                    
                } else if(anot[a] != 0 && anot[b] !=0) {
                    int x = anot[a];
                    int y = anot[b];
                    for(int i=0; i<n; i++) {
                        if(anot[i] ==y) {
                            anot[i] = x;
                        }
                    }
                }
            }
        }
        
        // Count the number of astronauts with same country number
        // map(country number, number of astronaut)
        // if anot[] is 0 
        // count it as different country with only one astronaut
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i=0; i<n; i++) {
            int a = anot[i];
            if(a==0) {
                map.put(-i, 1);
                continue;
            }
            if(map.containsKey(a)) {
                int v = map.get(a);
                map.put(a, ++v);
            } else {
                map.put(a, 1);
            }
        }
        
        // count the number of possible pairs
        long ans = 0;
        for(int k : map.keySet()) {
            int a = map.get(k);
            ans += a * (n-=a);
        }
        System.out.println(ans);
    }
}
