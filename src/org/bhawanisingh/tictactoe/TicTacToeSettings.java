package org.bhawanisingh.tictactoe;

public class TicTacToeSettings {
	private static final String SUN_JAVA_COMMAND = "sun.java.command";
	private static int BOARD_SIZE = 3;
	private static String PLAYER_ONE_LOGO = "O";
	private static String PLAYER_TWO_LOGO = "X";
	private static String PLAYER_ONE_NAME = "Player 1";
	private static String PLAYER_TWO_NAME = "Player 2";

	/**
	 * @return the bOARD_SIZE
	 */
	public static int getBOARD_SIZE() {
		return BOARD_SIZE;
	}

	/**
	 * @param bOARD_SIZE the bOARD_SIZE to set
	 */
	public static void setBOARD_SIZE(int bOARD_SIZE) {
		BOARD_SIZE = bOARD_SIZE;
	}

	/**
	 * @return the pLAYER_ONE_LOGO
	 */
	public static String getPLAYER_ONE_LOGO() {
		return PLAYER_ONE_LOGO;
	}

	/**
	 * @param pLAYER_ONE_LOGO the pLAYER_ONE_LOGO to set
	 */
	public static void setPLAYER_ONE_LOGO(String pLAYER_ONE_LOGO) {
		PLAYER_ONE_LOGO = pLAYER_ONE_LOGO;
	}

	/**
	 * @return the pLAYER_TWO_LOGO
	 */
	public static String getPLAYER_TWO_LOGO() {
		return PLAYER_TWO_LOGO;
	}

	/**
	 * @param pLAYER_TWO_LOGO the pLAYER_TWO_LOGO to set
	 */
	public static void setPLAYER_TWO_LOGO(String pLAYER_TWO_LOGO) {
		PLAYER_TWO_LOGO = pLAYER_TWO_LOGO;
	}

	/**
	 * @return the pLAYER_ONE_NAME
	 */
	public static String getPLAYER_ONE_NAME() {
		return PLAYER_ONE_NAME;
	}

	/**
	 * @param pLAYER_ONE_NAME the pLAYER_ONE_NAME to set
	 */
	public static void setPLAYER_ONE_NAME(String pLAYER_ONE_NAME) {
		PLAYER_ONE_NAME = pLAYER_ONE_NAME;
	}

	/**
	 * @return the pLAYER_TWO_NAME
	 */
	public static String getPLAYER_TWO_NAME() {
		return PLAYER_TWO_NAME;
	}

	/**
	 * @param pLAYER_TWO_NAME the pLAYER_TWO_NAME to set
	 */
	public static void setPLAYER_TWO_NAME(String pLAYER_TWO_NAME) {
		PLAYER_TWO_NAME = pLAYER_TWO_NAME;
	}

	/**
	 * @return the sunJavaCommand
	 */
	public static String getSunJavaCommand() {
		return SUN_JAVA_COMMAND;
	}
}
