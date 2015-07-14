package game.object;

import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import game.graphic.Sprite;

/**
 * Game object. All creatures and plants extends this class.
 * 
 * @author Jakub Chatrný
 *
 */
public abstract class GObject {
	public static final int[][] DIRECTIONS = new int[][] { { 0, -1 },
			{ 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 },
			{ -1, -1 } };
	protected Point position;
	protected Sprite sprite;

	public GObject() {
		newSprite();
	}

	/**
	 * @return the texture of sprite
	 */
	public Texture getTexture() {
		if (sprite != null) {
			return sprite.getTexture();
		}
		return null;
	}

	/**
	 * @param texture
	 *            of sprite
	 */
	public void setTexture(Texture texture) {
		if (sprite != null) {
			sprite.setTexture(texture);
		}
	}

	public float getTextureScale() {
		if (sprite != null) {
			return sprite.getScale();
		}
		return 1f;
	}

	public void setTextureScale(float scale) {
		if (sprite != null) {
			sprite.setScale(scale);
		}
	}

	/**
	 * @return the color of sprite
	 */
	public Color getColor() {
		if (sprite != null) {
			return sprite.getColor();
		}
		return null;
	}

	/**
	 * @param color
	 *            of sprite
	 */
	public void setColor(Color color) {
		if (sprite != null) {
			sprite.setColor(color);
		}
	}

	/**
	 * Sets rotation of object. Expected values are -180..180
	 * 
	 * @return the rotation of sprite
	 */
	public float getRotation() {
		if (sprite != null) {
			return sprite.getRotation();
		}
		return 0.0f;
	}

	/**
	 * @param rotation
	 *            of sprite
	 */
	public void setRotation(float rotation) {
		if (sprite != null) {
			sprite.setRotation(rotation);
			if (rotation > 180) {
				sprite.setRotation(rotation - 360);
			} else if (rotation <= -180) {
				sprite.setRotation(rotation + 360);
			} else
				sprite.setRotation(rotation);
		}
	}

	/**
	 * Gets direction of object. For rotation 0 is direction 0,-1, 45 is 1,-1
	 * and so on.
	 * 
	 * @return
	 */
	public Point getDirection() {
		int dir = (int) getRotation();
		// Rotation index is 0->0, 45->1, 90->2, 135->3, 180->4, -135->5,
		// -90->6, -45->7
		int ri;
		if (0 > dir) {
			ri = 8 - Math.abs(dir / 45);
		} else {
			ri = dir / 45;
		}
		return new Point(DIRECTIONS[ri][0], DIRECTIONS[ri][1]);
	}

	/**
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @return the position
	 */
	public String getPositionString() {
		return "(" + position.x + "," + position.y + ")";
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	public void setPosition(int x, int y) {
		this.position = new Point(x, y);
	}

	/**
	 * Gets coordinates field ahead no matter if field exists.
	 * 
	 * @return
	 */
	public Point getCoordsFieldAhead() {
		return new Point(getPosition().x + getDirection().x, getPosition().y
				+ getDirection().y);

	}

	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * @param sprite
	 *            the sprite to set
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * @param new sprite
	 */
	public void newSprite() {
		this.sprite = new Sprite();
	}
}