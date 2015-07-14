package game.object.creature;

import static game.graphic.Animation.A_ROTATION;
import static game.graphic.Animation.A_STEP;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import game.GMap;
import game.ai.SheepAI;
import game.object.Creature;
import game.object.Field;
import game.graphic.Animation;

import org.newdawn.slick.opengl.Texture;

import static game.Global.*;

/**
 * Represents sheep in game.
 * 
 * @author Jakub Chatrný
 *
 */
public class Sheep extends Creature {
	private Sheep leader;
	
	public Sheep(String name, Texture texture, Sheep leader) {
		super(name, "", new Point(0, 0), random(3,8), 0f, 0f, texture);
		setLeader(leader);
	}

	public Sheep(String name, String quote, Texture texture, Sheep leader) {
		super(name, quote, new Point(0, 0), random(3,8), 0f, 0f, texture);
		setLeader(leader);
	}

	public Sheep(String name, Point pos, Texture texture, Sheep leader) {
		super(name, "", pos, random(3,8), 0f, 0f, texture);
		
	}

	/**
	 * Make shep move, here might be just call sheepAI
	 * 
	 * @param map
	 * @return animation
	 */
	public Animation move(GMap map) {
		float oldRotation = getRotation();
		Point oldPos = getPosition();

		Random random = new Random();
		if (random.nextInt(2) == 0) {
			oldRotation = rotate();
			Animation animation = new Animation(oldPos, oldRotation,
					getPosition(), getRotation(), getVelocity()[0],
					getVelocity()[1], A_ROTATION);
			return animation; // success
		} else {
			Point pos = getCoordsFieldAhead();
			Field newField = map.getField(pos.x, pos.y);
			if (newField != null && map.seizeField(this, newField)) {
				Animation animation = new Animation(oldPos, oldRotation,
						getPosition(), getRotation(), getVelocity()[0],
						getVelocity()[1], A_STEP);
				return animation; // success
			}
		}
		return null; // stay on same position
	}

	/**
	 * Returns list of field which are under sheep max view.
	 * @return
	 */
	public List<Field> getVisionFields(){	
		return SheepAI.getVisionFields(this);
	}
	
	/**
	 * rotate object random for 45°
	 * 
	 * @return old rotation
	 */
	public float rotate() {
		Random random = new Random();
		float oldRotation = getRotation();
		int decide = random.nextInt(2); // +45 0 -45
		if (decide == 0) {
			setRotation(getRotation() + 45);
		} else if (decide == 1) {
			setRotation(getRotation() - 45);
		}
		// else stay same

		return oldRotation;
	}

	public Sheep getLeader() {
		return leader;
	}

	public void setLeader(Sheep leader) {
		this.leader = leader;
	}
	
	public boolean amILeader(){
		return getLeader() == null;
	}
}