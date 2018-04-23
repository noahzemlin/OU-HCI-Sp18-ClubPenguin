package edu.ou.cs.hci.stages.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import edu.ou.cs.hci.stages.util.Game;

public class GameInfoPanel extends JPanel{

	private static final long serialVersionUID = -4597173753271660181L;
	
	public static boolean changedData = false;
	
	Game game;
	GameCard gameCard;
	
	JLabel nameLabel;
	JLabel descLabel;
	JLabel publisherLabel;
	JLabel developerLabel;
	JTextField tagsLabel;
	JLabel genreLabel;

	public GameInfoPanel() {
		this.setBackground(hex2Rgb("#A0F2FF"));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = 1;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = .25;
		
		c.gridy = 0;
		nameLabel = new JLabel("No name.");
		this.add(nameLabel, c);
		
		c.gridy = 1;
		descLabel = new JLabel("No description.");
		this.add(descLabel, c);
		
		c.gridy = 2;
		publisherLabel = new JLabel("No publisher.");
		this.add(publisherLabel, c);
		
		c.gridy = 3;
		developerLabel = new JLabel("No developer.");
		this.add(developerLabel, c);
		
		c.gridy = 4;
		tagsLabel = new JTextField("No tags.");
		this.add(tagsLabel, c);
		
		c.gridy = 5;
		genreLabel = new JLabel("No genres.");
		this.add(genreLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.05;
		c.weighty = 0.05;
		JButton exit = new JButton("X");
		this.add(exit, c);
		
		GameInfoPanel panel = this;
		
		exit.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				game = null;
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
		
		tagsLabel.addCaretListener(new CaretListener()  {

			@Override
			public void caretUpdate(CaretEvent arg0) {
				if (tagsLabel != null && game != null) {
					if (tagsLabel.getText() != game.getTags()) {
						changedData = true;
						gameCard.getGame().setTags(tagsLabel.getText());
					}
				}
				
			}}
		);
	}

	public Game getCurrentGame() {
		return game;
	}
	
	public void setGame(GameCard gamecardin) {

		
		this.setVisible(true);
		
		game = gamecardin.getGame();
		gameCard = gamecardin;
		
		nameLabel.setText(game.getName());
		
		descLabel.setText(game.getDescription());
		
		publisherLabel.setText(game.getPublishers());
		
		developerLabel.setText(game.getDevelopers());
		
		genreLabel.setText(game.getGenres());
		
		tagsLabel.setText(game.getTags());
	}
	public static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
}
