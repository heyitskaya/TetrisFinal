
public class ZShape extends TetrisBlock //starts two to the left
{
	public ZShape()
	{
		super();
		
		shape();
		
		
	}
	public void shape() //where we define our shape
	{
		squareBoard[0][0][0]=true;
		squareBoard[0][0][1]=true;
		squareBoard[0][1][1]=true;
		squareBoard[0][1][2]=true;
		//rotate1
		
		squareBoard[1][0][1]=true;
		squareBoard[1][1][0]=true;
		squareBoard[1][1][1]=true;
		squareBoard[1][2][0]=true;
		//rotate2
		squareBoard[2][0][0]=true;
		squareBoard[2][0][1]=true;
		squareBoard[2][1][1]=true;
		squareBoard[2][1][2]=true;
		//rotate 3 same as rotate 1
		squareBoard[3][0][1]=true;
		squareBoard[3][1][0]=true;
		squareBoard[3][1][1]=true;
		squareBoard[3][2][0]=true;
		//rotate 4 same as initial shape
		
		
		                
		
		
		
		
		
	}

}
