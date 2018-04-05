package edu.ou.cs.hci.stages.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class LayoutAction extends AbstractAction {

	private static final long serialVersionUID = -195340078739964604L;
	
	public enum Type {Grid, List};
	
	private final Type type;

	public LayoutAction(Type type) {
		super(type.toString() + " View");
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(type.toString() + " View: Set the layout to " + type.toString());
	}

}
