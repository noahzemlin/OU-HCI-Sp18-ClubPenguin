package edu.ou.cs.hci.stages.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import edu.ou.cs.hci.resources.Resources;

public class RemoveGameAction extends AbstractAction {

	private static final long serialVersionUID = 730156257710000536L;
	
	public RemoveGameAction() {
		super("Remove Game");
		putValue(Action.LARGE_ICON_KEY, new ImageIcon(Resources.getImage("icons/delete_game.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Remove Game: Removing a Game");
	}

}
