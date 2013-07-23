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

public class OptionPanel extends JFrame {

	private TicTacToe ticTacToe;
	private JTextField boardSizeFld;
	private JTextField playerOneLogoField;
	private JTextField playerTwoLogoField;
	private String playerOneLogo;
	private String playerTwoLogo;
	private int boardSize;
	private JButton okButton;
	private JButton cancelButton;
	private JButton resetButton;
	private JPanel optionPanel;
	private JPanel emptyPanel;
	private JPanel buttonPanel;
	private JLabel boardSizeLabel;
	private JLabel playerOneLogoLabel;
	private JLabel playerTwoLogoLabel;

	public OptionPanel(TicTacToe ticTacToe) {
		super("Options To Change");
		this.ticTacToe = ticTacToe;
		this.initialize();
		this.theming();
		this.addListeners();
		this.addComponents();

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.ticTacToe.toggleMainFrame(false);
		this.setVisible(true);
	}

	private void initialize() {
		this.okButton = new JButton("OK");
		this.cancelButton = new JButton("Cancel");
		this.resetButton = new JButton("Defaults");
		this.optionPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		this.emptyPanel = new JPanel(new GridLayout(1, 2, 15, 5));
		this.buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		this.boardSizeLabel = new JLabel("Enter The Board Size (Between 3 to 10)");
		this.playerOneLogoLabel = new JLabel("Enter A Char For Player 1");
		this.playerTwoLogoLabel = new JLabel("Enter A Char For Player 2");
		this.boardSizeFld = new JTextField(TicTacToeSettings.getBOARD_SIZE() + "");
		this.playerOneLogoField = new JTextField(TicTacToeSettings.getPLAYER_ONE_LOGO());
		this.playerTwoLogoField = new JTextField(TicTacToeSettings.getPLAYER_TWO_LOGO());
	}

	private void theming() {
		this.optionPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}

	private void addListeners() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				OptionPanel.this.ticTacToe.toggleMainFrame(true);
			}
		});
		this.okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				OptionPanel.this.okButtonAction();
			}
		});
		this.cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				OptionPanel.this.dispose();
				OptionPanel.this.ticTacToe.toggleMainFrame(true);
			}
		});
		this.resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				OptionPanel.this.boardSize = 3;
				OptionPanel.this.playerOneLogo = "O";
				OptionPanel.this.playerTwoLogo = "X";
				OptionPanel.this.boardSizeFld.setText(OptionPanel.this.boardSize + "");
				OptionPanel.this.playerOneLogoField.setText(OptionPanel.this.playerOneLogo);
				OptionPanel.this.playerTwoLogoField.setText(OptionPanel.this.playerTwoLogo);
			}
		});
	}

	private void addComponents() {
		this.buttonPanel.add(this.resetButton);
		this.buttonPanel.add(this.cancelButton);
		this.buttonPanel.add(this.okButton);
		this.optionPanel.add(this.boardSizeLabel);
		this.optionPanel.add(this.boardSizeFld);
		this.optionPanel.add(this.playerOneLogoLabel);
		this.optionPanel.add(this.playerOneLogoField);
		this.optionPanel.add(this.playerTwoLogoLabel);
		this.optionPanel.add(this.playerTwoLogoField);
		this.optionPanel.add(this.emptyPanel);
		this.optionPanel.add(this.buttonPanel);
		this.add(this.optionPanel);
	}

	void okButtonAction() {
		String test = this.boardSizeFld.getText().trim();
		if (test.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Enter Some Value", "Error!!!", 0);
		} else {
			int boardSize = Integer.parseInt(test);
			String playerOneLogo = this.playerOneLogoField.getText().trim();
			String playerTwoLogo = this.playerTwoLogoField.getText().trim();
			if ((boardSize >= 3) && (boardSize <= 10) && (playerOneLogo.length() == 1) && (playerTwoLogo.length() == 1) && !playerOneLogo.equals(playerTwoLogo)) {
				TicTacToeSettings.setBOARD_SIZE(boardSize);
				TicTacToeSettings.setPLAYER_ONE_LOGO(playerOneLogo);
				TicTacToeSettings.setPLAYER_TWO_LOGO(playerTwoLogo);
				this.ticTacToe.dispose();
				new TicTacToe();
				this.dispose();
			} else {
				if ((boardSize < 3) && (boardSize > 10)) {
					JOptionPane.showMessageDialog(null, "Enter Some Value Between 3 - 10", "Error!!!", JOptionPane.ERROR_MESSAGE);
				} else if ((playerOneLogo.length() != 1) || (playerTwoLogo.length() != 1)) {
					JOptionPane.showMessageDialog(null, "Logo Must Be Of One Char", "Error!!!", JOptionPane.ERROR_MESSAGE);
				} else if (playerOneLogo.equals(playerTwoLogo)) {
					JOptionPane.showMessageDialog(null, "Players Logo Must Be Distinct", "Error!!!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
