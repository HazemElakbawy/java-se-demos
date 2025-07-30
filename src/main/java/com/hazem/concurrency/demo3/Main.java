package com.hazem.concurrency.demo3;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    
    UnsafeCounter counter = new UnsafeCounter();

    Thread[] threads = new Thread[5];

    for (int i = 0; i < 5; i++) {

      final int id = i;
      threads[i] = new Thread(() -> {

        for (int j = 0; j < 1000; j++) {
          counter.increment();

          if (j % 250 == 0) {
            System.out.println("Thread-" + id + " has completed " + (j + 1) + " increments");
          }
        }
      }, "Increment-Thread-" + i);
    }

    System.out.println("Starting the 5 threads");

    for (Thread t : threads)
      t.start();

    for (Thread t : threads)
      t.join();

    System.out.println("All threads are completed");
    System.out.println(counter.getCounter());
  }
}
