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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.IController;

/**
 * SizePanel class
 * @author Trinh Quang Tien
 *
 */
public class SizePanel extends PanelAbstract implements ActionListener,ChangeListener{
	private JLabel rowLabel;
	private JLabel colLabel;
	private JSlider rowSlider;
	private JSlider colSlider;
	private JButton button;
	private Component comp;
	private IController controller;
	public SizePanel(IController controller) {
		comp=createComponentPanel();
		this.controller=controller;
		ContentPanel content=new ContentPanel("HOW MANY BOXES?",comp,createModifiedButton());
		setLayout(new BorderLayout(5, 5));
		add(new TitleGamePanel("DOTS AND BOXES"),BorderLayout.NORTH);
		add(content,BorderLayout.CENTER);
	}
	public JPanel createComponentPanel() {
		JPanel pn=new JPanel(new GridLayout(0, 1, 10, 10));
		pn.setBackground(CONTENTCOLOR);

		rowSlider=createSlider();
		colSlider=createSlider();

		rowLabel=createLable(rowSlider.getValue()+"", FONTPLAIN);
		colLabel=createLable(colSlider.getValue()+"", FONTPLAIN);
		pn.add(createSliderPanel("Rows", rowSlider, rowLabel));
		pn.add(createSliderPanel("Cols  ", colSlider, colLabel));

		return pn;


	}
	public JPanel createSliderPanel(String title,JSlider slider,JLabel rowLabel) {
		JPanel pn=new JPanel();
		pn.setBackground(CONTENTCOLOR);
		GridBagLayout gbl=new GridBagLayout();
		pn.setLayout(gbl);

		JLabel lb=createLable(title, FONTBOLD);
		GridBagConstraints gbcLabel=new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 30, 30);
		pn.add(lb, gbcLabel);

		GridBagConstraints gbcSlider=new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 18, 5, 5), 90, 30);
		pn.add(slider, gbcSlider);

		GridBagConstraints gbcValueSlider=new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), 30, 30);

		pn.add(rowLabel,gbcValueSlider);

		return pn;
	}
	public JSlider createSlider() {
		JSlider slider=new JSlider();
		slider.setValue(3);
		slider.setMaximum(10);
		slider.setMinimum(2);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(2);
		slider.addChangeListener(this);
		slider.setBackground(CONTENTCOLOR);
		return slider;
	}
	public JPanel createModifiedButton() {
		JPanel pn=new JPanel();
		pn.setBackground(CONTENTCOLOR);
		button=createButton("PLAY", BACKGROUND_BUTTON, this);
		pn.add(button);
		return pn;
	}
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource()==button) {
			this.controller.handleButton("");;
		}

	}
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider=(JSlider) e.getSource();
		rowSlider.getValue();
		colSlider.getValue();
			if(slider==rowSlider) {
				rowLabel.setText(rowSlider.getValue()+"");
			}else if(slider==colSlider) {
				colLabel.setText(colSlider.getValue()+"");
			}
		
		this.repaint();

	}
	public int getRows() {
		return Integer.parseInt(this.rowLabel.getText());
	}
	public int getCols() {
		return Integer.parseInt(this.colLabel.getText());
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		throw new UnsupportedOperationException();
		
	}
}
