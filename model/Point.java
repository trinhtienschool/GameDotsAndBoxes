package model;
/**
 * Point class
 * has x,y
 * @author Trinh Quang Tien
 *
 */
public class Point extends EleOfBoard{
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(int col,int row,int widthOfACell,int heightOfACell,String direction) {
		init(col, row, widthOfACell, heightOfACell, direction);
	}
	public void init(int col,int row,int widthOfACell,int heightOfACell,String direction) {
		if(direction.equals("tl")) {
			this.x=(col+1)*widthOfACell;
			this.y=(row+1)*heightOfACell;
		}else if(direction.equals("tr")) {
			this.x=(col+2)*widthOfACell;
			this.y=(row+1)*heightOfACell;
		}else if(direction.equals("br")) {
			this.x=(col+2)*widthOfACell;
			this.y=(row+2)*heightOfACell;
		}else if(direction.equals("bl")) {
			this.x=(col+1)*widthOfACell;
			this.y=(row+2)*heightOfACell;
		}
	}
	@Override
	public String toString() {
		return this.x+":"+this.y;
	}
	
	@Override
	public boolean equals(Object other) {
		Point point=(Point)other;
		return this.x==point.x && this.y==point.y;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}


}
