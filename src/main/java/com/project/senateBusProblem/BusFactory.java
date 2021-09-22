package main.java.com.project.senateBusProblem;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BusFactory implements Runnable{

    private final Semaphore mutex;
    private final Semaphore bus;
    private final Semaphore boarded;
    private static Random random;

    public BusFactory(Semaphore mutex, Semaphore bus, Semaphore boarded) {
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        random = new Random();
    }


    @Override
    public void run() {
        int bus_count = 1;
        while (true) {
            try {
                Bus arriveBus = new Bus(mutex, bus, boarded, bus_count);
                (new Thread(arriveBus)).start();
                bus_count++;
                Thread.sleep(Util.getArrivalTime(Config.BUS_ARRIVAL_MEAN_TIME));

            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Thread.currentThread().isInterrupted()){
                System.out.println("All buses have arrived");
                break;
            }
        }
    }


}
