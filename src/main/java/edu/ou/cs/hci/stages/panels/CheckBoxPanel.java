package edu.ou.cs.hci.stages.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import edu.ou.cs.hci.stages.frames.ClubPenguin;

public class CheckBoxPanel extends JPanel implements ItemListener{

	private static final long serialVersionUID = 3490031711433228434L;

	private ArrayList<String> items;
	private static ArrayList<JCheckBox> tagBoxes = new ArrayList<JCheckBox>();
	private static ArrayList<JCheckBox> genreBoxes = new ArrayList<JCheckBox>();
	private static int numSelected = 0;

	public CheckBoxPanel(ArrayList<String> items, boolean isTag) {
		this.items = items;
		this.setOpaque(false);

		for(String item : this.items) {

			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

			JCheckBox cbox = new JCheckBox(item);
			cbox.setOpaque(false);
			cbox.addItemListener(this);

			this.add(cbox);
			
			if (isTag)
				tagBoxes.add(cbox);
			else
				genreBoxes.add(cbox);

		}
	}

	public void itemStateChanged(ItemEvent e) {
		
		//if the check box is selected
		if (((JCheckBox)e.getItem()).isSelected()) {
			numSelected++;
		} else {
			numSelected--;
		}
		
		System.out.println(numSelected);
		
		if (numSelected > 0) {
			
			for (GameCard card : ClubPenguin.getGames()) {
				
				card.setVisible(true);
				
				for (JCheckBox box : tagBoxes) {
					if (box.isSelected() && !card.getGame().getTags().contains(box.getText())) {
						card.setVisible(false);
					}
				}
				
				for (JCheckBox box : genreBoxes) {
					if (box.isSelected() && !card.getGame().getGenres().contains(box.getText())) {
						card.setVisible(false);
					}
				}
				
			}
		}
		else
		{
			for (GameCard card : ClubPenguin.getGames()) 
				card.setVisible(true);
			
		}

	}


}