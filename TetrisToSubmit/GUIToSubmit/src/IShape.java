
//COMPLETELY CHANGED
public class IShape extends TetrisBlock //currently not working
{
	private int x;
	private int y;
	
	
	public IShape() /**Constructor of our subclass**/
	{
		super(); //call constructor of superclass
		
		shape();
		
		
	}
	
	public void shape() //horizontal at the top
	{
		
			squareBoard[0][0][0]=true;
			squareBoard[0][0][1]=true;
			squareBoard[0][0][2]=true;
			squareBoard[0][0][3]=true;
			//rotate1
			squareBoard[1][0][0]=true;
			squareBoard[1][1][0]=true;
			squareBoard[1][2][0]=true;
			squareBoard[1][3][0]=true;
			//rotate2
			squareBoard[2][0][0]=true;
			squareBoard[2][0][1]=true;
			squareBoard[2][0][2]=true;
			squareBoard[2][0][3]=true;
			//rotate3
			squareBoard[3][0][0]=true;
			squareBoard[3][1][0]=true;
			squareBoard[3][2][0]=true;
			squareBoard[3][3][0]=true;
			//rotate4 same as original shape  
			
			
			
			
			
					
		
		
	}
	
	
}
