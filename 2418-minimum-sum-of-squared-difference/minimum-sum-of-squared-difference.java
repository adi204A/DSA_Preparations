class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        // Step 1: Count frequencies of absolute differences
        // The maximum possible difference is 100,000 based on constraints
        int maxDiff = 100000;
        long[] bucket = new long[maxDiff + 1];
        long totalDiffSum = 0;

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            bucket[diff]++;
            totalDiffSum += diff;
        }

        // If total operations 'k' can wipe out all differences, return 0
        if (totalDiffSum <= k) {
            return 0;
        }

        // Step 2: Greedy reduction from the largest difference downwards
        for (int d = maxDiff; d > 0; d--) {
            if (bucket[d] == 0) continue;

            // Determine how many elements we can reduce from difference 'd' to 'd - 1'
            long countToReduce = Math.min(bucket[d], k);
            
            bucket[d] -= countToReduce;
            bucket[d - 1] += countToReduce;
            k -= countToReduce;

            if (k == 0) break; // Out of operations
        }

        // Step 3: Calculate the final sum of squared differences
        long minSquaredSum = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (bucket[d] > 0) {
                minSquaredSum += bucket[d] * (long) d * d;
            }
        }

        return minSquaredSum;
    }
}
