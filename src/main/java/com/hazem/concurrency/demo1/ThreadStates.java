package com.hazem.concurrency.demo1;

public class ThreadStates {

  public static void main(String[] args) {

    Thread worker = new Thread(() -> {
      System.out.println("worker has started");

      try {
        Thread.sleep(2000);
        System.out.println("worker has finished");
      } catch (InterruptedException e) {
        System.out.println("worker was interrupted");
        throw new RuntimeException(e);
      }
    });

    System.out.println("Initial state: " + worker.getState()); // NEW

    worker.start();
    System.out.println("After start: " + worker.getState()); // RUNNABLE

    try {
      Thread.sleep(100);
      System.out.println("while running: " + worker.getState()); // TIMED_WAITING
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    try {
      worker.join();
      System.out.println("Final State: " + worker.getState()); // TERMINATED
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
