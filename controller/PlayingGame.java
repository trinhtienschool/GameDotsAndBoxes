package controller;



import model.IGame;
import view.NumOfPlayerPanel;

import view.IView;

/**
 * PlayingGame class
 * @author Trinh Quang Tien
 *
 */
public class PlayingGame implements IButtonAction{

	@Override
	public void handleButton(IController controller, IGame game, IView frame,
			String action) {

		if(action.equalsIgnoreCase("home")) {
			controller.setHandlingButton(new NumOfPlayer());
			frame.setPanel(new NumOfPlayerPanel(controller));
			game.setSettingButton();
		
		}else if(action.equalsIgnoreCase("replay")) {
			game.setReplayButton();
		}

	}
}
