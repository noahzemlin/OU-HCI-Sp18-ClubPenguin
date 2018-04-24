package edu.ou.cs.hci.stages.nav;


import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JToolBar;

import edu.ou.cs.hci.stages.actions.*;

public class ClubPenguinToolbar extends JToolBar{
	
	private static final long serialVersionUID = -1394430847381678582L;
	
	public ClubPenguinToolbar() {
		
		this.setBackground(new Color(199, 236, 238));
		
		this.setFloatable(false);
		JButton button;
		
		button = new JButton(new AddGameAction());
		this.add(button);
		button = new JButton(new RemoveGameAction());
		this.add(button);
		this.addSeparator();
		button = new JButton(new SortAction());
		this.add(button);

		
	}

}
