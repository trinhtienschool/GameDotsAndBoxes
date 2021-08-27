package model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.ai.LineGo;
/**
 * 
 * @author Trinh Quang Tien
 *
 */
import model.player.Player;
public class Board{
	private Cell[][] board;
	private int widthOfACell;
	private int heightOfACell;
	private PropertyChangeSupport support;
	public Board(int rows, int cols,Game game) {
		this.board = new Cell[rows][cols];
		createWidth_HeighOfACell();
		this.support=new PropertyChangeSupport(this);
		support.addPropertyChangeListener(game);
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				Line east=null;
				Line west=null;
				Line north=null;
				Line south=null;
				Cell cell=new Cell();

				east=new Line(col, row, widthOfACell, heightOfACell, "east");
				south=new Line(col, row, widthOfACell, heightOfACell, "south");

				try {
					Cell cellNeighbor=board[row][col-1];
					west=cellNeighbor.getEast();
				}catch (ArrayIndexOutOfBoundsException e) {
					west=new Line(col, row, widthOfACell, heightOfACell, "west");
				}

				try {
					Cell cellNeighbor=board[row-1][col];
					north=cellNeighbor.getSouth();
				}catch (ArrayIndexOutOfBoundsException e) {
					north=new Line(col, row, widthOfACell, heightOfACell, "north");
				}
				cell.setEast(east);
				cell.setWest(west);
				cell.setNorth(north);
				cell.setSouth(south);
				board[row][col]=cell;
			}
		}
	}
	public void  createWidth_HeighOfACell() {
		Dimension fullscreen=Toolkit.getDefaultToolkit().getScreenSize();
		int width=(int)(4*fullscreen.getWidth()/5);
		int heigh=(int)fullscreen.getHeight();
		heightOfACell=(heigh-50)/(getRows()+2);
		widthOfACell=width/(getCols()+2);

	}
	public int getWidthOfACell() {
		return widthOfACell;
	}
	public int getHeightOfACell() {
		return heightOfACell;
	}
	public int getRows() {
		return this.board.length;
	}
	public int getCols() {
		return this.board[0].length;
	}
	public Cell getCell(int i,int j) {
		return board[i][j];
	}
	public boolean checkStop() {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				Cell cell = getCell(row, col);
				if(!cell.getIsDrew())
					return false;
			}
		}
		return true;
	}
	public void deleteAllNewLine() {
		for(int row=0;row<getRows();row++) {
			for(int col=0;col<getCols();col++) {
				Cell cell = getCell(row,col);
				cell.deleteAllNewLine();
			}
		}
	}

	public void firePropertyChange(boolean isDrawCell,boolean isDrawLine) {
		if(isDrawCell) 
			support.firePropertyChange("CellDrew",null,true);
		else if(isDrawLine) 
			support.firePropertyChange("LineDrew",null,true);
	}
	public void cleanData() {
		for(int row=0;row<getRows();row++) {
			for(int col=0;col<getCols();col++) {
				Cell cell=getCell(row, col);
				cell.cleanData();
			}
		}
	}


	public boolean checkCanFill(int x,int y) {
		boolean isCanFill=false;
		for(int row=0;row<getRows();row++) {
			for(int col=0;col<getCols();col++) {
				Cell cell=getCell(row,col);
				isCanFill=cell.checkCanFill(x,y);
				if(isCanFill==true) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Line> getAvailabelLines(){
		List<Line> allLackLine = new ArrayList<Line>();
		Set<Line>set = new HashSet<Line>();
		for( int row = 0; row<getRows(); row++) {
			for(int col = 0;col<getCols(); col++) {
				Cell cell =getCell(row,col);
				set.addAll(cell.getEmptyLines());
			}
		}
		allLackLine.addAll(set);
		Collections.shuffle(allLackLine);
		return allLackLine;
	}

	//lay ra so luong cac cell da ve so num
	public int countCellByNumOfLineFill(int num) {
		int sum=0;
		for(int row=0;row<getRows();row++) {
			for(int col =0;col<getCols();col++) {
				Cell cell = getCell(row,col);
				if(cell.getNumOfLineFill()==num)
					sum++;
			}
		}
		return sum;
	}

	public int getScore(boolean isAiPlayer) {
		int sum =0;
		for(int row =0;row<getRows();row++) {
			for(int col=0;col<getCols();col++) {
				Cell cell = getCell(row,col);
				if(cell.getIsDrew()) {
					if(cell.getPlayer() !=null) {
						if(cell.getPlayer().getName().equalsIgnoreCase("ai") && isAiPlayer)
							sum++;
						else if(!isAiPlayer)
							sum++;
					}
				}
			}
		}
		return sum;
	}
	public void print() {
		for(int i=0;i<getRows();i++) {
			for(int j=0;j<getCols();j++) {
				System.out.println("Cell: "+i+":"+j);
				Cell cell = getCell(i, j);
				System.out.println(cell);
			}
		}
	}

	/**
	 * @return the board
	 */
	public Cell[][] getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Cell[][] board) {
		this.board = board;
	}
	public int size() {
		return board.length*board[0].length;

	}


}
