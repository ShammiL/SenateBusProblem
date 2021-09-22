package main.java.com.project.senateBusProblem;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class RiderFactory implements Runnable{

    private static Random random;
    private final Semaphore mutex;
    private final Semaphore bus;
    private final Semaphore boarded;

    public RiderFactory(Semaphore mutex, Semaphore bus, Semaphore boarded) {
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        random = new Random();
    }

    @Override
    public void run() {
        int rider_count = 1;

        while (true){
            try {
                Rider rider = new Rider(mutex, bus, boarded, rider_count);
                (new Thread(rider)).start();
                rider_count++;
                Thread.sleep(Util.getArrivalTime(Config.RIDER_ARRIVAL_MEAN_TIME));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Thread.currentThread().isInterrupted()){
                break;
            }
        }

    }
}
