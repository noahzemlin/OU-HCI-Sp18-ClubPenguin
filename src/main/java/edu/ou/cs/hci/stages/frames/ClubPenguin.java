package edu.ou.cs.hci.stages.frames;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import edu.ou.cs.hci.stages.panels.CheckBoxPanel;
import edu.ou.cs.hci.stages.panels.GameCard;
import edu.ou.cs.hci.stages.panels.GameInfoPanel;
import utils.Game;

public class ClubPenguin extends JFrame {

	private static final long serialVersionUID = 7914914509681332387L;
	
	private static JSplitPane gameAndInfoPanel;
	private static GameInfoPanel gamePanel;
	private static JFrame instance;
	
	private static CheckBoxPanel checkPanel;

	public ClubPenguin() {
		this(50, 50);
	}
	
	public ClubPenguin(int x, int y) {
		instance = this;
		
		//Create the main panel holding everything
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		//Create the header panel which holds the header bar
		JPanel headerPanel = new JPanel(new GridLayout(0, 3));
		headerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Create the header panel which holds the header bar
		JPanel searchSortPanel = new JPanel();
		searchSortPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Create the panel which will hold the check boxes
		ArrayList<String> filters = new ArrayList<String>();
		filters.add("Action");
		filters.add("Strategy");
		filters.add("RPG");
		checkPanel = new CheckBoxPanel(filters);
		checkPanel.setBorder(BorderFactory.createLineBorder(Color.black));	
		
		//Create the panel and scrollpane for the main game content browser
		JPanel contentPanel = new JPanel(new GridLayout(0, 4));
		JScrollPane contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentScroll.setBorder(BorderFactory.createLineBorder(Color.black));	
	
		//Create the panel which will hold the game info
		gamePanel = new GameInfoPanel();
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));	
		gamePanel.setVisible(false);
		
		//Create the panel which will act as panel for the viewer panel and games info
		gameAndInfoPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, contentPanel, gamePanel);
		gameAndInfoPanel.setDividerLocation(350);
		
		//Create the viewer panel which holds the search/sort bar and game browser
		JPanel viewerPanel = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.1;
		viewerPanel.add(searchSortPanel, c);
		c.gridy = 1;
		c.weighty = .9;
		viewerPanel.add(gameAndInfoPanel, c);

		//Create the panel which hold the body
		JSplitPane body = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, checkPanel, viewerPanel);
		body.setDividerLocation(150);
		
		//Add all these panels to the main panel
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.1;
		mainPanel.add(headerPanel, c);
		c.gridy = 1;
		c.weighty = .9;
		mainPanel.add(body, c);
		
		//Add some example buttons
		JButton button;
		button = new JButton("Add Game (not yet functional)");
		headerPanel.add(button);
		
		button = new JButton("Not yet used");
		headerPanel.add(button);
		
		button = new JButton("Not yet used");
		headerPanel.add(button);
		
		//Add a bunch of games
		for (int i=0;i<24;i++)
			contentPanel.add(new GameCard());

		this.pack();
		this.setTitle("Club Penguin");
		this.setBounds(x, y, 800, 600);
		this.add(mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					
					JFileChooser fileChose = new JFileChooser();
					fileChose.setDialogTitle("Save debug file");
					fileChose.showDialog(instance, "Choose");
					
					File file = fileChose.getSelectedFile();
					try {
						PrintWriter fout = new PrintWriter(file);
						fout.write("Selected genres: " + checkPanel.getSelectedAsText());
						fout.write("Current chosen game: " 
								+ (gamePanel.getCurrentGame() == null ? "None" : gamePanel.getCurrentGame().getName()));
						fout.close();
					} catch (FileNotFoundException e1) {
						//didn't select a file, whatever. just exit through finally case
					} finally {
						System.exit(0);
					}
			
					//just for good measure
					System.exit(0);
				}
			});
		
	}
	
	//Set the currently viewed game in the game info display
	public static void setGame(Game game) {
		gamePanel.setGame(game);
		gameAndInfoPanel.setDividerLocation(350);
	}
	
}
