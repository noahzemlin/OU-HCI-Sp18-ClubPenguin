package edu.ou.cs.hci.stages;

//import java.lang.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import edu.ou.cs.hci.stages.frames.*;
import edu.ou.cs.hci.stages.panels.*;

public final class Stage1
{
	public static void main(String[] args)
	{
		//Create the Main Window at 50, 50
		new ClubPenguin(50, 50);
		
		//Create the Scenario Window at 300, 150
		new ScenarioFrame(300, 150);
	}
	
}

class ClubPenguin extends JFrame {

	private static final long serialVersionUID = 7914914509681332387L;

	public ClubPenguin() {
		this(50, 50);
	}
	
	public ClubPenguin(int x, int y) {
		
		//Create the main panel holding everything
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		//Create the header panel which holds the header bar
		JPanel headerPanel = new JPanel(new GridLayout(0, 5));
		headerPanel.setPreferredSize(new Dimension(800, 40));
		headerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Create the panel which will hold the check boxes
		ArrayList<String> filters = new ArrayList<String>();
		filters.add("Action");
		filters.add("Strategy");
		filters.add("RPG");
		FilterPanel checkPanel = new FilterPanel(filters);
		checkPanel.setPreferredSize(new Dimension(200, 560));
		checkPanel.setBorder(BorderFactory.createLineBorder(Color.black));	
		
		//Create the panel which will act as a parent panel for the right side
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(585, 560));
		
		//Create the panel and scrollpane for the main game content browser
		JPanel contentPanel = new JPanel(new GridLayout(0, 4));
		JScrollPane contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentScroll.setBorder(BorderFactory.createLineBorder(Color.black));	
		contentScroll.setPreferredSize(new Dimension(585, 290));
		
		//Create the panel which will hold the game info
		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(585, 210));
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));		
		
		
		//Add all these panels to the main panel
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(checkPanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		
		rightPanel.add(contentScroll, BorderLayout.NORTH);
		rightPanel.add(gamePanel, BorderLayout.SOUTH);
		
		//Add some example buttons
		JButton button;
		button = new JButton("Add Game");
		headerPanel.add(button);
		
		button = new JButton("Button");
		headerPanel.add(button);
		
		button = new JButton("Button");
		headerPanel.add(button);
		
		//Add example text to game panel
		JLabel label = new JLabel("Game Info");
		gamePanel.add(label);
		
		//Add a bunch of games
		for (int i=0;i<24;i++)
			contentPanel.add(new GameCard("Game #" + (i + 1)));

		this.setTitle("Club Penguin");
		this.setBounds(x, y, 800, 600);
		this.setResizable(false);
		this.add(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		
	}
	
}

//******************************************************************************
