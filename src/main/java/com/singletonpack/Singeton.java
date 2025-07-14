package singletonpack;

public class Singeton {
    private static volatile Singeton instance;

    private Singeton() {
    }

    public static Singeton getInstance() {
        if (instance == null) {
            synchronized (Singeton.class) {
                if (instance == null) {
                    instance = new Singeton();
                }
            }
        }
    return  instance;
    }
}
