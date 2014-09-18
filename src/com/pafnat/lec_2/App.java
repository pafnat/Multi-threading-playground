package com.pafnat.lec_2;

import java.util.Scanner;

/**
 * Volatile Keyword, “… the volatile modifier guarantees that any thread that
 * reads a field will see the most recently written value.” - Josh Bloch
 */


/* Basic thread synchronization.

Volatile is used to prevent Threads caching variable when they are not changed from within that Thread.
When you want to change a variable from another Thread you have to use volatile or some kind of Thread synchronization

*/

class Processor extends Thread {

    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class App {
    public static void main(String[] args) {
        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Press return to stop ...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc1.shutdown();
    }
}
