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
import edu.ou.cs.hci.resources.*;
import edu.ou.cs.hci.resources.scenarios.*;

//import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//******************************************************************************

/**
 * The <CODE>BuildTest</CODE> class.<P>
 *
 * @author  Chris Weaver
 * @version %I%, %G%
 */
public final class Stage1
{
	public static int FRAME_LENGTH = 700;
	public static int FRAME_WIDTH = 375;
	
	//**********************************************************************
	// Main
	//*********************************************************************

	public static void main(String[] args)
	{
		
		JFrame			frame = new JFrame("Club Penguin");
		JFrame			frame2 = new JFrame("Personas");
		JPanel			pane2 = new GameFrame();
		JPanel			buttonpane1 = new Buttons();
		JPanel			Pane3 = new displayPanel();
		
		frame.setBounds(50, 50, FRAME_LENGTH, FRAME_WIDTH);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEADING));
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		Pane3.setBorder(border);		
	
		frame.getContentPane().add(pane2);
		frame.getContentPane().add(buttonpane1);
		Pane3.setPreferredSize(new Dimension(380, 320));
		frame.getContentPane().add(Pane3);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		
		
		//FRAME 2 PERSONAS
		frame2.setBounds(200, 150, FRAME_LENGTH * 2, FRAME_WIDTH * 2);
		JPanel			PersonaPane = new PersonaPane();
		frame2.setVisible(true);
		frame2.getContentPane().add(PersonaPane);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
	}
	
	    //**********************************************************************
		// Persona Class
		//**********************************************************************

			private static final class PersonaPane extends JPanel
			{

				public PersonaPane()
				{		
					super();
					setLayout(new FlowLayout(FlowLayout.LEADING));
					ArrayList<String>	scenTitles = Resources.getLines("scenarios/titles.txt");
					final JList<String> PersonaList = new JList(scenTitles.toArray());
			
					PersonaList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					PersonaList.setVisibleRowCount(5);
					JScrollPane listScroller = new JScrollPane(PersonaList);
					listScroller.setPreferredSize(new Dimension(200, FRAME_WIDTH * 2 - 50));
					add(listScroller);					
					
					JTextArea textArea = new JTextArea(43 , 105);
					JScrollPane scrollPane = new JScrollPane(textArea); 
					textArea.setEditable(false);
					textArea.setLineWrap(true);
					textArea.setWrapStyleWord(true);
					add(scrollPane);
					ArrayList<String>	scenDesc = Resources.getLines("scenarios/descriptions.txt");
					
					
					PersonaList.setSelectedIndex(0);
					if(scenDesc.get(0).equals(PersonaList.getSelectedValue()))
        	        {
						int i = 0;
        	        	textArea.setText("");
			   	        	 while((i)< scenDesc.size() && !scenDesc.get(i).equals(""))
			   	        	  {
			   	        		  textArea.append(scenDesc.get(i));
			   	        		  i++;
			   	        	  }
        	        }
					
					
					PersonaList.addListSelectionListener(new ListSelectionListener()
					{
				          public void valueChanged(ListSelectionEvent e) 
				          {
				   	        	  for(int i=0;i<scenDesc.size();i++)
				   	        	  {
					        	        if(scenDesc.get(i).equals(PersonaList.getSelectedValue()))
					        	        {
					        	        	int n = i + 1 ;
					        	        	textArea.setText("");
								   	        	 while((n)< scenDesc.size() && !scenDesc.get(n).equals(""))
								   	        	  {
								   	        		  textArea.append(scenDesc.get(n));
								   	        		  n++;
								   	        	  }
					        	        }
					        	  }				   	        	  
				          }  				       
				    });
					

				}
			}
	
	
	//**********************************************************************
	// Display Class
	//**********************************************************************

		private static final class displayPanel extends JPanel
		{

			public displayPanel()
			{		
				super();
				setLayout(new BorderLayout());
				
				JPanel example = new JPanel(); 	
				example.setLayout(new BoxLayout(example, BoxLayout.Y_AXIS));
				Panel p = new Panel();
				p.setLayout(new BorderLayout());	
				example.add(new Label("Name: "));
				example.add(new Label("Genre: "));
				example.add(new Label("Description: "));
				
				p.add(example);
				
				add(example,BorderLayout.NORTH);
			}
		}
	
	
	//**********************************************************************
	// Buttons Class
	//**********************************************************************

		private static final class Buttons extends JPanel
		{

			public Buttons()
			{		
				super();
				
				setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				JPanel example = new JPanel();  
				example.setLayout(new BoxLayout(example, BoxLayout.Y_AXIS));
				
				JButton 		button1 = new JButton("Find");  
				JButton 		button2 =new JButton("Details");
				JButton 		button3 =new JButton("Filter");
				
				Dimension d = button2.getMaximumSize();
				button1.setMaximumSize(new Dimension(d));
				button3.setMaximumSize(new Dimension(d));
					
				example.add(button1);
				example.add(Box.createVerticalStrut(5));
				example.add(button2);
				example.add(Box.createVerticalStrut(5));
				example.add(button3);
				add(example);
			}

		}
		
		//**********************************************************************
		// GameFrame Classes
		//**********************************************************************

			private static final class GameFrame extends JPanel
			{

				public GameFrame()
				{		
					super();
					
					String[] games= new String[20];
					for(int i = 0; i < 20;i ++)
					{
						games[i] = "A Game " + i;
					}
					JList<String> gameList = new JList(games);
					gameList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					gameList.setVisibleRowCount(5);
					JScrollPane listScroller = new JScrollPane(gameList);
					listScroller.setPreferredSize(new Dimension(200, 320));
					add(listScroller);
				}
			}	
}

//******************************************************************************
