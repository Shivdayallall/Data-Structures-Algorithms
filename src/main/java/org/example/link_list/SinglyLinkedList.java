package org.example.link_list;

public class SinglyLinkedList {

    // -------------------------
    // 1) NODE (building block)
    // -------------------------
    private static class Node {              // A Node is one element in the linked list
        int data;                            // The value stored in this node
        Node next;                           // Reference to the next node (null means end)

        Node(int data) {                     // Constructor to create a node with a value
            this.data = data;                // Store the value in the node
            this.next = null;                // New nodes start with no next node
        }
    }

    // -------------------------
    // 2) LINKED LIST STATE
    // -------------------------
    private Node head;                       // Points to the first node in the list
    private int size;                        // Tracks how many nodes are in the list

    // -------------------------
    // 3) CONSTRUCTOR
    // -------------------------
    public SinglyLinkedList() {                 // Creates an empty linked list
        this.head = null;                    // No head because list is empty
        this.size = 0;                       // Size is 0 because list is empty
    }

    // -------------------------
    // 4) ADT OPERATION: isEmpty
    // -------------------------
    public boolean isEmpty() {               // Returns true if list has no nodes
        return head == null;                 // If head is null, list is empty
    }

    // -------------------------
    // 5) ADT OPERATION: size
    // -------------------------
    public int size() {                      // Returns number of nodes in the list
        return size;                         // We track size, so this is O(1)
    }

    // -------------------------
    // 6) ADT OPERATION: prepend
    // -------------------------
    public void prepend(int value) {         // Add a new node at the front
        Node newNode = new Node(value);      // Create the new node
        newNode.next = head;                 // New node points to the old head
        head = newNode;                      // Head now becomes the new node
        size++;                              // Increase size because we added 1 node
    }

    // -------------------------
    // 7) ADT OPERATION: append
    // -------------------------
    public void append(int value) {          // Add a new node at the end
        Node newNode = new Node(value);      // Create a node holding value

        if (head == null) {                 // If the list is empty...
            head = newNode;                 // ...new node becomes head
            size++;                         // Update size
            return;                         // Done
        }

        Node current = head;                // Start at the head
        while (current.next != null) {      // Move until we reach last node
            current = current.next;         // Step forward
        }
        current.next = newNode;             // Link last node to new node
        size++;                             // Update size
    }

    // -------------------------
    // 8) ADT OPERATION: find
    // -------------------------
    public Node find(int value) {            // Return the first Node with matching value (or null)
        Node current = head;                // Start at the head
        while (current != null) {           // While we have not reached the end
            if (current.data == value) {    // If current node holds target value
                return current;             // Return the node reference
            }
            current = current.next;         // Otherwise move to the next node
        }
        return null;                        // Value not found
    }

    // -----------------------------------------
    // 9) ADT OPERATION: insertAfter(prev, value)
    // -----------------------------------------
    public void insertAfter(Node prevNode, int value) { // Insert value after prevNode
        if (prevNode == null) {                         // If prevNode is null...
            prepend(value);                             // ...treat as insert at head (common convention)
            return;                                     // Done
        }

        Node newNode = new Node(value);                 // Create the node to insert
        newNode.next = prevNode.next;                   // New node points to what prevNode pointed to
        prevNode.next = newNode;                        // prevNode now points to new node
        size++;                                         // Increase size
    }

    // -----------------------------------------
    // 10) ADT OPERATION: removeAfter(prev)
    // -----------------------------------------
    public void removeAfter(Node prevNode) { // Remove the node after prevNode
        if (head == null) {                  // If list is empty...
            return;                          // ...nothing to remove
        }

        if (prevNode == null) {              // If prevNode is null...
            head = head.next;                // ...remove the head by moving head forward
            size--;                          // Decrease size
            return;                          // Done
        }

        if (prevNode.next == null) {         // If there is no node after prevNode...
            return;                          // ...nothing to remove
        }

        prevNode.next = prevNode.next.next;  // Skip over the node after prevNode (unlink it)
        size--;                              // Decrease size
    }

    // -------------------------
    // 11) ADT OPERATION: remove(value)
    // -------------------------
    public boolean remove(int value) {       // Remove first occurrence of value; return true if removed
        if (head == null) {                  // If empty list...
            return false;                    // ...cannot remove anything
        }

        if (head.data == value) {            // If head is the target...
            head = head.next;                // ...remove head by moving head forward
            size--;                          // Update size
            return true;                     // Success
        }

        Node prev = head;                    // prev starts at head
        Node current = head.next;            // current starts at second node

        while (current != null) {            // Walk through the list
            if (current.data == value) {     // If we found the target...
                prev.next = current.next;    // ...unlink current by skipping it
                size--;                      // Update size
                return true;                 // Success
            }
            prev = current;                  // Move prev forward
            current = current.next;          // Move current forward
        }

        return false;                        // Not found
    }

    // -------------------------
    // 12) Utility: toString
    // -------------------------
    @Override
    public String toString() {               // Convert list to readable format like "4 -> 9 -> 8 -> null"
        StringBuilder sb = new StringBuilder(); // Efficient string builder
        Node current = head;                 // Start at head
        while (current != null) {            // Traverse nodes
            sb.append(current.data).append(" -> "); // Add current data + arrow
            current = current.next;          // Move forward
        }
        sb.append("null");                   // End marker
        return sb.toString();                // Return final string
    }

    // --------------------------
    // SORT (Insertion Sort)
    // --------------------------
    public void sort() {
        // If head is null, list is empty -> nothing to sort
        if (head == null) return;

        // previous_node starts at head (first node of the sorted "prefix")
        Node previousNode = head;

        // current_node starts at the node after head
        Node currentNode = head.next;

        // Walk through the list one node at a time
        while (currentNode != null) {

            // Save next_node now, because currentNode might move
            Node nextNode = currentNode.next;

            // Find where currentNode should be inserted in the sorted prefix [head .. previousNode]
            Node position = findInsertionPosition(currentNode.data, previousNode);

            // If position == previousNode, currentNode is already in correct place
            if (position == previousNode) {
                // Advance the sorted boundary forward
                previousNode = currentNode;
            } else {
                // Remove currentNode from its current spot (it's right after previousNode)
                previousNode.next = currentNode.next;

                // Insert currentNode at the correct position
                if (position == null) {
                    // Insert at head
                    currentNode.next = head;
                    head = currentNode;
                } else {
                    // Insert after 'position'
                    currentNode.next = position.next;
                    position.next = currentNode;
                }
            }

            // Move currentNode forward (using the saved next pointer)
            currentNode = nextNode;
        }
    }

    /**
     * Finds the node AFTER which we should insert a value to keep ascending order,
     * searching only within the sorted prefix from head up to sortedTail.
     *
     * Returns:
     * - null  => insert at head
     * - a Node => insert AFTER that Node
     */
    private Node findInsertionPosition(int value, Node sortedTail) {
        // If value should go before the current head, return null (meaning insert at head)
        if (head.data > value) {
            return null;
        }

        Node position = head;

        // Move position forward while:
        // - we haven't passed the sortedTail boundary
        // - and inserting after position is still valid (next is <= value)
        while (position != sortedTail && position.next != null && position.next.data <= value) {
            position = position.next;
        }

        // If we stopped at sortedTail, that means value belongs after sortedTail
        // (i.e., already in correct position at the end of the sorted prefix)
        return position;
    }

    // --------------------------
    // Iterate
    // --------------------------
    public void traverse() {

        if (head == null) {
            System.out.println("(empty)");
            return;
        }
        Node current_node = head;
        while(current_node != null) {
            System.out.println(current_node.data);
            current_node = current_node.next;
        }
    }




    // -------------------------
    // Main Method to test the code
    // -------------------------
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList(); // Create empty list

        list.append(4);                           // List: 4
        list.append(9);                           // List: 4 -> 9
        list.append(1);                           // List: 4 -> 9 -> 1
        list.append(8);                           // List: 4 -> 9 -> 1 -> 8

        System.out.println("Before: " + list);

        list.sort(); // ✅ invoke the sort function here

        System.out.println("After:  " + list);             // Print list

        Node node9 = list.find(9);                // Get a reference to the node that holds 9
        list.removeAfter(node9);                  // Remove the node after 9 (removes 1)
        System.out.println(list);                 // Print list: 4 -> 9 -> 8 -> null

        list.removeAfter(null);                   // Remove head (removes 4)
        System.out.println(list);                 // Print list: 9 -> 8 -> null

        list.insertAfter(node9, 7);               // Insert 7 after node9
        System.out.println(list);// Print list: 9 -> 7 -> 8 -> null

        list.traverse();
    }


}
