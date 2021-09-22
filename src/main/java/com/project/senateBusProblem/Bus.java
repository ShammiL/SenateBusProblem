package main.java.com.project.senateBusProblem;

import java.util.concurrent.Semaphore;

public class Bus implements Runnable{

    private int n;
    private final Semaphore mutex;
    private final Semaphore bus;
    private final Semaphore boarded;

    public Bus(Semaphore mutex, Semaphore bus, Semaphore boarded) {
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
            n = Math.min(Config.getWaiting(), Config.MAX_BUS_CAPACITY);
            for (int i=0; i<n; i++) {
                bus.release();
                boarded.acquire();
            }
            Config.setWaiting(Math.max(Config.getWaiting()-Config.MAX_BUS_CAPACITY, 0));
            mutex.release();

            depart();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void depart() {
        System.out.println("Bus Departed");
    }
}
