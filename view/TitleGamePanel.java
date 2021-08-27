package view;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.util.Observable;
import javax.swing.JPanel;

/**
 * TitleGamePanel class
 * @author Trinh Quang Tien
 *
 */
public class TitleGamePanel extends PanelAbstract{
	public static final Font FONTBOLD=new Font("Tahoma", Font.BOLD, 50);
	private String title;
	public TitleGamePanel(String title) {
		setBackground(TITLECOLOR_BACKGROUND);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.title=title;
		add(createContainPanel(title));
				
	}
	public JPanel createContainPanel(String title) {
		JPanel panel=new JPanel(new GridLayout(0, 1, 5, 5));
		panel.setBackground(TITLECOLOR_BACKGROUND);
		panel.add(createEmptyLabel());
		panel.add(createLable(title,FONTBOLD));
		panel.add(createEmptyLabel());
		return panel;
		
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		throw new UnsupportedOperationException();
		
	}
	
}
