//COMPLETELY CHANGED
public class SShape extends TetrisBlock //starts three to the left
{
	
	
	public SShape()
	{
		 super();
		 
		 shape();
	}
	
	public void shape() //here we define the shape
	{
		squareBoard[0][0][0]=true;
		squareBoard[0][1][0]=true;
		squareBoard[0][1][1]=true;
		squareBoard[0][2][1]=true;
		//rotate1
		squareBoard[1][0][1]=true;
		squareBoard[1][0][2]=true;
		squareBoard[1][1][0]=true;
		squareBoard[1][1][1]=true;
		//rotate2
		squareBoard[2][0][0]=true;
		squareBoard[2][1][0]=true;
		squareBoard[2][1][1]=true;
		squareBoard[2][2][1]=true;
		//rotate3
		
		squareBoard[3][0][1]=true;
		squareBoard[3][0][2]=true;
		squareBoard[3][1][0]=true;
		squareBoard[3][1][1]=true;
		//rotate 4 same as orignal shape
		
	}
	/**public void rotate1() //the first rotation
	{
		squareBoard[1][0][1]=true;
		squareBoard[1][0][2]=true;
		squareBoard[1][1][0]=true;
		squareBoard[1][1][1]=true;
		
	}
	public void rotate2() //the second rotation is the same as the initial shape
	{
		squareBoard[2][0][0]=true;
		squareBoard[2][1][0]=true;
		squareBoard[2][1][1]=true;
		squareBoard[2][2][1]=true;
		
	}
	public void rotate3() //same as rotate 1
	{
		squareBoard[3][0][1]=true;
		squareBoard[3][0][2]=true;
		squareBoard[3][1][0]=true;
		squareBoard[3][1][1]=true;
		
	}
	public void rotate4() //same as original shape
	{
		squareBoard[0][0][0]=true;
		squareBoard[0][1][0]=true;
		squareBoard[0][1][1]=true;
		squareBoard[0][2][1]=true;
		
	} **/
	
	
	
	
	
	
	

}
