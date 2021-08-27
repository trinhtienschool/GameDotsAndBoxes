package model.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * PlayerFactory class
 * @author Trinh Quang Tien
 *
 */
public class PlayerFactory {
	protected List<Color>playerColors;
	protected int colorIndex=0;
	public PlayerFactory() {
		createPlayerColor();
	}
	public Player createPlayer(String name,String typePlayer) {
		Player player=null;
		if(typePlayer.equalsIgnoreCase("normal")) {
			player=new NormalPlayer(name, getColorAt(colorIndex));
		}else if(typePlayer.equalsIgnoreCase("ai")) {
			player=new AI_player(name,getColorAt(colorIndex));
		}
		if(colorIndex==playerColors.size()-1){
			colorIndex=0;
		}else
			colorIndex++;
		return player;
	}
	public void createPlayerColor() {
		playerColors=new ArrayList<Color>();
		playerColors.add(Color.red);
		playerColors.add(Color.blue);
		playerColors.add(Color.yellow);
		playerColors.add(Color.green);
		playerColors.add(new Color(246, 0, 238));
		playerColors.add(new Color(144, 0, 255));
	}
	public Color getColorAt(int index) {
		return playerColors.get(index);
	}
	
}
