package com.hazem.concurrency.demo5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {

  private static final Logger LOGGER = LoggerFactory.getLogger(SharedBuffer.class);
  private final Queue<Integer> queue = new LinkedList<>();
  private final int MAX_SIZE;

  public SharedBuffer(int MAX_SIZE) {
    this.MAX_SIZE = MAX_SIZE;
  }

  public synchronized void produce(int item) throws InterruptedException {
    while (queue.size() == MAX_SIZE) {
      LOGGER.info("The buffer is full! Producer is waiting...");
      wait();
    }

    queue.offer(item);
    LOGGER.info("A new item {} is added to the buffer", item);
    notifyAll();
  }

  public synchronized int consume() throws InterruptedException {
    while (queue.isEmpty()) {
      LOGGER.info("Buffer is empty! Consumer is waiting...");
      wait();
    }

    int item = queue.poll();
    LOGGER.info("Item {} is consumed from the buffer, buffer size: {}", item, queue.size());
    notifyAll();

    return item;
  }

  public synchronized int getBuffSizeSync() {
    return queue.size();
  }
}
