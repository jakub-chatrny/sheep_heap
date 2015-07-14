package game.generator;

import java.util.Random;

import game.GObjectList;
import game.object.plant.Grass;
import game.object.plant.Tree;

/**
 * Generate game plants.
 * @author Jakub Chatrný
 *
 */
public class PlantGenerator {
	/**
	 * Generate trees on random position.
	 * @param list
	 * @param og
	 * @param treeCount
	 */
	public static void treesRandom(GObjectList list, ObjectGenerator generator, int treeCount){
		int x,y;
		Random random = new Random();
		for (int count = 0; count < treeCount; count++) {
			Tree tree = new Tree(1.0f);
			do{
				x = random.nextInt(generator.getMap().getCols());
				y = random.nextInt(generator.getMap().getRows());
			} while (generator.getMap().placePlant(tree, generator.getMap().getField(x, y)) == false);
			list.add(tree);
		}
	}
	/**
	 * Generates trees clusters on random position.
	 * @param list
	 * @param generator
	 * @param treeCount
	 * @param clusterSize
	 */
	public static void treeClusters(GObjectList list, ObjectGenerator generator , int treeCount, int clusterSize){	
		int x,y;
		Random random = new Random();
		for (int count = 0; count < treeCount/clusterSize; count++) {
			do{
				x = random.nextInt(generator.getMap().getCols());
				y = random.nextInt(generator.getMap().getRows());
			} while (generator.getMap().getField(x, y).canPlacePlant() == false);
			treeCluster(list,generator,clusterSize,x,y);
		}	
	}	
	/**
	 * Generate tree cluster.
	 * @param list
	 * @param generator
	 * @param clusterSize
	 * @param x
	 * @param y
	 */
	private static void treeCluster(GObjectList list, ObjectGenerator generator, int clusterSize, int x, int y){
		Random random = new Random();
		for (int count = 0; count < clusterSize; count++) {
			Tree tree = new Tree();
			x += random.nextInt(8)-3;
			y += random.nextInt(8)-3;
			if ((generator.getMap().getField(x, y) != null 
					&& (generator.getMap().placePlant(tree, generator.getMap().getField(x, y))))){
				list.add(tree);
			}
			
		}
	}
	
	/**
	 * Generates trees clusters on random position.
	 * @param list
	 * @param generator
	 * @param treeCount
	 * @param clusterSize
	 */
	public static void grassClusters(GObjectList list, ObjectGenerator generator , int treeCount, int clusterSize){	
		int x,y;
		Random random = new Random();
		for (int count = 0; count < treeCount/clusterSize; count++) {
			do{
				x = random.nextInt(generator.getMap().getCols());
				y = random.nextInt(generator.getMap().getRows());
			} while (generator.getMap().getField(x, y).canPlacePlant() == false);
			grassCluster(list,generator,clusterSize,x,y);
		}	
	}	
	/**
	 * Generate tree cluster.
	 * @param list
	 * @param generator
	 * @param clusterSize
	 * @param x
	 * @param y
	 */
	private static void grassCluster(GObjectList list, ObjectGenerator generator, int clusterSize, int x, int y){
		Random random = new Random();
		for (int count = 0; count < clusterSize; count++) {
			Grass grass = new Grass();
			x += random.nextInt(8)-3;
			y += random.nextInt(8)-3;
			if ((generator.getMap().getField(x, y) != null 
					&& (generator.getMap().placePlant(grass, generator.getMap().getField(x, y))))){
				list.add(grass);
			}
			
		}
	}
}