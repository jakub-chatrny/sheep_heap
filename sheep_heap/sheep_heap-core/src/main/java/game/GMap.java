package game;

import org.newdawn.slick.Color;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import game.generator.MapGenerator;
import game.object.Creature;
import game.object.Field;
import game.object.Plant;
import game.object.creature.Sheep;

import static game.Global.*;
import static game.generator.MapGenerator.initFields;

/**
 * Game map, take care for right field array initialization. Map can be saved
 * (@map.saveToFile) or loaded (trough constructor) from file
 * 
 * @author jchatrny
 *
 */
public class GMap {
	private String name;
	private int rows;
	private int cols;
	private long buildTime;
	private Field[][] fields;

	/**
	 * Create new map from file
	 */
	public GMap(String path, String name) {
		try {
			loadMapFromFile(path + name);
		} catch (IOException e) {
			System.err.println("Loading map from file failed.");
			e.printStackTrace();
		}
	}

	/**
	 * Creates new map from generator
	 * 
	 * @param size
	 * @param lakeCount
	 */
	public GMap(int size, int lakeCount) {
		name = "default_map";
		setRows(size);
		setCols(size);
		int mountainMaxSize = 12;
		int mountainMinSize = 6;
		int mountainCount = (size * size)
				/ (mountainMaxSize * mountainMaxSize * mountainMaxSize);
		MapGenerator generator = new MapGenerator(size, size, mountainMaxSize,
				mountainMinSize, mountainCount, lakeCount);
		this.fields = generator.random();
		this.buildTime = generator.getTime();
	}

	/**
	 * Creates new empty map.
	 * 
	 * @param size
	 */
	GMap(int size) {
		setName("default");
		setRows(size);
		setCols(size);
		setFields(MapGenerator.initFields(size, size, getTexture("grass")));
	}

	/**
	 * Try seize field of map by creature.
	 * 
	 * @param o
	 * @param m
	 * @return success of operation
	 */
	public boolean seizeField(Creature creature, Field field) {
		if (field.canSieze(creature)) {
			// remove from old position
			Field oldField = getField(creature.getPosition());
			if (null != oldField)
				oldField.removeCreature();

			// set new position
			field.setCreature(creature);
			creature.setPosition(field.getPosition());

			return true;
		} else {
			return false;
		}
	}
	public boolean seizeField(Creature creature, Point pos) {
		Field newField = getField(pos);
		if (newField!= null && newField.canSieze(creature)) {
			// remove from old position
			Field oldField = getField(creature.getPosition());
			if (null != oldField)
				oldField.removeCreature();

			// set new position
			newField.setCreature(creature);
			creature.setPosition(newField.getPosition());

			return true;
		} else {
			return false;
		}
	}
	public Creature getCreatureOn(int x, int y){
		Field field;
		if((field = getField(x, y)) != null){
			return field.getCreature();
		}
		return null;
	}

	public boolean placePlant(Plant plant, Field field) {
		if (field.canPlacePlant()) {
			field.setPlant(plant);
			plant.setPosition(field.getPosition());
			return true;
		} else
			return false;
	}

	/**
	 * Prints map to std out.
	 */
	public void print() {
		System.out.println(getName());
		// +2 counts frame 'x' on both sides
		for (int i = 0; i < getCols() + 2; i++) {
			for (int j = 0; j < getRows() + 2; j++) {
				// +1 means frame 'x'
				if ((i == this.getCols() + 1) || (j == this.getRows() + 1)
						|| (i == 0) || (j == 0)) {
					System.out.print("x ");
				} else {
					System.out.print(fields[i - 1][j - 1].getCharAltitude()
							+ " ");
				}
			}
			// new line
			System.out.println();
		}
	}
	/**
	 * Prints map and objects on map to std out.
	 */
	public void printObjects() {
		System.out.println(getName());
		// +2 counts frame 'x' on both sides
		for (int i = 0; i < getCols() + 2; i++) {
			for (int j = 0; j < getRows() + 2; j++) {
				// +1 means frame 'x'
				if ((i == this.getCols() + 1) || (j == this.getRows() + 1)
						|| (i == 0) || (j == 0)) {
					System.out.print("x ");
				} else {
					if(fields[i - 1][j - 1].isSeized()){
						if((fields[i - 1][j - 1].getCreature()) instanceof Sheep){
							System.out.print("S" + " ");	
						}
						//TODO other objects
					}
					else{
					System.out.print(fields[i - 1][j - 1].getCharAltitude()
							+ " ");
					}
				}
			}
			// new line
			System.out.println();
		}
	}
	public void printObjects(PrintStream out) {
		out.println(getName());
		// +2 counts frame 'x' on both sides
		for (int i = 0; i < getCols() + 2; i++) {
			for (int j = 0; j < getRows() + 2; j++) {
				// +1 means frame 'x'
				if ((i == this.getCols() + 1) || (j == this.getRows() + 1)
						|| (i == 0) || (j == 0)) {
					out.print("x ");
				} else {
					if(fields[i - 1][j - 1].isSeized()){
						if((fields[i - 1][j - 1].getCreature()) instanceof Sheep){
							out.print("S" + " ");	
						}
						//TODO other objects
					}
					else{
					out.print(fields[i - 1][j - 1].getCharAltitude()
							+ " ");
					}
				}
			}
			// new line
			out.println();
		}
	}
	/**
	 * Save map to text file with extension map.
	 * 
	 * @param name
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @name name of created file no need to specify type of file
	 */
	public int saveToFile(String name) throws FileNotFoundException,
			UnsupportedEncodingException {
		// TODO check input
		PrintWriter writer = new PrintWriter(SAVED_MAPS + name + ".map",
				"UTF-8");
		writer.println(getName());
		writer.println(getRows());
		writer.println(getCols());
		// +2 counts frame 'x' on both sides
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getCols(); j++) {
				writer.print(fields[j][i].getTextAltitude() + " ");
			}
			writer.println();
		}
		writer.close();
		return 0;
	}

	/**
	 * Loads map from file.
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void loadMapFromFile(String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String name = br.readLine();
		int rows = Integer.parseInt(br.readLine());
		int cols = Integer.parseInt(br.readLine());

		// init values
		Field[][] fields = initFields(rows, cols, getTexture("grass"));
		String[] parts;
		int i = 0;
		int j = 0;

		String line = br.readLine(); // first line of map

		while (line != null) {

			parts = line.split(" ");

			for (String part : parts) {
				// System.out.println(part);
				switch (part) {
				case "w":
				case "-":
					fields[j][i].setAltitude(-1);
					j++;
					break;
				case "x":
				case " ":
					break;
				default:
					if (tryParseInt(part) && Integer.parseInt(part) >= 0
							&& Integer.parseInt(part) <= 255) {
						fields[j][i].setAltitude(Integer.parseInt(part));
						j++;
					}
				}
			}
			line = br.readLine();
			j = 0;
			i++;
		}
		setName(name);
		setRows(rows);
		setCols(cols);
		setFields(fields);
		br.close();
	}

	/**
	 * is String int?
	 * 
	 * @param value
	 * @return
	 */
	private boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/**
	 * Returns time taken to build map
	 * 
	 * @return
	 */
	public long getBuildTime() {
		return this.buildTime;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Gets field on certain position.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Field getField(int x, int y) {
		if (x < 0 || y < 0 || x >= getRows() || y >= getCols())
			return null;
		else
			return fields[x][y];
	}

	/**
	 * Gets field on certain position.
	 * 
	 * @param position
	 * @return
	 */
	public Field getField(Point position) {
		if (position.x < 0 || position.y < 0 || position.x >= getRows()
				|| position.y >= getCols())
			return null;
		else
			return fields[position.x][position.y];
	}

	/**
	 * @return the fields
	 */
	public Field[][] getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(Field[][] fields) {
		this.fields = fields;
	}

	public static final HashMap<Integer, Color> colors;
	static {
		colors = new HashMap<Integer, Color>();
		colors.put(-1, new Color(55, 125, 162));
		colors.put(0, new Color(95, 186, 48));
		colors.put(1, new Color(106, 154, 40));
		colors.put(2, new Color(116, 123, 33));
		colors.put(3, new Color(122, 106, 29));
		colors.put(4, new Color(126, 93, 26));
		colors.put(5, new Color(129, 86, 24));
		colors.put(6, new Color(130, 84, 24));
		colors.put(7, new Color(131, 83, 24));
		colors.put(8, new Color(132, 82, 24));
		colors.put(9, new Color(255, 255, 255));
		colors.put(10, new Color(255, 255, 255));
		colors.put(11, new Color(255, 255, 255));
		colors.put(12, new Color(255, 255, 255));
		colors.put(13, new Color(255, 255, 255));
		colors.put(255, new Color(50, 50, 50));
	}
}