package com.arraysInterview;

import java.util.Arrays;

public class SequenceNumbers {

    public static void main(String[] args) {
        String[] arr = {"1", "2","3","5","9", "a", "6", "7" ,"8" ,"4" ,"@", "-5", "-7" ,"-3", "-2" ,"-1"};
        int[] array     = Arrays.stream(arr).filter( n -> {
            boolean out = true;
            try {
                 Integer.valueOf(n);
            }catch (Exception ex){
                out = false;
            }
            return out;
        }).mapToInt( el -> Integer.valueOf(el)).toArray();
        for(int i=0; i < array.length ; i++){
            StringBuffer  sf = new StringBuffer("");
            for(int j = i + 1; j < array.length ; j++){
                int next = array[i]+1;
                if(next == array[j]){
                    if(sf.toString().isEmpty())
                        sf.append(array[i] + "::" + array[j]);
                    else
                        sf.append("::" +array[j]);
                    i = j;
                }else {
                    break;
                }

            }
            if(!sf.toString().isEmpty())
                System.out.println(sf.toString());
        }
    }
}
