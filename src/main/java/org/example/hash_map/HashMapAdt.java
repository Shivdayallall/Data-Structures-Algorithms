package org.example.hash_map;
import java.util.HashMap;

public class HashMapAdt {
    public static void main(String[] args) {
        HashMap<String, Integer> employeeID = new HashMap<>();

        // Add values to the HashMap
        employeeID.put("john", 89876);
        employeeID.put("billy", 98743);
        employeeID.put("ben", 12397);

        // Preview the data in the map
        System.out.println(employeeID);

        // Get a value from the map using the key
        System.out.println(employeeID.get("ben"));

        // Check to see if a key is in the map by checking the key, This returns a boolean
        System.out.println(employeeID.containsKey("john"));

        // Check to see if a value is in the map by checking the value, This returns a boolean
        System.out.println(employeeID.containsValue(7845556));

        // Remove data from the hashmap
        System.out.println(employeeID.remove("ben"));

        // Preview the data in the map
        System.out.println(employeeID);
    }
}
