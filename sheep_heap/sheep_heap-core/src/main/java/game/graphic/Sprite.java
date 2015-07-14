package game.graphic;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/**
 * Represents rendered sprite. Contains texture, color , rotation and scale
 * @author Jakub Chatrný
 *
 */
public class Sprite {
	private Texture texture;
	private Color color;
	private float rotation;
	private float scale;

	public Sprite() {
		setRotation(0.0f);
		setScale(1.0f);
	}
	public Sprite(Texture texture) {
		setTexture(texture);
		setRotation(0.0f);
		setScale(1.0f);
	}

	/**
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * @param texture
	 *            the texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * Set color of sprite
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	public void setColor(float r, float g, float b, float a) {
		this.color = new Color(r,g,b,a);
	}

	/**
	 * @return the rotation
	 */
	public float getRotation() {
		return rotation;
	}

	/**
	 * @param rotation
	 *            the rotation to set
	 */
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	/**
	 * @return the size
	 */
	public float getScale() {
		return scale;
	}

	/**
	 * @param scale
	 *            the size to set
	 */
	public void setScale(float scale) {
		this.scale = scale;
	}
}