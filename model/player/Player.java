package model.player;

import java.awt.Color;

import model.*;

/**
 * Player class
 * @author Trinh Quang Tien
 *
 */
public abstract class Player implements Comparable<Player>{
	protected String name;
	protected int score;
	protected Color color;
	protected boolean priority;
	protected Board board;
	public Player(String name,Color color) {
		this.name=name;
		this.color=color;
		this.score=0;
		this.priority=false;
	}
	public void substractOneScore() {
		this.score--;
	}
	public abstract void draw(int x,int y);
	public abstract void draw(Line line);
	public void setBoard(Board board) {
		this.board=board;
	}
	public void setPriority() {
		this.priority=true;
	}
	public void returnPriority() {
		this.priority=false;
	}
	public boolean getPriority() {
		return this.priority;
	}
	public void deleteScore() {
		this.score=0;
	}

	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int sumOfCells) {
		this.score = sumOfCells;
	}
	public void addOneScore() {
		score++;
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", sumOfCells=" + score + ", color=" + color.toString() + "]";
	}
	public void subtractOneScore() {
		score--;
		
	}
	
}
