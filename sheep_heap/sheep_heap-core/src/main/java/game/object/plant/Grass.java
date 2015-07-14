package game.object.plant;

import java.util.Random;

import game.Global;
import game.object.Plant;

/**
 * Represents grass in game.
 * 
 * @author Jakub Chatrný
 *
 */
public class Grass extends Plant {
	public Grass() {
		super();
		Random rand = new Random();
		// setSize((float)(rand.nextInt(6)+9)/10);
		setRotation(rand.nextInt(8) * 45);
		setSeizeble(true);
		setTexture(Global.getTexture("eatable_grass2"));
	}

	public Grass(float size) {
		super();
		setSeizeble(true);
		Random rand = new Random();
		setSize(size);
		setRotation(rand.nextInt(4) * 90);
		setTexture(Global.getTexture("eatable_grass2"));
	}

	public Grass(float size, float rotation) {
		super();
		setSeizeble(true);
		setSize(size);
		setRotation(rotation);
		setTexture(Global.getTexture("eatable_grass2"));
	}
}