class Solution {
    public int scoreOfParentheses(String s) {
        int score = 0;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Moving down a nested layer
                depth++;
            } else {
                // Moving up a nested layer
                depth--;
                
                // If it is a core "()" pair, calculate its contribution
                if (s.charAt(i - 1) == '(') {
                    score += 1 << depth; // 1 << depth is equivalent to 2^depth
                }
            }
        }

        return score;
    }
}
