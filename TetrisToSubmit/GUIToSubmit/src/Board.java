
import java.io.BufferedReader;

import javax.swing.Timer;

import java.io.InputStreamReader;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.io.IOException;





public class Board 
{
	private boolean[][] boardArray; //the board where we actually draw stuff print
	private boolean[][] logicBoard; //the board where we check if we call move down
	private int column=10; //how many columns we have
	private int row=18; //how many rows we have
	//row by columns
	private int anchorX=0;
	private int anchorY=3;
	private TetrisBlock currentShape;
	private int numRotations=0;
	private boolean pieceLanded=false;
	private int rowsCleared=0;
	private boolean isGameOver=false;
	private int rowToClear;
	private int tetrisCleared;
	
	
	
	public Board() /**We will initilaize the number of rows and columns of the array
	and set every value in 2D array to false**/
	{
		boardArray= new boolean[row][column];
		logicBoard=new boolean[row][column];
		
		
		
	}
	 

	public void clearLine(int rowToRemove)/**we will call this in checkClear**/
	{
		for(int i=0; i<column; i++ )//traverse the column
		{
			boardArray[rowToRemove][i]=false; //set everythig to false
		}
	}
	
	public void add(TetrisBlock block, int rotations)  /** this adds a tetris block at the current rotation**/
	{
		numRotations=0;
		pieceLanded=false; //when we add a piece we need to set pieceLanded to false
		
		String string="";
		currentShape=block; //this is where we initialize currentShape
		boolean[][] array=currentShape.squareBoard[rotations]; //get the 4x4 array that currentShape at the curent location is in
		
		
		for(int a=0;a<4;a++) 
		{
			
			
			for(int b=0;b<4;b++) //traverse 4x4 array
			{
				
				if(array[a][b]==true) //if it's true at the current postion
				{
					 
					boardArray[anchorX+a][anchorY+b]=true; //find the corresponding places on the big board and set it to true
				}
				
				
			}
			
		}
		
		updateBoardArray();
		
		
	}
	
	public void moveDown() /** checks to see if we can move down, if we can, move down**/
	{
		
		
		if(checkValidDown(numRotations)==true) //not evaluating to false when it should be
		{ //when there is no collision and still on grid
			
			System.out.println("The block has been moved down");
			for(int a=3;a>=0;a--) //first two rows
			{
			
			//should set the previous shape to false
				for(int b=0;b<4;b++) //we should traverse every column in that row
				{
					
					
					if(currentShape.squareBoard[numRotations][a][b]) //if it's true for the shape on the 4x4 board
					{
						
						for(int i=0; i<column;i++) //clear out the entire first row
						{
							boardArray[0][i]=false;
							
						}
						
						boardArray[anchorX+a][anchorY+b]=false; 
						
						boardArray[anchorX+a+1][anchorY+b]=true;
						
						
						
					}
				
				}
			}
			
			anchorX++;
			
			
			updateBoardArray();
			
		}
		//when checkValidDown gets evaluated to false
		else  //it will come off the grid
		{
			pieceLanded=true;
			for(int c=0;c<4;c++)
			{
				for (int d=0;d<4;d++)
				{
					if(currentShape.squareBoard[numRotations][c][d]) //we only care about the places on 4x4 that are true
					{
						
							logicBoard[anchorX+c][anchorY+d]=true;
							
							
							
						
						
					}
				}
			}
			updateBoardArray(); //update viewer board
			anchorX=0; //reset anchor points so the next point can use them
			anchorY=1;
		}
		
		
		
	}
	
	
	public void moveLeft() /** first by calling checkValidLeft, we check to see if we can move left, if we can we set previously true positions on boardArray to false and set the one to the left of it to true**/
	{ //while validLeft is true
		
		
		if(checkValidLeft(numRotations)) //not checking all the cases we want it to probably an equals sign problem
		{
			
			for(int a=0;a<4;a++) //first two rows
			{
			
			//should set the previous shape to false
				for(int b=0;b<4;b++) //we should traverse every column in that row
				{
					if(currentShape.squareBoard[numRotations][a][b]) //we only care about the places that are true
					{
							boardArray[anchorX+a][anchorY+b]=false; //set the corresponding places in the big board to false
							
							
							boardArray[anchorX+a][anchorY+b-1]=true; 
						
					}
				
				}
			}
			anchorY--; 
		
		
			//updateBoardArray();
		}
		updateBoardArray();
		
	}
	//this is the fourth one I changed should be correct
	public void moveRight() /** first call checkValidRight if it evaluates to true, we will move the piece right, if it evaluates to false, do nothing**/
	{
		
		
		
		if(checkValidRight(numRotations)) //checkValidRight is not currently being evaluated to false when it needs to be false
			
		{
			
		
			for(int a=0;a<4;a++) 
			{	
			
			//should set the previous shape to false
				for(int b=3;b>=0;b--) //we should traverse every column in that row
				{
					
					if(currentShape.squareBoard[numRotations][a][b]) //if it's true for the shape in small grid
					{
						boardArray[anchorX+a][anchorY+b]=false;//set it to false on the corresponding big board we use to draw stuff
						
						
						boardArray[anchorX+a][anchorY+b+1]=true; //set one right to that to true
						
					}
				
				}
			}
			
			anchorY++; //increase y coordinate of the anchor point
			
		} 
		updateBoardArray(); //finally update the viewer board
		
	}
	
	
	public boolean checkValidLeft(int rotations)/**check to see if it can move left , if it can return true, it it can't return false**/
	{
		boolean[][] currentArray=currentShape.squareBoard[rotations]; /**get the 4x4 array that currently holds the shape in the current rotation and assign it to local variable currentArray**/
		//traverse currentArray
		for(int a =0;a<4;a++)
		{
			for (int b=0;b<4;b++)
			{
				
				
				
				if(currentArray[a][b]==true) //we only care about the positions that are true
				{
					
					if(b+anchorY-1>=0 )//if the move is within the board
					{//this is the position on the board moved one to the left
						//if its on the board
					
						if(logicBoard[a+anchorX][b+anchorY-1]==true)//check for collision
						{	
							
						return false;
						}
					}else{//if its going out of board
						
						return false;
					}
					
				}
				
			}
		}
		return true; //therefore we can move it left. return true
	} 
	
	public boolean checkValidRight(int rotations) /** checks to see of we can moveRight at the current rotation, if we can function returns true, else returns false**/
	{
		boolean[][] currentArray=currentShape.squareBoard[rotations]; /**get the 4x4 array that currently holds the shape in the current rotation and assign it to local variable currentArray**/
		for(int a =0;a<4;a++)
		{
			for (int b=0;b<4;b++) //traverse 4x4 array
			{
				
				if(currentArray[a][b]==true) //we only care about the positions that are true
				{
					if(anchorY+b+1<=column-1) //this is when we can move right used to be 6
					{
						if(logicBoard[a+anchorX][b+anchorY+1]==true) //if there is a collision
						{
							return false; //return false to indicate that we can't move right
						}
					}
					else
					{
						return false; //if there is no collision, but we will go off the board return false
					}
				}
				
				
			}
		}
		return true; // if it meets non of the conditions above, we can move it right therefore return true
		
	}
	public boolean checkValidRotate(int rotations) /**to check if it can rotate clockwise and counter clockwise, if it can it will return true, else it will return false**/
	{
		boolean[][] array= currentShape.squareBoard[rotations]; //the 4x4 array that holds our current shape at the current rotation
		for(int a=0; a<4;a++)
		{
			for(int b=0; b<4; b++)
			{
				if(array[a][b]) //if its true at the 4x4 array
				{
					if(anchorX+a >=0 && anchorX+a<=row-1 && anchorY+b>=0 && anchorY+b<=column-1) //if it doesn't go off the board at this rotation
					//check to see if it will collide with any pieces using logic board
					//once rotated if any corresponding position of it on the logic baord is true
					
					{
						if(logicBoard[anchorX+a][anchorY+b]) //if it doesn't go off the board but it collides with blocks on logicBoard
						{
							return false;
						}
						
					}
					else //when it does go off the board
					{
						return false;
					}
				}
			}
		}
		
		
		return true;
		
	}
	public boolean checkValidDown(int rotations) /** Checks to see if the piece can move down at the current rotation, if it can move down it will return true, if it can't return false**/
	{
		boolean[][] currentArray=currentShape.squareBoard[rotations]; //4x4 array
		for(int a =0;a<4;a++) //traverse the 4x4 2D array
		{
			for (int b=0;b<4;b++)
			{
				

				if(currentArray[a][b]==true) //we only care about the positions that are true
				{
					//if one down from the corresponding index is occupied we cannot move down
					if(a+anchorX+1<=row-1 && b+anchorY<=column-1)//if we can move it down
					{
						if(logicBoard[a+anchorX+1][b+anchorY])// if we can move it down but the corresponding position one down on logicBoard is occupied
						{
							pieceLanded=true; //then the piece has landed so we set pieceLanded to true
							return false;
							
						}
						
					}
					
					else //when we can't move it down a+anchorX+1>9 moving it down one will make it go off the grid
					{
						pieceLanded=true; //set global variable pieceLanded to true
						return false; //we can't move down so we return false
					}
					
					
				}
				
			}

		}
		
		
		return true; //else we return true
		
	}
	
	public void updateBoardArray() /** this prints out the board on the console, it will print x to indicate there is a block, and a 0 to indicate that its empty **/
	{
		
		String line="";
		
		for(int a =0;a<row;a++)
		{						//traverse the board array
			for(int b=0;b<column;b++)
			{
				if(boardArray[a][b]==true) 
				{
					line+="x";
					
				}
				else
				{
					line+="0";
				}
			}
			System.out.println(line);
			line="";
		}
	}
	
	public void clearBoardArray() /**this will clear everything in the boardArray by traversing it and setting everything to false**/
	{
		for(int a=0; a<row;a++)
		{
			for(int b=0; b<column; b++)
			{
				boardArray[a][b]=false;
			}
		}
	}
	
	public void rotateClock() //first clear out everything in the boardArray
	{
		
		int r=numRotations; //have temporary variable r and assign numRotations to temporary variable r
		r++; //increment r, because we want to rotate it
		
		
		if(r>3) //if r is bigger than 3, which wont allow because r can only be 0,1,2,3
		{
			r=0; //set r to 0
		}
		//after we have adjusted the rotation
		
		if(checkValidRotate(r)) //if we can rotate it at the current rotation
		{
			numRotations=r; //assign the value of temporary variable r to numRotations
			if(numRotations<=3 && numRotations>=0) //if numRotations is currently in range
			{
			
				clearBoardArray(); //we clear the viewer board by setting everything to false
			
				for(int c=0;c<row;c++)
				{
					for(int d=0;d<column;d++) //traverse the logicBoard
					{
						if(logicBoard[c][d]) //copy the pieces that have fallen to the bottom to the boardArray
						{
						
								boardArray[c][d]=true; //now the fallen pieces have been added to boardArray
							
						}
					}
				}
			
				boolean[][] array= currentShape.squareBoard[numRotations]; //get the 4x4 array that holds the shape at the current rotation and assign it to temporary variable array;
				for(int i=0; i<4;i++)
				{							//traverse the 4x4 array that holds the shape we want to copy onto viewerBoard
					for(int j=0; j<4; j++)
					{
						if (array[i][j]) //if at the location it is true
						{
							boardArray[anchorX+i][anchorY+j]=true; //the corresponding place on the big board gets set to true
						}
					}
				}
				 
			}
		}
		
		
		updateBoardArray(); //finally update viewer board
		
	}
	
	public void rotateCounter() /** this will rotate the shape counter clockwise**/
	{ //GUI
		
		int r=numRotations; //have a temporary variable r equal to numRotations
		r--; //decrement r, because we rotate it counter clockwise
		if(r<0) //if r is negative
		{
			r=3; //we set it to true, because r can only be 0,1,2,3
		}
		
		if(checkValidRotate(r)) //check to see if we can rotate at the current rotation
		{
			numRotations=r; // if we can , assign temporary variable r to numRotations
			
			
			if(numRotations>=0 &&numRotations<=3) //if the number of rotations is currently in range
			{
				
				clearBoardArray(); //first clear the board array
				for(int a =0; a<row;a++) //we traverse the logic board
				{
					for (int b=0;b<column;b++)
					{
						if(logicBoard[a][b]) //if its true at the logicBoard, meaning that it has already landed
						{
							boardArray[a][b]=true; //we set the fallen pieces on baordArray to true
						}
					}
				}
			}
			
			boolean[][] array= currentShape.squareBoard[numRotations]; //this is the 4x4 four array where the current shape we want to copy onto 
			
			for(int c=0; c<4;c++)
			{						//traverse 4x4 array
				for(int d=0;d<4;d++)
				{
					if(array[c][d]) //if it's true at the 4x4 array
					{
						boardArray[anchorX+c][anchorY+d]=true; //the corresponding position on the viewer board gets set to true
					}
						
				}
			}
		
		}
		
		updateBoardArray(); //we finally update the viewer board
		
			
	}
	
	
	//called before we add a shape
	public boolean gameLost() /**tell us if the game is over
	look in the first row, if anything in the LOGICBOARD is set to true and there is something in the first row,  game is over **/
	{
		for(int b=0; b<column;b++)
		{
			if(logicBoard[0][b])
			{
				return true; //game is over
			}
			
		}
		return false;
		
	}
	
	
	

	// NEW CHECK CLEAR
	public void checkClear() /** This will go through viewer board from the bottom up to see if there are any rows we can clear, if there are , it clears them and increments global variable 
	rowsCleared**/
	{
		System.out.println("We are in the checkClear method???????????????????????");
		for(int i=row-1; i>=0;i--) //starting from the bottom row up
		{
		    if(logicBoard[i][0]&& logicBoard[i][1]&& logicBoard[i][2] && logicBoard[i][3] && logicBoard[i][4] && logicBoard[i][5]&& logicBoard[i][6] && logicBoard[i][7] &&logicBoard[i][8] && logicBoard[i][9]) //the row that needs to be cleared
				//if the entire row is true
			{
				
				for(int a=i; a>0;a--)
					
				{
					for (int b=0; b<column; b++)
					{
						//get all the values from the a-1th row and copy it to the ath row then set the first row to false
						logicBoard[a][b]=logicBoard[a-1][b];
					
					}
				}
				for (int b=0; b<column; b++)
				{
					logicBoard[0][b]=false; //set everything in the first row to false
				}
				rowsCleared++; //increment the number of rows cleared
				calcTetrisCleared();  //calling this method will calculate how many tetrises we cleared and assign it to global variable tetrisCleared
				checkClear(); //check to see if we can clear anything again
				break; //break out of this function
			}
		}
		
		clearBoardArray(); //sets everything in viewerBoard to false
		for(int e=row-1; e>=0;e--)  //traverse the logicBoard
		{
			for (int f=0; f<column;f++)
			{
				
				
				if(logicBoard[e][f]==true) //if logic board at the current position is true
				{
					
					boardArray[e][f]=true; //set the corresponding position on boardArray to true
									
					
					
				}
						
			}
		}
		
		updateBoardArray(); //finally we update the viewer board
		
		
		
	}
				
	
	
	
	
	
	
	public void moveEverythingDown(int a, boolean[][] arrayToRemove) //after we clear a row, we move everything above that row that is true on the logic board down one
	{
		for(int i=a;i>=0;i--) //from down to up
		{
			for (int j=0;j<column;j++) //from left to right
			{
				if(arrayToRemove[i][j]) //we only care about things that are true on the logic board
				{
					arrayToRemove[i][j]=false; //set previously true items to false
					arrayToRemove[i+1][j]=true; //one below it gets set to true
					
				}
				
			}
		}
	
	}
	public void clearRow(int rowNumber, boolean[][] arrayToClear) /** takes in an int to indicate row number and a 2d array**/
	{
		/** it will clear the row on that particular array**/
		for(int a=0; a<column;a++) //traverse the row
		{
			arrayToClear[rowNumber][a]=false; //sets everything in that row to false
		}
	}
	
	public TetrisBlock makeRandomPiece() /** this method generates a random piece**/
	{
		int piece=(int)(Math.random()*7); 
		
		if(piece==0)
		{
			currentShape=new LShape();
			return currentShape;
		}
		else if(piece==1)
		{
			currentShape= new IShape();
			return currentShape;
		}
		else if(piece ==2)
		{
			currentShape=new JShape();
			return currentShape;
			
		}
		else if(piece==3)
		{
			currentShape=new OShape();
			return currentShape;
		}
		else if(piece==4)
		{
			currentShape=new SShape();
			return currentShape;
		}
		else if(piece==5)
		{
			currentShape=new TShape();
			return currentShape;
		}
		else 
		{
			currentShape= new ZShape();
			return currentShape;
		}
	}
	
	public void printLogic()
	{
		System.out.println("Meanwhile on the logic board");
		String line="";
		for(int a=0; a<row-1;a++)
		{
			for(int b=0; b<column-1; b++)
			{
				if(logicBoard[a][b]==true)
				{
					line+="x";
				}
				else
				{
					line+="o";
				}
				
			}
			System.out.println(line);
			line="";
		}
	}
	
	
	
	
	
	//getters and setters for private variables
	
	public void calcTetrisCleared()
	{
		tetrisCleared= (int)(rowsCleared/4);
	}
	
	public boolean[][] getBoardArray()
	{
		return boardArray;
	}
	
	public int getAnchorX()
	{
		return anchorX;
	}

	public int getAnchorY()
	{
		return anchorY;
	}
	
	public void setCurrentShape(TetrisBlock block)
	{
		currentShape=block;
	}
	
	public TetrisBlock getCurrentShape()
	{
		return currentShape;
	}
	
	public boolean getPieceLanded()
	{
		return pieceLanded;
	}
	
	public void setPieceLanded(boolean  b)
	{
		pieceLanded=b;
	}
	
	public int getRowsCleared()
	{
		return rowsCleared;
	}
		
	public void setRowsCleared(int i)
	{
		rowsCleared=i;
	}
	
	public boolean getIsGameOver()
	{
		return isGameOver;
	}
	public void setIsGameOver(boolean b)
	{
		isGameOver=b;
	}
		
	public int getTetrisCleared()
	{
		return tetrisCleared;
	}
	
	
	
	
	
	
	

















}
