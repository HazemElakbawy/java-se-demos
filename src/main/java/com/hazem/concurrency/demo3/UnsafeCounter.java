package com.hazem.concurrency.demo3;

public class UnsafeCounter {

  private int counter = 0;

  public void increment() {

    // counter ++;
    
    // used intead of `counter++` to increase the possibility of race conditions

    int temp = counter;

    temp = temp + 1;

    counter = temp;
  }

  public int getCounter() {
    return counter;
  }
}
