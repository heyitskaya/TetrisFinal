


public abstract class TetrisBlock //what all the different blocks have in common
{
	public boolean[][][] squareBoard;
	public int row=4; /** these will be the same for every subclass of this
	abstract class**/
	public int column=4;
	public int r=0;
	private int totalKinds=4;
	
	public TetrisBlock() /** the constructor**/
	{
		squareBoard=new boolean[totalKinds][row][column]; //everything automatically gets boolean value false
		
		
	}
	public void shape() /**this will define the shape of the block with boolean 
	values placed in certain indexis of the squareBoard**/
	{
		
	}
	
	



}
