package edu.ou.cs.hci.stages.actions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;

public class AboutAction extends AbstractAction {

	private static final long serialVersionUID = -2277354781255369803L;

	@Override
	public void actionPerformed(ActionEvent event) {
		
		System.out.println("About: About page opened");
		
		if (event == null) {
			return;
		}

		try {
			java.awt.Desktop.getDesktop().browse(new URI("http://store.steampowered.com/"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

	}

}
