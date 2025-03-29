package br.com.lwbaleeiro.eng_software.top_150;

import java.util.Arrays;

/*
 We are given two sorted arrays nums1 and nums2 of sizes m and n, respectively.
 We need to merge these two arrays into a single sorted array, and the result should be stored
 inside nums1. Since nums1 is of size m+n, we can use this extra space to store the merged array.
 We can iterate through the arrays from the end and place the larger element in the end of nums1.
*/
public class MergeSortedArray {
    // Using TLS
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Traverse through nums2 and append its elements to the end of nums1 starting from index m
        for (int j = 0, i = m; j < n; j++) {
            nums1[i] = nums2[j];
            i++;
        }
        // Sort the entire nums1 array using sort() function.
        Arrays.sort(nums1);
    }

    // my solution
    public static void mergeMy(int[] nums1, int m, int[] nums2, int n) {
        int aux = m;
        for (int i = 0; i < n; i++) {
            nums1[aux] = nums2[i];
            aux++;
        }

        Arrays.sort(nums1);
    }
}
