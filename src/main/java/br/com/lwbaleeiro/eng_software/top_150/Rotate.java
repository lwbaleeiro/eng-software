package br.com.lwbaleeiro.eng_software.top_150;

import java.util.Arrays;

/*
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

 */

public class Rotate {

    // My solution (don't work)
    public static int[] rotate(int[] nums, int k) {

        int auxA = 0;
        int auxB = 0;
        boolean continuar = nums.length - k == k;

        for (int i = 0; i < nums.length - 1; i++) {

            if (k != 0) {
                auxA = nums[i];
                auxB = nums[nums.length - k];

                nums[i] = auxB;
                nums[nums.length - k] = auxA;
                k--;
            } else if (continuar){
                auxA = nums[i];
                auxB = nums[i + 1];

                nums[i] = auxB;
                nums[i + 1] = auxA;
            }
        }

        return nums;
    }

    // Best solution 1:
    public static int[] rotate1(int[] nums, int k) {

        int n = nums.length;
        k = k % n; // handle cases where k >= n
        int[] rotated = new int[n];

        for (int i = 0; i < n; i++) {
            rotated[(i+k) %n] = nums[i];
        }

        nums = Arrays.copyOf(rotated, rotated.length);
//        for (int i = 0; i < n; i++) {
//            nums[i] = rotated[i];
//        }

        return nums;
    }

    public static int[] rotate2(int[] nums, int k) {
        k %= nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

        return nums;
    }

    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
