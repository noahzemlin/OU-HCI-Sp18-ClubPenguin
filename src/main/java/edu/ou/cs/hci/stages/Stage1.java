//******************************************************************************
// Copyright (C) 2016-2018 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Feb  9 20:33:16 2016 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20160209 [weaver]:	Original file (for CS 4053/5053 homeworks).
// 20180123 [weaver]:	Modified for use in CS 3053 team projects.
//
//******************************************************************************
// Notes:
//
//******************************************************************************

package edu.ou.cs.hci.stages;

//import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//******************************************************************************

/**
 * The <CODE>BuildTest</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class Stage1
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************

	private static final Font	FONT =
		new Font(Font.SERIF, Font.ITALIC, 36);
	private static final Color	FILL_COLOR = Color.GRAY;
	private static final Color	EDGE_COLOR = Color.BLACK;

	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	private static String		message;

	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		//Create the frame and main panel holding everything
		JFrame frame = new JFrame("Wireframe Test");
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		//Create the header panel which holds the header bar
		JPanel headerPanel = new JPanel(new GridLayout(0, 5));
		headerPanel.setPreferredSize(new Dimension(800, 40));
		headerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Create the panel which will hold the check boxes
		JPanel checkPanel = new JPanel();
		checkPanel.setPreferredSize(new Dimension(200, 560));
		checkPanel.setBorder(BorderFactory.createLineBorder(Color.black));	
		
		//Create the panel which will act as a parent panel for the right side
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(575, 560));
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));		
		
		//Create the panel and scrollpane for the main game content browser
		JPanel contentPanel = new JPanel(new GridLayout(3, 3));
		contentPanel.setPreferredSize(new Dimension(575, 290));
		JScrollPane contentScroll = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentScroll.setBorder(BorderFactory.createLineBorder(Color.black));	
		contentScroll.setPreferredSize(new Dimension(575, 290));
		
		//Create the panel which will hold the game info
		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(575, 200));
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));		
		
		
		//Add all these panels to the main panel
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		mainPanel.add(checkPanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		
		rightPanel.add(contentScroll, BorderLayout.NORTH);
		rightPanel.add(gamePanel, BorderLayout.SOUTH);
		
		//Add some example buttons
		JButton button;
		button = new JButton("Button");
		headerPanel.add(button);
		
		button = new JButton("Button");
		headerPanel.add(button);
		
		button = new JButton("Button");
		headerPanel.add(button);
		
		//Add example text to checkbox panel
		JLabel label;
		label = new JLabel("Tags");
		checkPanel.add(label);
		
		//Add example text to game panel
		label = new JLabel("Game Info");
		gamePanel.add(label);
		
		//Add a bunch of games
		for (int i=0;i<24;i++)
			contentPanel.add(new GameCard("game: " + (i + 1)));

		frame.setBounds(50, 50, 800, 600);
		frame.add(mainPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
	}
	
}

//GameCard class will create cards that show the game picture and title
class GameCard extends JPanel {
	
	public GameCard(String name) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(80, 160));
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.black)));
		this.add(new JLabel(name), BorderLayout.NORTH);
		this.add(new JLabel("hi 2"), BorderLayout.SOUTH);
	}
}

//******************************************************************************
