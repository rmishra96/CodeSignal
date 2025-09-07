package com.com.reflection;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

public class ArraysObjectCreationReflection {
    public static void main(String[] args) {
        int[] array = (int[]) Array.newInstance(int.class,10);

        for(int i=0;i<array.length;i++)
            array[i]=i;

        for(int i=0;i<array.length;i++)
            System.out.println(array[i]);

// object that can't be created using reflection
        Class<TimeUnit> c = TimeUnit.class;
        TimeUnit[] consts = c.getEnumConstants();
        for (int i = 0; i < consts.length; i++)
            System.out.println(consts[i].toString());
    }
}
