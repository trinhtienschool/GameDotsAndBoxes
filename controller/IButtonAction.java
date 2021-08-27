package controller;

import model.IGame;
import view.IView;
/**
 * IButtonAction interface
 * @author Trinh Quang Tien
 *
 */
public interface IButtonAction {
	public void handleButton(IController controller,IGame game,IView frame,String action);
	
}
