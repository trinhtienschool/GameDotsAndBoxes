package controller;


import model.IGame;
import view.NumOfPlayerPanel;
import view.IView;
import view.PanelAbstract;
import view.SizePanel;
/**
 * NumOfPlayer class
 * @author Trinh Quang Tien
 *
 */
public class NumOfPlayer implements IButtonAction{

	@Override
	public void handleButton(IController controller, IGame game, IView frame,
			String action) {
		PanelAbstract pn=frame.getPanel();
		if(pn instanceof NumOfPlayerPanel) {
			controller.setHandlingButton(new Size());
			NumOfPlayerPanel sizePanel=(NumOfPlayerPanel)pn;
			
//			Xu li lay so nguoi choi
			int numOfPlayers=-1;
			String selectedItem=sizePanel.getSelectedItem();
			selectedItem=selectedItem.trim();
			if(selectedItem.equalsIgnoreCase("AI"))
				numOfPlayers=0;
			else if(Character.isDigit(selectedItem.charAt(0))) {
				numOfPlayers=Integer.parseInt(String.valueOf(selectedItem.charAt(0)));
			}
			game.setNumOfPlayer(numOfPlayers);
			
			frame.setPanel(new SizePanel(controller));
		}
		
		
	}
	
}
