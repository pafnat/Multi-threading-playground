package com.pafnat.lec_9;

/**
 * How to implement the producer-consumer pattern using "low level" techniques;
 * namely, wait, notify and synchronized. This isn't the best way to implement a
 * producer-consumer pattern in Java (see tutorial 7 use of BlockingQueue for
 * the best way); but this tutorial will help you to understand how to use wait
 * and notify.
 */

/* a worked example using low-level synchronization */

public class App {
    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
