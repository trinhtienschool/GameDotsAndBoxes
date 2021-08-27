package controller;

import model.IGame;
import view.IView;
/**
 * IDrawAction interface
 * @author Trinh Quang Tien
 *
 */
public interface IDrawAction {
	public void draw(int x,int y, IController controller,IGame game,IView frame);
	public boolean checkCanFill(int x,int y, IController controller,IGame game,IView frame);
}
