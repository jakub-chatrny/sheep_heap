package game.generator;

import java.util.Random;
import java.util.Stack;

import game.util.FileParser;
import game.GMap;
import game.GObjectList;
import game.object.creature.Sheep;



import static game.Global.*;

/**
 * Generates creatures to map
 * 
 * @author Jakub Chatrný
 *
 */
public class CreatureGenerator extends Generator {

	/**
	 * Generates sheeps on random position.
	 * 
	 * @param sheepCount
	 * @return
	 */
	public static void sheepsRandom(GObjectList objects, ObjectGenerator generator, int sheepCount) {
		GMap map = generator.getMap();
		Random random = new Random();
		int distatnce = map.getCols();
		int x, y;
		Sheep sheep;
		Sheep heapLeader = null;
		Stack<String[]> SHEEP_NAMES = FileParser.names();

		for (int i = 0; i < sheepCount && i<MAP_SIZE*MAP_SIZE; i++) {
			//SHEEP_NAMES.
			// set names
			if (!SHEEP_NAMES.isEmpty()) {
				String[] s = SHEEP_NAMES.pop();
				//o = new Sheep(s[0], s[1],getTexture("sheep"));
				if(i == 0){
					heapLeader = sheep = new Sheep(s[0], s[1], getTexture("sheep"), null);
				}
				else{
					sheep = new Sheep(s[0], s[1], getTexture("sheep"),heapLeader);
				}
			} else {
				if(i == 0){
					heapLeader = sheep = new Sheep("sheep" + i, getTexture("sheep"), null);
				}
				else{
					sheep = new Sheep("sheep" + i, getTexture("sheep"),heapLeader);
				}
			}

			// search random position until field can be seized
			do {
				x = random.nextInt(distatnce);
				y = random.nextInt(distatnce);
			} while (map.seizeField(sheep, map.getField(x, y)) == false);
			objects.add(sheep);
		}
	}
	/**
	 * Generates sheep clusters on random position.
	 * @param list
	 * @param generator
	 * @param treeCount
	 * @param clusterSize
	 */
	public static void sheepHeaps(GObjectList objects, ObjectGenerator generator, int sheepCount, int heapSize){	
		int x,y;
		GMap map = generator.getMap();	
		Stack<String[]> SHEEP_NAMES = FileParser.names();
		Random random = new Random();
		for (int count = 0; count < sheepCount/heapSize; count++) {
			do{
				x = random.nextInt(generator.getMap().getCols());
				y = random.nextInt(generator.getMap().getRows());
			} while (map.getField(x, y).canSieze(new Sheep("",null, null)) == false);
			sheepHeap(objects,generator,heapSize,x,y,SHEEP_NAMES);
		}	
	}	
	/**
	 * Generate sheep cluster.
	 * @param list
	 * @param generator
	 * @param clusterSize
	 * @param x
	 * @param y
	 */
	private static void sheepHeap(GObjectList objects, ObjectGenerator generator, int clusterSize, int x, int y, Stack<String[]> SHEEP_NAMES){
		Random random = new Random();
		
		GMap map = generator.getMap();
		Sheep sheep;
		Sheep heapLeader = null;
		
		for (int count = 0; count < clusterSize; count++) {
			if (!SHEEP_NAMES.isEmpty()) {
				String[] s = SHEEP_NAMES.pop();
				if(count == 0){
					heapLeader = sheep = new Sheep(s[0], s[1], getTexture("sheep"), null);
				}
				else{
					sheep = new Sheep(s[0], s[1], getTexture("sheep"),heapLeader);
				}
			} else {
				if(count == 0){
					heapLeader = sheep = new Sheep("sheep" + count, getTexture("sheep"), null);
				}
				else{
					sheep = new Sheep("sheep" + count, getTexture("sheep"),heapLeader);
				}
			}
			x += random.nextInt(8)-3;
			y += random.nextInt(8)-3;
			if ((generator.getMap().getField(x, y) != null && (map.seizeField(sheep, map.getField(x,y))))){
				objects.add(sheep);
			}
			
		}
	}
	

}