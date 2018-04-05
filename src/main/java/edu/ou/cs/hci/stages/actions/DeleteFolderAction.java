package edu.ou.cs.hci.stages.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class DeleteFolderAction extends AbstractAction {

	private static final long serialVersionUID = 7887175526014336382L;

	public DeleteFolderAction() {
		super("Delete Folder");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Delete Folder: Deleting a folder");
	}

}
