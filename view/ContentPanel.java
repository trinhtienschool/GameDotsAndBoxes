package view;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ContentPanel class
 * @author Trinh Quang Tien
 *
 */
public class ContentPanel extends PanelAbstract{
	
	private JPanel contentPanel;
	private Component comp;
	
	public ContentPanel(String title,Component com,JPanel btPn) {
		this.comp=com;
		setBackground(CONTENTCOLOR);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		add(createBorderLayoutPanel(title,btPn));
			
	}
	public JPanel createBorderLayoutPanel(String title,JPanel btPn) {
		contentPanel=new JPanel(new BorderLayout(10, 10));
		contentPanel.setBackground(CONTENTCOLOR);
		contentPanel.add(createTitleOfThisPanel(title),BorderLayout.NORTH);
		
		contentPanel.add(comp,BorderLayout.CENTER);
		contentPanel.add(btPn,BorderLayout.SOUTH);
		return contentPanel;
		
	}
	public void setComponent(Component comp) {
		this.comp=comp;
	}
	public void addComponent(Component com) {
		contentPanel.add(com,BorderLayout.CENTER);
	}
	public JPanel createTitleOfThisPanel(String title) {
		JPanel panel=new JPanel(new GridLayout(0,1,0,0));
		panel.setBackground(CONTENTCOLOR);
		panel.add(createEmptyLabel());
		panel.add(createTitlePanel(title));
		panel.add(createEmptyLabel());
		return panel;
	}
	private JPanel createTitlePanel(String title) {
		JPanel panel=new JPanel();
		panel.setBackground(CONTENTCOLOR);
		panel.add(createLable(title, FONTBOLD));
		return panel;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		throw new UnsupportedOperationException();
		
	}
	
}
