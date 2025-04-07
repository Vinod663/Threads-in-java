package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Calculator calculator = new Calculator();
        Thread t1 = new Thread(()->{
            System.out.println("Thread 1");
            for (int i = 0; i < 1000; i++) {
                calculator.increment();

            }

        });

        Thread t2 = new Thread(()->{
            System.out.println("Thread 2");
            for (int i = 0; i < 1000; i++) {
                calculator.increment();

            }

        });

        t1.start();
        /*try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        t2.start();
        t1.join();//wait for t1 to finish before executing main thread
        t2.join();//wait for t2 to finish before executing main thread
        System.out.println("i:"+calculator.i);

    }
}

class Calculator{
    int i=0;
    public  synchronized void increment(){// synchronized- only one thread can access this method at a time
        i++;
        /*System.out.println("i:"+i);*/
    }
}