package br.com.lwbaleeiro.eng_software.top_150;

import java.util.Arrays;

/*
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2
*/

public class MajorityElements {

    // My shit solution
    public static int majorityElement(int[] nums) {
        int currentCount = 1;
        int biggerCount = 0;
        int auxBigger = 0;
        int aux = 0;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == aux) {
                currentCount++;
            } else {
                currentCount = 1;
                aux = nums[i];
            }

            if (currentCount > biggerCount) {
                biggerCount = currentCount;
                auxBigger = nums[i];
            }
        }

        return auxBigger;
    }

    // Best solution
    public static int majorityElementBest(int[] nums) {
        int res = 0; // return value
        int majority = 0; //  is frequency of majority number(= res)

        for (int n : nums) {
            // When majority is 0, it's time to change majority number(= res), because every time current number and res are different,
            // we add -1 to majority, so if majority is 0, that means a current majority number(= res) is not the majority number anymore
            if (majority == 0) {
                // That's why when majority is 0, update res with current number.
                res = n;
            }
            // Every time current number is the same as res, we add +1 to majority. If not, we add -1 to majority.
            majority += n == res ? 1 : -1;
        }

        return res;
    }
}
