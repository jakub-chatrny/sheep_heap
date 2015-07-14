package game.graphic;

import static game.Global.*;

import java.awt.Point;

import game.object.GObject;

/**
 * Represents animaiton. There two types of animation STEP and ROTATION.
 * 
 * @author Jakub Chatrný
 *
 */
public class Animation {
	// modes
	public static final int A_STEP = 0;
	public static final int A_ROTATION = 1;
	// flags
	private boolean finishedX;
	private boolean finishedY;
	private boolean rotated;
	private int type;
	// source location a rotation
	public float sx;
	public float sy;
	public float sr;
	// destination location a rotation
	public float dx;
	public float dy;
	public float dr;
	// velocity = object speed * direction
	private float velX;
	private float velY;
	// refence
	private GObject object;

	public Animation(Point source, float sr, Point dest, float dr, float velX,
			float velY, int TYPE) {
		sx = source.x;
		sy = source.y;
		this.sr = sr;
		this.velX = velX;
		dx = dest.x;
		dy = dest.y;
		this.dr = dr;
		this.velY = velY;
		this.finishedX = this.finishedY = this.rotated = false;
		this.type = TYPE;
	}

	/**
	 * Runs animation by @type of animation (step, rotate). Counts new values
	 * based on @animationSpeed.
	 * 
	 * @param animationSpeed
	 * @return
	 */
	public boolean run(float animationSpeed) {
		if (this.type == A_STEP) {
			return step(animationSpeed);
		} else if (this.type == A_ROTATION) {
			return rotate(animationSpeed);
		}
		return true;
	}

	/**
	 * Animation shift based on @animationSpeed and @velocity in one frame.
	 * 
	 * @param animationSpeed
	 * @return
	 */
	private boolean step(float animationSpeed) {

		if (DEBUG_ANIMATION)
			System.out.println("sx=" + sx + ";sy=" + sy + ";dx=" + dx + ";dy="
					+ dy + ";velX=" + velX + ";velY=" + velY);

		rotate(animationSpeed);
		if ((int) velX < 0 && sx > dx) {
			sx += animationSpeed * velX; // moving left
		} else if ((int) velX > 0 && sx < dx) {

			sx += animationSpeed * velX; // moving right
		} else {
			setFinishedX(true);
		}

		if ((int) velY < 0 && sy > dy) {
			sy += animationSpeed * velY; // moving up
		} else if ((int) velY > 0 && sy < dy) {
			sy += animationSpeed * velY; // moving down
		} else {
			setFinishedY(true);
		}

		return isFinished();
	}

	/**
	 * Animation shift based on @animationSpeed and @velocity in one frame.
	 * 
	 * @param animationSpeed
	 * @return
	 */
	private boolean rotate(float animationSpeed) {
		// TODO so far works with speed 45 mod speed. Maybe velocity could solve
		// this issue.
		if (DEBUG_ANIMATION)
			System.out.println("sr=" + sr + ";dr=" + dr);
		if (sr < dr) { // left to right
			sr += 1f;
		} else if (sr > dr) { // right to left
			sr -= 1f;
		} else {
			return rotated = true;
		}
		return false;
	}

	/**
	 * Gets actual x position relative to map (not scaled to window).
	 * 
	 * @return
	 */
	public float getX() {
		return sx;
	}

	/**
	 * Gets actual y position relative to map (not scaled to window).
	 * 
	 * @return
	 */
	public float getY() {
		return sy;
	}

	/**
	 * Gets actual object rotation.
	 * 
	 * @return
	 */
	public float getRotation() {
		return sr;
	}

	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		if (this.type == A_STEP) {
			return finishedX && finishedY;
		} else if (this.type == A_ROTATION) {
			return isRotated();
		} else
			return true; // unknown type, so set as finished
	}

	/**
	 * @param finished
	 *            the finished to set
	 */
	public void setFinishedX(boolean finished) {
		this.finishedX = finished;
	}

	public void setFinishedY(boolean finished) {
		this.finishedY = finished;
	}

	/**
	 * @return the rotated
	 */
	public boolean isRotated() {
		return this.rotated;
	}

	/**
	 * @return the object
	 */
	public GObject getObject() {
		return object;
	}

	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(GObject object) {
		this.object = object;
	}
}