package main.java.com.project.senateBusProblem;

import java.util.concurrent.Semaphore;

public class Rider implements Runnable{

    private int waiting = 0;
    private final Semaphore mutex;
    private final Semaphore bus;
    private final Semaphore boarded;

    public Rider(Semaphore mutex, Semaphore bus, Semaphore boarded) {
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
            waiting++;
            mutex.release();

            bus.acquire();
            board();
            boarded.release();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    private void board() {
        System.out.println("Boarded the bus");
    }
}
