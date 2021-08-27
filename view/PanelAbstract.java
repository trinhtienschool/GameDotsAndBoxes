 package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * PanelAbstract class
 * @author Trinh Quang Tien
 *
 */
public abstract class PanelAbstract extends JPanel implements PropertyChangeListener{
	public static final Color FOREGROUND=new Color(255,255,255);
	public static final Color TITLECOLOR_BACKGROUND=new Color(0,255,247);
	public static final Color CONTENTCOLOR=new Color(159,239,252);
	public static final Color BACKGROUND_BUTTON=new Color(11,19,243);
	public static final Font FONT_BUTTON=new Font("Tahoma", Font.BOLD, 30);
	public static final Font FONTBOLD=new Font("Tahoma", Font.BOLD, 30);
	public static final Font FONTPLAIN=new Font("Tahoma", Font.PLAIN, 30);
	public JLabel createEmptyLabel() {
		JLabel lb=new JLabel("");
		return lb;
	}
	public JLabel createLable(String title,Font font) {
		JLabel lb=new JLabel(title);
		lb.setForeground(FOREGROUND);
		lb.setFont(font);
		return lb;
	}
	
	public JButton createButton(String title,Color background,ActionListener action) {
		JButton bt=new JButton(title);
		bt.setFont(FONT_BUTTON);
		bt.setForeground(FOREGROUND);
		bt.setBackground(background);
		bt.addActionListener(action);
		return bt;
	}
	
}
