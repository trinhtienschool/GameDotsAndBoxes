package model;

import java.beans.PropertyChangeListener;

import model.player.Player;
import model.player.PlayerList;

/**
 * Game interface
 * @author Trinh Quang Tien
 *
 */
public interface IGame {
	public void setSettingButton();
	public void setReplayButton();
	public PlayerList getPlayers();
	public int getHeightOfACell();
	public int getWidthOfACell();
	public void setNumOfPlayer(int index);
	public void setBoard(int x,int y);
	public boolean checkStop();
	public Board getBoard();
	public void setGameForPlayer();
	public boolean checkCanFill(int x,int y);
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public Player getPlayerPlaying();
	public void startAIPlayer();
	public void draw(int x,int y);
	public String getPlayerWinName();
}
