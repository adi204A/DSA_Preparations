class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] right = new int[n];
        
        // Step 1: Precompute the minimum suffix value from right to left
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], nums[i]);
        }
        
        // Step 2: Track the maximum prefix value from left to right and check stability
        int left = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            left = Math.max(left, nums[i]);
            
            // Instability score: max(prefix) - min(suffix)
            if (left - right[i] <= k) {
                return i; // Found the smallest stable index
            }
        }
        
        return -1; // No stable index found
    }
}
