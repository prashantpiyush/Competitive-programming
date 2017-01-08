/**
* This is a sorting program which uses mergesort algorithm
* to sort an array of integers.
*
* @author prashantpiyush
*/
import java.util.Arrays;
import java.io.*;

class Mergesort {
        
    static void sort(int[] arr, int low, int high) {
        /**
        * This method divides unsorted array into subarrays
        * which are eventually sorted and the merged to form
        * a sorted array.
        *
        * @param arr Array to be sorted
        * @param low Lowest index of the subarray formed
        * @param high Highest index of the subarray formed
        */
        if(low < high) {
            int mid = low + (high - low)/2;
            sort(arr, low, mid);
            sort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }
    
    static void merge(int[] arr, int low, int mid, int high) {
        /**
        * Merges subarrays while sorting them until only the
        * final sorted array remains.
        *
        * @param arr
        * @param low Min index of first subarray
        * @param mid Max index of first subarray, also (mid+1)
        *           is the min indes of second subarray
        * @param high Max index of second subarray
        */
        int temp[] = new int[arr.length];
        for(int p=0; p<arr.length; p++){
            temp[p] = arr[p];
        }
        int i= low; 
        int j = mid+1;
        
        // First copy smaller elements form both the subarrays
        while(i<= mid && j<=high) {
            if(temp[i] < temp[j]){
                arr[low] = temp[i];
                i++;
            } else {
                arr[low] = temp[j];
                j++;
            }
            low++;
        }
        // Now copy the remaining elements of first subarray,
        // if any.
        while(i<= mid){
            arr[low] = temp[i];
            i++;
            low++;
        }
        // Now copy the remaining elements of second subarray,
        // if any.
        while(j<= high){
            arr[low] = temp[j];
            j++;
            low++;
        }
    }
    
    public static void main(String args[]) throws IOException {
        /**
        * This is main method. It inputs unsorted array and calls
        * sort() to start merge sort.
        * 
        * @throws IOException
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Take input as string array
        String[] string = br.readLine().split(" ");
        
        // Convert to int array
        int[] array = new int[string.length];
        for(int i=0; i<string.length; i++) {
            array[i] = Integer.parseInt(string[i]);
        }
        
        sort(array, 0, array.length-1);
        
        System.out.println(Arrays.toString(array));
    }
}