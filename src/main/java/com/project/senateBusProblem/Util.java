package main.java.com.project.senateBusProblem;

import java.util.Random;

public class Util {
    private static Random random = new Random();


    public static long getArrivalTime(float meanTime) {
        float lambda = 1 / meanTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }
}
