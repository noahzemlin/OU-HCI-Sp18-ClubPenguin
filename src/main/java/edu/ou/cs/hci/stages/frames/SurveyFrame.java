package edu.ou.cs.hci.stages.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import edu.ou.cs.hci.stages.panels.CheckBoxPanel;
import edu.ou.cs.hci.stages.panels.RadioButtonPanel;

public class SurveyFrame extends JFrame 
{
	private static final long serialVersionUID = 550493521518629512L;
	
	private long startTime = 0;

	public SurveyFrame() 
	{
		this(50, 50);
	}
	
	public SurveyFrame(int x, int y) 
	{
		
		startTime = System.currentTimeMillis();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 2));
		
		// START OF QUESTION 1
		JPanel r1 = new JPanel(new BorderLayout());
		JTextField r1Input = new JTextField();
		r1.add(r1Input, BorderLayout.NORTH);
		QPanel q1 = new QPanel("How many hours a week on average do you use collection managers of any type?", r1);
		mainPanel.add(q1);
		
		// START OF QUESTION 2
		JSlider r2 = new JSlider();
		r2.setMinimum(1);
		r2.setMaximum(5);
		r2.setMajorTickSpacing(1);
		r2.setLabelTable(r2.createStandardLabels(1, 1));
		r2.setValue(3);
		r2.setPaintTicks(true);
		r2.setPaintLabels(true);
		
		QPanel q2 = new QPanel("On a scale of 1 to 5, do you prefer simplicity (1) or complexity (5). Complexity is showing a lot of features all at once, while simplicity is hiding a lot of them behind menus.", r2);
		mainPanel.add(q2);
		
		// START OF QUESTION 3
		//Create the panel which will hold the check boxes
		ArrayList<String> gameGenres = new ArrayList<String>();
		gameGenres.add("Action");
		gameGenres.add("Adventure");
		gameGenres.add("Role Playing Game (RPG)");
		gameGenres.add("Puzzle/Strategy");
		gameGenres.add("Platformer");
		gameGenres.add("Simulation");
		CheckBoxPanel r3 = new CheckBoxPanel(gameGenres);
		
		QPanel q3 = new QPanel("What kind of games do you play?", r3);
		mainPanel.add(q3);
		
		// START OF QUESTION 4
		ArrayList<String> timeOptions = new ArrayList<String>();
		timeOptions.add("0-19");
		timeOptions.add("20-49");
		timeOptions.add("50-99");
		timeOptions.add("100-249");
		timeOptions.add("250+");
		RadioButtonPanel r4 = new RadioButtonPanel(timeOptions);

		QPanel q4 = new QPanel("How many games do you own?", r4);
		mainPanel.add(q4);
		
		// START OF QUESTION 5
		JTextArea r5 = new JTextArea();
		QPanel q5 = new QPanel("If you use a collection browser, what features do you value the most?", r5);
		mainPanel.add(q5);
		
		JPanel finishPanel = new JPanel();
		JButton finish = new JButton("Finish");
		finishPanel.add(finish);
		mainPanel.add(finishPanel);
		
		this.add(mainPanel);
		
		this.pack();
		this.setTitle("Survey Questionnare");
		this.setBounds(x, y, 800, 600);
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		SurveyFrame thisFrame = this;
		
		finish.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				ArrayList<String> results = new ArrayList<String>();
				results.add("Question 1: " + r1Input.getText());
				results.add("Question 2: " + r2.getValue());
				results.add("Question 3: " + r3.getSelectedAsText());
				results.add("Question 4: " + r4.getSelectedAsText());
				results.add("Question 5: " + r5.getText());
				results.add("Time taken: " + (System.currentTimeMillis() - startTime)/1000.0);
				
				thisFrame.remove(mainPanel);
				thisFrame.add(new ResultsPanel(results));
				
				thisFrame.pack();
			}			
		});
		
		/* Uncomment this if you want closing the survey browser to close the whole program
		this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		*/
	}
}

class QPanel extends JPanel 
{
	private static final long serialVersionUID = 1119620964758351695L;
	
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

class ResultsPanel extends JPanel 
{
	private static final long serialVersionUID = 8345830317401661641L;
	
	public ResultsPanel (ArrayList<String> results) 
	{	
		this.setLayout(new GridLayout(results.size(), 1));
		
		JLabel label;
		for (String result : results) 
		{
			label = new JLabel();
			label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			label.setText(result);
			this.add(label);
		}
	}
}