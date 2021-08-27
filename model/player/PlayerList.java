package model.player;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Board;
import model.IGame;

/**
 * PlayerList class
 * @author Trinh Quang Tien
 *
 */

public class PlayerList {
	private List<Player>players;
	private PlayerFactory playerFact;
	public PlayerList(int numOfPlayers) {
		playerFact=new PlayerFactory();
		players=new ArrayList<Player>();
		initPlayer(numOfPlayers);
		
	}
	public void initPlayer(int numOfPlayers) {
		if(numOfPlayers==0) {
			addPlayer("Player 1", "normal");
			addPlayer("AI", "ai");
			return;
		}
		for(int i=1;i<=numOfPlayers;i++) {
			  addPlayer("Player "+i,"normal");
		}
	}
	public void setBoard(IGame game) {
		for(Player player:players) {
			player.setBoard(game.getBoard());
		}
	}
	public void addPlayer(String name,String type) {
		players.add(playerFact.createPlayer(name, type));
	}
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
	public Player getPlayer(int i) {
		return players.get(i);
	}
	public int getSize() {
		return players.size();
	}
	public void deleteScore() {
		for(Player p:players) {
			p.deleteScore();
		}
	}
	public void deleteAllPriorityPlayer() {
		for(Player player : players) {
			player.returnPriority();
		}
	}
	public Player takePriorityNext() {
		for(int i=0;i<players.size();i++) {
			
			if(players.get(i).getPriority()) {
				if(i==players.size()-1) {
					players.get(i).returnPriority();
					 players.get(0).setPriority();
					 return players.get(0);
				}
				else {
					players.get(i).returnPriority();
					players.get(++i).setPriority();
					return players.get(i);
				}
			}
		}
		return null;
	}
	public AI_player getAIPlayer() {
		for(Player player:players) {
			if(player.getName().equalsIgnoreCase("ai"))
				return (AI_player)player;
		}
		return null;
	}
	
	public Player setDefaultPlayerPlaying() {
		for(Player player:players) {
			player.returnPriority();
		}
		this.players.get(0).setPriority();
		return this.players.get(0);
	}
	@Override
	public String toString() {
		StringBuilder st=new StringBuilder();
		for(Player player:players) {
			st.append(player);
		}
		return st.toString();
	}
	public Player getOpp() {
		for(Player player:players) {
			if(!player.getName().equalsIgnoreCase("ai"))
				return player;
		}
		return null;
	}
	public void startAIPlayer() {
		AI_player ai=getAIPlayer();
		if(ai ==null) return;
		if(getOpp() !=null);
			ai.setOpp(getOpp());
		ai.start();
		
		
	}
	public String getPlayerWinName() {
		String player_win_name="";
		int max = -1;
		for(Player player: players) {
			if(max < player.getScore()) {
				max = player.getScore();
				player_win_name = player.getName()+" WIN";
			}else if(max == player.getScore()) {
				player_win_name="TIE GAME";
			}
		}
		return player_win_name;
	}
	
	
}
