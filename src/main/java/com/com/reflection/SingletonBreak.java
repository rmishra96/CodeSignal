package com.com.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonBreak {
    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c = SingletonClass.class;

        Constructor<?> cs = c.getDeclaredConstructors()[0];
        cs.setAccessible(true);

        SingletonClass[] instances = new SingletonClass[10];
        for(int i = 0; i < instances.length; i++)
            instances[i] = (SingletonClass) cs.newInstance();
            System.out.println(instances.getClass().getName());
    }
}

class SingletonClass {
    private static SingletonClass instance = new SingletonClass();

    private SingletonClass() {}

    public static SingletonClass getInstance() {return instance;}
}