package game.object;

import static game.GMap.colors;

import org.newdawn.slick.opengl.Texture;

import game.Global;

/**
 * Game field. Can be seized by creatures.
 * 
 * @author Jakub Chatrný
 *
 */
public class Field extends GObject {
	private int altitude;

	private Creature creature;
	private Plant plant;

	public Field(int x, int y, int altitude, Texture texture) {
		super();
		setAltitude(altitude);
		setPosition(x, y);
	}

	/**
	 * Returns whether is field seized.
	 * 
	 * @return
	 */
	public boolean isSeized() {
		return (creature != null || (plant != null && !plant.isSeizeble()));
	}

	/**
	 * Returns if field can be seized.
	 * 
	 * @param o
	 * @return
	 */
	public boolean canSieze(GObject o) {
		return (!isSeized() && altitude == 0);
	}

	public boolean canPlacePlant() {
		return (getAltitude() == 0 && !isSeized());
	}

	public void setCreature(Creature creature) {
		this.creature = creature;
	}

	public Creature getCreature() {
		return this.creature;
	}

	/**
	 * @return the plant
	 */
	public Plant getPlant() {
		return plant;
	}

	/**
	 * @param plant
	 *            the plant to set
	 */
	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public void removeCreature() {
		this.creature = null;
	}

	/**
	 * @return the altitude
	 */
	public int getAltitude() {
		return altitude;
	}

	/**
	 * Return altitude as character. Integer.toString(altitude).charAt(0)
	 * 
	 * @return
	 */
	public char getCharAltitude() {
		return Integer.toString(altitude).charAt(0);
	}

	public String getTextAltitude() {
		if (altitude < 0) {
			return "w";
		}
		return String.valueOf(altitude);
	}

	/**
	 * @param altitude
	 *            the altitude to set
	 */
	public void setAltitude(int altitude) {
		this.altitude = altitude;
		setColor(colors.get(altitude));
		setTexture(getFieldTexture(altitude));
	}

	/**
	 * @param texture
	 *            the texture to set
	 */
	public static Texture getFieldTexture(int altitude) {
		if (altitude < 0) {
			return Global.getTexture("water");
		} else if (altitude >= 0 && altitude <= 3) {
			return Global.getTexture("grass");// grass
		} else if (altitude > 3 && altitude < 9) {
			return Global.getTexture("dirt");// dirt
		} else if (altitude >= 9 && altitude <= 255) {
			return Global.getTexture("snow");
		} else
			return Global.getTexture("blank");
	}
}