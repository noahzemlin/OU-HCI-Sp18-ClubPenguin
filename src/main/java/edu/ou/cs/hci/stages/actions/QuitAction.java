package edu.ou.cs.hci.stages.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import edu.ou.cs.hci.stages.frames.ClubPenguin;
import edu.ou.cs.hci.stages.panels.GameInfoPanel;

public class QuitAction extends AbstractAction {

	private static final long serialVersionUID = -932094879707000406L;
	
	private final JMenuBar menu;
	
	public static QuitAction _instance;
	
	public QuitAction(JMenuBar menu) {
		super("Quit");
		_instance = this;
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (GameInfoPanel.changedData) {
			switch(JOptionPane.showConfirmDialog(ClubPenguin.instance, "Do you wish to save before quitting?")) {
			case JOptionPane.NO_OPTION: System.exit(0);
			case JOptionPane.YES_OPTION: new SaveAction(menu).actionPerformed(null);
										System.exit(0);
			case JOptionPane.CANCEL_OPTION: return;
			}
				
		} else {
			System.exit(0);
		}
	}
	
	public void SearchAndPrint(JMenu menu) {
		for (int i = 0; i < menu.getMenuComponentCount(); i++) { 
			Component comp = menu.getMenuComponent(i);
			if (comp instanceof JMenu) {
				SearchAndPrint((JMenu)comp);
			} else if (comp instanceof JMenuItem && ((JMenuItem)comp).getAction() != null && ((JMenuItem)comp).getAction() != this) {
				((JMenuItem)comp).getAction().actionPerformed(null);
			} else if (comp instanceof JMenuItem && ((JMenuItem)comp).getAction() != null && ((JMenuItem)comp).getAction() == this) {
				System.out.println("Quit: Quitting the program");
			}
		}
	}

}
