package org.example;

public class Main {
    public static void main(String[] args)  {

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

        t2.setPriority(Thread.MAX_PRIORITY);//Max priority-10
        t1.setPriority(Thread.MIN_PRIORITY);//Min priority-1
        /*t1.setPriority(Thread.NORM_PRIORITY);//Normal priority-5*/
        /*t2.setPriority(3);//1-10*/

        t1.start();
        System.out.println(t1.getPriority());
        /*System.out.println(Thread.currentThread().getPriority());*/
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t2.start();
        System.out.println(t2.getPriority());
        /*System.out.println(Thread.currentThread().getPriority());*/

        /*t2.join();*/
        t1.setName("Thread-01");//setting name of thread
        t2.setName("Thread-02");//setting name of thread
        /*System.out.println(t1.getName());
        System.out.println(t2.getName());*/
    }
}