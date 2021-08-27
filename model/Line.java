package model;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.player.AI_player;
import model.player.Player;

/**
 * Line class has pointA, pointB
 * 
 * @author Trinh Quang Tien
 *
 */
public class Line extends EleOfBoard {
	private Point pointA;
	private Point pointB;
	private boolean isNew;
	private Color newColor;
	private int col;
	private int row;
	private String direction;

	public Line(int col, int row, int widthOfACell, int heightOfACell, String direction) {
		init(col, row, widthOfACell, heightOfACell, direction);
		this.col = col;
		this.row = row;
		this.direction = direction;
	}

	public void init(int col, int row, int widthOfACell, int heightOfACell, String direction) {
		if (direction.equals("east")) {
			this.pointA = createPoint(col, row, widthOfACell, heightOfACell, "tr");
			this.pointB = createPoint(col, row, widthOfACell, heightOfACell, "br");
		} else if (direction.equals("west")) {
			this.pointA = createPoint(col, row, widthOfACell, heightOfACell, "tl");
			this.pointB = createPoint(col, row, widthOfACell, heightOfACell, "bl");
		} else if (direction.equals("north")) {
			this.pointA = createPoint(col, row, widthOfACell, heightOfACell, "tl");
			this.pointB = createPoint(col, row, widthOfACell, heightOfACell, "tr");
		} else if (direction.equals("south")) {
			this.pointA = createPoint(col, row, widthOfACell, heightOfACell, "bl");
			this.pointB = createPoint(col, row, widthOfACell, heightOfACell, "br");
		}
		this.isNew = false;
		this.newColor = Color.black;
		this.isDrew = false;
		this.player = null;
	}

	public Point createPoint(int col, int row, int widthOfACell, int heightOfACell, String direction) {
		return new Point(col, row, widthOfACell, heightOfACell, direction);
	}

	public boolean isThisLine(int x, int y, boolean isHorizontal) {
		if (isHorizontal) {
			if (pointA.getX() - 10 < x && x < pointB.getX() - 10 && pointA.getY() < y && (pointB.getY() + 10) > y)
				return true;
		} else {
			if (pointA.getY() - 10 < y && y < pointB.getY() - 10 && pointA.getX() < x && pointB.getX() + 10 > x)
				return true;
		}
		return false;
	}

	public boolean draw(int x, int y, boolean isHorizontal, Player player) {
		if (!isDrew && isThisLine(x, y, isHorizontal)) {
			this.isNew = true;
			this.setDrew(true, player);
			this.color = player.getColor();
			return true;
		} else if (isNew)
			return true;
		return false;
	}

	public boolean draw(Line line, Player player) {
		if (!isDrew && isItSelf(line)) {
			this.isNew = true;
			this.setDrew(true, player);
			this.color = player.getColor();
			return true;
		} else if (isNew)
			return true;
		return false;
	}

	public void draw(Player player) {
		this.isNew = true;
		this.setDrew(true, player);
		this.color = player.getColor();
	}

	public void unDraw() {
		cleanData();
	}

	public boolean isItSelf(Line line) {
		return this.equals(line);
	}

	@Override
	public String toString() {

		return "[" + this.row + ";" + this.col + "] : " + this.direction + " ; " + this.isDrew + " ; " + this.player;
	}

	/**
	 * @return the pointA
	 */
	public Point getPointA() {
		return pointA;
	}

	/**
	 * @param pointA the pointA to set
	 */
	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public void setPointA(int x, int y) {
		this.pointA = new Point(x, y);
	}

	public void setPointB(int x, int y) {
		this.pointB = new Point(x, y);
	}

	/**
	 * @return the pointB
	 */
	public Point getPointB() {
		return pointB;
	}

	/**
	 * @param pointB the pointB to set
	 */
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	/**
	 * @return the isDrew
	 */
	public boolean isDrew() {
		return isDrew;
	}

	/**
	 * @param isDrew the isDrew to set
	 */
	public void setDrew(boolean isDrew, Player player) {
		if (!this.isDrew) {
			this.isDrew = isDrew;
			this.player = player;
		}
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(model.player.NormalPlayer player) {
		this.player = player;
	}

	public void add(String direction, Point a) {
		if (direction.equalsIgnoreCase("a")) {
			pointA = a;
		} else if (direction.equalsIgnoreCase("b")) {
			pointB = a;
		}
	}

	public void cleanData() {
		this.isNew = false;
		super.cleanData();

	}

	/**
	 * @return the isNew
	 */
	public boolean isNew() {
		return isNew;
	}

	/**
	 * @param isNew the isNew to set
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	/**
	 * @return the newColor
	 */
	public Color getNewColor() {
		return newColor;
	}

	/**
	 * @param newColor the newColor to set
	 */
	public void setNewColor(Color newColor) {
		this.newColor = newColor;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

}
