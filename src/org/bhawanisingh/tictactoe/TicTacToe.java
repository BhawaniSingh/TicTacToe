package org.bhawanisingh.tictactoe;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TicTacToe extends JFrame implements MouseListener {

	private int clicks;
	private boolean singlePlayer = true;
	private boolean firstPlayer;
	private boolean win;
	private boolean done;
	private Random randomGenerator;
	private int tic[][];

//    Menu Components
	JMenuBar mainMenuBar;
	JMenu fileMenu;
	JMenu optionsMenu;
	JMenu helpMenu;
	JMenuItem resetMenuItem;
	JMenuItem restartMenuItem;
	JMenuItem exitMenuItem;
	JMenuItem optionsMenuItem;
	JMenuItem changeNameMenuItem;
	JMenuItem singlePlayerSelectionMenuItem;
	JMenuItem multiPlayerSelectionMenuItem;
	JMenuItem aboutMenuItem;

//    GUI Components
	JLabel boxes[][];
	static Font labelFont = new java.awt.Font("Old English Text MT", 1, 36);
	JScrollPane scrollBoxesPanel;
	JPanel boxesPanel;
	JTextField playerOneNameField;
	JTextField playerTwoNameField;

	public TicTacToe() {

		this.initialize();
		this.addListeners();
		this.addComponents();

	}

	private void initialize() {
//      Variable Initialization
		this.clicks = 0;
		this.singlePlayer = true;
		this.win = false;
		this.firstPlayer = true;
		this.done = false;
		this.randomGenerator = new Random();
		this.tic = new int[TicTacToeSettings.getBOARD_SIZE()][TicTacToeSettings.getBOARD_SIZE()];

//      Menu Components Initialization

		this.mainMenuBar = new JMenuBar();
		this.fileMenu = new JMenu("File", true);
		this.optionsMenu = new JMenu("Options", true);
		this.helpMenu = new JMenu("Help", true);
		this.resetMenuItem = new JMenuItem("Reset");
		this.restartMenuItem = new JMenuItem("Restart");
		this.exitMenuItem = new JMenuItem("Exit");
		this.optionsMenuItem = new JMenuItem("Options");
		this.changeNameMenuItem = new JMenuItem("Edit Players Name");
		this.singlePlayerSelectionMenuItem = new JMenuItem("Single Player");
		this.multiPlayerSelectionMenuItem = new JMenuItem("Multi Player");
		this.aboutMenuItem = new JMenuItem("About");

//      GUI Components Initialization

		this.boxes = new JLabel[TicTacToeSettings.getBOARD_SIZE()][TicTacToeSettings.getBOARD_SIZE()];
		this.boxesPanel = new JPanel(new GridLayout(TicTacToeSettings.getBOARD_SIZE(), TicTacToeSettings.getBOARD_SIZE(), 5, 5));
		this.boxesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.scrollBoxesPanel = new JScrollPane(this.boxesPanel);
		for (int i = 0; i < TicTacToeSettings.getBOARD_SIZE(); ++i) {
			for (int j = 0; j < TicTacToeSettings.getBOARD_SIZE(); ++j) {
				this.boxes[i][j] = new JLabel();
				this.boxes[i][j].setPreferredSize(new Dimension(50, 50));
				this.boxes[i][j].setMinimumSize(new Dimension(50, 50));
				this.boxes[i][j].setBorder(BorderFactory.createEtchedBorder());
				this.boxes[i][j].addMouseListener(this);
				this.boxes[i][j].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				this.boxes[i][j].setVerticalAlignment(javax.swing.SwingConstants.CENTER);
				this.boxes[i][j].setFont(labelFont);
				this.boxesPanel.add(this.boxes[i][j]);
			}
		}
	}

	private void addListeners() {
		this.resetMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				TicTacToe.this.reset();
			}
		});
		this.restartMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				restartApp();
			}
		});
		this.exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		this.optionsMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new OptionPanel(TicTacToe.this);
			}
		});
		this.changeNameMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				new ChangeNameFrame(TicTacToe.this);
			}
		});
		this.singlePlayerSelectionMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				TicTacToe.this.singlePlayer = true;
				TicTacToe.this.reset();
				TicTacToe.this.singlePlayerSelectionMenuItem.setEnabled(false);
				TicTacToe.this.multiPlayerSelectionMenuItem.setEnabled(true);
			}
		});
		this.multiPlayerSelectionMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				TicTacToe.this.singlePlayer = false;
				TicTacToe.this.reset();
				TicTacToe.this.singlePlayerSelectionMenuItem.setEnabled(true);
				TicTacToe.this.multiPlayerSelectionMenuItem.setEnabled(false);
			}
		});
	}

	private void addComponents() {
		this.fileMenu.add(this.resetMenuItem);
		this.fileMenu.add(new JPopupMenu.Separator());
		this.fileMenu.add(this.restartMenuItem);
		this.fileMenu.add(this.exitMenuItem);
		this.optionsMenu.add(this.optionsMenuItem);
		this.optionsMenu.add(new JPopupMenu.Separator());
		this.optionsMenu.add(this.changeNameMenuItem);
		this.optionsMenu.add(new JPopupMenu.Separator());
		this.optionsMenu.add(this.singlePlayerSelectionMenuItem);
		this.optionsMenu.add(this.multiPlayerSelectionMenuItem);
		this.mainMenuBar.add(this.fileMenu);
		this.mainMenuBar.add(this.optionsMenu);
		this.setJMenuBar(this.mainMenuBar);
		this.add(this.scrollBoxesPanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Tic Tac Toe V9    (" + TicTacToeSettings.getBOARD_SIZE() + "x" + TicTacToeSettings.getBOARD_SIZE() + ")");
		this.reset();
		this.setVisible(true);

	}

	void winnerChecker(int player, String playerName) {
		++this.clicks;
		int countx = 0, county = 0, countd1 = 0, countd2 = 0;
		for (int i = 0; i < TicTacToeSettings.getBOARD_SIZE(); ++i) {
			countx = 0;
			county = 0;
			for (int j = 0; j < TicTacToeSettings.getBOARD_SIZE(); j++) {
				if (this.tic[i][j] == player) {
					++countx;
				}

				if (this.tic[j][i] == player) {
					++county;
				}

				if ((this.tic[i][j] == player) && (i == j)) {
					++countd1;
				}

				if ((this.tic[i][j] == player) && ((TicTacToeSettings.getBOARD_SIZE() - (i + 1)) == j)) {
					++countd2;
				}
			}

			if ((countd1 == TicTacToeSettings.getBOARD_SIZE()) || (countd2 == TicTacToeSettings.getBOARD_SIZE()) || (countx == TicTacToeSettings.getBOARD_SIZE()) || (county == TicTacToeSettings.getBOARD_SIZE())) {
				JOptionPane.showMessageDialog(null, playerName + " Wins The Game", "Winner", JOptionPane.INFORMATION_MESSAGE);
				this.win = true;
			}
		}
		if (this.singlePlayer && (player == 1) && !this.win) {
			this.singlePlayer();
		} else if (!this.singlePlayer && !this.win) {
			this.playerToggle();
		} else if (this.win) {
			this.reset();
		}
		if (!this.isEmptymatrix()) {
			JOptionPane.showMessageDialog(null, "It's A Tie", "No Winner", JOptionPane.INFORMATION_MESSAGE);
			this.win = true;
			this.reset();
		}
	}

	void playerToggle() {
		if (!this.win) {
			if (this.firstPlayer) {
				this.firstPlayer = false;
			} else {
				this.firstPlayer = true;
			}
		} else if (this.win) {
			this.firstPlayer = true;
			this.win = false;
		}
	}

	void reset() {
		for (int i = 0; i < TicTacToeSettings.getBOARD_SIZE(); ++i) {
			for (int j = 0; j < TicTacToeSettings.getBOARD_SIZE(); ++j) {
				this.tic[i][j] = 0;
				this.boxes[i][j].setText("");
			}
		}
		this.win = false;
		this.firstPlayer = true;
		this.done = false;
	}

	boolean isEmptymatrix() {
		for (int i = 0; i < TicTacToeSettings.getBOARD_SIZE(); ++i) {
			for (int j = 0; j < TicTacToeSettings.getBOARD_SIZE(); ++j) {
				if (this.tic[i][j] == 0) {
					return true;
				}
			}
		}
		return false;
	}

	void singlePlayer() {
		if ((this.tic[1][1] == 1) && (this.clicks == 1) && (TicTacToeSettings.getBOARD_SIZE() == 3)) {
			this.tic[0][0] = 2;
			this.singlePlayerSetText(0, 0);
			this.done = true;
		}
		int lim = 2;
		this.checkPlayer(2);
		if (!this.done) {
			this.checkPlayer(1);
		}

		if (!this.done) {
			if ((this.tic[1][1] == 0) && (TicTacToeSettings.getBOARD_SIZE() == 3)) {
				this.singlePlayerSetText(1, 1);
			} else {
				boolean b = this.isEmptymatrix();
				if (b) {
					for (; lim < TicTacToeSettings.getBOARD_SIZE(); ++lim) {
						if (!this.done) {
							this.checkPlayer(2, lim);
							if (!this.done) {
								this.checkPlayer(1, lim);
							}
						} else if (this.done) {
							break;
						}
					}
					if (!this.done) {
						int temp = 0;
						int temp2 = 0;
						do {
							temp = this.randomGenerator.nextInt(TicTacToeSettings.getBOARD_SIZE());
							temp2 = this.randomGenerator.nextInt(TicTacToeSettings.getBOARD_SIZE());
							if (this.tic[temp][temp2] == 0) {
								this.singlePlayerSetText(temp, temp2);
								break;
							}
						} while (this.tic[temp][temp2] != 0);
					}
				}
			}
		}
		this.done = false;
		this.winnerChecker(2, "Bhawani(Computer)");
	}

	void checkPlayer(int chkPlayer) {
		int countx = 0, county = 0, countd1 = 0, countd2 = 0;
		for (int i = 0; i < TicTacToeSettings.getBOARD_SIZE(); ++i) {
			countx = 0;
			county = 0;
			for (int j = 0; j < TicTacToeSettings.getBOARD_SIZE(); j++) {
				if (this.tic[i][j] == chkPlayer) {
					++countx;
					if (countx == (TicTacToeSettings.getBOARD_SIZE() - 1)) {
						for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
							if (this.tic[i][k] == 0) {
								this.singlePlayerSetText(i, k);
								this.done = true;
							}
							if (this.done) {
								break;
							}
						}
					}
				}

				if ((this.tic[j][i] == chkPlayer) && !this.done) {
					++county;
					if (county == (TicTacToeSettings.getBOARD_SIZE() - 1)) {
						for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
							if (this.tic[k][i] == 0) {
								this.singlePlayerSetText(k, i);
								this.done = true;
							}
							if (this.done) {
								break;
							}
						}
					}
				}

				if ((this.tic[i][j] == chkPlayer) && (i == j) && !this.done) {
					++countd1;
					if (countd1 == (TicTacToeSettings.getBOARD_SIZE() - 1)) {
						for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
							if (this.tic[k][k] == 0) {
								this.singlePlayerSetText(k, k);
								this.done = true;
							}
							if (this.done) {
								break;
							}
						}
					}
				}

				if ((this.tic[i][j] == chkPlayer) && ((TicTacToeSettings.getBOARD_SIZE() - (i + 1)) == j) && !this.done) {
					++countd2;

					if (countd2 == (TicTacToeSettings.getBOARD_SIZE() - 1)) {
						for (int p = 0; p < TicTacToeSettings.getBOARD_SIZE(); ++p) {
							for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
								if ((this.tic[p][k] == 0) && ((TicTacToeSettings.getBOARD_SIZE() - (p + 1)) == k)) {
									this.singlePlayerSetText(p, k);
									this.done = true;
								}
								if (this.done) {
									break;
								}
							}
						}
					}
				}
				if (this.done) {
					break;
				}
			}
			if (this.done) {
				break;
			}
		}
	}

	void checkPlayer(int chkPlayer, int lim) {
		int countx = 0, county = 0, countd1 = 0, countd2 = 0;
		for (int i = 0; i < TicTacToeSettings.getBOARD_SIZE(); ++i) {
			countx = 0;
			county = 0;
			for (int j = 0; j < TicTacToeSettings.getBOARD_SIZE(); j++) {
				if (this.tic[i][j] == chkPlayer) {
					++countx;
					ifx: if (countx == (TicTacToeSettings.getBOARD_SIZE() - lim)) {
						for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
							if ((this.tic[i][k] == 2) && (chkPlayer == 1)) {
								break ifx;
							}
						}
						for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
							if (this.tic[i][k] == 0) {
								this.singlePlayerSetText(i, k);
								this.done = true;
							}
							if (this.done) {
								break;
							}
						}
					}
				}

				if ((this.tic[j][i] == chkPlayer) && !this.done) {
					++county;
					ify: if (county == (TicTacToeSettings.getBOARD_SIZE() - lim)) {
						for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
							if ((this.tic[i][k] == 2) && (chkPlayer == 1)) {
								break ify;
							}
						}
						for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
							if (this.tic[k][i] == 0) {
								this.singlePlayerSetText(k, i);
								this.done = true;
							}
							if (this.done) {
								break;
							}
						}
					}
				}

				if ((this.tic[i][j] == chkPlayer) && (i == j) && !this.done) {
					++countd1;
					ifd1: if (countd1 == (TicTacToeSettings.getBOARD_SIZE() - lim)) {
						for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
							if ((this.tic[i][k] == 2) && (chkPlayer == 1)) {
								break ifd1;
							}
						}
						for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
							if (this.tic[k][k] == 0) {
								this.singlePlayerSetText(k, k);
								this.done = true;
							}
							if (this.done) {
								break;
							}
						}
					}
				}

				if ((this.tic[i][j] == chkPlayer) && ((TicTacToeSettings.getBOARD_SIZE() - (i + 1)) == j) && !this.done) {
					++countd2;
					ifd2: if (countd2 == (TicTacToeSettings.getBOARD_SIZE() - lim)) {
						for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
							if ((this.tic[i][k] == 2) && (chkPlayer == 1)) {
								break ifd2;
							}
						}
						for (int p = 0; p < TicTacToeSettings.getBOARD_SIZE(); ++p) {
							for (int k = 0; k < TicTacToeSettings.getBOARD_SIZE(); ++k) {
								if ((this.tic[p][k] == 0) && ((TicTacToeSettings.getBOARD_SIZE() - (p + 1)) == k)) {
									this.singlePlayerSetText(p, k);
									this.done = true;
								}
								if (this.done) {
									break;
								}
							}
						}
					}
				}
				if (this.done) {
					break;
				}
			}
			if (this.done) {
				break;
			}
		}
	}

	void singlePlayerSetText(int r, int c) {
		this.boxes[r][c].setText(TicTacToeSettings.getPLAYER_TWO_LOGO());
		this.tic[r][c] = 2;
	}

	public static void restartApp() {
		try {
			String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
			System.out.println(java);
			List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
			StringBuffer vmArgsOneLine = new StringBuffer();
			for (String arg : vmArguments) {
				if (!arg.contains("-agentlib")) {
					vmArgsOneLine.append(arg);
					vmArgsOneLine.append(" ");
				}
			}
			final StringBuffer cmd = new StringBuffer(java + " " + vmArgsOneLine);

			// program main and program arguments
			String[] mainCommand = System.getProperty(TicTacToeSettings.getSunJavaCommand()).split(" ");
			if (mainCommand[0].endsWith(".jar")) {
				cmd.append("-jar ").append(new File(mainCommand[0]).getPath());
			} else {
				cmd.append("-cp ").append(System.getProperty("java.class.path")).append(" ").append(mainCommand[0]);
			}
			for (int i = 1; i < mainCommand.length; i++) {
				cmd.append(" ");
				cmd.append(mainCommand[i]);
			}
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					try {
						System.out.println(cmd.toString());
						Runtime.getRuntime().exec(cmd.toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			System.exit(0);
		} catch (Exception e) {
			System.err.println("Error while trying to restart the application" + e);
		}
	}

	public void toggleMainFrame(boolean b) {
		this.mainMenuBar.setEnabled(b);
		this.boxesPanel.setVisible(b);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < TicTacToeSettings.getBOARD_SIZE(); ++i) {
			for (int j = 0; j < TicTacToeSettings.getBOARD_SIZE(); ++j) {
				if (e.getSource().equals(this.boxes[i][j])) {
					if (this.boxes[i][j].getText().equalsIgnoreCase("")) {
						if (this.firstPlayer) {
							this.boxes[i][j].setText(TicTacToeSettings.getPLAYER_ONE_LOGO());
							this.tic[i][j] = 1;
							this.winnerChecker(1, TicTacToeSettings.getPLAYER_ONE_NAME());
						} else if (!this.singlePlayer) {
							this.boxes[i][j].setText(TicTacToeSettings.getPLAYER_TWO_LOGO());
							this.tic[i][j] = 2;
							this.winnerChecker(2, TicTacToeSettings.getPLAYER_TWO_NAME());
						}
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static void main(String... s) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
		}
		new TicTacToe();
	}
}
