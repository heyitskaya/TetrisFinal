import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.GridLayout;
//THINGS I'M NOT SURE ABOUT
//should this class extend JPanel
//adding an action listener and action performed method, if else statement within action performed method
//should i put my keyPressed method here
//how do I write refresh display (is it the same as updateBoardArray)
//what is create view


/**this class and TetrisBoardGUI instantiates board**/
//WHAT THIS CLASS IS:
//my big panel
//my play class

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;

//big panel
public class TetrisGUIController extends JPanel implements KeyListener
{
	private int rows=18;
	private int columns=10;
	private Timer timer;
	static int DEFAULT_DROP_RATE=1;
	private int dropRate;
	public Board game= new Board(); //we implement 1 board here
	
	private javax.swing.JLabel linesLabel;
	private javax.swing.JLabel tetrisesLabel;
	
	public TetrisBoardGUIView gridPanel;
	
    public JLabel rule1= new JLabel("You have cleared "+ game.getTetrisCleared()+ "tetrises"); //for tetris cleared
    public JLabel rule2= new JLabel("You have cleared " + game.getRowsCleared() + "rows"); //label that displays how many tetrises cleared
    
	private int score;
	
	public boolean[][] viewerBoard= game.getBoardArray(); //is this the same boardArray as the one in gridPanel
	
	//this will be the big JPanel
	//jlabel needs to be global jpanel does not
	public TetrisGUIController() // this itself is a JPanel
	{
		super(new BorderLayout()); //makes itself a JPanel
		
		setupTimer(); //call this method to set up timer
		
		JPanel instructions = new JPanel(); //row by columns
		instructions.setLayout(new GridLayout(2,1));
		instructions.add(rule1); //add the 2 JLabels to the instructions panel
		instructions.add(rule2);
		
	
		gridPanel= new TetrisBoardGUIView(game); //instantiate gui view
		
		
		this.setLayout(new BorderLayout()); //set it to the border layout
		add(gridPanel,BorderLayout.CENTER); //add the gridPanel to the TetrisGUIController
		this.add(instructions,BorderLayout.NORTH); //add the instruction JPanel to the frame
		
		
		game.setCurrentShape( game.makeRandomPiece()); 
		game.add(game.getCurrentShape(),0);
		controlPlay();
		 //this will start playing the game
		setFocusable(true);
		addKeyListener(this);
		
		
	}
	
	
	
	public void keyPressed(KeyEvent e) /**this method handles the keyevent**/
	{
		int key=e.getKeyCode(); //we get the key code and store it in local variable key of type int
		
		
		if(key==KeyEvent.VK_LEFT) //if we press the left arrow key
		{
			game.moveLeft(); //call move left
			controlPlay(); //call the play method
			refreshDisplay(); //update the viewer display
			
			
			
			
		}
		else if(key==KeyEvent.VK_DOWN) //if we press the down array key
		{
			
			game.moveDown(); //move the piece down
			
			controlPlay(); //call play method
			
			refreshDisplay(); //refresh GUI
			
		}
		else if(key==KeyEvent.VK_RIGHT)
		{
			game.moveRight(); //move the piece right
			controlPlay(); //call play method
			
			refreshDisplay();//refresh GUI
		}
		else if(key==KeyEvent.VK_Z)
		{
			
			game.rotateCounter(); //rotate the piece counter clockwise
			controlPlay(); //play method
			
			refreshDisplay();//refresh GUI
		}
		else if(key==KeyEvent.VK_X)
		{
			game.rotateClock(); // rotate clcokwise
			controlPlay(); //call play method
			
			refreshDisplay();//refresh GUI
		}
	}
	public void keyReleased(KeyEvent e) /**defined but not implemented**/
	{
		
	}
	public void keyTyped(KeyEvent e) /**defined but not implemented**/
	{
		
	}
	public void refreshDisplay() /** we reset the text in the JLabels then call repaint and requestFocus**/
	{
		//reset to  white
		rule1.setText("You have cleared "+ game.getTetrisCleared()+ " tetrises");
		rule2.setText("You have cleared " + game.getRowsCleared()+ " rows");
		repaint();
		this.requestFocus();
		
		
	}
	
	
	private void setupTimer() /** Method where we set up the timer**/
	{
		
		
		timer= new Timer(1000, new ActionListener() /** Create a new timer and add an actionListener to it**/
		{
			public void actionPerformed(ActionEvent e) /** when the timer starts**/
			{
				game.moveDown(); /**it will have tetris blocks move down every 1000 milliseconds**/
				
				controlPlay(); /** this plays the game**/
				refreshDisplay(); /** call repaint to update the display**/
				
			}
		});
		
		timer.start(); /**start the timer**/
		
		
	}
	
	//the while loop?

    public void controlPlay() /** the game logic**/ 
	{
		
		
		
		if(game.getPieceLanded()) //when my piece has landed
		{
			
			game.setIsGameOver(game.gameLost()); //check to see if we've lost and upate the boolean value
			if(game.getIsGameOver()) //if the game is over
			{
				
				System.out.println("$$$$$$$$$$$$$$$You lose $$$$$$$$$$$$$$$$$"); //tells the player that they have lost
				
				
			}
			else  //when the game isn't over
			{
				
				game.checkClear(); //check if we can clear anything, if we can checkClear automatically clears it
				
				
				
				game.setCurrentShape(game.makeRandomPiece());//make a random piece  DONT FORGET TO UNCOMMENT THIS!!!
				
				game.add(game.getCurrentShape(),0); //add it to the game
				game.setPieceLanded(false); //and set global variable pieceLanded in game to false
				 
				
				
			}
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
