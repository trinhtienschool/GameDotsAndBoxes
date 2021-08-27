package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.IController;
import model.*;
import model.Game;
import model.IGame;
import model.player.Player;

/**
 * View class
 * @author Trinh Quang Tien
 *
 */
public class View extends JFrame implements IView {
	
	private JPanel contentPane;
	private PanelAbstract panel;
	/**
	 * Create the frame.
	 */
	public View(IGame game,IController controller,PanelAbstract panelAbstract) {
		this.panel=panelAbstract;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(panel);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		this.setVisible(true);
	}
	public void setFullScreen(boolean undecorated) {
		this.dispose();
		this.setUndecorated(undecorated);
		this.setVisible(true);
	}
	public void setPanel(PanelAbstract pn) {
		contentPane.removeAll();
		this.panel=pn;
		contentPane.add(panel);
		contentPane.revalidate();
		contentPane.repaint();
	}
	public PanelAbstract getPanel() {
		return this.panel;
	}
	

	
		
	

}
