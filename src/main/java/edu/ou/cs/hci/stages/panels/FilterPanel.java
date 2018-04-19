package edu.ou.cs.hci.stages.panels;

import java.awt.Color;
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
	private ArrayList<String> isSelected;
	
	public FilterPanel(ArrayList<String> filters, String type) {
		
		this.filters = filters;
		this.setOpaque(true);
		isSelected = new ArrayList<String>();
		//Add example text to checkbox panel
		JLabel glabel;
		glabel = new JLabel(type);
		this.add(glabel);
		
		for(String filter : this.filters) {
			
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			JCheckBox cbox = new JCheckBox(filter);
			cbox.addItemListener(this);
			
			this.add(cbox);	
		}		
	}
	
	public void itemStateChanged(ItemEvent e) {
	 
	   if(((JCheckBox) e.getItem()).isSelected() == true)
	   {
		   String t = ((JCheckBox)e.getItem()).getText().toString();
		   System.out.println(t + " Is Checked");
		   isSelected.add(((JCheckBox)e.getItem()).getText().toString());
	   }
	   if(((JCheckBox) e.getItem()).isSelected() == false)
	   {
		   System.out.println(((JCheckBox)e.getItem()).getText() + " Is unChecked");
		   isSelected.remove(((JCheckBox)e.getItem()).getText().toString());
	   }
	}

	public ArrayList<String> getSelected() {
		return isSelected;
		
	}

}