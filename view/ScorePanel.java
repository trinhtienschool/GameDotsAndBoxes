package view;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.util.Observable;

import javax.swing.BorderFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Game;
import model.IGame;
import model.player.Player;
import model.player.PlayerList;

/**
 * ScorePanel
 * @author Trinh Quang Tien
 *
 */
public class ScorePanel extends PanelAbstract{
	private PlayerList players;
	private IGame game;
	public ScorePanel(IGame game) {
		setLayout(new GridLayout(0, 1, 10, 10));
		this.game=game;
		setBackground(TITLECOLOR_BACKGROUND);
		this.players=game.getPlayers();
		game.addPropertyChangeListener(this);
		createPlayerListLabel();
	}
	public void createPlayerListLabel() {
		for( int i=0;i<players.getSize();i++) {
			Player player=players.getPlayer(i);
			JLabel lb=createLabel(player.getName()+" :  "+player.getScore(), player.getColor());
			if(player.getPriority()) {
				lb.setBorder(BorderFactory.createLineBorder(player.getColor(), 5, true));
			}
			add(lb);

		}

	}
	public JLabel createLabel(String title,Color color) {
		JLabel lb=new JLabel(title);
		lb.setForeground(color);
		lb.setBackground(TITLECOLOR_BACKGROUND);
		lb.setFont(FONTPLAIN);
		return lb;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equalsIgnoreCase("GameChange") && (Boolean)evt.getNewValue()) {
			this.removeAll();
			createPlayerListLabel();
			revalidate();
			repaint();
		}
		if(game.checkStop()) {
			JLabel lb=createLable(game.getPlayerWinName(), FONTPLAIN);
			lb.setForeground(Color.red);
			JOptionPane.showMessageDialog(null, lb);
			repaint();
		}
		
	}
}
