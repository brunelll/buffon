package Needle;

import java.util.Scanner;

public class Simulator {
    public int dropsCrossingCrack=0;
    public int totalDrops=0;
    public double est=0;
    public Floor floor;
    public Needle needle;
    static Scanner sc = new Scanner(System.in);
    public double [] xest = new double[6];
    public int yes[] = new int[6];
    public double y = 0;
    
    public static void main(String [] args) {

        int maxNeedleLength = 6;
        int maxDrops = 10000;
        int precision;
        double floorSideSize = 60;
        double width = 6;
        
        precision = sc.nextInt();
        
        Simulator s = new Simulator(floorSideSize, width, maxNeedleLength, maxDrops, precision);

    }

    Simulator() {
    }

    Simulator(double floorSideSize, double width, int maxNeedleLength, int maxDrops, int precision) {

        floor = new Floor(floorSideSize, floorSideSize, width);

        System.out.println("Floor created of size " + floor.getSizeX() + "x" + floor.getSizeY() +
        " with boards width: " + floor.getBoardWidth());
        for(int i = 0; i<100000; i++){
        for(int needleLength = 1; needleLength <= maxNeedleLength; needleLength++) {
            //System.out.println("\nNeedle Length=["+needleLength+"] :");
         
            needle = new Needle(needleLength);

            if(runDrops(maxDrops, precision)) {
                
                yes[needleLength-1] ++;;
                y = xest[needleLength-1]  + est;
                xest[needleLength-1] = y;
                //System.out.println("  Found an estimation to "+precision+" decimal places.");
                //System.out.println("  Needles crossing crack=["+dropsCrossingCrack+"] Total dropped needles=["+totalDrops+"]");
                //System.out.println("  The estimation comes to ["+est+"]");
            }
            else {
                //System.out.println("  No estimation was found to "+precision+" decimal places.");
                //System.out.println("  Needles crossic cracks=["+dropsCrossingCrack+"] Total dropped needles=["+totalDrops+"]");
                //System.out.println("  Final estimation was ["+est+"]");
            }
    }
}
    for(int i = 1; i <=maxNeedleLength; i++){
        System.out.println("Nållängd [" + i + "] gav en uppskattning på: " + xest[i-1]/yes[i-1]);
        System.out.println("Antal bra uppskattningar: " + yes[i-1]);
    }
    }

    public boolean runDrops(int maxDrops, int precision) {

            final double prec = Math.pow(10, precision);
            final double precisePI = Math.round(Math.PI * prec);

            dropsCrossingCrack=0;
            totalDrops=0;
            boolean found=false;
            est = 0;

            for(totalDrops=0; totalDrops < maxDrops; totalDrops++) {
                try {
                        needle.Drop(floor);
                }
                catch (TerminateException te) {
                        break;
                }
                
                if(floor.isOnCrack(needle)) {
                        dropsCrossingCrack++;
                }
                
                double P = (double)dropsCrossingCrack / (double)totalDrops;
                est = (2 * needle.getLength()) / (P * floor.getBoardWidth());

                if((Math.round(est * prec)) == precisePI) {
                        found=true;
                        break;
                }
            }

            return found;
    }
}