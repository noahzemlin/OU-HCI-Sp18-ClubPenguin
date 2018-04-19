package edu.ou.cs.hci.stages.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.apache.commons.csv.CSVRecord;

import edu.ou.cs.hci.stages.frames.ClubPenguin;
import edu.ou.cs.hci.stages.panels.GameCard;
import edu.ou.cs.hci.stages.util.Game;

public class OpenAction extends AbstractAction {

	private static final long serialVersionUID = -932094879707000406L;
	
	public static OpenAction _instance;
	
	public OpenAction(JMenuBar menu) {
		super("Open");
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
		fileChose.setDialogTitle("Open data file");
		fileChose.showDialog(ClubPenguin.instance, "Choose");
		
		File file = fileChose.getSelectedFile();

		try
		{
			// Create a reader for the CSV
			InputStream		is = new FileInputStream(file);
			InputStreamReader	isr = new InputStreamReader(is);
			BufferedReader		r = new BufferedReader(isr);

			// Use the Apache Commons CSV library to read records from it
			CSVFormat			format = CSVFormat.DEFAULT;
			CSVParser			parser = CSVParser.parse(r, format);
			java.util.List<CSVRecord>	records = parser.getRecords();

			// Allocate a 2-D array to keep the rows and columns in memory
			String[][]	values = new String[records.size()][8];

			for (CSVRecord record : records)	// Loop over the rows...
			{
				Iterator<String>	k = record.iterator();
				int				i = (int)record.getRecordNumber() - 1;
				int				j = 0;		// Column number
				
				while (k.hasNext())			// Loop over the columns...
				{
					values[i][j] = k.next();	// Grab each cell's value

					j++;
				}
				
				if (i != 0) {
				
				Game game = new Game();
				
				game.setName(values[i][0]);
				game.setDescription(values[i][1]);
				game.setDevelopers(values[i][2]);
				game.setPublishers(values[i][3]);
				game.setGenres(values[i][4]);
				game.setTags(values[i][5]);
				game.setPicture(values[i][6]);
				game.setLocation(values[i][7]);
				
				cards.add(new GameCard(game));
				
				}
				
			}

			is.close();
		}
		catch (Exception ex)
		{
			showError("Error in loading file data");
			ex.printStackTrace();
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
				System.out.println("Open: Open a csv file for data");
			}
		}
	}

}
