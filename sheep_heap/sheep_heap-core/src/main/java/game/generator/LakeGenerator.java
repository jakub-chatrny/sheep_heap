package game.generator;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import game.object.Field;


/**
 * Generates lakes to map
 * 
 * @author Jakub Chatrný
 *
 */
public class LakeGenerator extends Generator {

	protected Field[][] fields;


	public LakeGenerator(Field[][] fields) {
		this.fields = fields;
	}

	/**
	 * Create lake. Lake can be minimal one field far away of the mountain.
	 * 
	 * @param pos
	 * @param depth
	 */
	public void createSimpleLake(Point pos, int depth) {
		Random random = new Random();

		List<Field> surroundings = getSurroundings(pos, fields);
		// check if there isn't mountain nearby
		for (Field tmp : surroundings) {
			if (tmp.getAltitude() > 0)
				return;
		}

		if (fields[pos.x][pos.y].getAltitude() == 0) {
			fields[pos.x][pos.y].setAltitude(-1);
		} else if (fields[pos.x][pos.y].getAltitude() < 0) { // already lake
																// there
			return;
		}

		for (Field tmp : surroundings) {
			if (tmp.getAltitude() == 0 && depth - 1 > 0) {
				createSimpleLake(tmp.getPosition(), depth - random.nextInt(2)-1);
			}
		}

	}
}