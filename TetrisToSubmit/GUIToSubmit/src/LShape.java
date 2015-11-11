//CHANGED MAYBE LOOK AT LATER
public class LShape extends TetrisBlock //starts two to the left
{
	
	
	public LShape()
	
	{
		super();
		
		shape();
	}

	public void shape()
	{
		
		
		squareBoard[0][0][0]=true;
		
		squareBoard[0][0][1]=true;
		squareBoard[0][0][2]=true;
		squareBoard[0][1][0]=true;
		//rotate1
		squareBoard[1][0][0]=true;
		squareBoard[1][0][1]=true;
		squareBoard[1][1][1]=true;
		squareBoard[1][2][1]=true;
		//rotate2
		squareBoard[2][0][2]=true;
		squareBoard[2][1][0]=true;
		squareBoard[2][1][1]=true;
		squareBoard[2][1][2]=true;
		//rotate3
		squareBoard[3][0][0]=true;
		squareBoard[3][1][0]=true;
		squareBoard[3][2][0]=true;
		squareBoard[3][2][1]=true; 
		
		
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
