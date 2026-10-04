class Solution {
    public boolean checkValidString(String s) {
        int low = 0;  // Minimum possible open parentheses
        int high = 0; // Maximum possible open parentheses

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else if (c == '*') {
                low--;  // if we treat '*' as ')'
                high++; // if we treat '*' as '('
            }

            // If max possible open parentheses drops below 0, 
            // there are too many ')' to ever balance.
            if (high < 0) {
                return false;
            }

            // min open parentheses cannot be negative
            if (low < 0) {
                low = 0;
            }
        }

        // The string is valid if we can successfully close all parentheses (low == 0)
        return low == 0;
    }
}
