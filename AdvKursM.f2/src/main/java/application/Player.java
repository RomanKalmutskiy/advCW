package application;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player {

	Player() {

		String bip = "popcorn.mp3";
		System.out.println(1);
		Media hit = new Media(new File(bip).toURI().toString());
		System.out.println(2);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		System.out.println(3);
		mediaPlayer.play();

	}

}
