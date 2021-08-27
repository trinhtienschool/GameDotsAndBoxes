package model.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import model.Cell;
import model.Line;
/**
 * NormalPlayer class
 * @author Trinh Quang Tien
 *
 */
public class NormalPlayer extends Player{
	public NormalPlayer(String name,Color color) {
		super(name,color);
		
	}

	@Override
	public void draw(int x,int y) {
		board.deleteAllNewLine();
		int cellDrew=Cell.NODREW;
		int lineDrew=Cell.NODREW;
		for(int row=0;row<board.getRows();row++) {
			for(int col=0;col<board.getCols();col++) {
				Cell cell=board.getCell(row, col);
				int value=cell.drawLine(x, y, this);
				if(value == Cell.CELLDREW) {
					cellDrew=Cell.CELLDREW;
				}else if(value == Cell.LINEDREW) {
					lineDrew=Cell.LINEDREW;
				}
			}
		}
		board.firePropertyChange(cellDrew==Cell.CELLDREW, lineDrew==Cell.LINEDREW);
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

	@Override
	public void draw(Line line) {
			
	}

	
	
}
