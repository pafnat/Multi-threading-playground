package com.pafnat.lec_13;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Callable and Future in Java to get results from your threads and to allow
 * your threads to throw exceptions. Plus, Future allows you to control your
 * threads, checking to see if they’re running or not, waiting for results and
 * even interrupting them or descheduling them.
 *
 * Runnable is default abstraction for creating a task in Java. It has a single
 * method run() that accepts no arguments and returns no value, nor it can throw
 * any checked exception. To overcome these limitations, Java 5 introduced a new
 * task abstraction through Callable interface.
 */

/* Callable and future */

public class App {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
/*
        executor.submit(new Runnable() {
            @Override
            public void run() {

                Random random = new Random();
                int duration = random.nextInt(4000);

                System.out.println("Starting... ");

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finished.");
            }
        });
*/

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration > 2000) {
                    throw new IOException("Sleeping for too long.");
                }

                System.out.println("Starting... ");

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finished.");

                return duration;
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        try {
            System.out.println("Result is " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //System.out.println(e.getMessage());
            IOException ex = (IOException) e.getCause();
            System.out.println(ex.getMessage());
        }

    }
}
