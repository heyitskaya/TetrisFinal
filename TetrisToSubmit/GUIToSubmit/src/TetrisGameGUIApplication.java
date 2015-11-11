import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
//calls new gui controller
//JFrame holds JPanel
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
/** NEED TO CHECK TO SEE IF WE CAN LOSE**/
//I think I may have fixed the rotate problem
///// NUMBER OF ROWS CLEARED IS NOT WORKING

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import javax.swing.Timer; //t
public class TetrisGameGUIApplication 
{
	private static int FRAMELENGTH=700; //set frame length and width
	private static int FRAMEWIDTH=1090;
	
	public static void main(String[] args) 
	{
		
		
		
		JFrame frame= new JFrame("Tetris"); //make a new frame called tetris
		
		
		
		frame.setSize(FRAMELENGTH,FRAMEWIDTH); //set the frame size and width
		
		
				
		frame.add(new TetrisGUIController()); //instantiate a gui controller and add it to our frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set it to EXIT_ON_CLOSEE
		frame.setVisible(true); //set visibilty to true
		
	}
}

		
		
		
		
		
		
		
	

