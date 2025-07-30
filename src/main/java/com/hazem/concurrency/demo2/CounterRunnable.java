package com.hazem.concurrency.demo2;

public class CounterRunnable implements Runnable {

  private String threadName;
  private int maxCount;

  public CounterRunnable(int maxCount, String threadName) {
    this.maxCount = maxCount;
    this.threadName = threadName;
  }

  @Override
  public void run() {
    for (int i = 1; i <= maxCount; i++) {
      System.out.println(threadName + " count: " + i);

      try {
        Thread.sleep(500); // Sleep for a bit to make the output more readable
      } catch (InterruptedException e) {
        System.out.println(threadName + " was interrupted");
        return;
      }
    }

    System.out.println(threadName + " finished counting!");
  }
}
