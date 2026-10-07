package org.example.hash_map.ContainsDuplicate;

import java.util.HashMap;

public class ContainsDuplicate {
    public static void main(String[] args) {
        Boolean results = containsDuplicate(new int[] {1,2,3,4,1});
        System.out.println(results);

    }
    public static boolean containsDuplicate(int[] nums) {
        // Create the HashMap for storing the numbers and their count

        HashMap<Integer,Integer> map = new HashMap<>();

        // Iterate over the nums[] and add the numbers to the HashMap
        for (int i = 0; i < nums.length; i++) {

            // Add each number to the hashmap
            // Get the current count for nums[i]
            //
            // If nums[i] is NOT in the map yet:
            // getOrDefault(nums[i], 0) returns 0
            //
            // Then +1 makes the first count = 1
            //
            // If nums[i] already exists:
            // getOrDefault(...) returns its current count
            // and +1 increases that count

            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            // After updating the count,
            // check whether this number has appeared more than once
            //
            // count > 1 means we found a duplicate

            if (map.get(nums[i]) > 1) {
                return true;
            }
        }
        return false;
    }
}
