package com.hazem.concurrency.demo5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws InterruptedException {
    SharedBuffer buffer = new SharedBuffer(3);

    Producer producer1 = new Producer("Producer-1", buffer, 5);
    Producer producer2 = new Producer("Producer-2", buffer, 3);
    Producer producer3 = new Producer("Producer-3", buffer, 7);
    Producer producer4 = new Producer("Producer-4", buffer, 8);
    Consumer consumer1 = new Consumer("Consumer-1", buffer, 13);
    Consumer consumer2 = new Consumer("Consumer-2", buffer, 10);

    Thread prodThread1 = new Thread(producer1);
    Thread prodThread2 = new Thread(producer2);
    Thread prodThread3 = new Thread(producer3);
    Thread prodThread4 = new Thread(producer4);
    Thread consThread1 = new Thread(consumer1);
    Thread consThread2 = new Thread(consumer2);

    LOGGER.info("Buffer capacity: 3 items");
    LOGGER.info("Total items to produce: 8, Total items to consume: 8");

    prodThread1.start();
    prodThread2.start();
    prodThread3.start();
    prodThread4.start();
    consThread1.start();
    consThread2.start();

    prodThread1.join();
    prodThread2.join();
    prodThread3.join();
    prodThread4.join();
    consThread1.join();
    consThread2.join();

    LOGGER.info("All threads completed!");
    LOGGER.info("Final buffer size: {}", buffer.getBuffSizeSync());
  }
}
