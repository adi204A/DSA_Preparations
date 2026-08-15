class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        int nonZeroCount = 0;
        
        // Step 1: Calculate the total XOR sum and count non-zero elements
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                nonZeroCount++;
            }
        }
        
        // Case 1: The total XOR sum is already non-zero
        if (totalXor != 0) {
            return nums.length;
        }
        
        // Case 2: The total XOR is zero, but we have non-zero elements we can remove
        if (nonZeroCount > 0) {
            return nums.length - 1;
        }
        
        // Case 3: The array contains only zeros
        return 0;
    }
}
