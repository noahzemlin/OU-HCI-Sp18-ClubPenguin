package edu.ou.cs.hci.stages.actions;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import edu.ou.cs.hci.resources.Resources;
import edu.ou.cs.hci.stages.frames.ClubPenguin;

public class SortAction extends AbstractAction {

	private static final long serialVersionUID = 6444203253032935645L;

	public SortAction() {
		super("Sort");
		putValue(Action.LARGE_ICON_KEY, new ImageIcon(Resources.getImage("icons/sort.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ClubPenguin.sortGames();
	}

}
