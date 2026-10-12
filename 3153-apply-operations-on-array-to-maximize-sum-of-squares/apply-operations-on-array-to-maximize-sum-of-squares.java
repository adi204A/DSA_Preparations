import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        // Define modulo constant to prevent integer overflow
        final int MOD = 1_000_000_007;
        
        // Array to count the number of set bits at each bit position (0 to 30)
        // Since nums[i] <= 10^9, 31 bits are sufficient (2^30 > 10^9)
        int[] bitCount = new int[31];
        
        // Count set bits at each position across all numbers
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCount[i]++;
                }
            }
        }
        
        long totalSumOfSquares = 0;
        
        // Greedily construct the k largest possible numbers
        for (int step = 0; step < k; step++) {
            long currentNum = 0;
            
            // Collect one available 1-bit from each position to maximize currentNum
            for (int i = 0; i < 31; i++) {
                if (bitCount[i] > 0) {
                    currentNum |= (1L << i);
                    bitCount[i]--; // Consume the bit
                }
            }
            
            // Add the square of the constructed number to the sum modulo 10^9 + 7
            long square = (currentNum * currentNum) % MOD;
            totalSumOfSquares = (totalSumOfSquares + square) % MOD;
        }
        
        return (int) totalSumOfSquares;
    }
}
