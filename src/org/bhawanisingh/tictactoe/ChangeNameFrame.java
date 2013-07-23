package org.bhawanisingh.tictactoe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangeNameFrame extends JFrame {

	private TicTacToe ticTacToe;
	private JTextField playerOneNameField;
	private JTextField playerTwoNameField;
	private JButton okBtn;
	private JButton cancelBtn;
	private JButton resBtn;
	private JPanel optionPanel;
	private JPanel emptyPanel;
	private JPanel btnPanel;
	private JLabel firstNameLbl;
	private JLabel secondNameLbl;

	private String playerOneName;
	private String playerTwoName;

	public ChangeNameFrame(TicTacToe ticTacToe) {
		super("Edit Players Name");
		this.ticTacToe = ticTacToe;
		this.initialize();
		this.addListeners();
		this.addComponents();
		this.theming();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.ticTacToe.toggleMainFrame(false);
		this.setVisible(true);
	}

	private void initialize() {
		this.okBtn = new JButton("OK");
		this.cancelBtn = new JButton("Cancel");
		this.resBtn = new JButton("Defaults");
		this.optionPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		this.emptyPanel = new JPanel(new GridLayout(1, 2, 15, 5));
		this.btnPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		this.firstNameLbl = new JLabel("Enter Name For Player 1");
		this.secondNameLbl = new JLabel("Enter Name For Player 2");
		this.playerOneNameField = new JTextField(TicTacToeSettings.getPLAYER_ONE_NAME());
		this.playerTwoNameField = new JTextField(TicTacToeSettings.getPLAYER_TWO_NAME());

	}

	private void addListeners() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				ChangeNameFrame.this.ticTacToe.toggleMainFrame(true);
			}
		});
		this.okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				ChangeNameFrame.this.okButtonAction();
			}
		});
		this.cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				ChangeNameFrame.this.dispose();
				ChangeNameFrame.this.ticTacToe.toggleMainFrame(true);
			}
		});

		this.resBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				ChangeNameFrame.this.playerOneName = "Player 1";
				ChangeNameFrame.this.playerTwoName = "Player 2";
				ChangeNameFrame.this.playerOneNameField.setText(ChangeNameFrame.this.playerOneName);
				ChangeNameFrame.this.playerTwoNameField.setText(ChangeNameFrame.this.playerTwoName);
			}
		});
	}

	private void addComponents() {
		this.btnPanel.add(this.resBtn);
		this.btnPanel.add(this.cancelBtn);
		this.btnPanel.add(this.okBtn);
		this.optionPanel.add(this.firstNameLbl);
		this.optionPanel.add(this.playerOneNameField);
		this.optionPanel.add(this.secondNameLbl);
		this.optionPanel.add(this.playerTwoNameField);
		this.optionPanel.add(this.emptyPanel);
		this.optionPanel.add(this.btnPanel);
		this.add(this.optionPanel);
	}

	private void theming() {
		this.optionPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}

	void okButtonAction() {
		String playerOneName = this.playerOneNameField.getText().trim();
		String playerTwoName = this.playerTwoNameField.getText().trim();
		if ((playerOneName.length() != 0) && (playerTwoName.length() != 0) && !playerOneName.equalsIgnoreCase(playerTwoName)) {
			TicTacToeSettings.setPLAYER_ONE_NAME(playerOneName);
			TicTacToeSettings.setPLAYER_TWO_NAME(playerTwoName);
			this.dispose();
			this.ticTacToe.toggleMainFrame(true);
		} else {
			if (playerOneName.length() == 0) {
				JOptionPane.showMessageDialog(null, "Player 1 Must Have A Valid Name", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (playerTwoName.length() == 0) {
				JOptionPane.showMessageDialog(null, "Player 2 Must Have A Valid Name", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (playerOneName.equalsIgnoreCase(playerTwoName)) {
				JOptionPane.showMessageDialog(null, "Both Players Must Have A Disticnt Name", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
