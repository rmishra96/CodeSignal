package com.streampracticejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SeqParallelExample {


//    public static List<Integer> sequentialExecution(){
//        System.out.println("Sequentials Executions");
//        List<Integer>  nums = Arrays.asList(1,2,3,4,5,6,7);
//        return nums.stream().map(this :: transform)
//                .collect(Collectors.toList());
//    }

//    public static List<Integer> parallelExecution(){
//        System.out.println("Parallel Executions");
//        List<Integer>  nums = Arrays.asList(1,2,3,4,5,6,7);
//        return nums.parallelStream().map(this :: transform).collect(Collectors.toList());
//    }

    public Integer transform(Integer integer) {

        System.out.print(integer + " :: "+ Thread.currentThread().getName());
        return  integer * 10 ;
    }

    public void print() {
        String name = Thread.currentThread().getName();
        System.out.println("Sequentials Executions");
        List<Integer>  nums = Arrays.asList(1,2,3,4,5,6,7);
        List<Integer> num1s = nums.stream().map(this :: transform)
                .collect(Collectors.toList());

        System.out.println(num1s);

        List<Integer> num2s = nums.parallelStream().map(this :: transform).collect(Collectors.toList());
        System.out.println(num2s);

    }

    public static void main(String[] args) {
        SeqParallelExample seqParallelExample = new SeqParallelExample();
        seqParallelExample.print();
    }




}
