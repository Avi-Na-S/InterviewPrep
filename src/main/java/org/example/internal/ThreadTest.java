package org.example.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class ThreadTest {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    //getFutureThread();
    //getCompleteFutureThread();
    //getExpHanpler();
    //pubsubmodel();

  }

  private static void pubsubmodel() throws InterruptedException {
    BlockingQueue<String> queue =
        new LinkedBlockingQueue<>();

    // Producer
    Thread producer = new Thread(() -> {

      try {

        for (int i = 1; i <= 5; i++) {

          String event = "Shipment-" + i;

          queue.put(event);

          System.out.println(
              "Produced: " + event);

          Thread.sleep(500);
        }

      } catch (InterruptedException e) {

        Thread.currentThread().interrupt();
      }
    });

    // Consumer
    Thread consumer = new Thread(() -> {

      try {

        while (true) {

          String event = queue.take();

          System.out.println(
              "Consumed: " + event);

          Thread.sleep(2000);
        }

      } catch (InterruptedException e) {

        Thread.currentThread().interrupt();
      }
    });

    producer.start();
    consumer.start();

    Thread.sleep(12000);

    consumer.interrupt();
    producer.interrupt();

    System.out.println("Done");

  }

  private static void getExpHanpler() {
    System.out.println("main");
    try (ExecutorService executors = Executors.newFixedThreadPool(2)) {

      IntStream.range(0, 6).forEach(i -> {

        CompletableFuture.supplyAsync(() -> {
          System.out.println("Thread i am in " + Thread.currentThread().getName() + " " + i);
          try {
            if (i == 1) {
              Thread.sleep(7000);
            } else if (i == 3) {
              throw new RuntimeException("failed service");
            } else {
              Thread.sleep(2000);
            }
          } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
          }
          return "Done Thread i am in " + Thread.currentThread().getName() + " " + i;
        }, executors).thenAccept(System.out::println).exceptionally(ex -> {
          System.out.println(ex.getMessage());
          return null;
        });
      });

    }
  }

  private static void getCompleteFutureThread() {
    try (ExecutorService executor =
        Executors.newFixedThreadPool(2)) {

      System.out.println(
          "Main thread: "
              + Thread.currentThread().getName());

      List<CompletableFuture<String>> futures =
          new ArrayList<>();

      IntStream.range(0, 4).forEach(i -> {

        CompletableFuture<String> future =
            CompletableFuture.supplyAsync(() -> {

              System.out.println(
                  "Worker thread: "
                      + Thread.currentThread().getName()
                      + " " + i);

              try {

                if (i == 1) {
                  Thread.sleep(20000);
                } else {
                  Thread.sleep(3000);
                }

              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
              }

              System.out.println(
                  "---Work done thread: "
                      + Thread.currentThread().getName()
                      + " " + i);

              return "Event processed successfully " + i;

            }, executor);

        // Execute this as soon as THIS task completes
        future.thenAccept(result -> {

          System.out.println(
              "Result received: "
                  + result
                  + " on thread: "
                  + Thread.currentThread().getName());

        });

        futures.add(future);
      });

      System.out.println(
          "Main thread continues doing other work...");

      System.out.println(
          "Main thread is NOT waiting for each future...");

      // Wait only so the demo doesn't terminate early
//      CompletableFuture.allOf(
//          futures.toArray(new CompletableFuture[0])
//      ).join();

      System.out.println("End");
    }
  }

  private static void getFutureThread() {

    // The future will get in the submition order, even task 0,2,3 is completed. it will result the 0 then wait for 1
    // to complted and then only 2, 3 will be fetched even though it was completed early
    // bulkhead can be achived here. FIFO.
    // A fixed thread pool can be used to implement a thread-pool-based Bulkhead.
    try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

      System.out.println("Main thread: " + Thread.currentThread().getName());

      List<Future<String>> futures = new ArrayList<>();

      IntStream.range(0, 4).forEach(
          i -> {
            futures.add(executor.submit(() -> {

              System.out.println("Worker thread: " +
                  Thread.currentThread().getName() + " " + i);

              if (i == 1) {
                Thread.sleep(20000);
              } else {
                Thread.sleep(3000);
              }
              System.out.println("---Worked done thread: " +
                  Thread.currentThread().getName() + " " + i);
              return "Event processed successfully" + " " + i;
            }));
          }
      );

      System.out.println("Main thread continues doing other work...");

      // Main thread can do something else

      System.out.println("Now I need the result...");

      for (Future<String> future : futures) {
        // This blocks until the async task completes
        String result = future.get();

        System.out.println("Result: " + result);
      }
      System.out.println("End");
    } catch (ExecutionException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
