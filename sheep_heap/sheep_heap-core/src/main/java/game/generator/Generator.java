package game.generator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


import game.object.Field;

import static game.object.GObject.DIRECTIONS;;
/**
 * Abstract class every generator should extend this. Contains methods for finding surroundings fields
 * @author Jakub Chatrný
 *
 */
public abstract class Generator {
	public static final int[][] CARDINAL_DIRECTIONS = new int[][]{{-1,0},  {0,1},  {1,0},  {0, -1}};
	public static final int[][] DIAGONAL_DIRECTIONS = new int[][]{{-1,-1}, {-1,1}, {1,1}, {1,-1}};
	/**
	 * Get list of direct surroundings.
	 * 0 1 0
	 * 1 x 1
	 * 0 1 0
	 * @param pos
	 * @param fields
	 * @return
	 */
    public static List<Field> getDirectSurroundings(Point pos, Field fields[][]){
    	List<Field> surroundings = new ArrayList<Field>();
        for (int[] direction : CARDINAL_DIRECTIONS) {
            int cx = pos.x + direction[0];
            int cy = pos.y + direction[1];
            if(cy >=0 && cy < fields.length)
                if(cx >= 0 && cx < fields[cy].length){
                	surroundings.add(fields[cx][cy]);	
                }		
        }
    	return surroundings;
    }
    
    /**
     * Get list of diagonal surroundings.
	 * 1 0 1
	 * 0 x 0
	 * 1 0 1 
     * @param pos
     * @return
     */
    public static List<Field> getDiagonalSurroundings(Point pos, Field fields[][]){
    	List<Field> surroundings = new ArrayList<>();
        for (int[] direction : DIAGONAL_DIRECTIONS) {
            int cx = pos.x + direction[0];
            int cy = pos.y + direction[1];
            if(cy >=0 && cy < fields.length)
                if(cx >= 0 && cx < fields[cy].length){
                	surroundings.add(fields[cx][cy]);	
                }		
        }
    	return surroundings;
    }
    
    /**
     * Get list of all surroundings.
	 * 1 1 1
	 * 1 x 1
	 * 1 1 1 
     * @param pos
     * @return
     */
    public static  List<Field> getSurroundings(Point pos, Field fields[][]){
    	List<Field> surroundings = new ArrayList<>();
        for (int[] direction : DIRECTIONS) {
            int cx = pos.x + direction[0];
            int cy = pos.y + direction[1];
            if(cy >=0 && cy < fields.length)
                if(cx >= 0 && cx < fields[cy].length){
                	surroundings.add(fields[cx][cy]);	
                }		
        }
    	return surroundings;
    }
}