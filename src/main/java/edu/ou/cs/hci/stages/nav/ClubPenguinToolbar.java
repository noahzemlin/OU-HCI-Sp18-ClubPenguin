package edu.ou.cs.hci.stages.nav;


import javax.swing.JButton;
import javax.swing.JToolBar;

import edu.ou.cs.hci.stages.actions.*;

public class ClubPenguinToolbar extends JToolBar{
	
	private static final long serialVersionUID = -1394430847381678582L;
	
	public ClubPenguinToolbar() {
		
		this.setFloatable(false);
		
		this.add(new JButton(new AddGameAction()));
		this.add(new JButton(new RemoveGameAction()));
		this.add(new JButton(new EditGameAction()));
		this.addSeparator();
		this.add(new JButton(new SortAction()));
		this.add(new JButton(new SearchAction()));
		
	}

}
