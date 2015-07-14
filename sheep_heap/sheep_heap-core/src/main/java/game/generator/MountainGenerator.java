package game.generator;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import game.object.Field;

/**
 * Generates mountains to map
 * 
 * @author Jakub Chatrný
 *
 */
public class MountainGenerator extends Generator {
	protected Field[][] fields;
	private Random random;


	public MountainGenerator(Field[][] fields) {
		this.fields = fields;
		random = new Random();
	}

	/**
	 * Create simple mountain.
	 * 
	 * @param pos
	 * @param height
	 */
	public void mountain(Point pos, int height) {
		List<Field> surroudings = getSurroundings(pos, this.fields);
		for (Field neighbour : surroudings) {
			if (height > 0 && neighbour.getAltitude() < height) {
				neighbour.setAltitude(height);
				mountain(neighbour.getPosition(), height - random.nextInt(2) - 1);
			}
		}

	}
}