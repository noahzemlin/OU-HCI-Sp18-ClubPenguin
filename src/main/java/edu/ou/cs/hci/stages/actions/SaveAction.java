package edu.ou.cs.hci.stages.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import edu.ou.cs.hci.stages.frames.ClubPenguin;
import edu.ou.cs.hci.stages.panels.GameCard;

public class SaveAction extends AbstractAction {

	private static final long serialVersionUID = -932094879707000406L;
	
	public static SaveAction _instance;
	
	public SaveAction(JMenuBar menu) {
		super("Save");
		_instance = this;
	}
	
	@SuppressWarnings("deprecation")
	private static void showError(String error) {
		JOptionPane pane = new JOptionPane("Error: " + error);
		JDialog dialog = pane.createDialog(ClubPenguin.instance, "Error");
		dialog.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ArrayList<GameCard> cards = new ArrayList<GameCard>();
		
		JFileChooser fileChose = new JFileChooser();
		fileChose.setDialogTitle("Save data file");
		fileChose.showDialog(ClubPenguin.instance, "Save");
		
		File file = fileChose.getSelectedFile();
		
		if(file != null && file.exists() && !file.isDirectory()) { 
		    showError("File already exists");
		    return;
		}
		
		try {

	        BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));
	
	        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
	                .withHeader("Name", "Description", "Developers", "Publishers", "Genres", "Tags", "Picture", "Location"));
	
			for (GameCard card : ClubPenguin.curCards) {
				csvPrinter.printRecord(card.getGame().getCSVData());
			}
	
	        csvPrinter.flush();   
	        
	        csvPrinter.close();
	        
		} catch (Exception ex) {
        	showError("Could not save file");
        }

		
		ClubPenguin.setGamesList(cards);
		
	}
	
	public void SearchAndPrint(JMenu menu) {
		for (int i = 0; i < menu.getMenuComponentCount(); i++) { 
			Component comp = menu.getMenuComponent(i);
			if (comp instanceof JMenu) {
				SearchAndPrint((JMenu)comp);
			} else if (comp instanceof JMenuItem && ((JMenuItem)comp).getAction() != null && ((JMenuItem)comp).getAction() != this) {
				((JMenuItem)comp).getAction().actionPerformed(null);
			} else if (comp instanceof JMenuItem && ((JMenuItem)comp).getAction() != null && ((JMenuItem)comp).getAction() == this) {
				System.out.println("Save: Save a csv file for data");
			}
		}
	}

}
