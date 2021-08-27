package controller;

import model.Game;
import model.IGame;
import model.player.AI_player;
import model.player.Player;
import view.IView;
import view.PanelAbstract;
import view.PlayGamePanel;
import view.SizePanel;
/**
 * Size class
 * @author Trinh Quang Tien
 *
 */
public class Size implements IButtonAction {

	@Override
	public void handleButton(IController controller, IGame game, IView frame,
			String action) {
		PanelAbstract pn=frame.getPanel();
		if(pn instanceof SizePanel) {
			controller.setDrawing(new Draw());
			controller.setHandlingButton(new PlayingGame());
			SizePanel sizePanel=(SizePanel)pn;
			int rows=sizePanel.getRows();
			int cols=sizePanel.getCols();
			game.setBoard(rows, cols);
			game.setGameForPlayer();
			game.startAIPlayer();
			frame.setPanel(new PlayGamePanel(game,controller));
		}
		

	}

}
