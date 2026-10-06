class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;
        int closeNeeded = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // We have an open parenthesis; it might need a closing partner later
                closeNeeded++;
            } else {
                // We found a closing parenthesis
                if (closeNeeded > 0) {
                    // It matches with an existing open parenthesis
                    closeNeeded--;
                } else {
                    // No open parenthesis is available to match, so we need to add an open one
                    openNeeded++;
                }
            }
        }

        // The total additions required is the sum of unmatched open and close pairs
        return openNeeded + closeNeeded;
    }
}

