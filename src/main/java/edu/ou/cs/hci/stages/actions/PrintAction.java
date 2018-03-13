package edu.ou.cs.hci.stages.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class PrintAction extends AbstractAction {
	
	private static final long serialVersionUID = 114260983411953438L;
	private final String message;

	public PrintAction(String message) {
		this.message = message;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(message);
	}

}
