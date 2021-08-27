package view;
import model.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.IController;
import model.Game;

/**
 * GamePanel class
 * @author Trinh Quang Tien
 *
 */
public class GamePanel extends PanelAbstract implements MouseMotionListener,MouseListener{
	private Board board;
	private IController controller;
	private int heightOfACell;
	private int widthOfACell;
	private IGame game;
	private Color color=new Color(140, 208, 206);
	public GamePanel(IGame game,IController controller,int heightOfACell,int widthOfACell) {
		this.board=controller.getBoard();
		this.controller=controller;
		this.heightOfACell=heightOfACell;
		this.widthOfACell=widthOfACell;
		this.game=game;
		setBackground(PanelAbstract.CONTENTCOLOR);
		this.addMouseListener(this);
		addMouseMotionListener(this);
		game.addPropertyChangeListener(this);
	}
	
	@Override 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int row=0;row<board.getRows();row++){
			for(int col=0;col<board.getCols();col++) {
				Cell cell=board.getCell(row, col);
				
				if(cell.getPlayer()!=null) {
					Color color=cell.getColor();
					g.setColor(color);
					Point lefTopPoint=cell.getLeftTopPoint();
					g.fillRect(lefTopPoint.getX()+20, lefTopPoint.getY()+20, widthOfACell-30, heightOfACell-30);
				}
				
				drawLine(g, cell.getEast(), 10, heightOfACell);
				drawLine(g, cell.getWest(), 10, heightOfACell);
				drawLine(g, cell.getNorth(), widthOfACell, 10);
				drawLine(g, cell.getSouth(), widthOfACell, 10);
			}
		}
		
	}
	public void drawLine(Graphics g,Line line,int width,int heigh) {
		
		Point a=line.getPointA();
		Point b=line.getPointB();
		Color colorDrawLine=null;
		if(line.isNew()) {
			colorDrawLine=line.getNewColor();
		}else if(line.isDrew()) {
			colorDrawLine=line.getColor();
		}else {
			colorDrawLine=this.color;
		}
		g.setColor(colorDrawLine);
		g.fillRect(a.getX(), a.getY(), width	, heigh);
		g.setColor(Color.white);
		g.fillOval(a.getX()-5, a.getY()-5, 20, 20);
		g.fillOval(b.getX()-5, b.getY()-5, 20, 20);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		 int x=e.getX();
		 int y=e.getY();
		 controller.handleDraw(x, y);
		 
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {

		
	}
	@Override
	public void mouseExited(MouseEvent e) {
	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equalsIgnoreCase("GameChange") && (Boolean)evt.getNewValue()) {
			this.board=controller.getBoard();
			revalidate();
			repaint();
		}

	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
	
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		int x=event.getX();
		int y=event.getY();
		if(controller.checkCanFill(x,y))
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else {
			this.setCursor(Cursor.getDefaultCursor());
		}
		
	}


	
}
