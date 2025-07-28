package com.company.zemsos;

/*Input:
A string with repeated characters, e.g.
sssmmmnnnnAAAAmmmmmaaaaAAAA
Output:
A compressed format showing each character followed by its count, but grouped by consecutive runs, like:
s3 m3 n3 A3 m5 a4 A5


*/

public class Solution {

    public static void main(String[] args) {
        String input = "sssmmmnnnnAAAAmmmmmaaaaAAAA";
        System.out.println(StringCompressor(input));
    }

    private static String StringCompressor(String input) {
        StringBuilder builder = new StringBuilder();
        int count =1;
        for(int i =1; i < input.length() ; i++){
            if(input.charAt(i) == input.charAt(i-1)){
                count++;
            }else {
                builder.append(input.charAt(i-1)).append(count).append("");
                count = 1;
            }
        }
        builder.append(input.charAt(input.length()-1)).append(count).append("");
        return builder.toString();
    }
}
