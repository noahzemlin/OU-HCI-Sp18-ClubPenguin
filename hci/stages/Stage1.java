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

// Spriggs' Change: I'm adding the Graphics and Rectangle classes for this
import java.awt.Graphics;
import java.awt.Rectangle;

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
	private static final Color	FILL_COLOR = Color.YELLOW;
	private static final Color	EDGE_COLOR = Color.RED;

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
		// Decided to mostly start from scratch to relearn Java Graphics
		// Spriggs Code Start
		
		// THE BASE PANEL
		// The base panel will hold a series of panels stacked on top of it
		// Basically a panel containing panels, the frame holds everything
		JFrame	frame = new JFrame("Stage 1 Wireframe: Club Penguin");
		JPanel basePanel = new JPanel(new BorderLayout());
		
		// THE MENU BAR
		// Menu Bar has base functions: Add Game/Delete Game, Sort, Filter Settings,
		// and Grid/List Toggle
		// Will be changed to actual buttons if there is a shorter way to do this
		JPanel menuBarPanel = new JPanel(new GridLayout(0,5));
		menuBarPanel.setPreferredSize(new Dimension(1000,40));
		menuBarPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		// THE GAME LIST
		// There will be a List of Games at the left side of the Wireframe
		// Example text is included, but the scroll bar is not fully utilized until later
		JPanel gameListPanel = new JPanel(new GridLayout(3,3));
		gameListPanel.setPreferredSize(new Dimension(200, 460));
		gameListPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		JScrollPane listScroll = new JScrollPane(gameListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listScroll.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		listScroll.setPreferredSize(new Dimension(200, 460));
		
		// THE GAME GRID/LIST DISPLAY
		// The games are either displayed with game icons or in a list format, with the size of each
		// rectangle being adjusted for those modes. Both Types will be displayed for demo purposes.
		
		// For this first stage, there will be a base panel for this grid/list display
		JPanel mainDisplay = new JPanel();
		mainDisplay.setPreferredSize(new Dimension(800, 460)); // Base Panel for Grid/List Difference
		mainDisplay.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		JPanel gameDisplayGrid = new JPanel(new GridLayout(0,3)); // Grid Mode
		gameDisplayGrid.setPreferredSize(new Dimension(800, 230));
		gameDisplayGrid.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		JPanel gameDisplayList = new JPanel(new GridLayout(0,3)); // List Mode
		gameDisplayList.setPreferredSize(new Dimension(800,230));
		gameDisplayList.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		// Add the Game Panels to the Base Panel
		basePanel.add(menuBarPanel, BorderLayout.NORTH);
		basePanel.add(gameListPanel, BorderLayout.WEST);
		basePanel.add(mainDisplay, BorderLayout.EAST);
		
		// Add the Scroll Bar Panel to the gameListPanel
		gameListPanel.add(listScroll, BorderLayout.NORTH);
		
		// Add the Grid/List Display Panels to the mainDisplay Panel
		mainDisplay.add(gameDisplayGrid, BorderLayout.NORTH);
		mainDisplay.add(gameDisplayList, BorderLayout.SOUTH);
		
		// Add the Function Buttons to the Menu Bar
		JButton function;
		
		function = new JButton("Add Game(s)");
		menuBarPanel.add(function);
		
		function = new JButton("Delete Game(s)");
		menuBarPanel.add(function);
		
		function = new JButton("Sort Games");
		menuBarPanel.add(function);
		
		function = new JButton("Filter Settings");
		menuBarPanel.add(function);
		
		function = new JButton("Toggle Grid/List");
		menuBarPanel.add(function);
		
		// Add Game Examples to Game List Panel
		JLabel gameLabel;
		gameLabel = new JLabel("Game Go Here :)");
		gameListPanel.add(gameLabel);
		
		frame.setBounds(50, 50, 1000, 500);
		frame.add(basePanel);
		// Spriggs Code End
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
	}

	//**********************************************************************
	// Private Inner Classes
	//**********************************************************************
	
	// Custom class to be able to draw Dummy Rectangles using an overridden Graphics Class
	// Essentially a Canvas for any UI Placement
	private final static class UICanvas extends JPanel
	{
		private int xPosition; // Positions rectangle's x coordinate
		private int yPosition; // Positions rectangle's y coordinate
		private int boxWidth;  // Sets the rectangle's horizontal width
		private int boxHeight; // Sets the rectangle's vertical width (the height)
	}
}

//******************************************************************************