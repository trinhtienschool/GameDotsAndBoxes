package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import controller.IController;
import model.Game;
import model.IGame;

/**
 * PlayGamePanel
 * @author Trinh Quang Tien
 *
 */
public class PlayGamePanel extends PanelAbstract implements ActionListener{
	
	private JSplitPane splitPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel scorePanel;
	private JButton settingButton;
	private JButton replayButton;
	private IController controller;
	private IGame game;
	public PlayGamePanel(IGame game,IController controller) {
		setLayout(new BorderLayout());
		this.controller=controller;
		this.game=game;
		splitPanel=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		leftPanel=new GamePanel(game,controller, game.getHeightOfACell(), game.getWidthOfACell());
		leftPanel.setBackground(CONTENTCOLOR);
		
		rightPanel=new JPanel(new BorderLayout(5,5));
		rightPanel.add(createRightHeaderPanel(),BorderLayout.NORTH);
		
		JPanel containScorePanel=new JPanel();
		containScorePanel.setBackground(TITLECOLOR_BACKGROUND);
		scorePanel=new ScorePanel(game);
		containScorePanel.add(scorePanel);
		rightPanel.add(containScorePanel);
		
		setMinimumSizeScreen();
		
		
		splitPanel.setLeftComponent(leftPanel);
		splitPanel.setRightComponent(rightPanel);
		add(splitPanel,BorderLayout.CENTER);

		
	}
	public void setMinimumSizeScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int leftSpace=(int)(4*width/5);
		int rightSpace=(int)(width/5);
		int height = (int)screenSize.getHeight();
		
		rightPanel.setMinimumSize(new Dimension(rightSpace, height));
		leftPanel.setMinimumSize(new Dimension(leftSpace,height));
		splitPanel.setDividerLocation(leftSpace);
	}
	public JPanel createRightHeaderPanel() {
		JPanel borderPanel=new JPanel(new BorderLayout());
		borderPanel.setBackground(TITLECOLOR_BACKGROUND);
		borderPanel.add(new TitleGamePanel("PLAYING"),BorderLayout.NORTH);
		
		//create control panel
		JPanel controlPanel=new JPanel(new GridLayout(1, 0,10,10));
		controlPanel.setBackground(TITLECOLOR_BACKGROUND);
		settingButton=createButton("/img/settings_64.png", "Settting");
		replayButton=createButton("/img/replay_hoi1.png", "Replay");
			
		controlPanel.add(settingButton);
		controlPanel.add(replayButton);
		borderPanel.add(controlPanel,BorderLayout.CENTER);
		
		return borderPanel;

	}
	
	public JButton createButton(String img,String toolTips) {
		JButton bt=createButton("", TITLECOLOR_BACKGROUND, this);
		bt.setIcon(new ImageIcon(this.getClass().getResource(img)));
		bt.setToolTipText(toolTips);
		bt.setBorder(BorderFactory.createEmptyBorder());
		return bt ;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==settingButton) {
			controller.handleButton("home");
		}else if(e.getSource()==replayButton) {
			controller.handleButton("replay");
		}
		
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		throw new UnsupportedOperationException();
		
	}
	
	

}
