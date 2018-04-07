package edu.ou.cs.hci.stages.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import edu.ou.cs.hci.stages.frames.ClubPenguin;
import edu.ou.cs.hci.stages.util.Game;

//GameCard class will create cards that show the game picture and title
public class GameCard extends JPanel {
	
	private static final long serialVersionUID = -1585992822453830200L;
	
	private static final ArrayList<GameCard> cards = new ArrayList<GameCard>();
	
	private static GameCard selected = null;
	GameCard thisCard;
	Game game;
	
	public GameCard() {
		
		this(new Game());
		cards.add(this);
		thisCard = this;
	}

	public GameCard(Game game) {
		
		this.game = game;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ClubPenguin.setGame(game);
				selected = thisCard;
				
				for(GameCard card : cards) {
					card.selectionChange();
				}
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
		this.setBackground(new Color(255, 255, 255));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(167, 211, 255), 10), BorderFactory.createLineBorder(new Color(200, 200, 200))));
		
		//Add some basic filler components
		this.add(new JLabel("image"), BorderLayout.NORTH);
		this.add(new JLabel(game.getName()), BorderLayout.SOUTH);
	}
	
	public void selectionChange() {
		if (selected == this) {
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(167, 211, 255), 10), BorderFactory.createLineBorder(new Color(255, 211, 167), 3)));
		} else {
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(167, 211, 255), 10), BorderFactory.createLineBorder(new Color(200, 200, 200))));
		}
	}
	
}