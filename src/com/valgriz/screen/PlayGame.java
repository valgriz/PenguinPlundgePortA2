package com.valgriz.screen;

import com.twopercent.main.Global;
import com.twopercent.render.BackgroundA;
import com.twopercent.render.BackgroundB;
import com.twopercent.render.Bird;
import com.twopercent.render.PlatformSys;
import com.twopercent.render.UI;
import com.util.TimelineBuilder;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.util.Duration;

public class PlayGame {

	public static BackgroundA backgroundA;
	public static BackgroundB backgroundB;
	public static PlatformSys platformSys;
	public static Bird bird;
	private static Timeline timeline;
	private static Group root;
	private static Timeline timeLine;

	@SuppressWarnings("deprecation")
	public PlayGame() {
		root = new Group();
		backgroundA = new BackgroundA();
		backgroundB = new BackgroundB();
		platformSys = new PlatformSys();
		bird = new Bird();

		// Render Order
		root.getChildren().add(backgroundA.getGroup());
		root.getChildren().add(backgroundB.getGroup());
		root.getChildren().add(platformSys.getGroup());
		root.getChildren().add(bird.getGroup());

		bird.addCollisionDetection();
		timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> changePenguinOptionVisibility()));
		// Any extras
		// UI

		TimelineBuilder.create().cycleCount(Animation.INDEFINITE)
				.keyFrames(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						if (Global.inPlayGame && !Global.inPaused && !Global.inGameOver) {
							Global.time++;
						}
					}
				})).build().play();
	}

	public static void resetGame() {
		platformSys.reset();
		bird.reset();
		// Attribs
		Global.inPlayGame = true;
		Global.inHighScores = false;
		Global.inStats = false;
		Global.inOptions = false;
		Global.inHelp = false;
		Global.inPaused = false;
		Global.inGameOver = false;
		Global.score = 0;
		Global.gameStateChanged = true;
		timeline.play();

	}

	public static void changePenguinOptionVisibility() {
		UI.getButton("pgChangePenguin").fadeIn();
		UI.getButton("pgChangePenguin").setVisible(true);
		System.out.println(UI.getButton("pgChangePenguin").isVisible());

	}

	public void update() {
		if (!Global.inPaused) {
			backgroundA.update();
			backgroundB.update();
			platformSys.update();
			bird.update();

		}

		if (Global.inPlayGame) {
			if (Global.inPaused && !Global.inGameOver) {
				Bird.pauseAllAnimation();
			}
			if (!Global.inHighScores && !Global.inStats && !Global.inOptions && !Global.inHelp && !Global.inPaused
					&& !Global.inGameOver) {
				Global.score += 1;
			}
		}

		if (Global.score >= 300 && Global.score <= 310 && !UI.showChangePenguin) {
			if (UI.getButton("pgChangePenguin").isVisible()) {
				UI.changePenguinButtonExit();
			}
		}
	}

	public static void goToPlayGame() {

		TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(800), backgroundA.getGroup());
		translateTransition1.setCycleCount(1);
		translateTransition1.setFromY(500);
		translateTransition1.setToY(0);
		TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(800), backgroundB.getGroup());
		translateTransition2.setCycleCount(1);
		translateTransition2.setToY(0);
		translateTransition2.setFromY(500);
		TranslateTransition translateTransition3 = new TranslateTransition(Duration.millis(800), platformSys.getGroup());
		translateTransition3.setCycleCount(1);
		translateTransition3.setToY(0);
		translateTransition3.setFromY(500);
		TranslateTransition translateTransition4 = new TranslateTransition(Duration.millis(800), bird.getGroup());
		translateTransition4.setCycleCount(1);
		translateTransition4.setToY(0);
		translateTransition4.setFromY(500);

		translateTransition1.play();
		translateTransition2.play();
		translateTransition3.play();
		translateTransition4.play();

		translateTransition4.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// UI.getButton("pgChangePenguin").fadeIn();
				// UI.getButton("pgChangePenguin").translateToY(100, 10, true,
				// true);
				UI.getButton("pgChangePenguin").setVisible(true);
			}
		});

	}

	public static void translateOut() {
		TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(3000),
				backgroundA.getGroup());
		translateTransition1.setCycleCount(1);
		translateTransition1.setFromY(0);
		translateTransition1.setToY(500);
		TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(3000),
				backgroundB.getGroup());
		translateTransition2.setCycleCount(1);
		translateTransition2.setToY(500);
		translateTransition2.setFromY(0);
		TranslateTransition translateTransition3 = new TranslateTransition(Duration.millis(4000),
				platformSys.getGroup());
		translateTransition3.setCycleCount(1);
		translateTransition3.setToY(500);
		translateTransition3.setFromY(0);
		TranslateTransition translateTransition4 = new TranslateTransition(Duration.millis(4000), bird.getGroup());
		translateTransition4.setCycleCount(1);
		translateTransition4.setToY(500);
		translateTransition4.setFromY(0);

		// Code to make game screen flow downward when main menu is called
	}

	public static Group getGroup() {
		return root;
	}

	public static void setGroup(Group root) {
		PlayGame.root = root;
	}

}
