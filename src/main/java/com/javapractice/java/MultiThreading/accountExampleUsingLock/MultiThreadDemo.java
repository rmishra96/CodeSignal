package com.javapractice.java.MultiThreading.accountExampleUsingLock;

public class MultiThreadDemo {
    public static void main(String[] args) {

        PrintChar task1 = new PrintChar('a');
        PrintChar task2 = new PrintChar('b');
        PrintChar task3 = new PrintChar('f');
         PrintNum task4 = new PrintNum(1);
        PrintNum task5 = new PrintNum(2);

        Thread t1 = new Thread(task1);
        Thread t3 = new Thread(task3);
        Thread t2 = new Thread(task2);
        Thread t4 = new Thread(task4);
        Thread t5 = new Thread(task5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }

    static class PrintChar implements Runnable{

        private char myChar;

        public PrintChar(char myChar) {
            this.myChar = myChar;
        }

        @Override
        public void run() {
            for (int i =0; i < 100 ; i++){
                System.out.println(myChar);
            }
        }
    }

    static class PrintNum implements Runnable{

        private int myNum;

        public PrintNum(int myNum) {
            this.myNum = myNum;
        }

        @Override
        public void run() {
            for(int i= 0; i < 100 ; i++){
                System.out.println(myNum);
            }
        }
    }
}
