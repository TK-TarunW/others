/**
 * Created by tarun.walia on 12/4/2016.
 */
public class ThreadSequencing {

    public static void main(String[] args) {

        MyRunnable r = new MyRunnable(1);
        new Thread(r).start();


        r = new MyRunnable(6);
        new Thread(r).start();
        r = new MyRunnable(2);
        new Thread(r).start();
        r = new MyRunnable(4);
        new Thread(r).start();
        r = new MyRunnable(3);
        new Thread(r).start();
        r = new MyRunnable(5);
        new Thread(r).start();
        r = new MyRunnable(7);
        new Thread(r).start();
    }

}

class MyRunnable implements Runnable {

    MyRunnable(int current){
        this.current = current;
    }
    static final Object lock = new Object();

    static int toPrint = 1;

    final int  current;
    @Override
    public void run() {
        synchronized (lock){
            while(this.current != toPrint){
                try {
                    //System.out.println("**Before lock***" + current + " ***** " +toPrint);
                    lock.wait();
                   //System.out.println("**After lock***" + current + " ***** " +toPrint);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(current);
            toPrint++;
            lock.notifyAll();



        }
    }
}
