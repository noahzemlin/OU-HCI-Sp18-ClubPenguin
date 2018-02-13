package edu.ou.cs.hci.stages.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

//FilterPanel class will show a list of filterss
public class FilterPanel extends JPanel implements ItemListener{
	
	private static final long serialVersionUID = 3490031711433228434L;
	
	private ArrayList<String> filters;
	
	public FilterPanel(ArrayList<String> filters) {
		
		this.filters = filters;
		
		//Add example text to checkbox panel
		JLabel label;
		label = new JLabel("Tags");
		this.add(label);
		
		for(String filter : this.filters) {
			
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			JCheckBox cbox = new JCheckBox(filter);
			cbox.addItemListener(this);
			
			this.add(cbox);
			
		}
		
	}
	
	public void itemStateChanged(ItemEvent e) {
	    System.out.println(((JCheckBox)e.getItem()).getText() + " " + (((JCheckBox)e.getItem()).isSelected() ? "Checked" : "Unchecked"));
	}
	
}