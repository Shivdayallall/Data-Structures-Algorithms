package org.example.stack;

public class StackUsingArray {

    // This array will hold our stack items
    private int[] stack;

    // This tells us where the top of the stack is
    private int top;

    // Constructor: creates a stack with a fixed size
    public StackUsingArray(int size) {
        stack = new int[size]; // make the array
        top = -1;              // stack is empty
    }

    // Push: add an item to the top
    public void push(int value) {
        if (top == stack.length - 1) {
            System.out.println("Stack is FULL");
            return;
        }

        top++;                 // move top up
        stack[top] = value;    // put value at top
    }

    // Pop: remove and return the top item
    public int pop() {
        if (top == -1) {
            System.out.println("Stack is EMPTY");
            return -1;
        }

        int value = stack[top]; // get top value
        top--;                  // move top down
        return value;
    }

    // Peek: look at the top item
    public int peek() {
        if (top == -1) {
            System.out.println("Stack is EMPTY");
            return -1;
        }

        return stack[top];
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    public void printStack() {
        if (top == -1) {
            System.out.println("Stack is EMPTY");
            return;
        }

        System.out.print("Stack (bottom → top): ");
        for (int i = 0; i <= top; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    // Main method to test the stack
    public static void main(String[] args) {
        boolean result = isValid("{}");
        System.out.print(result);


    }

    // Leetcode#20 -
    //    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
    //
    //    An input string is valid if:
    //
    //    Open brackets must be closed by the same type of brackets.
    //    Open brackets must be closed in the correct order.
    //    Every close bracket has a corresponding open bracket of the same type.
    //  Input: s = "()"
    //  Output: true

    // Input: s = "(]"
    //  Output: true


    public static boolean isValid(String s) {

        // set up the stack array and make the size of the array equal to the length of the string
        char[] charStack = new char[s.length()];

        // define the top of the stack and set it to 1 for empty
        int top = -1;

        // iterate over the stack of char
        for(int i = 0; i < s.length(); i++) {
            // what is the ch at i
            char ch = s.charAt(i);

            // if the string is  an opening bracket push
            if(ch == '(' || ch == '{' || ch == '[') {
                top++;
                charStack[top] = ch;
            }
            else {
                // check if the stack is empty
                if(top == -1) {
                    return false;
                }
                char last = charStack[top];
                top--;

                // check for the correct bracket pairing
                if (ch == ')' && last != '(') return false;
                if (ch == '}' && last != '{') return false;
                if (ch == ']' && last != '[') return false;
            }
        }
        return top == -1;
    }










}