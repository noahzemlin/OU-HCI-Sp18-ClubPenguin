package edu.ou.cs.hci.stages.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import edu.ou.cs.hci.resources.Resources;
import edu.ou.cs.hci.stages.frames.ClubPenguin;
import edu.ou.cs.hci.stages.panels.GameCard;
import edu.ou.cs.hci.stages.util.Game;

public class SaveAction extends AbstractAction {

	private static final long serialVersionUID = -932094879707000406L;
	
	private final JMenuBar menu;
	
	public static SaveAction _instance;
	
	public SaveAction(JMenuBar menu) {
		super("Save");
		_instance = this;
		this.menu = menu;
	}
	
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
