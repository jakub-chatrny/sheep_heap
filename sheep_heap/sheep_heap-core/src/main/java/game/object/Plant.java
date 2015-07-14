package game.object;

/**
 * Represents plant in game
 * 
 * @author Jakub Chatrný
 *
 */
public class Plant extends GObject {
	protected boolean seizeble;

	/**
	 * @return the seizeble
	 */
	public boolean isSeizeble() {
		return seizeble;
	}

	/**
	 * @param seizeble
	 *            the seizeble to set
	 */
	public void setSeizeble(boolean seizeble) {
		this.seizeble = seizeble;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(float size) {
		setTextureScale(size);
	}
}