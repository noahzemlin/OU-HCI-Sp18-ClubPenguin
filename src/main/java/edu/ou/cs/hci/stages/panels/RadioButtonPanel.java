package edu.ou.cs.hci.stages.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButtonPanel extends JPanel implements ItemListener{
	
	private static final long serialVersionUID = 3490031711433228434L;
	
	private ButtonGroup bg = new ButtonGroup();
	private ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
	private ArrayList<String> items;
	
	public RadioButtonPanel(ArrayList<String> items) {
		
		this.items = items;

		for(String item : this.items) {
			
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			JRadioButton rb = new JRadioButton(item);
			rb.addItemListener(this);
			
			bg.add(rb);
			buttons.add(rb);
			this.add(rb);
			
		}
		
		bg.setSelected(((JRadioButton)(bg.getElements().nextElement())).getModel(), true);
	}
	
	public void itemStateChanged(ItemEvent e) {
	    System.out.println(((JRadioButton)e.getItem()).getText() + " " + (((JRadioButton)e.getItem()).isSelected() ? "Checked" : "Unchecked"));
	}

	public String getSelectedAsText() {
		for (JRadioButton button : buttons) {
			if (button.isSelected()) 
				return button.getText();
		}
		return "Nothing selected";
	}
	
}