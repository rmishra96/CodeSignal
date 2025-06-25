package com.Stacks;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElements {
    public static void main(String[] args) {
        int[] arr = {5,9,21,11,17,3,30,12,25,50};
        int[] ans = nextGreaterElement(arr);
        int[] ans1 = nextGreaterElement2(arr);
        int[] ans3 = nextGreaterElement3(arr);
        for(int a : ans){
            System.out.print(a + " ");
        }
        System.out.println("*************************");

        for(int a : ans1){
            System.out.print(a + " ");
        }
        System.out.println("*************************");

        for(int a : ans3){
            System.out.print(a + " ");
        }
    }

    private static int[] nextGreaterElement3(int[] arr) {
        int n  = arr.length;
        Stack<Integer> st = new Stack<>();
        int[]  ans = new int[n];
        Arrays.fill(ans, -1);
        st.push(0);
        for(int i =1; i < 2 * arr.length; i++){
            while(st.size() >0 && arr[st.peek()] < arr[i % n]){
                ans[st.pop()] = arr[i % n];
            }
            st.push(i % n);
        }
        return ans;
    }

    private static int[] nextGreaterElement2(int[] arr) {
        Stack<Integer>  st = new Stack<>();
        int[] ans = new int[arr.length];
        st.push(0);
        for(int i =1 ; i < arr.length ; i++){
            // pop all smaller elements
            // for all these similar elements , nge is current element
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                ans[st.peek()] = arr[i];
                st.pop();
            }
            st.push(i);
        }

        while(st.size() > 0 ){
            ans[st.pop()] = -1;

        }
        return ans;
    }

    private static int[] nextGreaterElement(int[] arr) {

        Stack<Integer> st = new Stack<>();
        int[] ans = new int[arr.length];
        for(int i = arr.length -1 ; i >= 0; i--){
            while(st.size() >0  && st.peek() < arr[i]){
                st.pop();
            }
            if(st.size() == 0){
                ans[i] = -1;
            }else {
                ans[i] = st.peek();
            }
            st.push(arr[i]);
        }

        return ans;
    }
}
