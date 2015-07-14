package game.generator;

import java.awt.Point;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;

import game.object.Field;

import static game.Global.*;

/**
 * Generates game map.
 * 
 * @author Jakub Chatrný
 *
 */
public class MapGenerator extends Generator {
	protected int rows;
	protected int cols;
	protected int maxAlt;
	protected int minAlt;
	protected int mountainCount;
	protected int lakeCount;
	protected long time;

	public MapGenerator(int rows, int cols, int maxAlt, int minAlt,
			int mountainCount, int lakeCount) {
		setRows(rows);
		setCols(cols);
		setMaxAlt(maxAlt);
		setMinAlt(minAlt);
		setMountainCount(mountainCount);
		setLakeCount(lakeCount);
	}

	/**
	 * Generates just one mountain in middle
	 * 
	 * @return
	 */
	public Field[][] test() {
		time = System.currentTimeMillis();
		int x = cols / 2;
		int y = rows / 2;
		Field[][] fields = initFields(rows, cols, getTexture("grass"));
		MountainGenerator mg = new MountainGenerator(fields);
		mg.mountain(new Point(x, y), maxAlt);
		time = System.currentTimeMillis() - time;
		return fields;
	}

	/**
	 * Generates mountains and lakes on random position
	 * 
	 * @return
	 */
	public Field[][] random() {
		// init
		int x, y, alt;
		time = System.currentTimeMillis();
		Field[][] fields = initFields(rows, cols, getTexture("grass"));
		MountainGenerator mg = new MountainGenerator(fields);
		LakeGenerator lg = new LakeGenerator(fields);
		Random random = new Random();

		for (int count = 0; count < mountainCount; count++) {
			alt = random.nextInt(maxAlt - minAlt) + 1 + minAlt;
			x = random.nextInt(cols);
			y = random.nextInt(rows);
			mg.mountain(new Point(x, y), alt);
		}

		for (int count = 0; count < lakeCount; count++) {
			alt = 20;
			x = random.nextInt(cols);
			y = random.nextInt(rows);
			lg.createSimpleLake(new Point(x, y), alt);
		}
		time = System.currentTimeMillis() - time;
		return fields;
	}

	/**
	 * Initialize array of fields with 0 altitude;
	 * 
	 * @param rows
	 * @param cols
	 * @return
	 */
	public static Field[][] initFields(int rows, int cols, Texture texture) {
		Field[][] fields = new Field[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				fields[i][j] = new Field(i, j, 0, texture);
			}
		}
		return fields;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @param cols
	 *            the cols to set
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}

	/**
	 * @return the maxAlt
	 */
	public int getMaxAlt() {
		return maxAlt;
	}

	/**
	 * @param maxAlt
	 *            the maxAlt to set
	 */
	public void setMaxAlt(int maxAlt) {
		this.maxAlt = maxAlt;
	}

	/**
	 * @return the minAlt
	 */
	public int getMinAlt() {
		return minAlt;
	}

	/**
	 * @param minAlt
	 *            the minAlt to set
	 */
	public void setMinAlt(int minAlt) {
		this.minAlt = minAlt;
	}

	/**
	 * @return the mountainCount
	 */
	public int getMountainCount() {
		return mountainCount;
	}

	/**
	 * @param mountainCount
	 *            the mountainCount to set
	 */
	public void setMountainCount(int mountainCount) {
		this.mountainCount = mountainCount;
	}

	/**
	 * @return the lakeCount
	 */
	public int getLakeCount() {
		return lakeCount;
	}

	/**
	 * @param lakeCount
	 *            the lakeCount to set
	 */
	public void setLakeCount(int lakeCount) {
		this.lakeCount = lakeCount;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

}