package edu.ou.cs.hci.stages.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.csv.*;

import edu.ou.cs.hci.stages.panels.CheckBoxPanel;
import edu.ou.cs.hci.stages.panels.GameCard;
import edu.ou.cs.hci.stages.panels.GameInfoPanel;
import edu.ou.cs.hci.stages.util.Game;
//import jdk.internal.module.Resources;
import edu.ou.cs.hci.resources.*;

public class ClubPenguin extends JFrame {

	private static final long serialVersionUID = 7914914509681332387L;
	File file = new File("menu-actions.txt");
	private static JSplitPane gameAndInfoPanel;
	private static GameInfoPanel gamePanel;
	private static JFrame instance;
	private static Game cGame;
	private static CheckBoxPanel checkPanelGenre;
	private static CheckBoxPanel checkPanelTag;
	
	private static String tempG = null;
	private static int lastGameDividerLocation;

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
		searchSortPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JTextField search = new JTextField();
		search.setText("Search");
		searchSortPanel.add(search);
		
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
		JPanel contentPanel = new JPanel(new GridLayout(0, 4, 15, 15));
		contentPanel.setPreferredSize(new Dimension(0, 1000));
		JScrollPane contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
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
		c.weighty = 0.05;
		viewerPanel.add(searchSortPanel, c);
		searchSortPanel.setBackground(hex2Rgb("#A0F2FF"));
		c.gridy = 1;
		c.weighty = .95;
		viewerPanel.add(gameAndInfoPanel, c);

		//Create the panel which hold the body
		JSplitPane body = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, filterPanel, viewerPanel);
		body.setDividerLocation(150);
		repaint();
		

		//ADD PRE CoNFIGUrED GAMES/////////////////////////////////////////////////////
		String[]	COLUMNS =
		{
			"Title",
			"Description",
			"Developer",
			"Publisher",
			"Genre",
			"Tag",
			"Picture",
		};
		JTable	table = new JTable();		// Component for displaying the CSV
		
		URL	url = Resources.getResource("data/stadiums.csv");

		try
		{
			// Create a reader for the CSV
			InputStream		is = url.openStream();
			InputStreamReader	isr = new InputStreamReader(is);
			BufferedReader		r = new BufferedReader(isr);

			// Use the Apache Commons CSV library to read records from it
			CSVFormat			format = CSVFormat.DEFAULT;
			CSVParser			parser = CSVParser.parse(r, format);
			java.util.List<CSVRecord>	records = parser.getRecords();

			// Allocate a 2-D array to keep the rows and columns in memory
			String[][]	values = new String[records.size()][COLUMNS.length];

			for (CSVRecord record : records)	// Loop over the rows...
			{
				Iterator<String>	k = record.iterator();
				int				i = (int)record.getRecordNumber() - 1;
				int				j = 0;		// Column number

				// Print each record to the console
				System.out.println("***** #" + i + " *****");

				while (k.hasNext())			// Loop over the columns...
				{
					values[i][j] = k.next();	// Grab each cell's value

					// Print each value to the console...
					System.out.println(COLUMNS[j] + " = " + values[i][j]);
					j++;
				}

				System.out.println();

				// ...and have the table show the entire value array.
				table.setModel(new DefaultTableModel(values, COLUMNS));
			}

			is.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		for (int i=0;i<24;i++)
		{
			
			contentPanel.add(new GameCard());
			
		}
		
		
		////////////////////////////////////////////////////////////////
		Font f = new Font("sans-serif", Font.PLAIN, 12);
		UIManager.put("Menu.font", f);
		UIManager.put("MenuItem.font", f);
		UIManager.put("MenuBar.font", f);
		//Add MenuBar
		JMenuBar menuBar = new JMenuBar();
		JMenu menu, submenu;
		JMenuItem menuItem;
				
		//File Menu
		JMenuItem fileOpen = new JMenuItem("Open");
		JMenuItem fileSave = new JMenuItem("Save");
		JMenuItem filePrint = new JMenuItem("Print");
		JMenuItem fileExit = new JMenuItem("Exit");					
		
		JMenu File = new JMenu("File");
		menuBar.add(File);
		File.add(fileOpen);
		File.add(fileSave);
		File.add(filePrint);
		File.add(fileExit);
		
		//Edit menu
		JMenuItem editCut = new JMenuItem("Cut");
		JMenuItem editCopy = new JMenuItem("Copy");
		JMenuItem editPaste = new JMenuItem("Paste");
		JMenuItem editDeselect = new JMenuItem("Deselect");
		JMenuItem editSelectAll = new JMenuItem("Select All");
		JMenu Edit = new JMenu("Edit");
		menuBar.add(Edit);
		Edit.add(editCut);
		Edit.add(editCopy);
		Edit.add(editPaste);
		Edit.add(editDeselect);
		Edit.add(editSelectAll);
		
		JMenuItem gameAdd = new JMenuItem("Add Game");
		Font f1 = new Font("sans-serif", Font.BOLD, 12);
		gameAdd.setFont(f1);
		JMenuItem gameEdit = new JMenuItem("Edit Game");
		JMenuItem gameRemove = new JMenuItem("Remove Game");
		JMenu addFolder = new JMenu("Add to Folder");
		JMenuItem removeFolder = new JMenu("Remove from Folder");
		JMenuItem subaddFolder1 = new JMenuItem("Folder 1");
		addFolder.add(subaddFolder1);
		JMenu Game = new JMenu("Game");
		menuBar.add(Game);
		Game.add(gameAdd);
		Game.add(gameEdit);
		Game.add(gameRemove);
		Game.addSeparator();
		Game.add(addFolder);
		Game.add(removeFolder);
		
		//Add view to menu bar
		
		//Edit menu
		JMenuItem viewSort = new JMenuItem("Sort");
		JMenuItem viewSearch = new JMenuItem("Search");
		JMenuItem viewZoomIn = new JMenuItem("Zoom In");
		JMenuItem viewZoomOut = new JMenuItem("Zoom out");
		JMenuItem viewList = new JMenuItem("List View");
		JMenuItem viewGrid = new JMenuItem("Grid View");
		JMenu view = new JMenu("View");
		menuBar.add(view);
		view.add(viewSort);
		view.add(viewSearch);
		view.add(viewZoomIn);
		view.add(viewZoomOut);
		view.add(viewList);
		view.add(viewGrid);
		
		//Folder
		JMenuItem folderNew = new JMenuItem("New Folder");
		JMenuItem folderEdit = new JMenuItem("Edit Folder");
		JMenuItem folderSave = new JMenuItem("Save Folder");
		JMenu Folder = new JMenu("Folder");
		menuBar.add(Folder);
		Folder.add(folderNew);
		Folder.add(folderEdit);
		Folder.add(folderSave);
		
		//Help
		JMenuItem helpAbout = new JMenuItem("About");
		JMenu Help = new JMenu("Help");
		menuBar.add(Folder);
		Folder.add(helpAbout);
		
		//COLOR
		contentPanel.setBackground(hex2Rgb("#A7D3FF"));
		contentPanel.setOpaque(true);
		this.setJMenuBar(menuBar);
		
		//
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(hex2Rgb("#A0F2FF"));
		 ImageIcon TBadd_Game = Resources.getImage("icons/add_game.png");
		 ImageIcon TBdelete_Game = Resources.getImage("icons/delete_game.png");
		 ImageIcon TBedit_Game = Resources.getImage("icons/edit_game.png");
		 ImageIcon TBsearch = Resources.getImage("icons/search.png"); 
		 
		 ImageIcon TBsort = Resources.getImage("icons/sort.png");
		
		 Action add_GameAction = new AbstractAction("Open", TBadd_Game) {
	           @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Open File");
	            }

	        };
	        Action delete_GameAction = new AbstractAction("Save", TBdelete_Game) {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("Save File");
	            }
	        };
	     Action edit_GameAction = new AbstractAction("New", TBedit_Game) {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("New File");
	            }
	        };
	        Action searchAction = new AbstractAction("New", TBsearch) {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("New File");
	            }
	        };
	        
	        Action sortAction = new AbstractAction("New", TBsort) {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.out.println("New File");
	            }
	        };

	    
		 toolBar.add(add_GameAction);
		 toolBar.add(delete_GameAction);
		 toolBar.add(edit_GameAction);
		 toolBar.addSeparator();
		 toolBar.add(searchAction);
		 toolBar.add(sortAction);
		 this.setLayout(new BorderLayout());
		 toolBar.setPreferredSize(new Dimension(0,73));
		 this.add(toolBar, BorderLayout.NORTH);
		this.add(body,BorderLayout.CENTER);
		this.pack();
		
		//Begin Listeners
		
		fileExit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
			try {
				PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
				fout.println("File:");
				fout.println(ev.getActionCommand());
				fout.close();
				fileOpen.doClick();
				
			} catch (FileNotFoundException e1) {
				//didn't select a file, whatever. just exit through finally case
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				System.exit(0);
			}
                System.exit(0);
        }
		});
		fileOpen.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					//fout.append("\n" + ev.getActionCommand());
					fout.println(ev.getActionCommand());
					fout.close();
					fileSave.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});
		fileSave.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					//fout.append("\n" + ev.getActionCommand());
					fout.println(ev.getActionCommand());
					fout.close();
					filePrint.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});
		filePrint.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					//fout.append("\n" + ev.getActionCommand());
					fout.println(ev.getActionCommand());
					fout.close();
					editCut.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});

		editCut.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					//fout.append("\n" + ev.getActionCommand());
					fout.println("Edit:");
					fout.println(ev.getActionCommand());
					fout.close();
					editCopy.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});
		editCopy.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					//fout.append("\n" + ev.getActionCommand());
					fout.println(ev.getActionCommand());
					fout.close();
					editDeselect.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});
		editDeselect.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					//fout.append("\n" + ev.getActionCommand());
					fout.println(ev.getActionCommand());
					fout.close();
					editSelectAll.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});
		editSelectAll.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					//fout.append("\n" + ev.getActionCommand());
					fout.println(ev.getActionCommand());
					fout.close();
					gameAdd.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});
		gameAdd.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					//fout.append("\n" + ev.getActionCommand());
					fout.println("Game:");
					fout.println(ev.getActionCommand());
					fout.close();
					gameRemove.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});
		gameRemove.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					//fout.append("\n" + ev.getActionCommand());
					fout.println(ev.getActionCommand());
					fout.close();
					gameEdit.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});
		gameEdit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					fout.println(ev.getActionCommand());
					fout.close();
					subaddFolder1.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});
		subaddFolder1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ev) {
				try {
					PrintWriter fout = new PrintWriter(new FileWriter("menu-actions.txt", true));
					fout.println("Add to Folder:");
					fout.println(ev.getActionCommand());
					fout.close();
					//subaddFolder1.doClick();
				} catch (FileNotFoundException e1) {
					//didn't select a file, whatever. just exit through finally case
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
	                System.exit(0);
	        }
			});
		
		

		
		//On Close Listener
		this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					//Redirect to file exit
					fileExit.doClick();		
					//just for good measure
					System.exit(0);
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
	public static void setGame(Game game) {
			gamePanel.setGame(game);
			gameAndInfoPanel.setDividerLocation(lastGameDividerLocation);
	}
	
	public static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
}
