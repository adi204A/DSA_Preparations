class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        // Frequency of characters in s
        int[] total = new int[26];

        for (char c : s.toCharArray()) {
            total[c - 'a']++;
        }

        // prefix[i] = frequency of target[0 ... i-1]
        int[][] prefix = new int[n + 1][26];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                prefix[i + 1][j] = prefix[i][j];
            }
            prefix[i + 1][target.charAt(i) - 'a']++;
        }

        // Try changing position from right to left
        for (int i = n - 1; i >= 0; i--) {

            int[] remaining = new int[26];

            // Check whether target[0...i-1] can be made from s
            boolean possible = true;

            for (int c = 0; c < 26; c++) {
                remaining[c] = total[c] - prefix[i][c];

                if (remaining[c] < 0) {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            int current = target.charAt(i) - 'a';

            // Find smallest character greater than target[i]
            for (int c = current + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // Same prefix
                    ans.append(target, 0, i);

                    // Smallest greater character
                    ans.append((char) ('a' + c));

                    remaining[c]--;

                    // Remaining characters in sorted order
                    for (int x = 0; x < 26; x++) {
                        while (remaining[x] > 0) {
                            ans.append((char) ('a' + x));
                            remaining[x]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}