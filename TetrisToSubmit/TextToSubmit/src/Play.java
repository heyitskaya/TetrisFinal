//Problems
// Once the piece lands, we have to move it down again for pieceLanded to be evalutated to true



//after a certain amount of blocks show up on the screen, the entire board disappears(I MAY HAVE FIXED THIS)
// not updating on the same board, it just prints out another board (THIS MIGHT NOT REALLY BE A PROBLEM)

/** NEED TO CHECK TO SEE IF WE CAN LOSE**/
//I think I may have fixed the rotate problem


///// NUMBER OF ROWS CLEARED IS NOT WORKING





import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Play 
{ /**the class where we play our game**/

	public static void main(String[] args) 
	{
		Board myBoard= new Board(); /**instantiate a new board**/
		myBoard.setAnchorX(0); /**set the anchor point**/
		myBoard.setAnchorY(1);
	
		
		myBoard.setCurrentShape(myBoard.makeRandomPiece());
		myBoard.add(myBoard.getCurrentShape(),0); //make a random piece and add it to the board
		
		
		
		
		
		
	
		
	
		BufferedReader in = 
				new BufferedReader( new InputStreamReader( System.in ) );
		
		
		System.out.println("----------------");
	
		
		try
		{ 
		   
			
			String line;
			do
			{
			    System.out.println("Please enter a move (l,r,d,z,x) or type Quit to end");
			    System.out.println("Number of rows cleared: "+myBoard.getRowsCleared());
				
				if(myBoard.getPieceLanded()) //when my piece has landed
				{
				    myBoard.setIsGameOver(myBoard.gameLost()); //update the boolean value
				    if(myBoard.getIsGameOver()) //if the game is over
					{
						
						System.out.println("You lose");
						break;
					}
					else  //when the game isn't over
					{
						myBoard.checkClear();
						
						//after we clear the rows, make a new shape and add it to the board
						System.out.println("-----------------");
						
						
						
						
						myBoard.setCurrentShape(myBoard.makeRandomPiece()); //make a random piece and add it to the board
						myBoard.add(myBoard.getCurrentShape(),0); 
						
							
						
						
					}
					
					
				}
				
				
				
				line=in.readLine();
				
				//depending on what the user typed in, call the following methods
				
				if(line.equals("l"))
				{
				        myBoard.moveLeft();
					System.out.println("---------------");
				}
				else if(line.equals("r"))
				{
					
					myBoard.moveRight();
					System.out.println("---------------");
				}
				else if(line.equals("d"))
				{
					myBoard.moveDown();
					System.out.println("---------------");
				}
				else if(line.equals("x"))
					
				{
					
					
					myBoard.rotateClock();
					System.out.println("---------------");
					
					
				}
				else if(line.equals("z"))
				{
					
					
					
					myBoard.rotateCounter();
					System.out.println("---------------");
					
				}
			}
			while ( (!line.equals( "Quit" ) ) && !(myBoard.getIsGameOver()));
		}
			catch ( IOException ioe )
			{
				// tell exception to print its error log
				ioe.printStackTrace();
			}
	}
	
	
}


			
	
		
		
		
		
	
		
		
	
		

	


