package com.valgriz.screen;

import com.twopercent.main.DataManager;
import com.twopercent.main.Global;
import com.twopercent.render.UI;

import com.util.TimelineBuilder;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Screen {
	private static Group root;
	private static PlayGame playGame;
	private static MainMenu mainMenu;
	private static UI userInterface;
	private static HighScores highScores;
	private LoadingScreen loadingScreen;
	private static Options options;
	private static Help help;
	private static Stats stats;
	private static int version = 20150118;

	// version = (year) + (month) + (day)
	// the larger the version integer is, the newer the release is

	public Screen(Group root) {
		this.root = root;

		// loadingScreen = new LoadingScreen(root);

		highScores = new HighScores();
		playGame = new PlayGame();
		mainMenu = new MainMenu();
		options = new Options();
		help = new Help();
		stats = new Stats();

		userInterface = new UI();

		root.getChildren().add(stats.getGroup());
		root.getChildren().add(help.getGroup());
		root.getChildren().add(options.getGroup());
		root.getChildren().add(highScores.getGroup());
		root.getChildren().add(playGame.getGroup());
		root.getChildren().add(mainMenu.getGroup());

		// Can use some optimization
		root.getChildren().add(userInterface.getGroup());

		Global.inMainMenu = true;
		Global.inGameOver = false;
		Global.inHelp = false;
		Global.inHighScores = false;
		Global.inOptions = false;
		Global.inPaused = false;
		Global.inPlayGame = false;
		Global.inStats = false;

		startTimeline();

		// Checks if the game is running the latest version
		if (DataManager.getStatValue("version") != version) {
			// Code inside of here will only run on the first game launch, or
			// game is updated;
			DataManager.setStatValue("version", version);
		}
	}

	public static void setVisibleGroup(String s) {

		root.getChildren().clear();
		System.out.println("> GROUP CLEARED");
		switch (s) {
		case "MainMenu":
			root.getChildren().add(playGame.getGroup());
			root.getChildren().add(mainMenu.getGroup());
			root.getChildren().add(userInterface.getGroup());
			break;
		case "PlayGame":
			root.getChildren().add(playGame.getGroup());
			root.getChildren().add(mainMenu.getGroup());
			MainMenu.goToPlayGameRightNow();
			MainMenu.translateOutButtonsRightNow();
			PlayGame.resetGame();
			root.getChildren().add(userInterface.getGroup());
			break;
		case "HighScores":
			root.getChildren().add(highScores.getGroup());
			root.getChildren().add(userInterface.getGroup());
			break;
		case "Stats":
			root.getChildren().add(stats.getGroup());
			root.getChildren().add(userInterface.getGroup());
			break;
		case "Options":
			root.getChildren().add(options.getGroup());
			root.getChildren().add(userInterface.getGroup());
			root.getChildren().add(options.getSubGroup());
			break;
		case "Help":
			root.getChildren().add(help.getGroup());
			root.getChildren().add(userInterface.getGroup());
			break;

		}

		System.out.println("> UI ADDED");
	}

	@SuppressWarnings("deprecati" + "on")
	private void startTimeline() {
		TimelineBuilder.create().cycleCount(Animation.INDEFINITE)
				.keyFrames(new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						//
						update();
					}
				})).build().play();
	}

	private void update() {
		if (Global.inMainMenu) {
			mainMenu.update();
		}
		if (Global.inPlayGame) {
			playGame.update();
		}
		if (Global.inHighScores) {
			// highScores.update();
		}
		if (Global.inOptions) {
			// options.update();
		}

		userInterface.update();

	}

	public static Group getGroup() {
		return root;
	}

	public static void setGroup(Group root) {
		Screen.root = root;
	}

}
