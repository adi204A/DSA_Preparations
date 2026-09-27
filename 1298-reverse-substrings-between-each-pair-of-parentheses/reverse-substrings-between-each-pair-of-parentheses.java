import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> stack = new Stack<>(); // Stores the starting index of characters inside '('

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Record the current length of the result string builder
                stack.push(result.length());
            } else if (c == ')') {
                // Get the starting index of the string to reverse
                int start = stack.pop();
                reverse(result, start, result.length() - 1);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }
}
