package main.java.com.project.senateBusProblem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("========Program Started========");
        System.out.println("--------Enter 'Q' to exit the program--------");

        BusFactory busFactory = new BusFactory(Config.MUTEX, Config.BUS, Config.BOARDED);
        RiderFactory riderFactory = new RiderFactory(Config.MUTEX, Config.BUS, Config.BOARDED);
        (new Thread(busFactory)).start();
        (new Thread(riderFactory)).start();

        while (true) {
            input = scanner.nextLine();
            if (input.equals("Q") || input.equals("q")) {
                System.out.println("========Program Terminated========");
                System.exit(0);
            }
        }


    }

}
