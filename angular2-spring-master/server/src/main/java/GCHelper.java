import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tarun.walia on 12/5/2016.
 */

public class GCHelper {

    static ConcurrentHashMap<Integer,Person>  map = new ConcurrentHashMap<>();

    public static void main(String[] args) {


        Thread t1 = new Thread(new Runnable() {

            Person p;

            @Override
            public void run() {
                int i=0;
                while (true) {
                    p = new Person();
                    map.put(i++,p);
                    if(i % 1000 == 0){
                        try {
                          //  Thread.currentThread().sleep(1000L);
                            System.out.print("*");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t1.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    printGCStats();
                    try {
                        Thread.sleep(30000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void printGCStats() {
        long totalGarbageCollections = 0;
        long garbageCollectionTime = 0;

        for (GarbageCollectorMXBean gc :
                ManagementFactory.getGarbageCollectorMXBeans()) {


            long count = gc.getCollectionCount();



            if (count >= 0) {
                totalGarbageCollections += count;
            }

            long time = gc.getCollectionTime();
            System.out.println(gc.getName() +  " : "+ count + " :  " + time);

            if (time >= 0) {
                garbageCollectionTime += time;
            }
        }

        System.out.println("Total Garbage Collections: "
                + totalGarbageCollections);
        System.out.println("Total Garbage Collection Time (ms): "
                + garbageCollectionTime);
    }


}

class Person {

    byte[] bytes;

    Person() {
        bytes = new byte[1024];
    }


}

