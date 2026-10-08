package org.example.gpt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

class Counter{

  int counter;

   void count(){
    counter++;
  }

}

public class ThreadTest {

  public static void main(String[] args) throws InterruptedException {
raceCondition();
    ConcurrentHashMap<String,String> data = new ConcurrentHashMap<>();
    BlockingQueue<Integer> queue =
        new LinkedBlockingQueue<>();

    queue.put(10);

    queue.take();

  }

  private static void raceCondition() throws InterruptedException {
    Counter c = new Counter();

    Thread t1 = new Thread(()->{
      for (int i = 1;i<=1000;i++){
        c.count();
      }
    });

    Thread t2 = new Thread(()->{
      for (int i = 1;i<=1000;i++){
        c.count();
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println(c.counter);
  }

}
