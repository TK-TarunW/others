import static java.lang.Thread.getDefaultUncaughtExceptionHandler;

/**
 * Created by tarun.walia on 12/4/2016.
 */
public class Thread2 {

    public static void main(String[] args) throws InterruptedException {




        Thread t1 = new Thread(new EvenRunnable(0));
        Thread t2 = new Thread(new EvenRunnable(1));
        Thread t3 = new Thread(new EvenRunnable(1));


        t1.start();
        t2.start();
        t3.start();





    }
}

class EvenRunnable implements Runnable {

    static Object lock = new Object();
    private static int start;

    EvenRunnable(int start) {
        this.start = start;
    }


    @Override
    public void run() {

            synchronized (lock) {
                while (start <= 30) {
                try {

                    lock.notifyAll();
                    System.out.println(Thread.currentThread().getName() + " ** " + start);
                    start += 1;
                    Thread.sleep(1000L);
                   lock.wait();

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

            }
        }
    }
}


