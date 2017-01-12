/**
* This is a sorting program which uses quick sort algorithm
* to sort an array of integers.
*
* @author prashantpiyush
*/
import java.io.*;
import java.util.*;

class Quicksort {
    
    static void quicksort(int[] array, int low, int high) {
        /**
        * First pick a element, pivot. Then reorder the elements
        * with all the elements less than pivot on one side and
        * those greater than pivot on other side of pivot.
        * Recursively call these subarrays till the whole array is
        * sorted.
        * 
        * @param array
        * @param low The lowest index of the subarray
        * @param high Max index of subarray
        */
        if(low>=high)
            return;
        
        int pivot = array[low];
        int i = low+1;
        int j = low+1;
        
        while(j <= high) {
            if(array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
            j++;
        }
        
        // Finaly place the pivot at its right postion
        swap(array, low, i-1);
        // Now solve the subarrays
        quicksort(array, low, i-2);
        quicksort(array, i, high);
    }
    
    static void swap(int[] array, int i, int j) {
        /**
        * This method swaps two elements at postion i and j of
        * array.
        */
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    public static void main(String[] args) throws IOException{
        /** 
        * This is main method. It inputs unsorted array of
        * integers and sorts it.
        *
        * @throws IOException
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Input as string array
        String[] string = br.readLine().split(" ");
        int len = string.length;
        
        // Parse to int array
        int[] array = new int[len];
        for(int i=0; i<len; i++) {
            array[i] = Integer.parseInt(string[i]);
        }
        
        quicksort(array, 0, len-1);
        System.out.println(Arrays.toString(array));
    }
}