import java.util.Arrays;

public class Solution {
    
    // Segment Tree Node
    long kValue;
    
    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            this.cnt = new int[k];
            this.prod = 1;
        }
    }

    private Node[] tree;
    private int n;
    private int K;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.K = k;
        this.tree = new Node[4 * n];
        
        // Build the segment tree initially
        build(0, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int targetX = queries[i][3];

            // 1. Update the value at the specified index
            update(0, 0, n - 1, index, value);

            // 2. Query the range from 'start' to the end of the array (n - 1)
            if (start < n) {
                Node queryResult = query(0, 0, n - 1, start, n - 1);
                result[i] = queryResult.cnt[targetX];
            } else {
                result[i] = (targetX == 0) ? 1 : 0; // Handle edge case if range is empty
            }
        }

        return result;
    }

    private void build(int node, int start, int end, int[] nums) {
        tree[node] = new Node(K);
        if (start == end) {
            int rem = nums[start] % K;
            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node + 1, start, mid, nums);
        build(2 * node + 2, mid + 1, end, nums);
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            int rem = value % K;
            Arrays.fill(tree[node].cnt, 0);
            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        if (index <= mid) {
            update(2 * node + 1, start, mid, index, value);
        } else {
            update(2 * node + 2, mid + 1, end, index, value);
        }
        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        if (r <= mid) {
            return query(2 * node + 1, start, mid, l, r);
        }
        if (l > mid) {
            return query(2 * node + 2, mid + 1, end, l, r);
        }
        Node leftNode = query(2 * node + 1, start, mid, l, r);
        Node rightNode = query(2 * node + 2, mid + 1, end, l, r);
        return merge(leftNode, rightNode);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node(K);
        res.prod = (left.prod * right.prod) % K;
        
        // Suffix counts from left child remain valid
        for (int i = 0; i < K; i++) {
            res.cnt[i] += left.cnt[i];
        }
        
        // Suffix counts from right child get multiplied by total product of left child
        for (int i = 0; i < K; i++) {
            int nextRem = (left.prod * i) % K;
            res.cnt[nextRem] += right.cnt[i];
        }
        
        return res;
    }
}
