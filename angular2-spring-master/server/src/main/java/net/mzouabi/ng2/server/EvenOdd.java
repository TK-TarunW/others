package net.mzouabi.ng2.server;

/**
 * Created by tarun.walia on 12/4/2016.
 */
public class EvenOdd {

    public static void main(String[] args) {

        new Thread(new EvenRunnable(1)).start();
        new Thread(new EvenRunnable(2)).start();


    }
}

class EvenRunnable implements Runnable {

    static Object lock = new Object();
    volatile boolean printOdd = false;
    private int start;

    EvenRunnable(int start) {
        this.start = start;
    }


    @Override
    public void run() {
        while (start < 20) {
        synchronized (lock) {
                try {
                    lock.notify();
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + " - " + start);
                    start += 2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
