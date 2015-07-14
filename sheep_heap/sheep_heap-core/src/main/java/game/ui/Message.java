package game.ui;

/**
 * Represents message printed to std out or rendered to display
 * 
 * @author jchatrny
 *
 */
public class Message {
	public static final String MES_GAME_SAVED = "Game successfuly saved.";
	public static final String MES_GAME_LOADED = "Game successfuly loaded.";
	public static final String MES_GAME_GENERATED = "New Game generated in: ";
	private int duration = 3;
	private String message;

	public Message(int key) {
		// message =
	}

	public Message(int key, int duration) {

	}

	public Message(String msg) {
		message = msg;

	}

	public Message(String msg, int duration) {
		message = msg;
	}

	public String getMessage() {
		return message;
	}

	public int decDuration() {
		return --duration;
	}

	public int getDuration() {
		return duration;
	}

}