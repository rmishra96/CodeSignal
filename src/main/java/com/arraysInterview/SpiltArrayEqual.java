    package com.arraysInterview;

    import java.util.Arrays;

    public class SpiltArrayEqual {
        public static void main(String[] args) {
            int[] original = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9,11};

            int givenIndex = original.length / 2 ;

            int[] spiltArray1 = Arrays.copyOfRange(original,0, givenIndex);
            int[] spiltArray2 = Arrays.copyOfRange(original, givenIndex, original.length);
            System.out.println(Arrays.toString(spiltArray1));
            System.out.println(Arrays.toString(spiltArray2));
        }
    }
