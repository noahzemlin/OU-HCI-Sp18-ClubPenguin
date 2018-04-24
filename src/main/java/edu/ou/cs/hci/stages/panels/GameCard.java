package edu.ou.cs.hci.stages.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.ou.cs.hci.resources.Resources;
import edu.ou.cs.hci.stages.frames.ClubPenguin;
import edu.ou.cs.hci.stages.util.Game;

//GameCard class will create cards that show the game picture and title
public class GameCard extends JPanel {
	
	private static final long serialVersionUID = -1585992822453830200L;
	
	private static final ArrayList<GameCard> cards = new ArrayList<GameCard>();
	
	public static GameCard selected = null;
	GameCard thisCard;
	Game game;
	
	public GameCard() {
		this(new Game());
	}

	public GameCard(Game game) {
		thisCard = this;
		cards.add(this);
		this.setBackground(Color.WHITE);
		this.game = game;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ClubPenguin.setGame(thisCard);
				selected = thisCard;
				
				for(GameCard card : cards) {
					card.selectionChange();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
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
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel picture = new JLabel();
		if (game.getPicture() != null && game.getPicture().length() > 0) {
			ImageIcon pic = Resources.getImage("images/" + game.getPicture());
			if (pic != null)
				picture.setIcon(new ImageIcon(pic.getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH)));
		}
		this.add(picture, BorderLayout.NORTH);
		this.add(new JLabel(game.getName()), BorderLayout.SOUTH);
	}

	public void selectionChange() {
		if (selected == this) {
			this.setBorder(BorderFactory.createLineBorder(new Color(255, 211, 167), 5));		
		} else {
			this.setBorder(new EmptyBorder(5, 5, 5, 5));
		}
	}
	
	public Game getGame() {
		return game;
	}

	
}