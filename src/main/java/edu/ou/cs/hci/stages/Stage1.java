package edu.ou.cs.hci.stages;

//import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.ou.cs.hci.resources.Resources;

//******************************************************************************
public final class Stage1
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************

	private static final Font	FONT =
		new Font(Font.SERIF, Font.ITALIC, 36);
	private static final Color	FILL_COLOR = Color.GRAY;
	private static final Color	EDGE_COLOR = Color.BLACK;

	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	private static String		message;

	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		//Create the Main Window at 50, 50
		new ClubPenguin(50, 50);
		
		//Create the Scenario Window at 300, 150
		new ScenarioFrame(300, 150);
	}
	
}

class ClubPenguin extends JFrame {
	
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
		JPanel checkPanel = new JPanel();
		checkPanel.setPreferredSize(new Dimension(200, 560));
		checkPanel.setBorder(BorderFactory.createLineBorder(Color.black));	
		
		//Create the panel which will act as a parent panel for the right side
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(575, 560));
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));		
		
		//Create the panel and scrollpane for the main game content browser
		JPanel contentPanel = new JPanel(new GridLayout(0, 4));
		JScrollPane contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentScroll.setBorder(BorderFactory.createLineBorder(Color.black));	
		contentScroll.setPreferredSize(new Dimension(575, 290));
		
		//Create the panel which will hold the game info
		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(575, 200));
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
		
		//Add example text to checkbox panel
		JLabel label;
		label = new JLabel("Tags");
		checkPanel.add(label);
		
		//Add example text to game panel
		label = new JLabel("Game Info");
		gamePanel.add(label);
		
		//Add a bunch of games
		for (int i=0;i<24;i++)
			contentPanel.add(new GameCard("Game #" + (i + 1)));

		this.setTitle("Club Penguin");
		this.setBounds(x, y, 800, 600);
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

//GameCard class will create cards that show the game picture and title
class GameCard extends JPanel {
	
	public GameCard(String name) {
		
		//Set Layout stuff
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(80, 160));
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.black)));
		
		//Add some basic filler components
		this.add(new JLabel(name), BorderLayout.NORTH);
		this.add(new JLabel("Bottom Text"), BorderLayout.SOUTH);
	}
}

class ScenarioFrame extends JFrame {
	
	private final int BORDER_SIZE = 5;
	
	public ScenarioFrame() {
		this(50, 50);
	}
	
	public ScenarioFrame(int x, int y) {
		
		//Get the data
		ArrayList<String> titles = Resources.getLines("personas/titles.txt");
		titles.addAll(Resources.getLines("scenarios/titles.txt"));
		ArrayList<String> descriptions = Resources.getLines("personas/descriptions.txt");
		descriptions.addAll(Resources.getLines("scenarios/descriptions.txt"));

		//Create the list of personas and scenarios
		JList<String> list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(titles.size());
		String[] type = {};
		list.setListData(titles.toArray(type));
		list.setSelectedIndex(0);
		list.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE)); //creates padding
		
		//Create the text area
		JTextArea desc = new JTextArea();
		desc.setEditable(false);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		desc.setText(descriptions.get(0));
		desc.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE)); //creates padding
		
		JScrollPane descScroll = new JScrollPane(desc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, list, descScroll);
		
		this.setTitle("Scenario Browser");
		this.setBounds(x, y, 600, 400);
		this.add(splitPane);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Listen for changes to the selected list item
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedIndex() < descriptions.size())
					desc.setText(descriptions.get(list.getSelectedIndex()));
			}
		});

		
		/* Uncomment this if you want closing the scenario browser to close the whole program
		this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		*/
	}
	
	
}

//******************************************************************************
