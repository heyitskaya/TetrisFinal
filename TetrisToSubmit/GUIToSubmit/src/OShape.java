// something wrong with my o shape
public class OShape extends TetrisBlock  //starts two to the left
{
	
	public OShape()
	{
		super();
		
		shape();
		
	}
	
	public void shape()
	{
		squareBoard[0][0][0]=true; //top left
		squareBoard[0][0][1]=true; //top right
		squareBoard[0][1][0]=true; //bottom left
		squareBoard[0][1][1]=true;
		
		squareBoard[1][0][0]=true; //top left
		squareBoard[1][0][1]=true; //top right
		squareBoard[1][1][0]=true; //bottom left
		squareBoard[1][1][1]=true;
		
		squareBoard[2][0][0]=true; //top left
		squareBoard[2][0][1]=true; //top right
		squareBoard[2][1][0]=true; //bottom left
		squareBoard[2][1][1]=true;
		
		squareBoard[3][0][0]=true; //top left
		squareBoard[3][0][1]=true; //top right
		squareBoard[3][1][0]=true; //bottom left
		squareBoard[3][1][1]=true;
		
		
		
	}
	
	
	//no need to implement rotate, its the same rotated
}
