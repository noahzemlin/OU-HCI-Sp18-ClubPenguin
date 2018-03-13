package edu.ou.cs.hci.stages.panels;

import java.awt.BorderLayout;
import java.awt.Color;
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
	
	public GameCard() {
		
		this(new Game());
	}

	public GameCard(Game game) {
		
		this.game = game;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ClubPenguin.setGame(game);
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		//Set Layout stuff
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.black)));
		
		//Add some basic filler components
		this.add(new JLabel("image"), BorderLayout.NORTH);
		this.add(new JLabel(game.getName()), BorderLayout.SOUTH);
	}
	
	
	
}