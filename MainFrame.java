package Needle;

import java.awt.*;
import javax.swing.*;

//Fönstret
public class MainFrame extends JFrame {
	public int width;
	public int height;

	public MainFrame(int width, int height) {
		this.width = width;
		this.height = height;
		
		setSize(width, height);
	}
}