package edu.ou.cs.hci.stages.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.ou.cs.hci.stages.frames.ClubPenguin;
import edu.ou.cs.hci.stages.util.Game;

//GameCard class will create cards that show the game picture and title
public class GameCard extends JPanel {
	
	private static final long serialVersionUID = -1585992822453830200L;
	
	Game game;
	Object cGame;
	
	public GameCard() {
		
		this(new Game());
	}

	public GameCard(Game game) {
		this.setBackground(hex2Rgb("#FFFFFF"));
		this.game = game;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {	
				ClubPenguin.setGame(game);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(BorderFactory.createLineBorder(hex2Rgb("#ffd3a7"),5));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(2, 2, 2, 2),
						BorderFactory.createLineBorder(Color.black)));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		//Set Layout stuff
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(2, 2, 2, 2),
				BorderFactory.createLineBorder(Color.black)));
		//Add some basic filler components
		this.add(new JLabel("image"), BorderLayout.NORTH);
		this.add(new JLabel(game.getName()), BorderLayout.SOUTH);
	}
	public void RemoveBorder()
	{
		setBorder(BorderFactory.createLineBorder(hex2Rgb("#ffd3a7"),-1));
	}

	public static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
	
	
}