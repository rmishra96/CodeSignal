package com.stringInterview;

public class AddString {
    public static String addStrings(String num1, String num2){
        StringBuilder res = new StringBuilder();
        int carry = 0;

        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;

        while(p1 >= 0 || p2 >=0){
            int x1 = 0,x2=0;
            if(p1 >=0)
                x1 = num1.charAt(p1) - '0';
            if(p2 >=0)
                x2 = num2.charAt(p2) - '0';

            int value = (x1+x2+carry) % 10;
            carry = (x1+x2+carry) / 10;

            res.append(value);
            p1--;
            p2--;
        }

        if(carry!= 0)
            res.append(carry);
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String[] num1 = {"1545", "149", "0", "149", "1465"};
        String[] num2 = {"167", "0", "0", "101", "903"};
        for(int i=0; i < num1.length; i++){
            System.out.println("Sum of " + num1[i] + " and " + num2[i] + " is: " + addStrings(num1[i], num2[i]));
        }
    }
}
