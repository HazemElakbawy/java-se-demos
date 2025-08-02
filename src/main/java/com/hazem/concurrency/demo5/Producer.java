package com.hazem.concurrency.demo5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer implements Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
  private final String name;
  private final SharedBuffer buffer;
  private final int itemsToProduce;

  public Producer(String name, SharedBuffer buffer, int itemsToProduce) {
    this.name = name;
    this.buffer = buffer;
    this.itemsToProduce = itemsToProduce;
  }

  @Override
  public void run() {
    try {

      for (int i = 0; i < itemsToProduce; i++) {
        Thread.sleep(500 + (int) (Math.random() * 500)); // 500-1000ms
        int item = i * 10;
        buffer.produce(item);
      }

      LOGGER.info("Producer {} has completed adding all items", name);
    } catch (InterruptedException e) {
      LOGGER.info("Producer {} is interrupted", name);
    }
  }
}
