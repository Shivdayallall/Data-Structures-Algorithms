package org.example.hash_map.repeatedNumber;

/*
* Problem 1 — First Repeated Number
Given an integer array nums, return the first number that appears twice while scanning from left to right.
If no number repeats, return -1.
*
*
*
*    Input:
    nums = [4, 7, 2, 7, 9, 4]

    Output:
    7
*
*   4 → first time
    7 → first time
    2 → first time
    7 → seen before → return 7
* */

import java.util.HashMap;

public class RepeatedNumber {
    public static void main(String[] args) {
        int results = firstRepeated(new int[] {4,7,3,9,7,4});
        System.out.println(results);

    }
    public static int firstRepeated(int[] nums) {
        HashMap<Integer,Integer> repeatedNums = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            // Check if the current item in the array is in the map
            if(repeatedNums.containsKey(nums[i])) {
                 return nums[i];
            }
            // if the current item is not in the map, att it to the map and uses its value as its value in index position as the value, value = nums[i] key = i
            repeatedNums.put(nums[i],i);
        }
        return -1;
    }

}
