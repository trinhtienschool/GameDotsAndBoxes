package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;
import java.util.Observer;
import model.player.*;

/**
 * Game class
 * @author Trinh Quang Tien
 *
 */
public class Game implements IGame,PropertyChangeListener{
	private Board board;
	private PlayerList players;
	private boolean continuePlayer;
	private Player playerPlaying;
	private PropertyChangeSupport support;
	public Game() {
		support=new PropertyChangeSupport(this);
		continuePlayer=false;
	};

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}
	/**
	 * @return the support
	 */
	public PropertyChangeSupport getSupport() {
		return support;
	}

	/**
	 * @param support the support to set
	 */
	public void setSupport(PropertyChangeSupport support) {
		this.support = support;
	}

	public void setGameForPlayer() {
		players.setBoard(this);
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equalsIgnoreCase("LineDrew")) {
			takePriorityNext();
		}
		change();
		
	}
	@Override
	public void setNumOfPlayer(int index) {
		players=new PlayerList(index);
		playerPlaying=players.setDefaultPlayerPlaying();
	}
	public void setSettingButton() {
		board=null;
		players=null;
	}
	public Player getAIPlayer() {
		return players.getAIPlayer();
	}
	public void setReplayButton() {
		board.cleanData();
		players.deleteScore();
		playerPlaying=players.setDefaultPlayerPlaying();
		change();
	}
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
	/**
	 * @param board the board to set
	 */
	public void setBoard(int rows, int cols) {
		this.board = new Board(rows, cols,this);
	}
	/**
	 * @return the players
	 */
	public PlayerList getPlayers() {
		return players;
	}
	public void takePriorityNext() {
		playerPlaying=players.takePriorityNext();
	}


	public void setPlayers(int numOfPlayers) {
		players=new PlayerList(numOfPlayers);
	}
	public void setDefaultPlayerPlaying() {
		players.setDefaultPlayerPlaying();
	}

	public void change() {
		support.firePropertyChange("GameChange", null, true);

	}
	/**
	 * @param players the players to set
	 */
	public void setPlayers(PlayerList players) {
		this.players = players;
	}
	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public int getHeightOfACell() {
		return board.getHeightOfACell();

	}

	@Override
	public int getWidthOfACell() {
		return board.getWidthOfACell();
	}

	@Override
	public boolean checkStop() {
		if(this.board.checkStop()) {
			players.deleteAllPriorityPlayer();
			return true;
		}
		return false;

	}
	public String getPlayerWinName() {
		return this.players.getPlayerWinName();
	}
	@Override
	public boolean checkCanFill(int x,int y) {
		return board.checkCanFill(x,y);

	}

	/**
	 * @return the playerPlaying
	 */
	public Player getPlayerPlaying() {
		return playerPlaying;
	}

	/**
	 * @param playerPlaying the playerPlaying to set
	 */
	public void setPlayerPlaying(Player playerPlaying) {
		this.playerPlaying = playerPlaying;
	}

	@Override
	public void startAIPlayer() {
		players.startAIPlayer();

	}

	@Override
	public void draw(int x, int y) {
		this.playerPlaying.draw(x, y);		
	}






}
