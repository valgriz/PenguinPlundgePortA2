package com.valgriz.screen;

import javafx.scene.Group;
import javafx.scene.image.Image;

import com.twopercent.render.VisibleObject;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Objects;

public class LoadingScreen extends VisibleObject {

	@SneakyThrows
	public LoadingScreen(Group load) {
		setGroup(load);
		try (InputStream resourceAsStream = getClass()
				.getResourceAsStream("/res/images/loadingScreen.png")) {
			setImageViewToImage(new Image(Objects.requireNonNull(resourceAsStream)));
		}
		getGroup().getChildren().add(getImageView());
		setX(0);
		setY(0);
		syncCoords();
	}

	public void update() {

	}

}
