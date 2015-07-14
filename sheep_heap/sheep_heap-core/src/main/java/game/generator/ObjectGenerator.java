package game.generator;

import game.GMap;
import game.GObjectList;

/**
 * Generates game objects.
 * 
 * @author Jakub Chatrný
 *
 */
public class ObjectGenerator extends Generator {
	private GMap map;

	public ObjectGenerator(GMap map) {
		this.map = map;
	}
	public void sheepsRandom(GObjectList objects, int sheepCount){
		CreatureGenerator.sheepsRandom(objects, this, sheepCount);
	}
	public void sheepHeaps(GObjectList objects, int sheepCount, int heapSize){
		CreatureGenerator.sheepHeaps(objects, this, sheepCount, heapSize);
	}
	public void treesRandom(GObjectList objects, int treeCount){
		PlantGenerator.treesRandom(objects, this, treeCount);
	}
	public void treesClusters(GObjectList objects, int treeCount, int clusterSize){
		PlantGenerator.treeClusters(objects, this, treeCount, clusterSize);
	}
	public void grassClusters(GObjectList objects, int treeCount, int clusterSize){
		PlantGenerator.grassClusters(objects, this, treeCount, clusterSize);
	}
	/**
	 * @return the map
	 */
	public GMap getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(GMap map) {
		this.map = map;
	}
}