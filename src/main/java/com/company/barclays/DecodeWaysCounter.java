package com.company.barclays;

public class DecodeWaysCounter {
    public static int countDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;

        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1; // Base case: empty string
        dp[1] = 1; // First character is valid (not '0')

        for (int i = 2; i <= n; i++) {
            char oneDigit = s.charAt(i - 1);
            String twoDigit = s.substring(i - 2, i);

            // Check single digit decode (1–9)
            if (oneDigit != '0') {
                dp[i] += dp[i - 1];
            }

            // Check two-digit decode (10–26)
            int val = Integer.parseInt(twoDigit);
            if (val >= 10 && val <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // Example input
        String input = "226";

        // Count decoding ways
        int result = countDecodings(input);

        // Output result
        System.out.println("Number of ways to decode \"" + input + "\": " + result);
    }

}
