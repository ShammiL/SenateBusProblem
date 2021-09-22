package main.java.com.project.senateBusProblem;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class RiderFactory implements Runnable{

    private float mean;
    private static Random random;
    private final Semaphore mutex;
    private final Semaphore bus;
    private final Semaphore boarded;

    public RiderFactory(float mean, Semaphore mutex, Semaphore bus, Semaphore boarded) {
        this.mean = mean;
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        random = new Random();
    }

    @Override
    public void run() {
        int rider_number = 0;

        while (true){
            try {
                Rider rider = new Rider(mutex, bus, boarded);
                (new Thread(rider)).start();
                rider_number++;
                Thread.sleep(getExponentiallyDistributedInterval(mean));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Thread.currentThread().isInterrupted()){
                break;
            }
        }

    }

    public long getExponentiallyDistributedInterval(float mean) {
        float lambda = 1 / mean;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}
