package org.example.stack;



public class StackUsingLinkedList {
    // Node class (one box in the stack)
    private static class Node {
        int value;     // the number
        Node next;     // the node below

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // Top of the stack
    private Node top;

    // Constructor
    public StackUsingLinkedList() {
        top = null; // stack starts empty
    }

    // Push: add to top
    public void push(int value) {
        Node newNode = new Node(value); // make new box
        newNode.next = top;             // tie to old top
        top = newNode;                  // move top up
    }

    // Pop: remove from top
    public int pop() {
        if (top == null) {
            System.out.println("Stack is EMPTY");
            return -1;
        }

        int value = top.value; // remember value
        top = top.next;        // move top down
        return value;
    }

    // Peek: look at top
    public int peek() {
        if (top == null) {
            System.out.println("Stack is EMPTY");
            return -1;
        }
        return top.value;
    }

    // Print entire stack
    public void printStack() {
        if (top == null) {
            System.out.println("Stack is EMPTY");
            return;
        }

        System.out.print("Stack (top → bottom): ");
        Node current = top;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Test it
    public static void main(String[] args) {
        StackUsingLinkedList stack = new StackUsingLinkedList();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);


        stack.printStack();   // 30 20 10

        stack.pop();

        stack.printStack();   // 20 10
    }
}
