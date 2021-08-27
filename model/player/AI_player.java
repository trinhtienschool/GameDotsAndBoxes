package model.player;

import java.awt.Color;
import model.Board;
import model.Cell;
import model.Line;
import model.ai.Alpha_beta_pruning;

public class AI_player extends Player implements Runnable{
	private Thread thread;
	private Player opp;
	private Alpha_beta_pruning alpha_beta;
	public AI_player(String name, Color color) {
		super(name, color);
		this.alpha_beta = new Alpha_beta_pruning(board);
		thread=new Thread(this);
		
	}
	public void algorithm(){
		
		//lay ra best line
		Line lineGo = alpha_beta.getNextMove(this, opp);
		draw(lineGo);
		
	}

	@Override
	public void draw(Line line) {
		
		//set newDraw cho tat ca cac line
		board.deleteAllNewLine();
		int rowLine = line.getRow();
		int colLine = line.getCol();
		line.draw(this);
		boolean isDrawCell=false;
		for(int row = rowLine-1;row<=rowLine+1;row++) {
			for(int col= colLine-1;col<=colLine+1;col++) {
				if(row<0 || row>=board.getRows())
					continue;
				if(col<0 || col>=board.getCols())
					continue;
				Cell cell = board.getCell(row,col);
				if(cell.draw(this)==Cell.CELLDREW) {
					isDrawCell=true;
				}
			}
		}
		board.firePropertyChange(isDrawCell,true);
		
	}

	@Override
	public int compareTo(Player o) {
		if(this.score<o.getScore()) {
			return 1;
		}else if(this.score>o.getScore()) {
			return -1;
		}
		return 0;
	}
	public void start() {
		thread.start();
	}
	public void sleep(long time) {
		try {
			thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true) {
			if(this.priority) 
				algorithm();
			sleep(1000);
		}
		
	}
	/**
	 * @return the opp
	 */
	public Player getOpp() {
		return opp;
	}
	/**
	 * @param opp the opp to set
	 */
	public void setOpp(Player opp) {
		this.opp = opp;
	}
	@Override
	public void draw(int x, int y) {
		
	}
	@Override
	public void setBoard(Board board) {
		super.setBoard(board);
		this.board=board;
		this.alpha_beta.setBoard(board);
	}
	
	
}
