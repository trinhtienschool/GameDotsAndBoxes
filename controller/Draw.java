package controller;

import model.IGame;
import view.IView;
/**
 * Draw class
 * @author Trinh Quang Tien
 *
 */
public class Draw implements IDrawAction{

	@Override
	public void draw(int x, int y, IController controller, IGame game, IView frame) {
		game.draw(x, y);
	}

	@Override
	public boolean checkCanFill(int x, int y, IController controller, IGame game,
			IView frame) {
		return game.checkCanFill(x, y);
		
	}
	
}
