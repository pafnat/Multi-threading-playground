package com.pafnat.lec_3;

/**
 * synchronized ("only let one thread in here at a time".) and join ("wait until
 * thread on which join has called finished") keyword.
 *
 */


/* Synchronized keyword
you might think volatile will help, but it's not yet fixed. The basic problem is caching, interleaving.
to fix it we need synchronized keyword
*/



public class App {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public static void main(String[] args) {
        App app = new App();
        app.doWork();
    }

    private void doWork() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10_000; i++) {
                    //count++;

                    // 3-steps op
                    //count = count + 1;

                    increment();

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10_000; i++) {
                    //count++;

                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Count is: " + count);

    }
}
