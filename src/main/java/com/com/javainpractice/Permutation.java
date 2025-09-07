package com.com.javainpractice;

public class Permutation {
    public static void main(String[] args) {
        String str1 = "abc";
        int n = str1.length();
        Permutation p = new Permutation();
        p.permutate(str1,0,n-1);
    }

    private void permutate(String str1, int l, int r) {
        if (l == r) {
            System.out.println(str1);
        }else{
            for (int i = l; i <= r; i++) {
                str1 = swaap(str1,l,i);
                permutate(str1,l+1,r);
                str1 =swaap(str1,l,i);
            }
        }
    }

    private String swaap(String str1, int l, int i)
    {
        char temp;
        char[] charr = str1.toCharArray();
        temp = charr[l];
        charr[l] = charr[i];
        charr[i] = temp;
        return String.valueOf(charr);
    }
}
