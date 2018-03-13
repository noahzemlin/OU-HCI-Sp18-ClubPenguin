package edu.ou.cs.hci.stages.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class EditFolderAction extends AbstractAction {

	private static final long serialVersionUID = 2362968592522600186L;

	public EditFolderAction() {
		super("Edit Folder");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Edit Folder: Edit a folder");
	}

}
