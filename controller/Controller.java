package controller;

import model.Board;
import model.IGame;
import view.NumOfPlayerPanel;
import view.View;
import view.IView;
/**
 * Controller class
 * @author Trinh Quang Tien
 *
 */
public class Controller implements IController{
	private IGame game;
	private IView frame;
	private IButtonAction buttonAction;
	private IDrawAction draw;
	
	public Controller(IGame game,IButtonAction handlingButtonAction,IDrawAction draw) {
		this.game=game;
		this.buttonAction=handlingButtonAction;
		this.draw=draw;
		frame=new View(game, this, new NumOfPlayerPanel(this));
	}
	@Override
	public void handleButton(String action) {
		buttonAction.handleButton(this, game, frame, action);
		
	}

	@Override
	public void handleDraw(int x,int y) {
		draw.draw(x, y, this, game, frame);
		
		
	}
	public boolean checkCanFill(int x,int y) {
		return draw.checkCanFill(x, y, this, game, frame);
	}

	@Override
	public void setHandlingButton(IButtonAction action) {
		this.buttonAction=action;
		
	}

	@Override
	public void setDrawing(IDrawAction drawAction) {
		this.draw=drawAction;
		
	}
	@Override
	public Board getBoard() {
		return game.getBoard();
	}
	



	

}
