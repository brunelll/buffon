package Needle;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Floor extends JPanel {
    double sizeX;
    double sizeY;
    double boardWidth;
    double skala = 6;
    Needle needle;
    
    public Floor(double sizeX, double sizeY, double boardWidth) {
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.boardWidth=boardWidth;
        setSize(new Dimension((int)(sizeX*skala), (int)(sizeY*skala)) );
    }
    
    //Avgör om nål hamnat på bräda eller inte
    public boolean isOnCrack(Needle n) {
        double y1 = n.getStartY();
        double y2 = n.getStopY();
        //Simplifierad metod för att beräkna om nålen överlappar en bräda
        if((int)((int)y1 / boardWidth) == (int)((int)y2 / boardWidth)) {
            return false;
        }
        else return true;
    }
    
    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public double getBoardWidth() {
        return boardWidth;
    }

    public double getSkala() {
        return skala;
    }

    public void clean() {
        repaint();
    }
    
    //Bestämmer och färgger "brädor"
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        //Räknar ut antalet brädor baserat på golvets och brädornas storlek
        double numRows = sizeY / boardWidth;
        //Skiftar brädornas storlek efter inzoomning (skala)
        double pixelBoardWidth = boardWidth * skala;
        //Ger nya värden för storleken efter zoomning
        double xMaxPixel = sizeY * skala;
        double yMaxPixel = sizeX * skala;
        //Målar brädor
        g2.setPaint(Color.red);
        for(int i=0; i<=numRows; i++) {
            for(double j=((i*pixelBoardWidth)-skala); j<=(i*pixelBoardWidth); j++) {
                g2.draw(new Line2D.Double(1, j, xMaxPixel, j));
            }
        }
        //Ritar outline
        Rectangle2D outline = new Rectangle2D.Double(0,0, xMaxPixel, yMaxPixel);
        g2.setPaint(Color.black);
        g2.draw(outline);
    }
}