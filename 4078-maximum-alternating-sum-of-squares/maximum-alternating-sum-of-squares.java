import java.util.Arrays;

public class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squaredNums = new long[n];
        
        // 1. Convert all numbers to their squares using long to prevent overflow
        for (int i = 0; i < n; i++) {
            squaredNums[i] = (long) nums[i] * nums[i];
        }
        
        // 2. Sort the squared array in ascending order
        Arrays.sort(squaredNums);
        
        long ans = 0;
        int m = n / 2;
        
        // 3. Subtract the smaller half (first m elements)
        for (int i = 0; i < m; i++) {
            ans -= squaredNums[i];
        }
        
        // 4. Add the larger half (remaining elements)
        for (int i = m; i < n; i++) {
            ans += squaredNums[i];
        }
        
        return ans;
    }
}
