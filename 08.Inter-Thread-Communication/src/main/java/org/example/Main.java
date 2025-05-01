package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        A a = new A();
        new Producer(a);
        new Consumer(a);
    }
}

class A{
    int num;
    boolean flag=false;

    public synchronized void put(int num){

        while(flag){
            try {
                wait();//wait for the other thread to finish
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println("PUT :"+num);
        this.num=num;
        flag=true;
        notify();//notify the waiting thread to wake up and its come to runnable state from waiting state(waiting state eken runnable state ekata enawa)
    }

    public synchronized void get(){

        while(!flag){
            try {
                wait();//wait for the other thread to finish
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("GET:"+num);
        flag=false;//flag eka false karala notify karanawa
        notify();//notify the waiting thread to wake up and runnable state from waiting state(waiting state eken runnable state ekata enawa)

    }
}

class Producer implements Runnable{
    A a;

    public Producer(A a){
        this.a=a;
        Thread t1 = new Thread(this, "Producer");
        t1.start();
    }
    @Override
    public void run() {
        int i=0;
        while(true){
            a.put(i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class Consumer implements Runnable{
    A a;

    public Consumer(A a) {
        this.a = a;
        Thread t2 = new Thread(this, "Consumer");
        t2.start();
    }

    @Override
    public void run() {
        int i=0;
        while(true){
            a.get();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


