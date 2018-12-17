package Ants;

import Display.*;
import Graph.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * Solves the Traveling Salesman Problem using vanilla Ant Colony Optimization.
 */
public class TravelingSalesman {

    private Graph graph;
   private int numOfAnts, generations;

    /**
     * Construct TravelingSalesman.
     * @param ants          the number of ants to run per generation
     * @param generations   the number of generations to run
     * @param evaporation   the rate of evaporation
     * @param alpha         the impact of pheromones on decision making
     * @param beta          the impact of distance in decision making
     */
    public  TravelingSalesman (int ants, int generations, double evaporation, int alpha, int beta) {
        this.numOfAnts = ants;
        this.generations = generations;
        graph = IO.Import.getGraph(evaporation, alpha, beta);
    }

    /**
     * Run the algorithm.
     */
    public void run  () throws FileNotFoundException {
        //WindowTSP windowTSP = new WindowTSP(graph.getVertices());
        long startTime = System.currentTimeMillis();
        Ant bestAnt = null;
        int bestEval = 0;

        //delay(1000); // Allow WindowTSP to load.

        for (int i = 0; i < generations; i++) {
            Ant[] ants = createAnts(numOfAnts);
            Ant ant = travel(ants);
            updatePheromones(ants);

            if (bestAnt == null) {
                bestAnt = ant;
                bestEval = ant.eval();
            } else if (ant.eval() < bestEval) {
                bestAnt = ant;
                bestEval = ant.eval();
            }
            //System.out.println("Generasi" +i+" "+bestAnt);
            //windowTSP.draw(bestAnt.getTour());
            
        }
        
        System.out.println("Best Tour:");
        System.out.println("City " + bestAnt);
        System.out.println("Evaluation: " + bestEval);
        
        
        PrintWriter pw = new PrintWriter(new File("D://SolusiACOmed1.csv"));
            StringBuilder sb = new StringBuilder();
          
            sb.append("Hasil untuk ACO algorithm ");
            sb.append("\n");
            sb.append("Best Tour : ");
            sb.append(" City " + bestAnt);
            sb.append("\n");
            sb.append("Evaluation: " + bestEval);
            
            
            pw.write((sb.toString()));
            pw.close();
            
            //System.out.println("1 file solusi berhasil dibuat");
    }

    /**
     * Create ants and put them on random starting positions on the graph.
     * @param quantity  the quantity of ants to create
     * @return          an array of the ants created
     */
    private Ant[] createAnts (int quantity) {
        Ant[] ants = new Ant[quantity];
        for (int i = 0; i < quantity; i++) {
            ants[i] = new Ant(graph);
        }
        return ants;
    }

    /**
     * Let each ant in the input array travel until an entire tour is completed.
     * @param ants      the ants to allow to travel
     * @return          the ant with the best evaluation
     */
    private Ant travel (Ant[] ants) {

        Ant bestAnt = null;
        int bestEval = 0;

        for (Ant ant : ants) {
            while (ant.notFinished()) {
                ant.travel();
            }

            if (bestAnt == null) {
                bestAnt = ant;
                bestEval = ant.eval();
               // System.out.println(ant.eval());
            } else if (ant.eval() < bestEval) {
                bestAnt = ant;
                bestEval = ant.eval();
            }
        }

        return bestAnt;
    }

    /**
     * Update the pheromones in the graph based on an array of ants with
     * completed tours.
     * @param ants  the ants that will be used to update the pheromones
     */
    private void updatePheromones (Ant[] ants) {
        for (Ant ant : ants) {
            graph.updatePheromone(ant);
        }
    }

    /**
     * Sleep the thread for a specified amount of time.
     * @param ms    milliseconds to sleep for
     */
    private static void delay (int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    

}
