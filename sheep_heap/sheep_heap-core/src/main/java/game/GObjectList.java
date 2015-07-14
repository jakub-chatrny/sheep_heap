package game;

import java.util.ArrayList;
import java.util.List;

import game.object.GObject;
import game.object.creature.Sheep;
import game.object.plant.Grass;
import game.object.plant.Tree;

/**
 * Contains all game objects (so far creatures and plants) except fields.
 * Objects are placed on proper display layer.
 * 
 * @author Jakub Chatrný
 *
 */
public class GObjectList {
	private List<GObject> displayLayer1;
	private List<GObject> displayLayer2;
	private List<GObject> displayLayer3;

	public GObjectList() {
		displayLayer1 = new ArrayList<GObject>();
		displayLayer2 = new ArrayList<GObject>();
		displayLayer3 = new ArrayList<GObject>();
	}

	/**
	 * Add Object to proper display layer by his class.
	 * 
	 * @param object
	 */
	public void add(GObject object) {
		if (object instanceof Sheep) {
			this.displayLayer2.add(object);
		} else if (object instanceof Tree) {
			this.displayLayer3.add(object);
		} else if (object instanceof Grass) {
			this.displayLayer1.add(object);
		}
	}

	/**
	 * Removes Object to proper display layer by his class.
	 * 
	 * @param object
	 */
	public void remove(GObject object) {
		if (object instanceof Sheep) {
			this.displayLayer2.remove(object);
		} else if (object instanceof Tree) {
			this.displayLayer3.remove(object);
		} else if (object instanceof Grass) {
			this.displayLayer1.remove(object);
		}
	}

	/**
	 * Size of all display layers together.
	 * 
	 * @return
	 */
	public int size() {
		return this.displayLayer1.size() + this.displayLayer2.size()
				+ this.displayLayer3.size();
	}

	/**
	 * Clears all display layers.
	 */
	public void clear() {
		this.displayLayer1.clear();
		this.displayLayer2.clear();
		this.displayLayer3.clear();
	}

	/**
	 * Returns layer with creatures.
	 * 
	 * @return
	 */
	public List<GObject> getCreatures() {
		return displayLayer2;
	}

	/**
	 * Returns layer with trees.
	 * 
	 * @return
	 */
	public List<GObject> getTrees() {
		return displayLayer3;
	}

	/**
	 * Returns layer with grass.
	 * 
	 * @return
	 */
	public List<GObject> getGrass() {
		return displayLayer1;
	}
}