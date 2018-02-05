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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;

//******************************************************************************

/**
 * The <CODE>BuildTest</CODE> class.<P>
 *
 * @author  Chris Weaver, editted Zach Terry
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

	//resizes the image given by a url and returnit into a JLabel
    public static JLabel resize(String inputImagePath,int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image and creates the image
		URL url = null;
		BufferedImage image = null;
		try {
			url = new URL(inputImagePath);
			image = ImageIO.read(url);
		} catch (IOException b) {
			b.printStackTrace();
		}
 
		//fixes the image type if it returns a 0
		int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
		
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, type);
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        //returning the jlabel
        JLabel label = new JLabel(new ImageIcon(outputImage));
        return label;
        
    }
	
	// State (internal) variables
	private static String		message;
	
	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		//panel used in the big display
		JPanel displayPanel = new ComboBoxPanel(new Color(0,0,0),new Color(250,250,250),487,600);
		displayPanel.setLayout(new GridBagLayout());
		
		//panel used to show description of game
		JPanel descriptionPanel = new ComboBoxPanel(new Color(0,0,0),new Color(128, 157, 125),600,200);
		descriptionPanel.setLayout(new GridBagLayout());
		
		//string arrays for each of the 6 wombo combo boxes
		String[] purchased = {"Purchased", "Fallout", "FlappyBird", "Farmville", "Minesweeper"};
		String[] catagories = {"Catagories", "Strategy", "Adventure", "FPS", "MMO", "Action"};
		String[] recentlyPlayed = {"Recent Plays", "Solitaire", "Golden Eye", "Mario Kart", "Super Smash Bros"};
		String[] popular = {"Popular", "Not Battlefront II", "Titanfall", "Goat Simulator", "Portal"};
		String[] newReleases = {"New Releases", "Metal Gear", "Pokemon", "COD", "Super Mario"};
		String[] free = {"Free Games", "nothing"};
		

		JFrame frame = new JFrame("Club Penguin");
		//color for combobox fill
		Color fill = new Color(119,136,153);
		//color for combobox border
		Color border = new Color(0,0,0);
		JPanel panel = new ComboBoxPanel(border, fill, 150, 600);
	
		//creating each combobox with the desired drop down menu
		JComboBox<String[]> Purchased = new JComboBox(purchased);
		JComboBox<String[]> Catagories = new JComboBox(catagories);
		JComboBox<String[]> RecentlyPlayed = new JComboBox(recentlyPlayed);
		JComboBox<String[]> Popular = new JComboBox(popular);
		JComboBox<String[]> NewReleases = new JComboBox(newReleases);
		JComboBox<String[]> Free = new JComboBox(free);
		
		//creating a gridbaglayout for the panel
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//lines up the comboboxes
		c.fill = GridBagConstraints.HORIZONTAL;
		//start them at the top right of the screen
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		//makes the boxes shorter length wise
		c.ipadx = -50;
		panel.add(Purchased, c);

		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = -50;
		panel.add(Catagories, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = -50;
		panel.add(RecentlyPlayed, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = -50;
		panel.add(Popular, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.ipadx = -50;
		panel.add(NewReleases, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.ipadx = -50;
		panel.add(Free, c);
		
		
		
		frame.setBounds(50, 50, 600, 600);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(panel,BorderLayout.WEST);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//*******************
		//Action Listener for PURCHASE combobox
		//*******************
		Purchased.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
            	//gets the combobox to use
                String s = (String) Purchased.getSelectedItem();
                
        		GridBagConstraints dc = new GridBagConstraints();
            	
            	//creates the labels to be used in the display panel
				JLabel falloutLabel = null;
				JLabel flappyBirdLabel = null;
				JLabel farmvilleLabel = null;
				JLabel minesweeperLabel = null;
				
				//labels used for the description box
			    JLabel falloutD = new JLabel("<html>Fallout is a pretty lit game. You shoot "
			    		+ "some stuff and gather some things and<br/>it's all real fun "
			    		+ "you should totes play it.<br/>rating:4.4/5<html>");
			    falloutD.setFont(new Font("Verdana",1,12));
			    JLabel flappyD = new JLabel("<html>Play this game if you want to absolutely"
			    		+ "<br/>hate every second of it<br/>rating:10/10<html>");
			    flappyD.setFont(new Font("Verdana",1,12));
			    JLabel farmD = new JLabel("<html>Play this game if you want to waste"
			    		+ "<br/>your life away doing nothing<br/>rating: 2/5<html>");
			    farmD.setFont(new Font("Verdana",1,12));
			    JLabel mineD = new JLabel("<html>This is literally the best game you"
			    		+ "<br/>will ever play in your life<br/>rating:10/5");
			    mineD.setFont(new Font("Verdana",1,12));
				
				//gets the pictures and puts them into jlabels
				try {
					falloutLabel = resize("https://upload.wikimedia.org/wikipedia/"
							+ "commons/6/68/Fallout_4_logo.png", 121, 100);
					flappyBirdLabel = resize("https://lh5.ggpht.com/vk1Rwgz-CetExFRGe0"
							+ "zJfhcTboOSPfNdo_R3wjlDE79_FuxOjOmGxAprDkEWvBpmxWPXvDbQnEL"
							+ "Wn5IFwvTwyYw3t4A=s0", 122, 90);
					farmvilleLabel = resize("https://c1.staticflickr.com/5/4060/453040998"
							+ "9_07013ddf82.jpg", 122, 100);
					minesweeperLabel = resize("https://upload.wikimedia.org/wikipedia/com"
							+ "mons/thumb/a/a9/Minesweeper_generic_end.svg/768px-Mineswe"
							+ "eper_generic_end.svg.png", 122, 100);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        		
                //Checks the selected string for what to do when clicked
                switch (s) {
                	case "Purchased":
					
					displayPanel.removeAll();
			    	descriptionPanel.removeAll();
			    	descriptionPanel.revalidate();
			    	descriptionPanel.repaint();
	        		//lines up the comboboxes
	        		//dc.fill = GridBagConstraints.HORIZONTAL;
	        		//start them at the top right of the screen
	        		dc.anchor = GridBagConstraints.FIRST_LINE_START;
	        		dc.weighty = 1;
	        		dc.weightx = 1;
	        		dc.gridx = 0;
	        		dc.gridy = 0;
            		displayPanel.add(falloutLabel,dc);
	        		dc.weighty = 1;
	        		dc.weightx = 1;
	        		dc.gridx = 1;
	        		dc.gridy = 0;
            		displayPanel.add(flappyBirdLabel,dc);
	        		dc.weighty = 1;
	        		dc.weightx = 1;
	        		dc.gridx = 2;
	        		dc.gridy = 0;
            		displayPanel.add(farmvilleLabel,dc);
	        		dc.weighty = 1;
	        		dc.weightx = 1;
	        		dc.gridx = 3;
	        		dc.gridy = 0;
            		displayPanel.add(minesweeperLabel,dc);
					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
					frame.setVisible(true);
					
					//for the fallout icon
	        		dc.anchor = GridBagConstraints.CENTER;
	        		dc.ipady = 50;
					falloutLabel.addMouseListener(new MouseAdapter(){
					    public void mouseClicked(MouseEvent e) {
					    	descriptionPanel.removeAll();
					    	descriptionPanel.revalidate();
					    	descriptionPanel.repaint();
					    	descriptionPanel.add(falloutD, dc);
					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
					        frame.setVisible(true);
					    }
					});
					
					//for the flappyD jlabel
					flappyBirdLabel.addMouseListener(new MouseAdapter(){
					    public void mouseClicked(MouseEvent e) {
					    	descriptionPanel.removeAll();
					    	descriptionPanel.revalidate();
					    	descriptionPanel.repaint();
					    	descriptionPanel.add(flappyD, dc);
					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
					        frame.setVisible(true);
					    }
					});
					
					//for farmville icon
					farmvilleLabel.addMouseListener(new MouseAdapter(){
					    public void mouseClicked(MouseEvent e) {
					    	descriptionPanel.removeAll();
					    	descriptionPanel.revalidate();
					    	descriptionPanel.repaint();
					    	descriptionPanel.add(farmD, dc);
					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
					        frame.setVisible(true);
					    }
					});
					
					//for minesweeper icon
					minesweeperLabel.addMouseListener(new MouseAdapter(){
					    public void mouseClicked(MouseEvent e) {
					    	descriptionPanel.removeAll();
					    	descriptionPanel.revalidate();
					    	descriptionPanel.repaint();
					    	descriptionPanel.add(mineD, dc);
					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
					        frame.setVisible(true);
					    }
					});
          		
                		break;
                    case "Fallout":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
						displayPanel.removeAll();
						try {
							falloutLabel = resize("https://upload.wikimedia.org/wikipedia/" + 
								 "commons/6/68/Fallout_4_logo.png", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		dc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		dc.weighty = 1;
    	        		dc.weightx = 1;
    	        		dc.gridx = 0;
    	        		dc.gridy = 0;
                		displayPanel.add(falloutLabel,dc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
				    	descriptionPanel.add(falloutD, dc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);

                        break;
                    case "FlappyBird":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							flappyBirdLabel = resize("https://lh5.ggpht.com/vk1Rwgz-CetExFRGe0"
									+ "zJfhcTboOSPfNdo_R3wjlDE79_FuxOjOmGxAprDkEWvBpmxWPXvDbQnEL"
									+ "Wn5IFwvTwyYw3t4A=s0", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		dc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		dc.weighty = 1;
    	        		dc.weightx = 1;
    	        		dc.gridx = 0;
    	        		dc.gridy = 0;
                		displayPanel.add(flappyBirdLabel,dc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(flappyD, dc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Farmville":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							farmvilleLabel = resize("https://c1.staticflickr.com/5/4060/453040998"
									+ "9_07013ddf82.jpg", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		dc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		dc.weighty = 1;
    	        		dc.weightx = 1;
    	        		dc.gridx = 0;
    	        		dc.gridy = 0;
                		displayPanel.add(farmvilleLabel,dc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(farmD, dc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Minesweeper":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							minesweeperLabel = resize("https://upload.wikimedia.org/wikipedia/com"
									+ "mons/thumb/a/a9/Minesweeper_generic_end.svg/768px-Mineswe"
									+ "eper_generic_end.svg.png", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		dc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		dc.weighty = 1;
    	        		dc.weightx = 1;
    	        		dc.gridx = 0;
    	        		dc.gridy = 0;
                		displayPanel.add(minesweeperLabel,dc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(mineD, dc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;                        
                }
            }
        });
		
		//*******************
		//Action Listener for CATAGORIES combobox
		//*******************
		Catagories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
            	//gets the combobox to use
                String s = (String) Catagories.getSelectedItem();
                //Checks the selected string for what to do when clicked
                
        		GridBagConstraints cc = new GridBagConstraints();
                
        		//creates the labels to be used in the display panel
				JLabel strategyLabel = null;
				JLabel adventureLabel = null;
				JLabel fpsLabel = null;
				JLabel actionLabel = null;
				JLabel mmoLabel = null;
				
				//labels used for the description box
			    JLabel strategyD = new JLabel("<html>Use your barin and do stuff"
			    		+ "<br/>it will be fun to use your brain for once<html>");
			    strategyD.setFont(new Font("Verdana",1,12));
			    JLabel adventureD = new JLabel("<html>Go on an adventure in your "
			    		+ "computer<br/> do it do it do it<html>");
			    adventureD.setFont(new Font("Verdana",1,12));
			    JLabel fpsD = new JLabel("<html>shoot people within your computer"
			    		+ "<br/>you will literally do this for hours<html>");
			    adventureD.setFont(new Font("Verdana",1,12));
			    JLabel actionD = new JLabel("<html>basically fps games but not al"
			    		+ "ways<br/>first person so you do you<htlm>");
			    actionD.setFont(new Font("Verdana",1,12));
			    JLabel mmoD = new JLabel("<html>Want to interact with people or m"
			    		+ "aybe<br/> just get online and troll someone<htlm>");
			    mmoD.setFont(new Font("Verdana",1,12));
				
				//gets the pictures and puts them into jlabels
				try {
					strategyLabel = resize("https://encrypted-tbn0.gstatic.com/images?q"
							+ "=tbn:ANd9GcRntT5jyqCsv6sjo81qLQDZ17T9cqDdS_ztNtOeECittXG"
							+ "mzkj0", 121, 100);
					adventureLabel = resize("https://encrypted-tbn0.gstatic.com/images?"
							+ "q=tbn:ANd9GcTfyfJAoAoWOG4Hs-7rsVoGZnan1S5otMLTbSP5"
							+ "BsmJgr3leGUTFg", 122, 90);
					fpsLabel = resize("https://i.vimeocdn.com/portrait/3720106_300x300", 122, 100);
					actionLabel = resize("https://encrypted-tbn0.gstatic.com/images?q"
							+ "=tbn:ANd9GcSjwfmX4Anws_7xc1illpiNW-qf8Syv_68ERbS0DVfC"
							+ "4RWIz401", 122, 100);
					mmoLabel = resize("https://encrypted-tbn0.gstatic.com/images?q=t"
							+ "bn:ANd9GcQUAplVgd2h0n5QYWWHr32qT-Cf43Opr69MrtstK2SdR"
							+ "47qOu0JcQ", 122, 100);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                
                switch (s) {
                	case "Catagories":
                    	
    					
    					displayPanel.removeAll();
    			    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
    	        		//lines up the comboboxes
    	        		//cc.fill = GridBagConstraints.HORIZONTAL;
    	        		//start them at the top right of the screen
    	        		cc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		cc.weighty = 0.1;
    	        		cc.weightx = 1;
    	        		cc.gridx = 0;
    	        		cc.gridy = 0;
                		displayPanel.add(strategyLabel,cc);
    	        		cc.weighty = 0;
    	        		cc.weightx = 1;
    	        		cc.gridx = 1;
    	        		cc.gridy = 0;
                		displayPanel.add(adventureLabel,cc);
    	        		cc.weighty = 0;
    	        		cc.weightx = 1;
    	        		cc.gridx = 2;
    	        		cc.gridy = 0;
                		displayPanel.add(fpsLabel,cc);
    	        		cc.weighty = 0;
    	        		cc.weightx = 0;
    	        		cc.gridx = 3;
    	        		cc.gridy = 0;
                		displayPanel.add(actionLabel,cc);
    	        		cc.weighty = 1;
    	        		cc.weightx = 1;
    	        		cc.gridx = 0;
    	        		cc.gridy = 1;
                		displayPanel.add(mmoLabel,cc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					frame.setVisible(true);
                		
    					
    					//for the strategy icon
    	        		cc.anchor = GridBagConstraints.CENTER;
    	        		cc.ipady = 50;
    					strategyLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(strategyD, cc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for the flappyD jlabel
    					adventureLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(adventureD, cc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for action icon
    					actionLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(actionD, cc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for fps icon
    					fpsLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(fpsD, cc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for mmo icon
    					mmoLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(mmoD, cc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
                		break;
                    case "Strategy":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							strategyLabel = resize("https://encrypted-tbn0.gstatic.com/images?q"
									+ "=tbn:ANd9GcRntT5jyqCsv6sjo81qLQDZ17T9cqDdS_ztNtOeECittXG"
									+ "mzkj0", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		cc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		cc.weighty = 1;
    	        		cc.weightx = 1;
    	        		cc.gridx = 0;
    	        		cc.gridy = 0;
                		displayPanel.add(strategyLabel,cc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(strategyD, cc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Adventure":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							adventureLabel = resize("https://encrypted-tbn0.gstatic.com/images?"
									+ "q=tbn:ANd9GcTfyfJAoAoWOG4Hs-7rsVoGZnan1S5otMLTbSP5"
									+ "BsmJgr3leGUTFg", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		cc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		cc.weighty = 1;
    	        		cc.weightx = 1;
    	        		cc.gridx = 0;
    	        		cc.gridy = 0;
                		displayPanel.add(adventureLabel,cc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(adventureD, cc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "FPS":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							fpsLabel = resize("https://i.vimeocdn.com/portrait/3720106_300x300", 487, 500);;
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		cc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		cc.weighty = 1;
    	        		cc.weightx = 1;
    	        		cc.gridx = 0;
    	        		cc.gridy = 0;
                		displayPanel.add(fpsLabel,cc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(fpsD, cc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Action":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							actionLabel = resize("https://encrypted-tbn0.gstatic.com/images?q"
									+ "=tbn:ANd9GcSjwfmX4Anws_7xc1illpiNW-qf8Syv_68ERbS0DVfC"
									+ "4RWIz401", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		cc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		cc.weighty = 1;
    	        		cc.weightx = 1;
    	        		cc.gridx = 0;
    	        		cc.gridy = 0;
                		displayPanel.add(actionLabel,cc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(actionD, cc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;          
                    case "MMO":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							mmoLabel = resize("https://encrypted-tbn0.gstatic.com/images?q=t"
									+ "bn:ANd9GcQUAplVgd2h0n5QYWWHr32qT-Cf43Opr69MrtstK2SdR"
									+ "47qOu0JcQ", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		cc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		cc.weighty = 1;
    	        		cc.weightx = 1;
    	        		cc.gridx = 0;
    	        		cc.gridy = 0;
                		displayPanel.add(mmoLabel,cc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(mmoD, cc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                    	break;
                }
            }
        });
		
		//*******************
		//Action Listener for RECENTLYPLAYED combobox
		//*******************
		RecentlyPlayed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
            	//gets the combobox to use
                String s = (String) RecentlyPlayed.getSelectedItem();
        		//creating a gridbaglayout for the panel
                //Checks the selected string for what to do when clicked
                
                //constraints for this
        		GridBagConstraints rc = new GridBagConstraints();
                
        		//creates the labels to be used in the display panel
				JLabel solitaireLabel = null;
				JLabel goldeneyeLabel = null;
				JLabel mariokartLabel = null;
				JLabel supersmashLabel = null;
				
				
				//labels used for the description box
			    JLabel solitaireD = new JLabel("<html>Solitaire will rock your wo"
			    		+ "rld<br/>play this game once and never want to play ano"
			    		+ "ther<br/>rating: 100/100<html>");
			    solitaireD.setFont(new Font("Verdana",1,12));
			    JLabel goldeneyeD = new JLabel("<html>Become the man of all men a"
			    		+ "nd<br/>all whilst getting babes<br/>game nsfw<br/>"
			    		+ "rating: 6/7<html>");
			    goldeneyeD.setFont(new Font("Verdana",1,12));
			    JLabel mariokartD = new JLabel("<html>Wanna play with your friend"
			    		+ "s while<br/>at the same losing all your friends, well"
			    		+ "<br/> this is your game<br/>rating: 5/5<html>");
			    mariokartD.setFont(new Font("Verdana",1,12));
			    JLabel supersmashD = new JLabel("<html>If you want to be annoying"
			    		+ "this is your game<br/>just play a character with range"
			    		+ "and shoot them<br/>rating: 20/19<html>");
			    supersmashD.setFont(new Font("Verdana",1,12));
			    
				//gets the pictures and puts them into jlabels
				try {
					solitaireLabel = resize("https://encrypted-tbn0.gstatic."
							+ "com/images?q=tbn:ANd9GcRrVhfwSQuJotaNI5SGarBh"
							+ "qQM_uUPM2qhRO4jZVSG9eAB5jFJglQ", 121, 100);
					goldeneyeLabel = resize("https://encrypted-tbn0.gstatic.com"
							+ "/images?q=tbn:ANd9GcSeJRh7OKTJeBreBaHMpPgdM02EL8"
							+ "giUhx1e8_RlrXy0XCS_Yn9", 122, 90);
					mariokartLabel = resize("https://encrypted-tbn0.gstatic.com/"
							+ "images?q=tbn:ANd9GcTfp-xwm0IOqhiBOvbprZhLpuL-LZtAZ"
							+ "Enc3Ba8T0qc64zV_LwkoA", 122, 100);
					supersmashLabel = resize("https://encrypted-tbn0.gstatic.com/"
							+ "images?q=tbn:ANd9GcQd0jnLDNZsqIxUgYoYI3wtwQEciylTY"
							+ "TBAdjw9U-V8amWcD8BY", 122, 100);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                
                switch (s) {
                
                	
                	case "Recent Plays":

    					
    					displayPanel.removeAll();
    			    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
    	        		//lines up the comboboxes
    	        		//rc.fill = GridBagConstraints.HORIZONTAL;
    	        		//start them at the top right of the screen
    	        		rc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		rc.weighty = 1;
    	        		rc.weightx = 1;
    	        		rc.gridx = 0;
    	        		rc.gridy = 0;
                		displayPanel.add(solitaireLabel,rc);
    	        		rc.weighty = 1;
    	        		rc.weightx = 1;
    	        		rc.gridx = 1;
    	        		rc.gridy = 0;
                		displayPanel.add(goldeneyeLabel,rc);
    	        		rc.weighty = 1;
    	        		rc.weightx = 1;
    	        		rc.gridx = 2;
    	        		rc.gridy = 0;
                		displayPanel.add(mariokartLabel,rc);
    	        		rc.weighty = 1;
    	        		rc.weightx = 1;
    	        		rc.gridx = 3;
    	        		rc.gridy = 0;
                		displayPanel.add(supersmashLabel,rc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					frame.setVisible(true);
                		
    					
    					
    					//for the solitaire icon
    	        		rc.anchor = GridBagConstraints.CENTER;
    	        		rc.ipady = 50;
    					solitaireLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(solitaireD, rc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for the flappyD jlabel
    					goldeneyeLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(goldeneyeD, rc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for mariokart icon
    					mariokartLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(mariokartD, rc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for supersmash icon
    					supersmashLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(supersmashD, rc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
                		break;
                    case "Solitaire":

    					//for the solitaire icon
    	        		rc.anchor = GridBagConstraints.CENTER;
    	        		rc.ipady = 50;
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	
    							displayPanel.removeAll();
    							try {
    								solitaireLabel = resize("https://encrypted-tbn0.gstatic."
    										+ "com/images?q=tbn:ANd9GcRrVhfwSQuJotaNI5SGarBh"
    										+ "qQM_uUPM2qhRO4jZVSG9eAB5jFJglQ", 487, 500);
    							} catch (IOException e1) {
    								e1.printStackTrace();
    							}
    	    	        		rc.anchor = GridBagConstraints.FIRST_LINE_START;
    	    	        		rc.weighty = 1;
    	    	        		rc.weightx = 1;
    	    	        		rc.gridx = 0;
    	    	        		rc.gridy = 0;
    	                		displayPanel.add(solitaireLabel,rc);
    	    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    	    					
    					    	descriptionPanel.add(solitaireD, rc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
                    	
                        break;
                    case "Golden Eye":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							goldeneyeLabel = resize("https://encrypted-tbn0.gstatic.com"
									+ "/images?q=tbn:ANd9GcSeJRh7OKTJeBreBaHMpPgdM02EL8"
									+ "giUhx1e8_RlrXy0XCS_Yn9", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		rc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		rc.weighty = 1;
    	        		rc.weightx = 1;
    	        		rc.gridx = 0;
    	        		rc.gridy = 0;
                		displayPanel.add(goldeneyeLabel,rc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(goldeneyeD, rc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Mario Kart":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							mariokartLabel = resize("https://encrypted-tbn0.gstatic.com/"
									+ "images?q=tbn:ANd9GcTfp-xwm0IOqhiBOvbprZhLpuL-LZtAZ"
									+ "Enc3Ba8T0qc64zV_LwkoA", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		rc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		rc.weighty = 1;
    	        		rc.weightx = 1;
    	        		rc.gridx = 0;
    	        		rc.gridy = 0;
                		displayPanel.add(mariokartLabel,rc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(mariokartD, rc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Super Smash Bros":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							supersmashLabel = resize("https://encrypted-tbn0.gstatic.com/"
									+ "images?q=tbn:ANd9GcQd0jnLDNZsqIxUgYoYI3wtwQEciylTY"
									+ "TBAdjw9U-V8amWcD8BY", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		rc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		rc.weighty = 1;
    	        		rc.weightx = 1;
    	        		rc.gridx = 0;
    	        		rc.gridy = 0;
                		displayPanel.add(supersmashLabel,rc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
				    	
				    	descriptionPanel.add(supersmashD, rc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;                        
                }
            }
        });
		
		//*******************
		//Action Listener for Popular combobox
		//*******************
		Popular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
            	//gets the combobox to use
                String s = (String) Popular.getSelectedItem();
                //Checks the selected string for what to do when clicked
                
        		GridBagConstraints popc = new GridBagConstraints();
                
        		//creates the labels to be used in the display panel
				JLabel battlefrontLabel = null;
				JLabel titanfallLabel = null;
				JLabel goatLabel = null;
				JLabel portalLabel = null;
				
				//labels used for the description box
			    JLabel battlefrontD = new JLabel("<html>Wanna play a game and not"
			    		+ "be able to win<br/>without paying money well here you "
			    		+ "can buy your way to the top<br/>rating: 0/1000<html>");
			    battlefrontD.setFont(new Font("Verdana",1,12));
			    JLabel titanfallD = new JLabel("<html>Play a game just like any"
			    		+ "other game.<br/>shoot things and do stuff and fly around"
			    		+ "<br/>rating: 8.5/10<html>");
			    titanfallD.setFont(new Font("Verdana",1,12));
			    JLabel goatD = new JLabel("<html>The best game ever created run"
			    		+ "around being derpy and<br/> finding glitches, all while"
			    		+ "earning achievements doing so<br/>rating: 1000/1000<html>");
			    goatD.setFont(new Font("Verdana",1,12));
			    JLabel portalD = new JLabel("<html>Hurt your brain all while having"
			    		+ "fun doing so<br/>a mix of comedy and puzzles<br/>"
			    		+ "rating: 9.5/10<html>");
			    portalD.setFont(new Font("Verdana",1,12));
			    
				//gets the pictures and puts them into jlabels
				try {
					battlefrontLabel = resize("https://encrypted-tbn0.gstatic.com"
							+ "/images?q=tbn:ANd9GcS1enjFeqppNofCdSJUwVKCEDXXhm3N"
							+ "M1gYGEwYLgtpf_gxJdDLlw", 121, 100);
					titanfallLabel = resize("https://encrypted-tbn0.gstatic.com/i"
							+ "mages?q=tbn:ANd9GcQ5mBN_yMzpklr3N2cAnbhNk67l22xq2C"
							+ "TLCpixcjtUQnw8HvzQ", 122, 90);
					goatLabel = resize("https://www.speedrun.com/themes/goat/cove"
							+ "r-256.png", 122, 100);
					portalLabel = resize("https://upload.wikimedia.org/wikipedia/"
							+ "commons/d/df/Portal_logo.png", 122, 100);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                
                switch (s) {
                	case "Popular":
    					
    					displayPanel.removeAll();
    			    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
    	        		//lines up the comboboxes
    	        		//popc.fill = GridBagConstraints.HORIZONTAL;
    	        		//start them at the top right of the screen
    	        		popc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		popc.weighty = 1;
    	        		popc.weightx = 1;
    	        		popc.gridx = 0;
    	        		popc.gridy = 0;
                		displayPanel.add(battlefrontLabel,popc);
    	        		popc.weighty = 1;
    	        		popc.weightx = 1;
    	        		popc.gridx = 1;
    	        		popc.gridy = 0;
                		displayPanel.add(titanfallLabel,popc);
    	        		popc.weighty = 1;
    	        		popc.weightx = 1;
    	        		popc.gridx = 2;
    	        		popc.gridy = 0;
                		displayPanel.add(goatLabel,popc);
    	        		popc.weighty = 1;
    	        		popc.weightx = 1;
    	        		popc.gridx = 3;
    	        		popc.gridy = 0;
                		displayPanel.add(portalLabel,popc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					frame.setVisible(true);
                		
    					
    					
    					//for the battlefront icon
    	        		popc.anchor = GridBagConstraints.CENTER;
    	        		popc.ipady = 50;
    					battlefrontLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(battlefrontD, popc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for the flappyD jlabel
    					titanfallLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(titanfallD, popc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for goat icon
    					goatLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(goatD, popc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for portal icon
    					portalLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(portalD, popc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
                		break;
                    case "Not Battlefront II":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							battlefrontLabel = resize("https://encrypted-tbn0.gstatic.com"
									+ "/images?q=tbn:ANd9GcS1enjFeqppNofCdSJUwVKCEDXXhm3N"
									+ "M1gYGEwYLgtpf_gxJdDLlw", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		popc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		popc.weighty = 1;
    	        		popc.weightx = 1;
    	        		popc.gridx = 0;
    	        		popc.gridy = 0;
                		displayPanel.add(battlefrontLabel,popc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(battlefrontD, popc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Titanfall":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							titanfallLabel = resize("https://encrypted-tbn0.gstatic.com/i"
									+ "mages?q=tbn:ANd9GcQ5mBN_yMzpklr3N2cAnbhNk67l22xq2C"
									+ "TLCpixcjtUQnw8HvzQ", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		popc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		popc.weighty = 1;
    	        		popc.weightx = 1;
    	        		popc.gridx = 0;
    	        		popc.gridy = 0;
                		displayPanel.add(titanfallLabel,popc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
				    	
				    	descriptionPanel.add(titanfallD, popc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Goat Simulator":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							goatLabel = resize("https://www.speedrun.com/themes/goat/cove"
									+ "r-256.png", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		popc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		popc.weighty = 1;
    	        		popc.weightx = 1;
    	        		popc.gridx = 0;
    	        		popc.gridy = 0;
                		displayPanel.add(goatLabel,popc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(goatD, popc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Portal":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							portalLabel = resize("https://upload.wikimedia.org/wikipedia/"
									+ "commons/d/df/Portal_logo.png", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		popc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		popc.weighty = 1;
    	        		popc.weightx = 1;
    	        		popc.gridx = 0;
    	        		popc.gridy = 0;
                		displayPanel.add(portalLabel,popc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(portalD, popc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;                        
                }
            }
        });
		
		//*******************
		//Action Listener for New Releases combobox
		//*******************
		NewReleases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
            	//gets the combobox to use
                String s = (String) NewReleases.getSelectedItem();
                //Checks the selected string for what to do when clicked
                
        		GridBagConstraints classc = new GridBagConstraints();
                
        		//creates the labels to be used in the display panel
				JLabel metalgearLabel = null;
				JLabel pokemonLabel = null;
				JLabel codLabel = null;
				JLabel supermarioLabel = null;
				
				//labels used for the description box
			    JLabel metalgearD = new JLabel("<html>Wanna play the same shootin"
			    		+ "g game as any other.<br/>It is extrordinarily unextrordinary"
			    		+ "<br/>rating: 2/4<html>");
			    metalgearD.setFont(new Font("Verdana",1,12));
			    JLabel pokemonD = new JLabel("<html>Best game ever created it is "
			    		+ "such a good game that they<br/> actually created multi"
			    		+ "ple generations of it<br/>rating: 10/10<html>");
			    pokemonD.setFont(new Font("Verdana",1,12));
			    JLabel codD = new JLabel("<html>Pew Pew shoot shoot. shoot at you"
			    		+ "friends and stuff<br/>rating: 10/10<html>");
			    codD.setFont(new Font("Verdana",1,12));
			    JLabel supermarioD = new JLabel("<html>jumpy jumpy. just jump and"
			    		+ "kill stuff. Your basicaly a<br/> murderer in this game"
			    		+ "all to steal bowser's girl<br/>rating: 10/10<html>");
			    supermarioD.setFont(new Font("Verdana",1,12));
			    
				//gets the pictures and puts them into jlabels
				try {
					metalgearLabel = resize("https://encrypted-tbn0.gstatic.com/i"
							+ "mages?q=tbn:ANd9GcTUkYjwAbazPkMGf2Bs-pcQGJc748tSd0"
							+ "1sLlJQnbsjyeBQxxrN", 121, 100);
					pokemonLabel = resize("https://encrypted-tbn0.gstatic.com/ima"
							+ "ges?q=tbn:ANd9GcQMewLhPX3pQ8clLhNyaYt1dvw62jeNy_xf"
							+ "o7wjKGwJIYWx0o-oeA", 122, 90);
					codLabel = resize("https://encrypted-tbn0.gstatic.com/images?"
							+ "q=tbn:ANd9GcRE4YzRcd65jZdmrlB33rupdkIUCBpRAyZlnPRu"
							+ "YWXXuIC07M0Jsw", 122, 100);
					supermarioLabel = resize("https://encrypted-tbn0.gstatic.com/"
							+ "images?q=tbn:ANd9GcRr0UyO39lRTnnz9y6rB5wy993wYntu2"
							+ "jcg91ItlaFTQzBnNv3EZg", 122, 100);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                
                switch (s) {
                	case "New Releases":
    					
    					displayPanel.removeAll();
    			    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
    	        		//lines up the comboboxes
    	        		//classc.fill = GridBagConstraints.HORIZONTAL;
    	        		//start them at the top right of the screen
    	        		classc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		classc.weighty = 1;
    	        		classc.weightx = 1;
    	        		classc.gridx = 0;
    	        		classc.gridy = 0;
                		displayPanel.add(metalgearLabel,classc);
    	        		classc.weighty = 1;
    	        		classc.weightx = 1;
    	        		classc.gridx = 1;
    	        		classc.gridy = 0;
                		displayPanel.add(pokemonLabel,classc);
    	        		classc.weighty = 1;
    	        		classc.weightx = 1;
    	        		classc.gridx = 2;
    	        		classc.gridy = 0;
                		displayPanel.add(codLabel,classc);
    	        		classc.weighty = 1;
    	        		classc.weightx = 1;
    	        		classc.gridx = 3;
    	        		classc.gridy = 0;
                		displayPanel.add(supermarioLabel,classc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					frame.setVisible(true);
                		
    					//for the metalgear icon
    	        		classc.anchor = GridBagConstraints.CENTER;
    	        		classc.ipady = 50;
    					metalgearLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(metalgearD, classc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for the flappyD jlabel
    					pokemonLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(pokemonD, classc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for cod icon
    					codLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(codD, classc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					//for supermario icon
    					supermarioLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(supermarioD, classc);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
    					
                		break;
                    case "Metal Gear":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							metalgearLabel = resize("https://encrypted-tbn0.gstatic.com/i"
									+ "mages?q=tbn:ANd9GcTUkYjwAbazPkMGf2Bs-pcQGJc748tSd0"
									+ "1sLlJQnbsjyeBQxxrN", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		classc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		classc.weighty = 1;
    	        		classc.weightx = 1;
    	        		classc.gridx = 0;
    	        		classc.gridy = 0;
                		displayPanel.add(metalgearLabel,classc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(metalgearD, classc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Pokemon":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							pokemonLabel = resize("https://encrypted-tbn0.gstatic.com/ima"
									+ "ges?q=tbn:ANd9GcQMewLhPX3pQ8clLhNyaYt1dvw62jeNy_xf"
									+ "o7wjKGwJIYWx0o-oeA", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		classc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		classc.weighty = 1;
    	        		classc.weightx = 1;
    	        		classc.gridx = 0;
    	        		classc.gridy = 0;
                		displayPanel.add(pokemonLabel,classc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(pokemonD, classc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "COD":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							codLabel = resize("https://encrypted-tbn0.gstatic.com/images?"
									+ "q=tbn:ANd9GcRE4YzRcd65jZdmrlB33rupdkIUCBpRAyZlnPRu"
									+ "YWXXuIC07M0Jsw", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		classc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		classc.weighty = 1;
    	        		classc.weightx = 1;
    	        		classc.gridx = 0;
    	        		classc.gridy = 0;
                		displayPanel.add(codLabel,classc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(codD, classc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;
                    case "Super Mario":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							supermarioLabel = resize("https://encrypted-tbn0.gstatic.com/"
									+ "images?q=tbn:ANd9GcRr0UyO39lRTnnz9y6rB5wy993wYntu2"
									+ "jcg91ItlaFTQzBnNv3EZg", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		classc.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		classc.weighty = 1;
    	        		classc.weightx = 1;
    	        		classc.gridx = 0;
    	        		classc.gridy = 0;
                		displayPanel.add(supermarioLabel,classc);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					
				    	descriptionPanel.add(supermarioD, classc);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;                        
                }
            }
        });
		
		//*******************
		//Action Listener for FREE GAMES combobox
		//*******************
		Free.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
            	//gets the combobox to use
                String s = (String) Free.getSelectedItem();
                //Checks the selected string for what to do when clicked
                
        		GridBagConstraints freec = new GridBagConstraints();
                
        		//creates the labels to be used in the display panel
				JLabel freeLabel = null;
				
			    JLabel freegamesD = new JLabel("<html>There are no fun free games"
			    		+ "dont bother<br/>looking for any as you will be unsucce"
			    		+ "sful<br/>rating: NA/NA<html>");
			    freegamesD.setFont(new Font("Verdana",1,12));
				
				//gets the pictures and puts them into jlabels
				try {
					freeLabel = resize("https://encrypted-tbn0.gstatic.com/i"
							+ "mages?q=tbn:ANd9GcQS-XC3ZsfKmSn-gLbxNGzqp_jKiNP-4e"
							+ "kr4fsQP8vA9uTbziuv", 487, 500);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                
                switch (s) {
                //"Free Games", "nothing"
                	case "Free Games":
    					
    					displayPanel.removeAll();
    			    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
    	        		//lines up the comboboxes
    	        		//freec.fill = GridBagConstraints.HORIZONTAL;
    	        		//start them at the top right of the screen
    	        		freec.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		freec.weighty = 1;
    	        		freec.weightx = 1;
    	        		freec.gridx = 0;
    	        		freec.gridy = 0;
                		displayPanel.add(freeLabel,freec);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);
    					frame.setVisible(true);
    					
    					//free games jlabel listener
    	        		freec.anchor = GridBagConstraints.CENTER;
    	        		freec.ipady = 50;
    					freeLabel.addMouseListener(new MouseAdapter(){
    					    public void mouseClicked(MouseEvent e) {
    					    	descriptionPanel.removeAll();
    					    	descriptionPanel.revalidate();
    					    	descriptionPanel.repaint();
    					    	descriptionPanel.add(freegamesD, freec);
    					        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
    					        frame.setVisible(true);
    					    }
    					});
    					
                		break;
                    case "nothing":
				    	descriptionPanel.removeAll();
				    	descriptionPanel.revalidate();
				    	descriptionPanel.repaint();
				    	
						displayPanel.removeAll();
						try {
							freeLabel = resize("https://encrypted-tbn0.gstatic.com/i"
									+ "mages?q=tbn:ANd9GcQS-XC3ZsfKmSn-gLbxNGzqp_jKiNP-4e"
									+ "kr4fsQP8vA9uTbziuv", 487, 500);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    	        		freec.anchor = GridBagConstraints.FIRST_LINE_START;
    	        		freec.weighty = 1;
    	        		freec.weightx = 1;
    	        		freec.gridx = 0;
    	        		freec.gridy = 0;
                		displayPanel.add(freeLabel,freec);
    					frame.getContentPane().add(displayPanel,BorderLayout.EAST);

				    	descriptionPanel.add(freegamesD, freec);
				        frame.getContentPane().add(descriptionPanel, BorderLayout.PAGE_END);
				        frame.setVisible(true);
                    	
                        break;                      
                }
            }
        });
		

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
	}
	
	//**********************************************************************
	// Private Inner Classes
	//**********************************************************************
	
	private static final class ComboBoxPanel extends JPanel
	{
		
		Color cb;
		Color cf;
		int h;
		int w;
		
		public ComboBoxPanel(Color colorB, Color colorF, int height, int width)
		{
			cb = colorB;
			cf = colorF;
			h = height;
			w = width;
		}

		public void	paintComponent(Graphics g)
		{
			Rectangle r = new Rectangle(h, w);
			
			if (FILL_COLOR != null)
			{
				g.setColor(cf);
				g.fillRect(r.x, r.y, r.width - 1, r.height - 1);
			}

			if (EDGE_COLOR != null)
			{
				g.setColor(cb);
				g.drawRect(r.x, r.y, r.width - 1, r.height - 1);
			}
		}
	}
}

//******************************************************************************