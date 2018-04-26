package Needle;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class GraphicNeedle extends Needle {
    private Graphics2D g2;
    private ArrayList needleLines;
    private int dropDelay;
    private boolean pauseFlag=false;
    private boolean stopFlag=false;

    private class needleLine {
        public double startX, startY, stopX, stopY;
        Color color;

        needleLine(double startX, double startY, double stopX, double stopY, Color color) {
            this.startX=startX;
            this.startY=startY;
            this.stopX=stopX;
            this.stopY=stopY;
            this.color=color;
        }

        void draw(Graphics2D g2, Color color, double skala) {
            Paint savePaint = g2.getPaint();
            g2.setPaint(color);

            double x1 = (startX * skala);
            double y1 = (startY * skala);
            double x2 = (stopX * skala);
            double y2 = (stopY * skala);

            g2.draw(new Line2D.Double(x1, y1, x2, y2));

            g2.setPaint(savePaint);
        }

        void draw(Graphics2D g2, double skala) {
            draw(g2, this.color, skala);
        }
    }

    GraphicNeedle(double length, int dropDelay) {
        super(length);
        this.dropDelay = dropDelay;
        needleLines = new ArrayList();
    }

    public void Drop(Floor f) throws TerminateException{

        while((pauseFlag == true) && (stopFlag == false)) {
            Thread.yield();
        }

        if(stopFlag == true) {
            throw new TerminateException();
        }
        
       g2 = (Graphics2D) f.getGraphics();

       super.Drop(f);
        try {
            Thread.sleep(dropDelay);
        }
        catch (InterruptedException e) {
        }
        
        if(f.isOnCrack(this)) {
            needleLines.add(new needleLine(getStartX(), getStartY(), getStopX(), getStopY(), Color.green));
        }
        else {
            needleLines.add(new needleLine(getStartX(), getStartY(), getStopX(), getStopY(), Color.blue));
        }

        ((needleLine)needleLines.get(needleLines.size()-1)).draw(g2, f.getSkala());
    }

    public void pause() {
        pauseFlag = true;
    }

    public void resume() {
        pauseFlag = false;
    }

    public void terminate() {
        stopFlag = true;
    }
}