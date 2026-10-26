class Solution {
    int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    public long dfs(int i,int j, int[][] grid, boolean[][] vis) {
        int n=grid.length,m=grid[0].length;
        long sum=grid[i][j];
        vis[i][j] = true;
        for(int k=0;k<4;k++) {
            int x = i + dir[k][0], y = j + dir[k][1];
            if(x>=0 && y>=0 && x<n && y<m && grid[x][y] > 0 && !vis[x][y]) {
                sum += dfs(x,y,grid,vis);
            }
        }
        return sum;
    }
    public int countIslands(int[][] grid, int k) {
        int n=grid.length,m=grid[0].length,ans=0;
        boolean[][] vis = new boolean[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(!vis[i][j] && grid[i][j] > 0) {
                    long sum = dfs(i,j,grid,vis);
                    if(sum%k==0)
                        ans++;
                }
            }
        }
        return ans;
    }
}