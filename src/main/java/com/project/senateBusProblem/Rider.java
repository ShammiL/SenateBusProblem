package main.java.com.project.senateBusProblem;

import java.util.concurrent.Semaphore;

public class Rider implements Runnable{

    private final Semaphore mutex;
    private final Semaphore bus;
    private final Semaphore boarded;
    private int count;

    public Rider(Semaphore mutex, Semaphore bus, Semaphore boarded, int count) {
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
            System.out.println(String.format("Rider %d arrived at the bus stop", count));
            Config.incrementWaiting();
            mutex.release();

            bus.acquire();
            System.out.println(String.format("Rider %d boarded the bus", count));
            boarded.release();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

}
