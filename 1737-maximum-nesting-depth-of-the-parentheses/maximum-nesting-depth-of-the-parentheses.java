class Solution {
    public int maxDepth(String s) {
        int maxDepth = 0;
        int currentDepth = 0;
        
        // Loop through the characters of the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                currentDepth++;
                // Update max depth encountered so far
                if (currentDepth > maxDepth) {
                    maxDepth = currentDepth;
                }
            } else if (c == ')') {
                // An open parenthesis is closed, decrease the depth
                currentDepth--;
            }
        }
        
        return maxDepth;
    }
}
