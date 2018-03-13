package edu.ou.cs.hci.stages.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import edu.ou.cs.hci.resources.Resources;

public class AddGameAction extends AbstractAction {

	private static final long serialVersionUID = 6916343421279143814L;
	
	public AddGameAction() {
		super("Add Game");
		putValue(Action.LARGE_ICON_KEY, new ImageIcon(Resources.getImage("icons/add_game.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Add Game: Add a new game");
	}

}
