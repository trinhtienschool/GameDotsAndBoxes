package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import model.player.AI_player;
import model.player.Player;

/**
 * Cell class
 * 
 * @author Trinh Quang Tien
 *
 */
public class Cell extends EleOfBoard{
	private Line east;
	private Line west;
	private Line south;
	private Line north;
	public static final int CELLDREW=0;
	public static final int LINEDREW=1;
	public static final int NODREW=2;
	public static final int NORTH=3;
	public static final int SOUTH=4;
	public static final int EAST=5;
	public static final int WEST=6;
	public static final int NONE=7;
	
	
	public Cell() {
	}
	public Cell(Line east, Line west, Line south, Line north) {
		this.east = east;
		this.west = west;
		this.north = north;
		this.south = south;
		this.player=null;
	}
	public Point getLeftTopPoint() {
		return north.getPointA();

	}
	public boolean isFullLine(boolean isNoPlayerFill) {
		if (isNoPlayerFill) {
			if ((east.isDrew() && west.isDrew() && north.isDrew() && south.isDrew()) && player == null) {
				return true;
			}
		} else {
			if ((east.isDrew() && west.isDrew() && north.isDrew() && south.isDrew()))
				return true;
		}
		return false;
	}

	/**
	 * draw dash if this cell has 2 point combine to a dash then draw this dash
	 * 
	 * @param a
	 * @param b
	 */
	public int drawLine(int x,int y,Player player) {
		boolean e=east.draw(x, y, false,player);
		boolean w=west.draw(x, y, false,player);
		boolean n=north.draw(x, y, true,player);
		boolean s=south.draw(x, y, true,player);
		if(e || w || n || s) {
			if(isFullLine(true)) {
				isDrew=true;
				setPlayer(player);
				player.addOneScore();
				this.color=player.getColor();
				return CELLDREW;
			}
			return LINEDREW;
		}
		return NODREW;
	}
	public int draw(Player player) {
		if(isFullLine(true)) {
			isDrew=true;
			setPlayer(player);
			player.addOneScore();
			this.color=player.getColor();
			return CELLDREW;
		}
		return NODREW;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("East: " + east.toString() + "\n");
		str.append("West: " + west.toString() + "\n");
		str.append("South: " + south.toString() + "\n");
		str.append("North: " + north.toString() + "\n");
		str.append(player + "\n");
		return str.toString();
	}
	public boolean checkCanFill(int x,int y) {
		return north.isThisLine(x, y, true)||south.isThisLine(x, y, true) || east.isThisLine(x, y, false) ||west.isThisLine(x, y, false);
	}
	public void cleanData() {
		east.cleanData();
		west.cleanData();
		north.cleanData();
		south.cleanData();
		
		super.cleanData();
		
	}
	public int getNumOfLineFill() {
		int count=0;
		if(east.getIsDrew()) count++;
		if(west.getIsDrew()) count++;
		if(south.getIsDrew()) count++;
		if(north.getIsDrew()) count++;
		return count;
	}
	public void unDraw() {
		if(!this.isFullLine(false)) {
			if(this.player !=null)
				this.player.subtractOneScore();
			super.cleanData();
		}
	}
	public boolean isItSelf(Cell cell) {
		return this.equals(cell);
	}
	public List<Line> getEmptyLines() {
		List<Line> lines=new ArrayList<Line>();
		if(!west.getIsDrew()) lines.add(west);
		if(!north.getIsDrew()) lines.add(north);
		if(!east.getIsDrew()) lines.add(east);
		if(!south.getIsDrew()) lines.add(south);
		return lines;
		
	}
	public void deleteAllNewLine() {
		west.setNew(false);
		east.setNew(false);
		north.setNew(false);
		south.setNew(false);
	}
	/**
	 * @return the east
	 */
	public Line getEast() {
		return east;
	}

	/**
	 * @param east the east to set
	 */
	public void setEast(Line east) {
		this.east = east;
	}

	/**
	 * @return the west
	 */
	public Line getWest() {
		return west;
	}

	/**
	 * @param west the west to set
	 */
	public void setWest(Line west) {
		this.west = west;
	}

	/**
	 * @return the south
	 */
	public Line getSouth() {
		return south;
	}

	/**
	 * @param south the south to set
	 */
	public void setSouth(Line south) {
		this.south = south;
	}

	/**
	 * @return the north
	 */
	public Line getNorth() {
		return north;
	}

	/**
	 * @param north the north to set
	 */
	public void setNorth(Line north) {
		this.north = north;
	}

}
