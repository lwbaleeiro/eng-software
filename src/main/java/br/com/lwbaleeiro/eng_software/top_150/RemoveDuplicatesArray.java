package br.com.lwbaleeiro.eng_software.top_150;

import java.util.Arrays;
/*
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once.
 The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially.
The remaining elements of nums are not important as well as the size of nums.
Return k.

Example 1:
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
 */
public class RemoveDuplicatesArray {

    // my solution
    public static int removeDuplicates(int[] nums) {
        int count = 1;
        for (int i = 0; i < nums.length -1; i++) {
            if (nums[i] == nums[i+1]) {
                nums[i] = 9999;
            }
            if (nums[i] != 9999) {
                count++;
            }
        }
        Arrays.sort(nums);
        return count;
    }

    // best solution
    public static int removeDuplicates2(int[] nums) {
        // Why i and j start from index 1?
        // R: Since the array is sorted, the first element nums[0] is always unique.
        //    Therefore, there is no need to compare the first element with any other elements.
        int i = 1;
        for (int j = 1; j < nums.length; j++) {
            // We check if the current element nums[j] is different from the previous unique element nums[i - 1].
            if (nums[j] != nums[i - 1]) {
                // If they are different, it means nums[j] is a unique element.
                nums[i] = nums[j];
                // We set nums[i] to nums[j], placing the unique element at the i-th position.
                // We then increment i by 1 to prepare for the next unique element.
                i++;
            }
        }
        // Return the Count of Unique Elements
        return i;
    }
}
