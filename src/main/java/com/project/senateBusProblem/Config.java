package main.java.com.project.senateBusProblem;


import java.util.concurrent.Semaphore;

public class Config {
    private static int waiting = 0;
    public static final int MAX_BUS_CAPACITY = 50;
    public static final float RIDER_ARRIVAL_MEAN_TIME = 30f * 1000;
    public static final float BUS_ARRIVAL_MEAN_TIME = 20 * 60f * 1000;

    private static final Semaphore MUTEX = new Semaphore(1);
    private static final Semaphore BUS = new Semaphore(0);
    private static final Semaphore BOARDED = new Semaphore(0);

    public static int getWaiting() {
        return waiting;
    }

    public static void setWaiting(int waiting) {
        Config.waiting = waiting;
    }

    public static void incrementWaiting(){
        Config.waiting++;
    }

}
