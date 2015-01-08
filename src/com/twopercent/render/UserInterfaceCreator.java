package com.twopercent.render;

import java.util.ArrayList;

import com.twopercent.main.Global;

import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UserInterfaceCreator {

	private int score, buttonCount;
	private Group group;
	private Text scoreText;
	private static ArrayList<Button> buttonArrayList;

	public UserInterfaceCreator() {
		group = new Group();
		scoreText = new Text();
		buttonArrayList = new ArrayList<>();

	}

	public void addButton(int x, int y, int width, int height, String text, int buttonSpriteKey, String id) {
		buttonArrayList.add(new Button(x, y, width, height, text, buttonSpriteKey, id));
		group.getChildren().add(buttonArrayList.get(buttonArrayList.size() - 1).getGroup());
		buttonArrayList.get(buttonArrayList.size() - 1).setVisible(false);
	}

	public void addNullButton(int x, int y, int width, int height, String id) {
		buttonArrayList.add(new Button(x, y, width, height, id));
		group.getChildren().add(buttonArrayList.get(buttonArrayList.size() - 1).getGroup());
		buttonArrayList.get(buttonArrayList.size() - 1).setVisible(false);
	}

	public static Button getButton(String id) {
		for (int i = 0; i < buttonArrayList.size(); i++) {
			if (id.equals(buttonArrayList.get(i).getId())) {
				return buttonArrayList.get(i);
			}

		}
		return null;
	}

	public void update() {

		for (int i = 0; i < buttonArrayList.size(); i++) {
			buttonArrayList.get(i).update();
		}

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Text getScoreText() {
		return scoreText;
	}

	public void setScoreText(Text scoreText) {
		this.scoreText = scoreText;
	}

	public static ArrayList<Button> getButtonArrayList() {
		return buttonArrayList;
	}

	public static void setButtonArrayList(ArrayList<Button> buttonArrayList) {
		UserInterfaceCreator.buttonArrayList = buttonArrayList;
	}

}