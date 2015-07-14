package game.object.plant;

import java.util.Random;

import game.object.Plant;
import game.Global;

/**
 * Represents tree in game.
 * 
 * @author Jakub Chatrný
 *
 */
public class Tree extends Plant {

	public Tree() {
		super();
		setSeizeble(false);
		Random rand = new Random();
		setSize((float) (rand.nextInt(6) + 9) / 10);
		setRotation(rand.nextInt(4) * 90);
		setTexture(Global.getTexture("tree"));
	}

	public Tree(float size) {
		super();
		setSeizeble(false);
		Random rand = new Random();
		setSize(size);
		setRotation(rand.nextInt(4) * 90);
		setTexture(Global.getTexture("tree"));
	}

	public Tree(float size, float rotation) {
		super();
		setSeizeble(false);
		setSize(size);
		setRotation(rotation);
		setTexture(Global.getTexture("tree"));
	}
}