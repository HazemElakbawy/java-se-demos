package com.hazem.concurrency.demo4;

public class SafeCounter {

  private int counter = 0;

  public synchronized void increment() {

    // * Without synchronized ==> randomly: 1001
    //    int temp = counter;
    //    try {
    //      Thread.sleep(5);
    //    } catch (InterruptedException e) {
    //      throw new RuntimeException(e);
    //    }
    //    temp = temp + 1;
    //    counter = temp;

    // * With synchronized ==> always: 5000
    //    synchronized (this) {
    //      int temp = counter;
    //      try {
    //        Thread.sleep(5);
    //      } catch (InterruptedException e) {
    //        throw new RuntimeException(e);
    //      }
    //      temp = temp + 1;
    //      counter = temp;
    //    }

    int temp = counter;
    try {
      Thread.sleep(5);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    temp = temp + 1;
    counter = temp;
  }

  public synchronized int getCounter() {
    return counter;
  }
}
