package br.com.lwbaleeiro.eng_software;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 An input string is valid if:
 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Every close bracket has a corresponding open bracket of the same type.

 Example 1:
 Input: s = "()"
 Output: true

 Example 2:
 Input: s = "()[]{}"
 Output: true

 Example 3:
 Input: s = "(]"
 Output: false

 Example 4:
 Input: s = "([])"
 Output: true
 */

public class ValidParentheses {

    // My Solution
    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == '{') {
                stack.push(c);
            } else if (c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.search('(') == -1 || stack.peek() != '('){
                    return false;
                }

                stack.pop();
            } else if (c == '}') {
                if (stack.isEmpty() || stack.search('{') == -1 || stack.peek() != '{'){
                    return false;
                }

                stack.pop();
            } else if (c == ']') {
                if (stack.isEmpty() || stack.search('[') == -1 || stack.peek() != '['){
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    // Best practice
    public static boolean isValidParentheses2(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put('}', '{');
        mapping.put(']', '[');

        for (char c : s.toCharArray()) {
            if (mapping.containsValue(c)) {
                stack.push(c);
            } else if (mapping.containsKey(c)) {
                if (stack.isEmpty() || mapping.get(c) != stack.pop()) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
