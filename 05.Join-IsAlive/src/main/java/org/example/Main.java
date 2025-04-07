package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread 2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        t1.start();
        System.out.println(t1.isAlive());//true-thread is alive
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t2.start();
        System.out.println(t2.isAlive());//true-thread is alive

        t1.join();//wait for t1 to finish before executing main thread
        System.out.println(t1.isAlive());//false-thread is dead
        t2.join();//wait for t2 to finish before executing main thread
        System.out.println(t2.isAlive());//false-thread is dead
        System.out.println("Bye");//executed from main thread

    }
}