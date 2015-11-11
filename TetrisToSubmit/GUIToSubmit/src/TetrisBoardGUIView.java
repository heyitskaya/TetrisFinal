// the panel with the grid
import java.awt.Graphics;
import java.awt.Color;
import java.awt.GridLayout;
//import math
import java.lang.Math;

import javax.swing.JPanel;
import javax.swing.JComponent;
//just for drawing stuff
public class TetrisBoardGUIView extends JComponent
{
	
    public Board game ;
	private javax.swing.Timer gameTimer;
	private javax.swing.JLabel linesLabel;
	private javax.swing.JLabel tetrisesLabel;
	private TetrisBoardGUIView view;
	private static int frameLength=540; //corresponds to row
	private static int frameWidth=300;  //corresponds to column
	private static int rows=18; //corresponds to length
	private static int columns=10;
	public int blockSize=computeBlockSize();
	public boolean[][] viewerBoard; //the viewer board 
	public Color currentColor;
	
	//get boardArray in view
	
	public TetrisBoardGUIView(Board b) //the constructor
	{
		
		super(); // this makes TetrisBoardGUIView itself a JPanel
		game=b; //assign parameter of type game to global variable game
		
		viewerBoard=b.getBoardArray(); //get the viewer board from the board class
		repaint(); //repaint the whole thing
		
		
		
	}
	public void paint(Graphics g)
	{
		
		paintBoardOutline(g); //call method paintBoardOutline with parameter g
		
		
		
		
		for(int i=0; i<rows;i++) //traverse the viewer board
		{
			for(int j=0;j<columns;j++)
			{
				g.setColor(Color.white);
				paintBlock(g,i,j,blockSize);
				g.setColor(Color.black); //set the color to black
				g.drawRect(j*blockSize,i*blockSize,blockSize,blockSize); //draw the individual squares
				
				
				if(viewerBoard[i][j]) //if its set to true in the viewer board
				{
					
					g.setColor(Color.blue); //first set the color to blue
					
					paintBlock(g,i,j,blockSize); //paint the block
					
					g.setColor(Color.black); //set the color to black
					g.drawRect(j*blockSize,i*blockSize,blockSize,blockSize); //draw the individual squares
				}
				else
				{
					//if its not true
					g.setColor(Color.white); //set the color to white
					paintBlock(g,i,j,blockSize); //call helper method paintBlock to paint the square
					g.setColor(Color.black);
					g.drawRect(j*blockSize,i*blockSize,blockSize,blockSize);
					
				}
			}
		}
		
	}
	
	public void paintBoardOutline(Graphics g) //this draws the outline for the board
	{
		g.setColor(Color.black); //set it to black
		g.drawRect(0,0,frameWidth,frameLength);
	}
	
	
	private void paintBoardOutline(java.awt.Graphics g, int blockSize)
	{
		//how do we implement this method
		g.setColor(Color.black);
		g.drawRect(0,0,blockSize*7, blockSize*10);
		// do we call repaint
		
		
		
	}
	
	
	private void paintBlock(java.awt.Graphics g,int row, int col, int size)/** paint the individual square at index col, row**/
	{
		
		g.fillRect(col*blockSize, row*blockSize, blockSize ,blockSize );
			
	}
	
	
	private int computeBlockSize() /** computes the size of the block**/
	{
		
		return frameLength/rows;
		
	}
	

}
