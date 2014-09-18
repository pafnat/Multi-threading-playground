package com.pafnat.lec_12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphores are mainly used to limit the number of simultaneous threads that
 * can access a resources, but you can also use them to implement deadlock
 * recovery systems since a semaphore with one permit is basically a lock that
 * you can unlock from other threads.
 *
 * Source:
 * http://stackoverflow.com/questions/771347/what-is-mutex-and-semaphore-in-java-what-is-the-main-difference
 *
 * Mutex is basically mutual exclusion. Only one thread can acquire the resource
 * at once. When one thread acquires the resource, no other thread is allowed to
 * acquire the resource until the thread owning the resource releases. All
 * threads waiting for acquiring resource would be blocked.
 *
 * Semaphore is used to control the number of threads executing. There will be
 * fixed set of resources. The resource count will gets decremented every time
 * when a thread owns the same. When the semaphore count reaches 0 then no other
 * threads are allowed to acquire the resource. The threads get blocked till
 * other threads owning resource releases.
 *
 * In short, the main difference is how many threads are allowed to acquire the
 * resource at once ?
 *
 * Mutex --its ONE. Semaphore -- its DEFINED_COUNT, ( as many as semaphore
 * count)
 */

/* Semaphores */

public class App {
    public static void main(String[] args) throws InterruptedException {

/*
        Semaphore sem = new Semaphore(1);


        sem.acquire();
        sem.release();

        System.out.println("Available permits: " + sem.availablePermits());
*/



        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i=0; i<200; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
