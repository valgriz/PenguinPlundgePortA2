package com.twopercent.render;

import java.awt.Color;

import com.twopercent.main.DataManager;
import com.twopercent.main.Global;
import com.twopercent.main.Stat;
import com.valgriz.screen.HighScores;
import com.valgriz.screen.Options;
import com.valgriz.screen.Screen;
import com.valgriz.screen.Stats;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UI extends UserInterfaceCreator {

	private Text gameMessage;
	private String gameEndMessageString;
	public static int selection = 0;
	public static boolean showChangePenguin;

	public UI() {
		super();
		DropShadow dropShadow = new DropShadow(5, new javafx.scene.paint.Color(0, 0, 0, 1));

		getGroup().getChildren().add(getScoreText());

		getScoreText().setX(15);
		getScoreText().setY(40);
		getScoreText().setText("");
		getScoreText().setFill(new javafx.scene.paint.Color(1, 1, 1, 1));
		getScoreText().setFont(new Font("Arial", 36));
		getScoreText().setEffect(dropShadow);
		dropShadow.setOffsetX(3);
		dropShadow.setOffsetY(3);

		getGroup().getChildren().add(getStarText());

		getStarText().setX(620);
		getStarText().setY(40);
		getStarText().setText("");
		getStarText().setFill(new javafx.scene.paint.Color(1, 1, .5, 1));
		getStarText().setFont(new Font("Calibri", 42));
		getStarText().setEffect(new Glow(1));

		gameMessage = new Text("GAME OVER");
		gameMessage.setFont(new Font("Arial", 60));
		gameMessage.setFill(new javafx.scene.paint.Color(1, 1, 1, 1));
		gameMessage.setEffect(dropShadow);
		gameMessage.setX((720 / 2) - (gameMessage.getLayoutBounds().getWidth() / 2));
		gameMessage.setY(150);
		gameMessage.setVisible(false);

		getGroup().getChildren().add(gameMessage);

		addNullButton(0, 0, 357, 500, "pgLeftTap");
		addNullButton(358, 0, 357, 500, "pgRightTap");
		addNullButton(0, 0, 130, 60, "pgPauseGame");

		addButton(210, 195, 300, 69, "PLAY AGAIN", 0, "goPlayAgain");
		addButton(210, 287, 300, 69, "MAIN MENU", 0, "goMainMenu");
		addButton(210, 195, 300, 69, "RESUME", 0, "gpResume");
		addButton(210, 287, 300, 69, "END GAME", 0, "gpEndGame");
		addCustomButton(210, 367, 300, 69, "CHANGE PENGUIN", 28, 43, 0, "pgChangePenguin");
		addButton(387, 32, 300, 69, "PLAY!", 0, "mmPlay");
		addButton(387, 113, 300, 69, "CUSTOMIZE", 0, "mmOptions");

		addButton(387, 194, 300, 69, "SCORES", 0, "mmScores");
		addButton(387, 275, 300, 69, "STATS", 0, "mmStats");

		addButton(387, 356, 300, 69, "HELP", 0, "mmHelp");
		addButton(33, 356, 300, 69, "MAIN MENU", 0, "smMainMenu");
		addButton(387, 356, 300, 69, "PLAY GAME", 0, "smPlayGame");
		addCustomButton(94, 95, 45, 236, "", 1, 0, 305, "omLeft0");
		addCustomButton(576, 95, 45, 236, "", 1, 0, 69, "omRight0");
		addSwitch(340, 95, 34, 21, 541, "switchA", "switchB", "omSound");
		addCustomButton(277, 180, 160, 43, "", 1, 0, 583, "omUnlock");
		addNullButton(94, 95, 183, 236, "omLeft");
		addNullButton(437, 95, 184, 236, "omRight");
		addButton(210, 369, 300, 69, "MAIN MENU", 0, "hmMainMenu");

		DataManager.initStat();

		getButton("omSound").setSubState(DataManager.getStatValue("muted"));
		SoundPlayer.volumeControl(DataManager.getStatValue("muted"));

		stateChanged();

	}

	public void showScreenStatus() {
		// Attribs
		System.out.println("inMainMenu:" + Global.inMainMenu);
		System.out.println("inPlayGame:" + Global.inPlayGame);
		System.out.println("inHighScores:" + Global.inHighScores);
		System.out.println("inStats:" + Global.inStats);
		System.out.println("inOptions:" + Global.inOptions);
		System.out.println("inHelp:" + Global.inHelp);
		System.out.println("inPaused:" + Global.inPaused);
		System.out.println("inGameOver:" + Global.inGameOver);
		System.out.println("-----------------------");
	}

	public Button controlButton(String id) {
		return getButton(id);
	}

	public static void unhighlightAllButtons() {
		for (int i = 0; i < getButtonArrayList().size(); i++) {
			getButtonArrayList().get(i).setState(0);
		}
	}

	public static void highlightButton(String id) {
		getButton(id).setState(2);
	}

	public void stateChanged() {
		selection = 0;
		System.out.println("screenStateChanged");
		// Attribs

		// Main Menu
		if (Global.inMainMenu) {
			getButton("pgLeftTap").setVisible(false);
			getButton("pgRightTap").setVisible(false);
			getButton("goPlayAgain").setVisible(false);
			getButton("goMainMenu").setVisible(false);
			getButton("pgPauseGame").setVisible(false);
			getButton("gpEndGame").setVisible(false);
			getButton("gpResume").setVisible(false);
			getButton("mmPlay").setVisible(true);
			getButton("mmScores").setVisible(true);
			getButton("mmStats").setVisible(true);
			getButton("mmOptions").setVisible(true);
			getButton("mmHelp").setVisible(true);
			getScoreText().setVisible(false);
			getStarText().setVisible(false);

		} else if (!Global.inPlayGame) {
			getButton("mmPlay").setVisible(false);
			getButton("mmScores").setVisible(false);
			getButton("mmStats").setVisible(false);
			getButton("mmOptions").setVisible(false);
			getButton("mmHelp").setVisible(false);
		}

		// In Game
		if (Global.inPlayGame) {
			if (showChangePenguin) {
				UI.getButton("pgChangePenguin").setVisible(true);
			}
			if (Global.inGameOver) {
				gameMessage.setText("GAME OVER");
				gameMessage.setX((720 / 2) - (gameMessage.getLayoutBounds().getWidth() / 2));
				gameMessage.setVisible(true);
				getButton("pgLeftTap").setVisible(false);
				getButton("pgRightTap").setVisible(false);
				getButton("goPlayAgain").setVisible(true);
				getButton("goMainMenu").setVisible(true);
				UI.getButton("pgChangePenguin").setVisible(false);
				DataManager.addScore(Global.score);
				DataManager.setStatValue("totalStarCount", DataManager.getStatValue("totalStarCount") + Global.stars);
				DataManager.setStatValue("bankStarCount", DataManager.getStatValue("bankStarCount") + Global.stars);
				DataManager.setStatValue("totalGames", DataManager.getStatValue("totalGames") + 1);
				DataManager.setStatValue("totalStars", DataManager.getStatValue("totalStars") + Global.stars);
				DataManager.setStatValue("totalBounces", DataManager.getStatValue("totalBounces") + Global.bounces);
				DataManager.setStatValue("totalTime", DataManager.getStatValue("totalTime") + Global.time);
				Global.time = 0;
				Global.bounces = 0;
				System.out.println(DataManager.getStatArraySize());

				if (DataManager.isInHighScores(Global.score)) {
					gameMessage.setText("HIGH SCORE!");
				} else {
					gameMessage.setText("GAME OVER");
				}

			} else {
				gameMessage.setVisible(false);
				getButton("pgLeftTap").setVisible(true);
				getButton("pgRightTap").setVisible(true);
				getButton("goPlayAgain").setVisible(false);
				getButton("goMainMenu").setVisible(false);

			}

			if (Global.inPaused) {
				gameMessage.setText("GAME PAUSED");
				gameMessage.setX((720 / 2) - (gameMessage.getLayoutBounds().getWidth() / 2));
				gameMessage.setVisible(true);
				getButton("pgLeftTap").setVisible(false);
				getButton("pgRightTap").setVisible(false);
				getButton("gpEndGame").setVisible(true);
				getButton("gpResume").setVisible(true);
				UI.getButton("pgChangePenguin").setVisible(false);
			} else {
				getButton("pgLeftTap").setVisible(true);
				getButton("pgRightTap").setVisible(true);
				getButton("gpEndGame").setVisible(false);
				getButton("gpResume").setVisible(false);
			}

			if (!Global.inGameOver && !Global.inPaused) {
				gameMessage.setVisible(false);
				getButton("pgLeftTap").setVisible(true);
				getButton("pgRightTap").setVisible(true);
				getButton("goPlayAgain").setVisible(false);
				getButton("goMainMenu").setVisible(false);
				getButton("pgPauseGame").setVisible(true);
				getButton("gpEndGame").setVisible(false);
				getButton("gpResume").setVisible(false);
				getScoreText().setVisible(true);
				getStarText().setVisible(true);

			}
		} else {
			gameMessage.setVisible(false);
			getButton("pgChangePenguin").setVisible(false);
		}

		// In Highscores
		if (Global.inHighScores || Global.inOptions) {
			if (Global.inHighScores) {
				Screen.setVisibleGroup("HighScores");
				HighScores.incrementalUpdate();
			} else if (Global.inOptions) {
				Screen.setVisibleGroup("Options");
				getButton("omRight").setVisible(true);
				getButton("omLeft").setVisible(true);
				getButton("omRight0").setVisible(true);
				getButton("omLeft0").setVisible(true);
				getButton("omSound").setVisible(true);
				getButton("omUnlock").setVisible(true);
				getScoreText().setVisible(false);
				getStarText().setVisible(true);
				getStarText().setText(DataManager.getStatValue("bankStarCount") + "");
				Options.updatePenguinPositions();
				Options.definePenguins();
			} else {
				Screen.setVisibleGroup("MainMenu");
				getButton("omRight").setVisible(false);
				getButton("omLeft").setVisible(false);
				getButton("omRight0").setVisible(false);
				getButton("omLeft0").setVisible(false);
				getButton("omSound").setVisible(false);
				getButton("omUnlock").setVisible(false);
			}
			getButton("smMainMenu").setVisible(true);
			getButton("smPlayGame").setVisible(true);
			getButton("mmPlay").setVisible(false);
			getButton("mmScores").setVisible(false);
			getButton("mmStats").setVisible(false);
			getButton("mmOptions").setVisible(false);
			getButton("mmHelp").setVisible(false);
			getButton("goPlayAgain").setVisible(false);
			getButton("goMainMenu").setVisible(false);
			getButton("pgLeftTap").setVisible(false);
			getButton("pgRightTap").setVisible(false);
			getButton("gpEndGame").setVisible(false);
			getButton("gpResume").setVisible(false);
		} else {
			getButton("smMainMenu").setVisible(false);
			getButton("smPlayGame").setVisible(false);
			getButton("omRight").setVisible(false);
			getButton("omLeft").setVisible(false);
			getButton("omRight0").setVisible(false);
			getButton("omLeft0").setVisible(false);
			getButton("omSound").setVisible(false);
			getButton("omUnlock").setVisible(false);

		}

		if (Global.inHelp || Global.inStats) {
			getButton("hmMainMenu").setVisible(true);
		} else {
			getButton("hmMainMenu").setVisible(false);
		}

		if (Global.inStats) {
			DataManager.pringAllStatValues();
			Stats.refreshStats();
		}

		// if (!Global.inMainMenu && Global.inPlayGame && !Global.inHighScores
		// && !Global.inStats && !Global.inOptions
		// && !Global.inHelp && !Global.inPaused && !Global.inGameOver) {
		// }

		Global.gameStateChanged = false;
	}

	public void update() {
		super.update();

		// showScreenStatus();

		if (Global.gameStateChanged)
			stateChanged();
		// Add Highscore Message

		getScoreText().setText("" + Global.score);

		if (Global.inPlayGame) {
			getStarText().setText("" + Global.stars);
		}
	}

	public static void changePenguinButtonExit() {
		getButton("pgChangePenguin").fadeOut();
	}
}
