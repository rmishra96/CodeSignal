package BasicThreading;

public class SharedResource {

    boolean isItemPresnt = false;

    public  synchronized void addItem(){
        isItemPresnt = true;
        System.out.println("Producer thread calling the notifying method");
        notifyAll();
    }

    public synchronized void consumeItem()  {
        System.out.println("Consumer thread inside the conumer method");
        while (!isItemPresnt )
            System.out.println("Consumer thread is waiting ");
        try {
            wait(); // release all the monitor lock
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        isItemPresnt = false;
    }

}
