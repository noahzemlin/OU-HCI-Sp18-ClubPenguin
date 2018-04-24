package edu.ou.cs.hci.stages.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import edu.ou.cs.hci.stages.util.Game;

public class GameInfoPanel extends JPanel{

	private static final long serialVersionUID = -4597173753271660181L;
	
	public static boolean changedData = false;
	
	Game game;
	GameCard gameCard;
	
	NameValuePair nameLabel;
	NameValuePair descLabel;
	NameValuePair publisherLabel;
	NameValuePair developerLabel;
	
	NameValuePairEditable tagsLabel;
	NameValuePairEditable genreLabel;

	public GameInfoPanel() {

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = 1;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = .25;
		
		c.gridy = 0;
		nameLabel = new NameValuePair("Name", "No name.");
		this.add(nameLabel, c);
		
		c.gridy = 1;
		descLabel = new NameValuePair("Description", "No description.");
		descLabel.pair.setPreferredSize(new Dimension(0, 100));
		this.add(descLabel, c);
		
		c.gridy = 2;
		publisherLabel = new NameValuePair("Publisher", "No publisher.");
		this.add(publisherLabel, c);
		
		c.gridy = 3;
		developerLabel = new NameValuePair("Developer", "No developer.");
		this.add(developerLabel, c);
		
		c.gridy = 4;
		tagsLabel = new NameValuePairEditable("Tags", "No tags.");
		this.add(tagsLabel, c);
		
		c.gridy = 5;
		genreLabel = new NameValuePairEditable("Genres", "No genres.");
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
		
		tagsLabel.pair.addCaretListener(new CaretListener()  {

			@Override
			public void caretUpdate(CaretEvent arg0) {
				if (tagsLabel != null && game != null) {
					if (tagsLabel.pair.getText() != game.getTags()) {
						changedData = true;
						gameCard.getGame().setTags(tagsLabel.pair.getText());
					}
				}
				
			}}
		);
		
		genreLabel.pair.addCaretListener(new CaretListener()  {

			@Override
			public void caretUpdate(CaretEvent arg0) {
				if (genreLabel != null && game != null) {
					if (genreLabel.pair.getText() != game.getGenres()) {
						changedData = true;
						gameCard.getGame().setGenres(genreLabel.pair.getText());
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
		
		nameLabel.pair.setText(game.getName());
		
		descLabel.pair.setText(game.getDescription());
		
		publisherLabel.pair.setText(game.getPublishers());
		
		developerLabel.pair.setText(game.getDevelopers());
		
		genreLabel.pair.setText(game.getGenres());
		
		tagsLabel.pair.setText(game.getTags());
	}
	
}

class NameValuePair extends JPanel{
	public JLabel name;
	public JTextArea pair;
	
	public NameValuePair(String name, String defaultVal) {
		
		this.setBackground(null);
		
		this.name = new JLabel(name + ": ");
		this.pair = new JTextArea(defaultVal);
		this.pair.setLineWrap(true);
		this.pair.setWrapStyleWord(true);
		this.pair.setEditable(false);
		this.pair.setFocusable(false);
		this.pair.setMaximumSize(new Dimension(0, 50));
		this.pair.setBackground(UIManager.getColor("Label.background"));
		this.pair.setBorder(UIManager.getBorder("Label.border"));
		
		JScrollPane scroll = new JScrollPane (this.pair);
		
		this.setLayout(new BorderLayout());
		
		this.add(this.name, BorderLayout.WEST);
		this.add(scroll, BorderLayout.CENTER);
	}
}

class NameValuePairEditable extends JPanel{
	public JLabel name;
	public JTextField pair;
	
	public NameValuePairEditable(String name, String defaultVal) {
		
		this.setBackground(null);
		
		this.name = new JLabel(name + ": ");
		this.pair = new JTextField(defaultVal);
		
		this.setLayout(new BorderLayout());
		
		this.add(this.name, BorderLayout.WEST);
		this.add(this.pair, BorderLayout.CENTER);
	}
}