package com.hazem.concurrency.demo5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
  private final String name;
  private final SharedBuffer buffer;
  private final int itemsToConsume;

  public Consumer(String name, SharedBuffer buffer, int itemsToConsume) {
    this.name = name;
    this.buffer = buffer;
    this.itemsToConsume = itemsToConsume;
  }

  @Override
  public void run() {
    try {

      for (int i = 0; i < itemsToConsume; i++) {
        Thread.sleep(300 + (int) (Math.random() * 400)); // 300-700ms
        int item = buffer.consume();
        LOGGER.info("item: {} was consumed from the buffer", item);
      }

      LOGGER.info("Consumer {} has completed removing all items", name);
    } catch (InterruptedException e) {
      LOGGER.info("Consumer {} is interrupted", name);
    }
  }
}
