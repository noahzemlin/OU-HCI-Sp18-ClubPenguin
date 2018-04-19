package edu.ou.cs.hci.stages.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import edu.ou.cs.hci.stages.actions.QuitAction;
import edu.ou.cs.hci.stages.nav.ClubPenguinMenu;
import edu.ou.cs.hci.stages.nav.ClubPenguinToolbar;
import edu.ou.cs.hci.stages.panels.CheckBoxPanel;
import edu.ou.cs.hci.stages.panels.GameCard;
import edu.ou.cs.hci.stages.panels.GameInfoPanel;
import edu.ou.cs.hci.stages.util.Game;
//import jdk.internal.module.Resources;
import edu.ou.cs.hci.resources.*;

public class ClubPenguin extends JFrame {

	private static final long serialVersionUID = 7914914509681332387L;

	private static JSplitPane gameAndInfoPanel;
	private static GameInfoPanel gamePanel;
	public static JFrame instance;
	private static CheckBoxPanel checkPanelGenre;
	private static CheckBoxPanel checkPanelTag;
	
	private static int lastGameDividerLocation;
	
	public static ArrayList<GameCard> curCards = null;
	
	private static JPanel contentPanel;

	public ClubPenguin() {
		this(50, 50);
	}
	
	public ClubPenguin(int x, int y) {
		instance = this;
		
		addStandardComponents();
		
		this.setTitle("Club Penguin");
		this.setBounds(x, y, 800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                new QuitAction(null).actionPerformed(null);
            }
        });
	}
	
	public void addStandardComponents() {
		
		/*
		 * 	Layout
		 * 	
		 * 	Sub sections denote children (e.g. body is the parent of filterPanel and viewerPanel)
		 * 
		 * 	-body (Scrollpane between the left and the right)
		 * 		-filterPanel (The panel on the left containing the filters)
		 * 			-checkPanelGenre (Genre checkboxes)
		 * 			-checkPanelTag	(Tag checkboxes)
		 * 		-viewerPanel (The panel on the right containing the search bar and games view)
		 * 			-searchSortPanel (The search bar)
		 * 			-gameAndInfoPanel (Scrollpane between the game grid view and the game info view)
		 * 				-contentScroll	(Game view)
		 * 				-gamePanel	(Game info view)
		 */

		//Create the header panel which holds the header bar
		JPanel searchSortPanel = new JPanel(new GridLayout(0, 3));
		//searchSortPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JTextField search = new JTextField();
		search.setText("Search");
		searchSortPanel.add(search);
		searchSortPanel.setBackground(new Color(167, 211, 255));
		
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));
		filterPanel.add(new JLabel("Genres"));
		
		//Create the panel which will hold the check boxes
		ArrayList<String> genres = new ArrayList<String>();
		genres.add("Action");
		genres.add("Strategy");
		genres.add("RPG");
		checkPanelGenre = new CheckBoxPanel(genres);

		filterPanel.add(checkPanelGenre);
		
		filterPanel.add(new JLabel("Tags"));
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Couch Co-op");
		tags.add("Multiplayer");
		tags.add("Ridiculous");
		checkPanelTag = new CheckBoxPanel(tags);

		filterPanel.add(checkPanelTag);
		filterPanel.setBackground(hex2Rgb("#A0F2FF"));
		//filterPanel.setOpaque(true);
		
		//Create the panel and scrollpane for the main game content browser
		contentPanel = new JPanel(new GridLayout(0, 4, 15, 15));
		contentPanel.setPreferredSize(new Dimension(0, 1000));
		JScrollPane contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPanel.setBackground(new Color(167, 211, 255));
		
		//Create the panel which will hold the game info
		gamePanel = new GameInfoPanel();
		//gamePanel.setVisible(false); //Should gamePanel should at start?
		
		//Create the panel which will act as panel for the viewer panel and games info
		gameAndInfoPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, contentScroll, gamePanel);
		gameAndInfoPanel.setDividerLocation(350);
		lastGameDividerLocation = 350;
		
		//Create the viewer panel which holds the search/sort bar and game browser
		JPanel viewerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.03;
		viewerPanel.add(searchSortPanel, c);
		searchSortPanel.setBackground(hex2Rgb("#A0F2FF"));
		c.gridy = 1;
		c.weighty = .97;
		viewerPanel.add(gameAndInfoPanel, c);

		//Create the panel which hold the body
		JSplitPane body = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, viewerPanel);
		body.setDividerLocation(150);
		repaint();
		
		contentPanel.setPreferredSize(new Dimension(0,400)); 
		
		////////////////////////////////////////////////////////////////

		
		//COLOR
		contentPanel.setBackground(hex2Rgb("#A7D3FF"));
		contentPanel.setOpaque(true);
		this.setJMenuBar(new ClubPenguinMenu());
		
		//
		
		this.add(new ClubPenguinToolbar());
		this.add(body,BorderLayout.CENTER);
		this.pack();
		
		//On Close Listener
		this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					//QuitAction._instance.actionPerformed(null);
			}
		});
		
		//Game divider listener
		//Keeps the game divider from randomly disappearing by reseting it's position when the box
		//is opened and closed.
		gameAndInfoPanel.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if ((int)e.getNewValue() < instance.getHeight() * (5.0/6.0)) { //if the divider was not shoved all the way down
					lastGameDividerLocation = (int) e.getNewValue();
				} else {
					gameAndInfoPanel.setDividerLocation((int)e.getOldValue());
				}
			}});
		
		//Clear search box when clicked
		search.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if(search.getText().equals("Search"))
					search.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}});
		
		//Print when search box changes
		search.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				changed();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				changed();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				changed();
			}
			
			public void changed() {
				System.out.println("New search: " + search.getText());
			}
			
		});
		
	}

	//Set the currently viewed game in the game info display
	public static void setGame(GameCard gameCard) {
			gamePanel.setGame(gameCard);
			gameAndInfoPanel.setDividerLocation(lastGameDividerLocation);
	}
	
	public static void setGamesList(ArrayList<GameCard> cards) {
		contentPanel.removeAll();
		
		System.out.println("Adding " + cards.size() + " cards");
		
		curCards = (ArrayList<GameCard>) cards.clone();
		
		for(GameCard card : cards)
			contentPanel.add(card);
		
		contentPanel.revalidate();
		
		
	}
	
	public static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
}
