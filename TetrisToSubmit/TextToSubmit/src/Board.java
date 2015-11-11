//PROBLEM IS IN moveEverything down
//only change the value of the big board when lands or clears out

//anchor
//have two grids logic board view display board
//display take logic grid, tetris piece and anchor
//CHECK: CHECKCLEAR
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class Board 
{
    private boolean[][] boardArray; //the board where we actually draw stuff print /** recently set to private**/
        private boolean[][] logicBoard; //the board where we check if we call move down /** recently changed to private**/
        private int column=10; //how many columns we have /**recently changed to private**/
	private int row=18; //how many rows we have
	//row by columns
        private int anchorX=0; 
	private int anchorY=1;
        private TetrisBlock currentShape; 
        private int numRotations=0;  //its fine because we never use numRotations outside of this class
	private boolean pieceLanded=false; 
	private int rowsCleared=0; 
	private boolean isGameOver=false; 
        private int rowToClear; 
	
	
	
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
	
	public void add(TetrisBlock block, int rotations) /** this method adds a tetris block onto the board at the current rotation**/
	{
		/** we call this after a piece has landed so we set numRotations to 0 because every shape starts with rotation 0, and set
		 * piece landed to false**/
		 
		numRotations=0; 
		pieceLanded=false;
		
		String string="";
		currentShape=block; //this is where we initialize currentShape
		boolean[][] array=currentShape.squareBoard[rotations]; 
		
		
		
		for(int a=0;a<4;a++) //traverse the 4x4 array that holds our current shape
		{
			
			
			for(int b=0;b<4;b++) 
			{
				
				if(array[a][b]==true) //if its true at the current position
				{
					
					boardArray[anchorX+a][anchorY+b]=true; //find the corresponding places on the big board and set it to true
				}
				
				
			}
			
		}
		
		updateBoardArray(); //finally we update the boardArray
		
		
		
	}
	
	public void moveDown() /** first this will check to see if we can move down, we can it will be moved down**/
	{
		
		if(checkValidDown(numRotations)==true) // not evaluating to false when it should be
		{ //when there is no collision and still on grid
			
			for(int a=3;a>=0;a--) //first two rows
			    { //traverse 4x4
			
			//should set the previous shape to false
				for(int b=0;b<4;b++) //we should traverse every column in that row
				{
					
					
				    if(currentShape.squareBoard[numRotations][a][b]) //if it's true for the shape on the 4x4 board
					{
						
						boardArray[anchorX+a][anchorY+b]=false; //find the corresponding position on the
					//big board and set it to false
						
						boardArray[anchorX+a+1][anchorY+b]=true;
						
					}
				
				}
			}
			
			anchorX++; //update the x coordinate of anchorPoint
			
			
			
			updateBoardArray();
			
		}
		//when checkValidDown gets evaluated to false
		else  //it will come off the grid
		{
			pieceLanded=true; //set piece landed to true
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
			updateBoardArray(); //update viewer baord
			anchorX=0; //reset anchor points so we can use them for the next shape
			anchorY=1;
		}
		
		
		
	}
	
	//this is second I've changed
	public void moveLeft()/** first calls checkValidLeft to see of we can move left at the current rotation **/
	{
	    //while validLeft is true
		
		
		if(checkValidLeft(numRotations)) //if we can move it left at the current rotation
		{
			
			for(int a=0;a<4;a++)  //traverse 4x4 array
			{
			
			
				for(int b=0;b<4;b++) //we should traverse every column in that row
				{
				    if(currentShape.squareBoard[numRotations][a][b]) //we only care about the places that are true
					{
							boardArray[anchorX+a][anchorY+b]=false; //set the corresponding places in the big board to false
							
							
							boardArray[anchorX+a][anchorY+b-1]=true; //set positions one left to true
						
					}
				
				}
			}
			anchorY--;  //decrement Y coordinate of anchor point
		
		
			
		}
		updateBoardArray();
		
	}
	//this is the fourth one I changed should be correct
	public void moveRight() /** check to see if we can moveRight, if we can move right move it**/
	{
		//System.out.println("checkValidRight is:"+ checkValidRight(numRotations));
		
		
		if(checkValidRight(numRotations)) /** call checkValidRight to see if we can move right**/
			
		{
			
		
			for(int a=0;a<4;a++) 
			{	
			
			
				for(int b=3;b>=0;b--) //we should traverse every column in that row
				{
					
				    if(currentShape.squareBoard[numRotations][a][b]) //if it's true for the shape in small grid
					{
						boardArray[anchorX+a][anchorY+b]=false;//set it to false on the corresponding big board we use to draw stuff
						
						
						boardArray[anchorX+a][anchorY+b+1]=true; //set one right to that to true
						
					}
				
				}
			}
			
			anchorY++; //increment y coordinate
			
		} 
		updateBoardArray(); //update our array
		
	}
	
	
	public boolean checkValidLeft(int rotations)/**check to see if it can move left move left or move down**/
	{
	    boolean[][] currentArray=currentShape.squareBoard[rotations]; /**set the current 4x4 array that holds the shape to temporary variable**/
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
		return true;
	} 
	
	public boolean checkValidRight(int rotations) /** checks to see if we can move right, checks for collision and if we will go off the board**/
	{/**returns a boolean**/
	    boolean[][] currentArray=currentShape.squareBoard[rotations]; /**set the current 4x4 array that holds the shape to temporary variable**/
		for(int a =0;a<4;a++)
		{
		    for (int b=0;b<4;b++) //the y coordinate
			{
				
				if(currentArray[a][b]==true) //we only care about the positions that are true
				{
					if(anchorY+b+1<column) // THIS I THINK I FIXED, 
					{
						if(logicBoard[a+anchorX][b+anchorY+1]==true) //if there is a collision
						{
							return false;
						}
					}
					else
					{
						return false;
					}
				}
				
				
			}
		}
		return true;
		
	}
	public boolean checkValidRotate(int rotations) /**to check if it can rotate clockwise and counter clockwise**/
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
	public boolean checkValidDown(int rotations) /** to check if we can move it down, checks for collision and when it goes off the board**/
	{
	    boolean[][] currentArray=currentShape.squareBoard[rotations]; //4x4 array
		for(int a =0;a<4;a++) //traverse the 4x4 2D array
		{
			for (int b=0;b<4;b++)
			{
				

				if(currentArray[a][b]==true) //we only care about the positions that are true
				{
					//if one down from the corresponding index is occupied we cannot move down
					if(a+anchorX+1<row-1)//if we can move it down
					{
						if(logicBoard[a+anchorX+1][b+anchorY])// if we can move it down but the corresponding position one down on logicBoard is occupied
						{
							pieceLanded=true; //then the piece has landed so we set pieceLanded to true
							return false;
							
						}
						
					}
					
					else //when we can't move it down a+anchorX+1>9 moving it down one will make it go off the grid
					{
						
						pieceLanded=true;
						return false;
					}
					
					
				}
				
			}

		}
		
		
		return true;
		
	}
	
	public void updateBoardArray() /** this prints out the updated viewer board to the terminal**/
	{
		
		String line="";
		
		for(int a =0;a<row-1;a++)
		{						//traverse the board array
			for(int b=0;b<column;b++)
			{
				if(boardArray[a][b]==true) 
				{
					line+="x"; //prints x to indicate that there is a tetris piece there
					
					
				}
				else
				{
					line+="0"; //prints spaces
				}
			}
			System.out.println(line);
			line="";
		}
	}
	
	
	
	public void clearBoardArray() 
	{
		for(int a=0; a<row;a++)
		{
			for(int b=0; b<column; b++)
			{
				boardArray[a][b]=false;
			}
		}
	}
	public void rotateClock() /**first this will check if we can rotate at the current rotation, if we can it will rotate**/
	{
		int r=numRotations;
		r++;
		if(r>3) //if r is bigger than 3, which wont allow because r can only be 0,1,2,3
		{
			r=0; //set r to 0
		}
		
		
		//after we have adjusted the rotation
		System.out.println("We are currently in rotation " + numRotations);
		if(checkValidRotate(r)) //if 
		{
			numRotations=r; //assign the value of r to numRotations
			if(numRotations<=3 && numRotations>=0) //if numRotations is within bounds
			{
			
				clearBoardArray(); //this is clearing the board
			
				for(int c=0;c<row;c++) //traverse the bog board
				{
					for(int d=0;d<column;d++)
					{
						if(logicBoard[c][d]) //copy the pieces that have fallen to the bottom to the boardArray
						{
						
								boardArray[c][d]=true; //now the fallen pieces have been added to boardArray
							
						}
					}
				}
			
				boolean[][] array= currentShape.squareBoard[numRotations];
				for(int i=0; i<4;i++) //traverse the 4x4 array
				{
					for(int j=0; j<4; j++)
					{
						if (array[i][j]) //if at the location it is true
						{
							boardArray[anchorX+i][anchorY+j]=true; //the corresponding place on the big board gets set to true
						}
					}
				}
				 //draw everything out
			}
		}
		
		
		updateBoardArray();
		
	}
	
	public void rotateCounter() /**first this will check if we can rotate at the current rotation, if we can it will rotate**/
	{
		int r=numRotations;
		r--;
		numRotations--;
		if(r<0)
		{
			r=3;
		}
		if(checkValidRotate(r))
		{
			numRotations=r;
			System.out.println("Counter clockwise rotation, we are currently in rotation"+numRotations);
			//if the number of rotations is currently in range
			if(numRotations>=0 &&numRotations<=3)
			{
				//first clear the board array
				clearBoardArray();
				for(int a =0; a<row;a++)
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
			//updateBoardArray();
			boolean[][] array= currentShape.squareBoard[numRotations]; //this is the 4x4 four array where the current shape we want to copy onto 
			//big board lies
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
		else
		{
			//do nothing
		}
		updateBoardArray();
		
			
	}
	
	
	
	public boolean gameLost() /**tell us if the game is over
	by looking  in the first row, if anything in the LOGICBOARD is set to true game is over**/
	{
		for(int b=0; b<column-1;b++)
		{
			if(logicBoard[0][b])
			{
				return true; //game is over
			}
			
		}
		return false;
		
	}
	
	
	

	// NEW CHECK CLEAR
	public void checkClear() //it doesn't send a new piece out until we have called checkClear
	{
		for(int i=row-1; i>=0;i--) //starting from the bottom row up
		{
			if(logicBoard[i][0]&& logicBoard[i][1]&& logicBoard[i][2] && logicBoard[i][3] && logicBoard[i][4] && logicBoard[i][5]&& logicBoard[i][6] &&logicBoard[i][7] && logicBoard[i][8] &&logicBoard[i][9]  ) //the row that needs to be cleared
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
					logicBoard[0][b]=false;
				}
				rowsCleared++;//increment the value of how many rows we've cleared
				checkClear();  //call this again to see if any lined need to be cleared
				
				
			}
		}
		
		clearBoardArray(); // then I clear the board array by setting everything to false
		for(int e=row-1; e>=0;e--)  //traverse the board
		{
			for (int f=0; f<column;f++)
			{
				
				
				if(logicBoard[e][f]==true) //if logic board at the current position is true
				{
					
					boardArray[e][f]=true; //set the corresponding position on boardArray to true
									
					
					
				}
						
			}
		}
		
		
		
		
		
	}
				
	
	
	
	public TetrisBlock makeRandomPiece() /** generates a random number, and based off of what that random number is return a shape**/
	{
		int piece=(int)(Math.random()*7); //forgot to multiply by 10
		
		
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
	
	// getters and setters for private variables
    public int getAnchorX()
    {
	return anchorX;
    }

    public void setAnchorX(int numToSet)
    {
	anchorX=numToSet;

    }

    public int getAnchorY()
    {
	return anchorY;

    }

    public void setAnchorY(int numToSet)
    {
	anchorY= numToSet;
    }

    public TetrisBlock getCurrentShape()
    {
	return currentShape;
    }
	
    public void setCurrentShape(TetrisBlock shapeToSet)
    {
	currentShape= shapeToSet;
    }

    public boolean getPieceLanded()
    {
	return pieceLanded;
    }

    public boolean getIsGameOver()
    {
	return isGameOver;
    }
    public int getRowsCleared()
    {
	return rowsCleared;
    }

   
    public void setIsGameOver(boolean valueToSet)
    {
	isGameOver=valueToSet;
    }
	
    public boolean[][] getBoardArray()
    {
	return boardArray;
    }
    
   
	

















}
