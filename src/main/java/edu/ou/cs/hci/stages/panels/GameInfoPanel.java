package edu.ou.cs.hci.stages.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ou.cs.hci.stages.util.Game;

public class GameInfoPanel extends JPanel{

	private static final long serialVersionUID = -4597173753271660181L;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	Game game;
	
	JLabel nameLabel;
	JLabel descLabel;
	JLabel dateLabel;
	JTextField tagsBox;

	public GameInfoPanel() {
		
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
		dateLabel = new JLabel("No date.");
		this.add(dateLabel, c);
		
		c.gridy = 3;
		tagsBox = new JTextField();
		this.add(tagsBox, c);
		
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
		
	}
	
	public Game getCurrentGame() {
		return game;
	}
	
	public void setGame(Game game) {
		
		this.setVisible(true);
		
		this.game = game;
		
		nameLabel.setText(game.getName());
		
		descLabel.setText(game.getDescription());
		
		dateLabel.setText(dateFormat.format(game.getReleaseDate().getTime()));
		
		tagsBox.setText(game.getTagsAsString());
	}
	
}
