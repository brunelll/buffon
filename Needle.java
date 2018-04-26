package Needle;

import java.util.Random;

public class Needle {

    private double startX, startY, stopX, stopY;
    private double length;
    private double angle;
    private Random rand;

    public Needle(double length) {
        this.length=length;
        rand = new Random();
    }
    
    public void Drop(Floor f) throws TerminateException {
        double xMax = f.getSizeX();
        double yMax = f.getSizeY();
        boolean done = false;

        do {
                  startX = (rand.nextDouble() * 100.0) % xMax;
                  startY = (rand.nextDouble() * 100.0) % yMax;
                  angle = (rand.nextDouble() * 1000.0) % 360.0;

                  stopX = length * Math.cos(angle) + startX;
                  stopY = length * Math.sin(angle) + startY;
                  
                  if((startX > 0.0) && (startX < xMax) &&
                     (startY > 0.0) && (startY < yMax) &&
                      (stopX > 0.0) && (stopX < xMax) &&
                      (stopY > 0.0) && (stopY < yMax)) {

                                done = true;
                  }
        } while (!done);
    }

    public double getLength() {
        return length;
    }
    
   public double getAngle(){
       return angle;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }
    
    public double getStopX() {
        return stopX;
    }

    public double getStopY() {
        return stopY;
    }

}