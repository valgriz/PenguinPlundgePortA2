package com.twopercent.render;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.util.Map;
import java.util.stream.Collectors;

public class SoundPlayer {

	private static Media bounce = new Media(SoundPlayer.class.getResource("/res/sounds/BounceEffect.mp3").toString());
	private static Media button = new Media(SoundPlayer.class.getResource("/res/sounds/ButtonSound.mp3").toString());
	private static Media fallingPlatform = new Media(SoundPlayer.class.getResource("/res/sounds/FallingPlatform.mp3")
			.toString());
	private static Media starSound = new Media(SoundPlayer.class.getResource("/res/sounds/StarSound.mp3").toString());
	private static double volume = 1.0;

	private static final Map<Media, Double> volumes = Map.ofEntries(
			Map.entry(bounce, volume),
			Map.entry(button, volume / 10),
			Map.entry(fallingPlatform, volume / 10),
			Map.entry(starSound, volume / 20)
	);

	private static final Map<Media, AudioClip> playerMap = volumes.entrySet().stream()
			.collect(Collectors.toMap(Map.Entry::getKey, entry -> {
				var m = new AudioClip(entry.getKey().getSource());
				m.setVolume(entry.getValue());
				return m;
			}));

	// private static Media

	public static void playBounce() {
		playerMap.get(bounce).play();
	}

	public static void playButton() {
		playerMap.get(button).play();
	}

	public static void playFallingPlatform() {
		playerMap.get(fallingPlatform).play();
	}

	public static void playStarSound() {
		playerMap.get(starSound).play();
	}

	public static void volumeControl(int i) {
		if (i == 0) {
			unmute();
		} else {
			mute();
		}
	}

	public static void mute() {
		playerMap.values().forEach(e -> e.setVolume(0));
	}

	public static void unmute() {
		playerMap.forEach((key, value) -> value.setVolume(volumes.get(key)));
	}

}
