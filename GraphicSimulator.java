package Needle;


import java.awt.*;
import javax.swing.*;

public class GraphicSimulator extends Simulator {
    Stats stats;
    Inställningar inst;
    MainFrame window;

    public static void main(String[] args) {
      final int maxNeedleLength = 6;
      final int maxDrops = 100;// 10000;
      final int precision = 5;
      final int floorSideSize = 60;
      final int boardSize = 6;
      final int dropDelay = 0;

      GraphicSimulator s = new GraphicSimulator(floorSideSize, boardSize, maxNeedleLength);
    }
    
    GraphicSimulator() {
    }

    GraphicSimulator(int floorSideSize, int boardSize, int maxNeedleLength) {

        floor = new Floor(floorSideSize, floorSideSize, boardSize);

        stats = new Stats();
        inst = new Inställningar(this,floor);

        window = new MainFrame(700, 700);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.show();
        Container pane = window.getContentPane();
        
        pane.setFont(new Font("Arial", Font.PLAIN, 14));

        pane.setLayout(new BorderLayout());

        pane.add(inst, BorderLayout.NORTH);
        pane.add(floor, BorderLayout.CENTER);
        pane.add(stats, BorderLayout.SOUTH);

    }

    public void simulate(int needleSize, int maxDrops, int precision, int dropDelay) {
        //Simulerar släppningen av nålar
        needle = new GraphicNeedle(needleSize, dropDelay);
        stats.resetStatus();
        stats.setNeedleSize(needleSize);
        //Sätter hur precist svaret krävs vara
        stats.setPrecision(precision);
        
        //Ser om estimationen är bra nog för den precision som valts, tar in boolean från runDrops i klassen Simulate
        double x;
        if(runDrops(maxDrops, precision)) {
            stats.setMessage("A GOOD Estimation was Found!");
            x= Math.abs(Math.PI - est);
            System.out.println("Pi: " + Math.PI);
            System.out.println("Est: " + est);
            System.out.printf("Good estimation gave a difference of %.7f", x);
        }
        else { 
            stats.setMessage("No GOOD Estimation was Found!");
            x= Math.abs(Math.PI - est);
            System.out.println("Pi: " + Math.PI);
            System.out.println("Est: " + est);
            System.out.printf("Bad estimation gave a difference of %.7f", x);
        }
        System.out.println();
        stats.setDropsMax(maxDrops);
        stats.setDropsTotal(totalDrops);
        stats.setDropsCrossing(dropsCrossingCrack);
        stats.setEstimation(est);
    }

    public void pause() {
        if(needle != null) {
            ((GraphicNeedle) needle).pause();
        }
    }

    public void resume() {
        if(needle != null) {
            ((GraphicNeedle) needle).resume();
        }
    }

    public void terminate() {
        if(needle != null) {
            ((GraphicNeedle) needle).terminate();
        }
    }

    public void reset() {
        floor.clean();
        needle = null;
    }
}