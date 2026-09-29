class Solution {
    private Boolean[][][] memo;
    private int m;
    private int n;

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;

        // Optimization: Path length must be even to be valid
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // The maximum open bracket balance cannot exceed the path length
        int maxBalance = (m + n - 1) / 2;
        this.memo = new Boolean[m][n][maxBalance + 1];

        return dfs(0, 0, 0, grid);
    }

    private boolean dfs(int i, int j, int k, char[][] grid) {
        // Update balance based on current cell
        if (grid[i][j] == '(') {
            k++;
        } else {
            k--;
        }

        // Invalid state checks
        if (k < 0 || k >= memo[0][0].length) {
            return false;
        }

        // Pruning: if remaining steps are less than the required steps to balance out
        if (k > (m - 1 - i) + (n - 1 - j)) {
            return false;
        }

        // Base case: Reached the bottom-right cell
        if (i == m - 1 && j == n - 1) {
            return k == 0;
        }

        // Return cached result if already calculated
        if (memo[i][j][k] != null) {
            return memo[i][j][k];
        }

        boolean foundPath = false;

        // Move Right
        if (j + 1 < n) {
            foundPath = dfs(i, j + 1, k, grid);
        }

        // Move Down (only if Right path didn't already succeed)
        if (!foundPath && i + 1 < m) {
            foundPath = dfs(i + 1, j, k, grid);
        }

        // Cache and return the result
        return memo[i][j][k] = foundPath;
    }
}
