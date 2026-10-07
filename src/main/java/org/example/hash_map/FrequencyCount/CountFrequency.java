package org.example.hash_map.FrequencyCount;

/*
* Problem
Given an integer array nums, return the number that appears the most times.
You may assume there is only one number with the highest frequency.
Input:
nums = [5, 2, 5, 3, 2, 5]

Output:
5

Because:
5 → appears 3 times
2 → appears 2 times
3 → appears 1 timeProblem
Given an integer array nums, return the number that appears the most times.
You may assume there is only one number with the highest frequency.
Input:
nums = [5, 2, 5, 3, 2, 5]

Output:
5

Because:
5 → appears 3 times
2 → appears 2 times
3 → appears 1 time
*
*
* */

import java.util.HashMap;

public class CountFrequency {
    public static void main(String[] args) {
        int results = mostFrequent(new int[] {5,2,5,3,2,5,4,6,8,7,8,8,8,9,8});
        System.out.println(results);
    }

    public static int mostFrequent(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i],1);
            }
        }
        int mostFrequentNumber = 0;
        int highestCount = 0;

        for (int number : map.keySet()) {
            int count = map.get(number);

            if (count > highestCount) {
                highestCount = count;
                mostFrequentNumber = number;
            }
        }

        return mostFrequentNumber;
    }
}



























