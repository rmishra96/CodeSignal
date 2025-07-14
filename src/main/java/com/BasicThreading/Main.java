package BasicThreading;

public class Main {
    public static void main(String[] args) {

        SharedResource sharedResourceObj = new SharedResource();
        Thread producerThread = new Thread(() -> {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sharedResourceObj.addItem();
        });


        Thread consumeThread = new Thread(() -> {
            sharedResourceObj.consumeItem();
        });

        producerThread.start();
        consumeThread.start();
    }
}
