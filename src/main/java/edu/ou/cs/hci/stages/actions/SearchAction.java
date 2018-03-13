package edu.ou.cs.hci.stages.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import edu.ou.cs.hci.resources.Resources;

public class SearchAction extends AbstractAction {

	private static final long serialVersionUID = 3723940936066892593L;
	
	public SearchAction() {
		super("Search");
		putValue(Action.LARGE_ICON_KEY, new ImageIcon(Resources.getImage("icons/search.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Search: Search for a Game");
	}

}
