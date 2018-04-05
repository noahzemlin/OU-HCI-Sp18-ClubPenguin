package edu.ou.cs.hci.stages.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class NewFolderAction extends AbstractAction {

	private static final long serialVersionUID = -7667897409729381564L;

	public NewFolderAction() {
		super("New Folder");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("New Folder: Create a new folder");
	}

}
