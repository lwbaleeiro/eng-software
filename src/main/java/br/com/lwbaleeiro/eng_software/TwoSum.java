package br.com.lwbaleeiro.eng_software;

import java.util.HashMap;
import java.util.Map;

/***
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 ***/

public class TwoSum {

    // mine solution
    public static int[] twoSum(int[] nums, int target) {

        int[] position = new int[2];
        int posA = 0;
        boolean contn = true;

        while (contn) {

            for (int i = 0; i < nums.length; i++) {
                if ((nums[posA] + nums[i]) == target && posA != i) {
                    contn = false;
                    position[0] = posA;
                    position[1] = i;
                }
            }

            posA++;

            if (posA == nums.length) break;
        }

        return position;
    }

    // "Brute Force"
    public static int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{}; // No solution found
    }


    // Time & space complexity: 0(n)
    // time: O(n) - we iterate through the list of numbers exactly once
    // space: O(n) - the space used by the map is proportional to the number of elements in the list
    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int position = 0; position < nums.length; position++) {

            // currentNumber + x = target
            // x = target - currentNumber

            int currentNumber = nums[position];
            int x = target - currentNumber;

            // If I have already seen x, then I have found the solution
            if (numMap.containsKey(x)) {
                return new int[] {numMap.get(x), position};
            }

            // Otherwise, I store the current number in the map
            numMap.put(currentNumber, position);
        }

        return new int[]{}; // No solution found
    }
}
