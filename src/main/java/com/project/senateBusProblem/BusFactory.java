package main.java.com.project.senateBusProblem;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BusFactory implements Runnable{

    private float meanTime = 2 * 60f * 1000;
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
        while (true) {
            try {
                Bus arriveBus = new Bus(mutex, bus, boarded);
                (new Thread(arriveBus)).start();

                Thread.sleep(Util.getArrivalTime(meanTime));

            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("All busses have arrived");
    }


}
