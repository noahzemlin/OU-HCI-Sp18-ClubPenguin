package edu.ou.cs.hci.stages.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ZoomAction extends AbstractAction {

	private static final long serialVersionUID = -3151824867131106876L;
	
	private final boolean zoomIn;
	
	public ZoomAction(boolean zoomIn) { 
		super("Zoom " + (zoomIn ? "In" : "Out"));
		this.zoomIn = zoomIn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Zoom " + (zoomIn ? "In" : "Out") + ": Zoom the view " + (zoomIn ? "In" : "Out"));
	}

}
