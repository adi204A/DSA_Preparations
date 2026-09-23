import java.util.Arrays;

class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // The middle subarray must sum exactly up to target
        int target = totalSum - x;
        
        // If target is 0, we must remove all elements
        if (target == 0) {
            return nums.length;
        }
        // If target is negative, it's impossible to reduce x to 0 
        // because all elements are positive integers.
        if (target < 0) {
            return -1;
        }
        
        int left = 0;
        int currentSum = 0;
        int maxLen = -1;
        int n = nums.length;
        
        // Sliding window to find the longest subarray summing to 'target'
        for (int right = 0; right < n; right++) {
            currentSum += nums[right];
            
            // Shrink the window from the left if the sum exceeds the target
            while (left <= right && currentSum > target) {
                currentSum -= nums[left];
                left++;
            }
            
            // Check if we hit the exact target sum
            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        
        // If maxLen is still -1, no valid middle subarray was found
        return maxLen == -1 ? -1 : n - maxLen;
    }
}
