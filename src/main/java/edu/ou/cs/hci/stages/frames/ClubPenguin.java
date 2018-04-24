package edu.ou.cs.hci.stages.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.ou.cs.hci.stages.actions.QuitAction;
import edu.ou.cs.hci.stages.nav.ClubPenguinMenu;
import edu.ou.cs.hci.stages.nav.ClubPenguinToolbar;
import edu.ou.cs.hci.stages.panels.CheckBoxPanel;
import edu.ou.cs.hci.stages.panels.GameCard;
import edu.ou.cs.hci.stages.panels.GameInfoPanel;

public class ClubPenguin extends JFrame {

	private static final long serialVersionUID = 7914914509681332387L;
	
	private static final int startingHorizSeperatorPos = 250;
	
	private static JSplitPane gameAndInfoPanel;
	private static GameInfoPanel gamePanel;
	public static JFrame instance;
	
	private static CheckBoxPanel checkPanelGenre;
	private static CheckBoxPanel checkPanelTag;
	
	private static JTextField search;
	
	public static ArrayList<GameCard> curCards = new ArrayList<GameCard>();
	
	private static int lastGameDividerLocation = startingHorizSeperatorPos;
	
	public static JPanel contentPanel;

	public ClubPenguin() {
		this(50, 50);
	}
	
	public ClubPenguin(int x, int y) {
		instance = this;
		
		addStandardComponents();
		
		this.setTitle("Club Penguin");
		this.setBounds(x, y, 800, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
	}
	
	public void addStandardComponents() {
		
		/*
		 * 	Layout
		 * 	
		 * 	Sub sections denote children (e.g. body is the parent of filterPanel and viewerPanel)
		 * 
		 * 	-main
		 * 		-toolbar
		 * 		-body (Scrollpane between the left and the right)
		 * 			-filterPanel (The panel on the left containing the filters)
		 * 				-checkPanelGenre (Genre checkboxes)
		 * 				-checkPanelTag	(Tag checkboxes)
		 * 			-viewerPanel (The panel on the right containing the search bar and games view)
		 * 				-searchSortPanel (The search bar)
		 * 				-gameAndInfoPanel (Scrollpane between the game grid view and the game info view)
		 * 					-contentScroll	(Game view)
		 * 					-gamePanel	(Game info view)
		 */
		
		JPanel main = new JPanel(new GridBagLayout());

		//Create the header panel which holds the header bar
		JPanel searchSortPanel = new JPanel(new GridLayout(0, 3));
		searchSortPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		search = new JTextField();
		search.setText("Search");
		searchSortPanel.add(search);
		
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.PAGE_AXIS));

		filterPanel.add(new JLabel("Genres"));
		
		filterPanel.setBackground(new Color(199, 236, 238));
		
		//Create the panel which will hold the check boxes
		ArrayList<String> genres = new ArrayList<String>();
		genres.add("Action");
		genres.add("MOBA");
		genres.add("Survival");
		checkPanelGenre = new CheckBoxPanel(genres, false);
		filterPanel.add(checkPanelGenre);

		filterPanel.add(new JLabel("Tags"));
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Couch Co-op");
		tags.add("Multiplayer");
		tags.add("Ridiculous");
		checkPanelTag = new CheckBoxPanel(tags, true);
		filterPanel.add(checkPanelTag);
		
		//Create the panel and scrollpane for the main game content browser
		contentPanel = new JPanel(new FlowLayout(3));
		JScrollPane contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPanel.setBackground(new Color(167, 211, 255));
		
		//Create the panel which will hold the game info
		gamePanel = new GameInfoPanel();
		gamePanel.setBackground(new Color(199, 236, 238));
		//gamePanel.setVisible(false); //Should gamePanel should at start?
		
		//Create the panel which will act as panel for the viewer panel and games info
		gameAndInfoPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, contentScroll, gamePanel);
		gameAndInfoPanel.setDividerLocation(startingHorizSeperatorPos);
		lastGameDividerLocation = 320;
		
		//Create the viewer panel which holds the search/sort bar and game browser
		JPanel viewerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.05;
		viewerPanel.add(searchSortPanel, c);
		c.gridy = 1;
		c.weighty = .95;
		viewerPanel.add(gameAndInfoPanel, c);

		//Create the panel which hold the body
		JSplitPane body = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, viewerPanel);
		body.setDividerLocation(150);

		this.setJMenuBar(new ClubPenguinMenu());

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.05;
		main.add(new ClubPenguinToolbar(), c);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0.95;
		main.add(body, c);
		
		this.add(main);
		this.pack();
		
		
		//Begin Listeners
		
		//On Close Listener
		this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					QuitAction._instance.actionPerformed(null);
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

			//sets the gamecards to show what names contain what is within the search bar
			public void changed() {
	
				for (int i = 0; i < ClubPenguin.getGames().size(); i++)
				{
					if(ClubPenguin.getGames().get(i) != null) {
						if (!ClubPenguin.getGames().get(i).getGame().getName().toLowerCase().contains(ClubPenguin.getSearch().toLowerCase()))
						{
							ClubPenguin.getGames().get(i).setVisible(false);
						}
						else if (ClubPenguin.getSearch().equals(""))
						{
							ClubPenguin.getGames().get(i).setVisible(true);
						}
					}
				}

			}

		});
		
	}
	
	//Set the currently viewed game in the game info display
	public static void setGame(GameCard game) {
		gamePanel.setGame(game);
		gamePanel.setVisible(true);
		gameAndInfoPanel.setDividerLocation(startingHorizSeperatorPos);
	}

	public static void setGamesList(ArrayList<GameCard> cards) {
		curCards = cards;
		contentPanel.removeAll();
		
		for (GameCard card : cards) {
			contentPanel.add(card);
		}
		
		contentPanel.revalidate();
		
		contentPanel.setPreferredSize(new Dimension(0, 300 * cards.size() / 4));
	}
	
	public static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}

	public static void addToGamesArray(GameCard gameCard) {
		contentPanel.add(gameCard);
		curCards.add(gameCard);
		contentPanel.revalidate();
		
		contentPanel.setPreferredSize(new Dimension(0, 210 * curCards.size() / 4));
	}
	
	public static String getSearch()
	{
		return search.getText();
	}

	public static ArrayList<GameCard> getGames() {
		return curCards;
	}

	public static void removeSelectedCard() {
		for (GameCard card : curCards) {
			if (card == GameCard.selected) {
				curCards.remove(card);
				contentPanel.remove(card);
				contentPanel.revalidate();
				break;
			}
		}
	}

	public static void sortGames() {
		curCards.sort(new Comparator<GameCard>() {

			@Override
			public int compare(GameCard arg0, GameCard arg1) {
				return arg0.getGame().getName().compareTo(arg1.getGame().getName());
			}});
		
		setGamesList(curCards);
	}
	
}
