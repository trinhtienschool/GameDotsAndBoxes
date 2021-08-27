package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Observable;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import controller.IController;

/**
 * NumOfPlayerPanel class
 * @author Trinh Quang Tien
 *
 */


public class NumOfPlayerPanel extends PanelAbstract implements ActionListener{
	private Component comp;
	private JButton button;
	private JComboBox<String> comboBox;
	private IController controller;
	public NumOfPlayerPanel(IController controller) {
		comp=createComponentPanel();
		this.controller=controller;
		ContentPanel content=new ContentPanel("HOW MANY PLAYERS ?",comp,createModifiedButton());
		setLayout(new BorderLayout(5, 5));
		add(new TitleGamePanel("DOTS AND BOXES"),BorderLayout.NORTH);
		add(content,BorderLayout.CENTER);
	}
	public JPanel createComponentPanel() {
		JPanel pn=new JPanel(new GridLayout(0, 1, 10, 10));
		pn.setBackground(CONTENTCOLOR);
		pn.add(createComboBoxPanel());
		pn.add(createEmptyLabel());
		return pn;
		
		
	}

	public JPanel createComboBoxPanel() {
		JPanel pn=new JPanel();
		pn.setBackground(CONTENTCOLOR);
		GridBagLayout gbl=new GridBagLayout();
		pn.setLayout(gbl);
		
		comboBox = new JComboBox<String>();
		addComboBoxItem();
		comboBox.setForeground(FOREGROUND);
		comboBox.setBackground(BACKGROUND_BUTTON);
		
		comboBox.setFont(FONT_BUTTON);
		GridBagConstraints gbcComboBox=new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 90, 30);

		pn.add(comboBox, gbcComboBox);

		return pn;
		
	}
	public JPanel createModifiedButton() {
		JPanel pn=new JPanel();
		pn.setBackground(CONTENTCOLOR);
		button=createButton("PLAY",BACKGROUND_BUTTON, this);
		pn.add(button);
		return pn;
	}
	public void addComboBoxItem() {
		comboBox.addItem("   AI");
		for(int i=2;i<7;i++) {
			comboBox.addItem("   "+i+" PLAYERS");
		}
	}
	public String getSelectedItem() {
		return (String)comboBox.getSelectedItem();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			this.controller.handleButton("");
			
		}
		
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		throw new UnsupportedOperationException();
		
	}
}
