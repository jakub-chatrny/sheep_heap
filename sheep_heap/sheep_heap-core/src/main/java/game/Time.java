package game;

/**
 * Game time. needs to be incremented by inc().
 * 
 * @author Jakub Chatrný
 *
 */
public class Time {
	private static long seconds = 0;

	/**
	 * Gets Game Time in seconds.
	 * 
	 * @return
	 */
	public static String getSecondsString() {
		return seconds + "s";
	}

	/**
	 * Gets Game Time in format 0h0m0s.
	 * 
	 * @return
	 */
	public static String getTimeString() {
		long hours = seconds / 3600;
		long minutes = (seconds % 3600) / 60;
		long second = seconds % 60;
		return hours + "h" + minutes + "m" + second + "s";
	}

	/**
	 * Increments game time by one second.
	 * 
	 * @return actual seconds of game time.
	 */
	public static long inc() {
		return ++seconds;
	}
}