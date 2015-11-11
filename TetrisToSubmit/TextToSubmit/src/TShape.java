//COMPLETELY CHANGED
public class TShape extends TetrisBlock //starts two to the left
{
	
	public TShape() //a and b are coordinates for where anchor point will lie
	{
		super(); //anchor point is upper left corner
		
		shape();
	}
	
	public void shape() //the index of the point we want to use as the point we want to base all our other points on**/
	
	{
		//the initial shape will be place at index 0 which is the same as where the fourth rotation is
		
		 
			squareBoard[0][0][0]=true;
			squareBoard[0][0][1]=true;
			squareBoard[0][0][2]=true;
			squareBoard[0][1][1]=true;
		
		//rotate 1
	
		
		
			squareBoard[1][0][1]=true;
			squareBoard[1][1][0]=true;
			squareBoard[1][2][1]=true; //uncomment if necessary
			squareBoard[1][1][1]=true;
			
			squareBoard[2][0][1]=true;
			squareBoard[2][1][0]=true;
			squareBoard[2][1][1]=true;
			squareBoard[2][1][2]=true;
			//rotate 3
			squareBoard[3][0][0]=true;
			squareBoard[3][1][0]=true;
			squareBoard[3][2][0]=true;
			squareBoard[3][1][1]=true;
		//rotate4 same as original shape
			
		
		
		
	}
    
}
