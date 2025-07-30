package com.hazem.concurrency.demo2;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    CounterThread counterOne = new CounterThread(5, "counter-1");
    CounterThread counterTwo = new CounterThread(3, "counter-2");

    CounterRunnable runnableThree = new CounterRunnable(8, "counter-3");
    CounterRunnable runnableFour = new CounterRunnable(10, "counter-4");

    Thread counterThree = new Thread(runnableThree); // wrapped in a Thread class
    Thread counterFour = new Thread(runnableFour);

    Thread counterFive = new Thread(() -> {
      for (int i = 1; i <= 7; i++) {
        System.out.println("counter-5 count: " + i);

        try {
          Thread.sleep(500); // Sleep for a bit to make the output more readable
        } catch (InterruptedException e) {
          System.out.println("counter-5 was interrupted");
          return;
        }
      }

      System.out.println("counter-5 finished counting!");
    });

    System.out.println("Starting counters");
    
    counterOne.start();
    counterTwo.start();
    counterThree.start();
    counterFour.start();
    counterFive.start();

    counterOne.join();
    counterTwo.join();
    counterThree.join();
    counterFour.join();
    counterFive.join();

    System.out.println("All counters are finished");
  }
}
