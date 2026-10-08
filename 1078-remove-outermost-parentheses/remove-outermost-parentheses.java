class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int opened = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If opened > 0, it means this '(' is not the outermost one
                if (opened > 0) {
                    result.append(c);
                }
                opened++;
            } else { // c == ')'
                opened--;
                // If opened > 0 after decrementing, this ')' is not the outermost one
                if (opened > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
