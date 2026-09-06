class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] stores the number of distinct subsequences matching t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: an empty target string t can always be matched in 1 way
        dp[0] = 1; 
        
        // Iterate through each character of string s
        for (int i = 1; i <= m; i++) {
            char charS = s.charAt(i - 1);
            
            // Traverse t backwards to use values from the previous iteration of s
            for (int j = n; j > 0; j--) {
                char charT = t.charAt(j - 1);
                
                // If the characters match, accumulate the combinations
                if (charS == charT) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[n];
    }
}
