package controller;

import model.Board;
/**
 * IController interface
 * @author Trinh Quang Tien
 *
 */
public interface IController {
	public Board getBoard();
	public boolean checkCanFill(int x,int y);
	public void setHandlingButton(IButtonAction action);
	public void setDrawing(IDrawAction drawAction);
	public void handleButton(String action);
	public void handleDraw(int x,int y);
}
