package arraysInterview;

public class MaxElement {

    public static void main(String[] args) {
        int[] arr = {12, 45, 7, 89, 23, 56};
        int max = arr[0];
        for(int i=1;  i < arr.length; i++)
            if(arr[i] < arr[i-1])
                max = arr[i];
        System.out.println(max);


        int[] arr1 = {2, 5, 2, 3, 2, 7, 8, 2};
        int count =0;
        for(int a : arr1)
            if(a == 2)
                count++;
        System.out.println(count);
    }
}
