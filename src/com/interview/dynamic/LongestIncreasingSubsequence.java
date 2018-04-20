package com.interview.dynamic;

import java.util.Arrays;

/**
 * Date 05/02/2014
 * @author tusroy
 * 
 * Youtube link - https://youtu.be/CE2b_-XfVDk
 * 
 * Find a subsequence in given array in which the subsequence's elements are 
 * in sorted order, lowest to highest, and in which the subsequence is as long as possible
 * 
 * Solution : 
 * Dynamic Programming is used to solve this question. DP equation is 
 * if(arr[i] > arr[j]) { T[i] = max(T[i], T[j] + 1 }
 * 
 * Time complexity is O(n^2).
 * Space complexity is O(n)
 * 
 * Reference 
 * http://en.wikipedia.org/wiki/Longest_increasing_subsequence
 * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {

    /**
     * DP way of solving LIS
     */
    public int longestSubsequenceWithActualSolution(int arr[]){
        System.out.println(System.currentTimeMillis() );
        int T[] = new int[arr.length];
        int actualSolution[] = new int[arr.length];
        for(int i=0; i < arr.length; i++){
            T[i] = 1;
            actualSolution[i] = i;
        }
        
        for(int i=1; i < arr.length; i++){
            for(int j=0; j < i; j++){
                if(arr[i] > arr[j]){
                    if(T[j] + 1 > T[i]){
                        //this array is for largest value position
                        T[i] = T[j] + 1;
                        //*this array has location key for all the  indexes involved in LIs chain
                        actualSolution[i] = j;
                    }
                }
            }
        }
        
        //find the index of max number in T 
        int maxIndex = 0;
        for(int i=0; i < T.length; i++){
            if(T[i] > T[maxIndex]){
                maxIndex = i;
            }
        }
        
        //lets print the actual solution
        int t = maxIndex;
        int newT = maxIndex;
        do{
            t = newT;
            System.out.print(arr[t] + " ");
            newT = actualSolution[t];
        }while(t != newT);
        System.out.println();
        for(int x:actualSolution) {
            System.out.print(" "+x);
        }
        System.out.println();
        for(int x:T) {
            System.out.print(" "+x);
        }
        System.out.println();
        System.out.println(System.currentTimeMillis() );
        return T[maxIndex];
    }
    
    /**
     * Recursive way of solving LIS
     */
    public int longestSubsequenceRecursive(int arr[]){
        System.out.println(System.currentTimeMillis() );
        int maxLen = 0;
        for(int i=0; i < arr.length-1; i++){
            int len = longestSubsequenceRecursive(arr,i+1,arr[i]);
            if(len > maxLen){
                maxLen = len;
            }
        }
        System.out.println(System.currentTimeMillis() );
        return maxLen + 1;
        
    }
    
    private int longestSubsequenceRecursive(int arr[], int pos, int lastNum){
        if(pos == arr.length){
            return 0;
        }
        
        
        int t1 = 0;
        if(arr[pos] > lastNum){
            t1 = 1 + longestSubsequenceRecursive(arr, pos+1, arr[pos]);
        }
        int t2 = longestSubsequenceRecursive(arr, pos+1, lastNum);
        

        return Math.max(t1, t2);
    }
    
    public static void main(String args[]){
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int arr[] = {23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
        int arr2[]= {22,4,5,2,12,6,7,77};
        int result = lis.longestSubsequenceWithActualSolution(arr);
        //int result1 = lis.longestSubsequenceRecursive(arr);
        System.out.println(result);
      //  System.out.println(result1);
    }
}
