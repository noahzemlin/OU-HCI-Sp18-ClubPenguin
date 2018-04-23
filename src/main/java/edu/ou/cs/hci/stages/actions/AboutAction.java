package edu.ou.cs.hci.stages.actions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import edu.ou.cs.hci.resources.Resources;

public class AboutAction extends AbstractAction {

	private static final long serialVersionUID = -2277354781255369803L;

	@Override
	public void actionPerformed(ActionEvent event) {
		
		System.out.println("About: About page opened");
		
		if (event == null) {
			return;
		}

		JEditorPane	info;
		URL			url = Resources.getResource("about/about.html");

		try
		{
			// Try to load the about.html file in resources
			info = new JEditorPane(url);
		}
		catch (IOException ex)
		{
			// If loading fails, use a default message
			info = new JEditorPane("text/plain", "[Loading info failed.]");
		}

		// Setting the editor pane size to  the html layout
		info.setEditable(false);
		info.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
					Resources.openURLInSystemBrowser(e.getURL());
			}});
		
		JButton button = new JButton("Close");

		JWindow window = new JWindow();
		window.add(new JScrollPane(info), BorderLayout.CENTER);
		window.add(button, BorderLayout.SOUTH);
		
		window.setBounds(200, 200, 800, 600);
		window.setVisible(true);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
			}});
		
		

	}

}
