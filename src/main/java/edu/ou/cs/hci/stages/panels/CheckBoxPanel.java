package edu.ou.cs.hci.stages.panels;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CheckBoxPanel extends JPanel implements ItemListener{
	
	private static final long serialVersionUID = 3490031711433228434L;
	
	private ArrayList<String> items;
	private ArrayList<JCheckBox> cboxes = new ArrayList<JCheckBox>();
	
	public CheckBoxPanel(ArrayList<String> items) {
		this.items = items;
		this.setOpaque(false);
	//	this.setBackground(hex2Rgb("#a7d3ff"));
		for(String item : this.items) {
			
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			JCheckBox cbox = new JCheckBox(item);
			cbox.setOpaque(false);
			//cbox.setHorizontalTextPosition(SwingConstants.LEFT); //Checkboxes on the right
			cbox.addItemListener(this);
			
			this.add(cbox);
			cboxes.add(cbox);
			
		}
	}
	
	public void itemStateChanged(ItemEvent e) {
	    System.out.println(((JCheckBox)e.getItem()).getText() + " set to " + (((JCheckBox)e.getItem()).isSelected() ? "Checked" : "Unchecked"));
	}

	//Prints out the currently selected boxes comma separated.
	public String getSelectedAsText() {
		String output = "";
		int i=0;
		for (; i<cboxes.size()-1; i++) {
			if (cboxes.get(i).isSelected())
				output += cboxes.get(i).getText() + ", ";
		}
		if (cboxes.get(i).isSelected())
			output += cboxes.get(i).getText();
		return output;
	}
	
	public static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
	
}