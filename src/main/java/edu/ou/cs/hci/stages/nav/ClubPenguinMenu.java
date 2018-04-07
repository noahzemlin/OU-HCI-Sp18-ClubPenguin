package edu.ou.cs.hci.stages.nav;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import edu.ou.cs.hci.stages.actions.*;

public class ClubPenguinMenu extends JMenuBar{

	private static final long serialVersionUID = 4812901101449987270L;

	public ClubPenguinMenu() {
		
		JMenu menu; //reusing this for all menus
		JMenu subMenu; //this too
		JMenuItem menuItem; //this too
		
		//File Top-level Menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		this.add(menu);
		
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Open: Open Library File"));
		menuItem.setText("Open");
		menuItem.setMnemonic(KeyEvent.VK_O);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Save: Saving Library File"));
		menuItem.setText("Save");
		menuItem.setMnemonic(KeyEvent.VK_S);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Print: Printing Library"));
		menuItem.setText("Print");
		menuItem.setMnemonic(KeyEvent.VK_P);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new QuitAction(this));
		menuItem.setText("Quit");
		menuItem.setMnemonic(KeyEvent.VK_Q);
		menu.add(menuItem);
		
		//Edit Top-level Menu
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);
		this.add(menu);
		
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Cut: Cut the selected game"));
		menuItem.setText("Cut");
		menuItem.setMnemonic(KeyEvent.VK_U);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Copy: Copy the selected game"));
		menuItem.setText("Copy");
		menuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Paste: Paste game from clipboard"));
		menuItem.setText("Paste");
		menuItem.setMnemonic(KeyEvent.VK_P);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Deselect: Deselect all selected"));
		menuItem.setText("Deselect");
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Select All: Select all games"));
		menuItem.setText("Select All");
		menuItem.setMnemonic(KeyEvent.VK_A);
		menu.add(menuItem);
		
		//Game Top-level Menu
		menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_G);
		this.add(menu);
		
		menuItem = new JMenuItem();
		menuItem.setAction(new AddGameAction());
		menuItem.setText("Add Game");
		menuItem.setFont(new Font("Arial", Font.BOLD, 14));
		menuItem.setMnemonic(KeyEvent.VK_A);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new EditGameAction());
		menuItem.setText("Edit Game");
		menuItem.setMnemonic(KeyEvent.VK_E);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new RemoveGameAction());
		menuItem.setText("Remove Game");
		menuItem.setMnemonic(KeyEvent.VK_X);
		menu.add(menuItem);
		menu.addSeparator();
		
		subMenu = new JMenu();
		menu.add(subMenu);
		subMenu.setText("Add To Folder");
		
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Add to Folder: Folder 1"));
		menuItem.setText("Folder 1");
		subMenu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Add to Folder: Folder 2"));
		menuItem.setText("Folder 2");
		subMenu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Add to Folder: Folder 3"));
		menuItem.setText("Folder 3");
		subMenu.add(menuItem);
		
		subMenu = new JMenu();
		menu.add(subMenu);
		subMenu.setText("Remove From Folder");
		
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Remove from Folder: Folder 1"));
		menuItem.setText("Folder 1");
		subMenu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Remove from Folder: Folder 2"));
		menuItem.setText("Folder 2");
		subMenu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new PrintAction("Remove from Folder: Folder 3"));
		menuItem.setText("Folder 3");
		subMenu.add(menuItem);
		
		//View Top-level Menu
		menu = new JMenu("View");
		menu.setMnemonic(KeyEvent.VK_V);
		this.add(menu);
		
		menuItem = new JMenuItem();
		menuItem.setAction(new SortAction());
		menuItem.setText("Sort");
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new SearchAction());
		menuItem.setText("Search");
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem();
		menuItem.setAction(new ZoomAction(true));
		menuItem.setText("Show less games");
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new ZoomAction(false));
		menuItem.setText("Show more games");
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem();
		menuItem.setAction(new LayoutAction(LayoutAction.Type.Grid));
		menuItem.setText("Grid View");
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new LayoutAction(LayoutAction.Type.List));
		menuItem.setText("List View");
		menu.add(menuItem);
		
		//Folder Top-level Menu
		menu = new JMenu("Folder");
		menu.setMnemonic(KeyEvent.VK_O);
		this.add(menu);
		
		menuItem = new JMenuItem();
		menuItem.setAction(new NewFolderAction());
		menuItem.setText("New Folder");
		menuItem.setMnemonic(KeyEvent.VK_N);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new EditFolderAction());
		menuItem.setText("Edit Folder");
		menuItem.setMnemonic(KeyEvent.VK_E);
		menu.add(menuItem);
		menuItem = new JMenuItem();
		menuItem.setAction(new DeleteFolderAction());
		menuItem.setText("Delete Folder");
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);
		
		//Help Top-level Menu
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		this.add(menu);
		
		menuItem = new JMenuItem();
		menuItem.setAction(new AboutAction());
		menuItem.setText("About");
		Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		menuItem.setForeground(new Color(0, 0, 255));
		menuItem.setFont(menuItem.getFont().deriveFont(fontAttributes));
		menuItem.setMnemonic(KeyEvent.VK_A);
		menu.add(menuItem);
		
		
	}

}
