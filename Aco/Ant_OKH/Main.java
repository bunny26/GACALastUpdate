import Ants.TravelingSalesman;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;


import java.util.Scanner;

public class Main {

    public static void main(String[] args)  throws FileNotFoundException {
        System.out.println("------------------ANT COLONY OPTIMIZATION------------------");
        long startTime = System.currentTimeMillis();
        if (args.length == 1 && args[0].equals("-p")) {
            menu();
        } else {
            System.out.println("Use the parameter '-p' for custom settings.");
            System.out.println("Otherwise the default values will be: ");
            /*System.out.println("Ants per epoch:           " );
            System.out.println("Epochs:                   ");
            System.out.println("Evaporation Rate:         ");
            System.out.println("Alpha (pheromone impact): ");
            System.out.println("Beta (distance impact):   ");*/

                      // Number of ants to run per generation.
            int gen     = 100 ;
            int ants    = 2;          // Number of generations.
            double evap = 0.1;          // Evaporation rate of pheromones.
            int alpha   = 1;            // Impact of pheromones on decision making.
            int beta    = 0;            // Impact of distance868 on decision making.

            TravelingSalesman tvS = new TravelingSalesman(ants, gen, evap, alpha, beta);
            tvS.run();
        }
       /*PrintWriter pw= new PrintWriter(new File("D://SolusiACO.csv"));
        StringBuilder sb = new StringBuilder();
          
            sb.append("Hasil untuk ACO algorithm ");
            sb.append("\n");
            sb.append((tvS.run()));
            
            pw.write((sb.toString()));
            pw.close();
            System.out.println("File e mari cuk");*/
        System.out.println("Waktu ngerun: " + (System.currentTimeMillis() - startTime) + " millisecond(s)."); 
        System.out.println("-------------------------COMPLETE--------------------------");
    }

    private static void menu ()  throws FileNotFoundException{
        TravelingSalesman tsp;
        int ants, gen;
        double evap;
        int alpha, beta;

        ants        = getUserInt("Ants per epoch:           ");
        gen         = getUserInt("Epochs:                   ");
        evap        = getUserDouble("Evaporation Rate:         ");
        alpha       = getUserInt("Alpha (pheromone impact): ");
        beta        = getUserInt("Beta (distance impact):   ");

        tsp = new TravelingSalesman(ants, gen, evap, alpha, beta);
        tsp.run();
    }

    private static double getUserDouble (String msg) {
        double input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(msg);

            if (sc.hasNextDouble()) {
                input = sc.nextDouble();

                if (input <= 0) {
                    System.out.println("Number must be positive.");
                } else {
                    break;
                }

            } else {
                System.out.println("Invalid input.");
            }
        }
        return input;
    }

    private static int getUserInt (String msg) {
        int input;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print(msg);

            if (sc.hasNextInt()) {
                input = sc.nextInt();

                if (input <= 0) {
                    System.out.println("Number must be positive.");
                } else {
                    break;
                }

            } else {
                System.out.println("Invalid input.");
            }
        }
        return input;
    }

}
