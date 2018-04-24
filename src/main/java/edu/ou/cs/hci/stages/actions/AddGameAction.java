package edu.ou.cs.hci.stages.actions;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import edu.ou.cs.hci.resources.Resources;
import edu.ou.cs.hci.stages.frames.ClubPenguin;
import edu.ou.cs.hci.stages.panels.GameCard;
import edu.ou.cs.hci.stages.util.Game;

public class AddGameAction extends AbstractAction {

	private static final long serialVersionUID = 6916343421279143814L;

	class QPanel extends JPanel {

		private static final long serialVersionUID = 1119620964758351695L;

		//used to easier add games
		public QPanel(String question, JComponent answer)
		{
			super(new GridLayout(0, 2));

			JTextArea label = new JTextArea(question);
			label.setLineWrap(true);
			label.setWrapStyleWord(true);
			label.setEditable(false);
			label.setFocusable(false);
			label.setBackground(UIManager.getColor("Label.background"));
			label.setBorder(UIManager.getBorder("Label.border"));

			this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

			this.add(label);
			this.add(answer);
		}

	}

	public AddGameAction() {
		super("Add Game");
		putValue(Action.LARGE_ICON_KEY, new ImageIcon(Resources.getImage("icons/add_game.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Game game = new Game();
		JFrame frame = new JFrame(); 
		frame.setSize(800, 400);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(7, 2));

		//game name
		JTextField r1Input = new JTextField();
		JPanel r1 = new JPanel(new BorderLayout());
		r1.add(r1Input, BorderLayout.NORTH);
		QPanel q1 = new QPanel("Game name?", r1);
		mainPanel.add(q1);

		//game description
		JTextField r2Input = new JTextField();
		JPanel r2 = new JPanel(new BorderLayout());
		r2.add(r2Input, BorderLayout.NORTH);
		QPanel q2 = new QPanel("Game Description?", r2);
		mainPanel.add(q2);

		//add game image
		JTextField r3Input = new JTextField();
		JPanel r3 = new JPanel(new BorderLayout());
		r3.add(r3Input, BorderLayout.NORTH);
		QPanel q3 = new QPanel("Image url to add?", r3);
		r3Input.setText("Fallout4.png");
		r3Input.setEnabled(false);
		mainPanel.add(q3);
		
		//add game image
		JTextField r4Input = new JTextField();
		JPanel r4 = new JPanel(new BorderLayout());
		r4.add(r4Input, BorderLayout.NORTH);
		QPanel q4 = new QPanel("Game Developer?", r4);
		mainPanel.add(q4);
		
		//add game image
		JTextField r5Input = new JTextField();
		JPanel r5 = new JPanel(new BorderLayout());
		r5.add(r5Input, BorderLayout.NORTH);
		QPanel q5 = new QPanel("Game Genre?", r5);
		mainPanel.add(q5);
		
		//add game image
		JTextField r6Input = new JTextField();
		JPanel r6 = new JPanel(new BorderLayout());
		r6.add(r6Input, BorderLayout.NORTH);
		QPanel q6 = new QPanel("Game Tags?", r6);
		mainPanel.add(q6);
		
		//add game image
		JTextField r7Input = new JTextField();
		JPanel r7 = new JPanel(new BorderLayout());
		r7.add(r7Input, BorderLayout.NORTH);
		QPanel q7 = new QPanel("Game Publisher", r7);
		mainPanel.add(q7);
		
		//add game image
		JButton button = new JButton("Add");
		mainPanel.add(button);

		frame.add(mainPanel);
		frame.setVisible(true);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				game.setName(r1Input.getText());
				game.setDescription(r2Input.getText());
				game.setPicture(r3Input.getText());
				game.setPublishers(r7Input.getText());
				game.setDevelopers(r4Input.getText());
				game.setGenres(r5Input.getText());
				game.setTags(r6Input.getText());
				ClubPenguin.addToGamesArray(new GameCard(game));
			}
		});

	}

}




























