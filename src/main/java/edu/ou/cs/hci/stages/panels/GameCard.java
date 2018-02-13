package edu.ou.cs.hci.stages.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//GameCard class will create cards that show the game picture and title
public class GameCard extends JPanel {
	
	private static final long serialVersionUID = -1585992822453830200L;

	public GameCard(String name) {
		
		//Set Layout stuff
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(80, 160));
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.black)));
		
		//Add some basic filler components
		this.add(new JLabel(name), BorderLayout.NORTH);
		this.add(new JLabel("Bottom Text"), BorderLayout.SOUTH);
	}
}