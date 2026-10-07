package org.example.hash_map.Anagram;

/*
*
* ****************
242. Valid Anagram
* ****************
Given two strings s and t, return true if t is an anagram of s, and false otherwise.


----------
Example 1:
----------
Input: s = "anagram", t = "nagaram"
Output: true
*
*
----------
Example 2:
---------
Input: s = "rat", t = "car"
Output: false
*
* */

import java.util.HashMap;

public class Anagram {
    public static void main(String[] args) {
        Boolean result = isAnagram("happy", "pphay");
        System.out.println(result);

    }

    public static boolean isAnagram(String s, String t) {
        // Cant be an anagram if the amount of letters is not equal
        if (s.length() != t.length()) {
            return false;
        }

        // Create HashMap for the first string
        HashMap<Character, Integer> map = new HashMap<>();

        // iterate over the characters of string s and place them in the hashmap
        for (int i = 0; i < s.length(); i++) {
            //  Add the current value at position i in the loop to the HashMap, The KEY is current character at position i (charAt(i)),
            // KEY = current character
            // VALUE = how many times that character has appeared so far
            //
            // getOrDefault(character, 0):
            // - returns the current count if the character already exists
            // - returns 0 if the character is not in the map yet
            //
            // + 1 increases the count
            // put(...) stores the updated count back into the HashMap
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {

            // Get the current character from string t
            char ch = t.charAt(i);

            // Check if the current character exists in the HashMap
            //
            // If it does NOT exist, that means t contains a character
            // that was not found in s
            //
            // Example:
            // s = "cat"
            // t = "car"
            //
            // map contains: c, a, t
            // when we reach 'r', map does not contain 'r'
            // so the strings cannot be anagrams
            if (!map.containsKey(ch)) {
                return false;
            }

            // Get the current count for this character
            // and subtract 1 because we just found one matching
            // occurrence of that character in string t
            //
            // Example:
            // map contains:
            // a -> 2
            //
            // if ch = 'a':
            // 2 - 1 = 1
            //
            // then store:
            // a -> 1
            map.put(ch, map.get(ch) - 1);

            // If the count reaches 0, that means every occurrence
            // of this character from s has now been matched by t
            //
            // Example:
            // a -> 1
            //
            // after subtracting:
            // a -> 0
            //
            // so we no longer need 'a' in the HashMap
            if (map.get(ch) == 0) {
                map.remove(ch);
            }
        }

        // If the HashMap is empty, every character and every count
        // from s was perfectly matched by t
        //
        // Empty map = valid anagram
        //
        // If anything is still left in the map,
        // then some characters from s were not fully matched
        return map.isEmpty();
    }
}









