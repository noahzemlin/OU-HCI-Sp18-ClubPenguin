package edu.ou.cs.hci.stages.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
<<<<<<< HEAD
=======
import javax.swing.border.EmptyBorder;

import edu.ou.cs.hci.resources.Resources;
>>>>>>> master
import edu.ou.cs.hci.stages.frames.ClubPenguin;
import edu.ou.cs.hci.stages.util.Game;

//GameCard class will create cards that show the game picture and title
public class GameCard extends JPanel {
	
	private static final long serialVersionUID = -1585992822453830200L;
	
	private static final ArrayList<GameCard> cards = new ArrayList<GameCard>();
	
	private static GameCard selected = null;
	GameCard thisCard;
	Game game;
	GameCard _instance;
	
	private static final int picSize = 50;
	
	public GameCard() {
		
		this(new Game());
<<<<<<< HEAD
		cards.add(this);
		thisCard = this;
=======
		
>>>>>>> master
	}

	public GameCard(Game game) {
		_instance = this;
		this.setBackground(hex2Rgb("#FFFFFF"));
		this.game = game;
		this.addMouseListener(new MouseListener() {

			@Override
<<<<<<< HEAD
			public void mouseClicked(MouseEvent e) {
				ClubPenguin.setGame(game);
				selected = thisCard;
				
				for(GameCard card : cards) {
					card.selectionChange();
				}
=======
			public void mouseClicked(MouseEvent e) {	
				ClubPenguin.setGame(_instance);
>>>>>>> master
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
<<<<<<< HEAD
		this.setBackground(new Color(255, 255, 255));
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(167, 211, 255), 10), BorderFactory.createLineBorder(new Color(200, 200, 200))));
=======
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(2, 2, 2, 2),
				BorderFactory.createLineBorder(Color.black)));

>>>>>>> master
		
		JLabel picture = new JLabel();
		if (game.getPicture() != null && game.getPicture().length() > 0) {
			ImageIcon pic = Resources.getImage("images/" + game.getPicture());
			if (pic != null)
				picture.setIcon(new ImageIcon(pic.getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH)));
		}
		this.add(picture, BorderLayout.NORTH);
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
	
<<<<<<< HEAD
	public void selectionChange() {
		if (selected == this) {
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(167, 211, 255), 10), BorderFactory.createLineBorder(new Color(255, 211, 167), 3)));
		} else {
			this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(167, 211, 255), 10), BorderFactory.createLineBorder(new Color(200, 200, 200))));
		}
	}
=======
	public Game getGame() {
		return game;
	}
	
>>>>>>> master
	
}