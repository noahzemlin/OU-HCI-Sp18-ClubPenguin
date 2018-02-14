package edu.ou.cs.hci.stages.frames;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.ou.cs.hci.resources.Resources;

public class ScenarioFrame extends JFrame {
	
	private static final long serialVersionUID = -8527858218742891570L;
	
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
		this.setResizable(false);
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