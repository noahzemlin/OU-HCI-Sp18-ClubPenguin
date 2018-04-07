package edu.ou.cs.hci.stages.nav;


import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JToolBar;

import edu.ou.cs.hci.stages.actions.*;

public class ClubPenguinToolbar extends JToolBar{
	
	private static final long serialVersionUID = -1394430847381678582L;
	
	public ClubPenguinToolbar() {
		
		this.setFloatable(false);
		JButton button;
		
		
		button = new JButton(new AddGameAction());
		button.setBackground(new Color(160,242,255));
		this.add(button);
		button = new JButton(new RemoveGameAction());
		button.setBackground(new Color(160,242,255));
		this.add(button);
		button = new JButton(new EditGameAction());
		button.setBackground(new Color(160,242,255));
		this.add(button);
		this.addSeparator();
		button = new JButton(new SortAction());
		button.setBackground(new Color(160,242,255));
		this.add(button);
		button = new JButton(new SearchAction());
		button.setBackground(new Color(160,242,255));
		this.add(button);

		
	}

}
