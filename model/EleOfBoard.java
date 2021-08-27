package model;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;
import java.util.Observer;
import model.player.Player;

/**
 * EleOfBoard class
 * @author Trinh Quang Tien
 *
 */
public abstract class EleOfBoard {
	protected boolean isDrew;
	protected Color color;
	protected Player player;
	public static Point createPoint(int x, int y) {
		return new Point(x, y);
	}
	public void cleanData() {
		isDrew=false;
		color=null;
		player=null;
	}
	public void setPlayer(Player player) {
		this.player=player;
	}
	public void setColor(Color color) {
		this.color=color;
	}
	public Color getColor() {
		return this.color;
	}
	public void setIsDrew(boolean isDrew) {
		this.isDrew=isDrew;
	}
	public boolean getIsDrew() {
		return isDrew;
	}
	public Player getPlayer() {
		return player;
	}
	/**
	 * @param isDrew the isDrew to set
	 */
	public void setDrew(boolean isDrew) {
		this.isDrew = isDrew;
	}
		
	
}
