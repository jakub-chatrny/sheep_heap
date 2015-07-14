package game;

import java.util.Random;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import game.util.*;
import kryten.factory.*;

/**
 * Game settings with global accessibility.
 * loadConfig() loads settings from config.ini file. 
 * @author Jakub Chatrný
 *
 */
public class Global {
	public static int DEFAULT_WINDOW_WIDTH = 800;
	public static int DEFAULT_WINDOW_HEIGHT = 600;
	
	public static int ZOOM = 2;
	public static int MIN_ZOOM = 10;
	public static int MAX_ZOOM = 80;
	
	public static boolean TEST_MODE = false;
	public static boolean DEBUG = false;
	public static boolean DEBUG_MAP = false;
	public static boolean DEBUG_ANIMATION = false;
	public static boolean DEBUG_RESOURCE = false;
	
	public static int MAP_SIZE = 200;
	public static int MAX_MESSAGES = 5;

	public static int LAKE_COUNT = 10;
	public static int SHEEP_COUNT = 10;
	
	public static void loadConfig(){
		FileParser.config();
	}
	/**
	 * Returns texture saved in texture factory
	 * @param name
	 * @return
	 */
	public static Texture getTexture(String name){
		return TextureFactory.get(name);
	}
	/**
	 * Returns font saved in texture factory
	 * @param name
	 * @param style
	 * @param size
	 * @return
	 */
	public static TrueTypeFont getFont(String name, int style, int size){
		return FontFactory.getFont(name, style, size);
	}
	//---------------------//
	//		  MATH 	       //
	//---------------------//
	/**
	 * Get random integer from min to max.
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min, int max){
		Random rand = new Random();
		return rand.nextInt((max-min) +1) + min;
	}
	/**
	 * Get random float from min to max.
	 * @param min
	 * @param max
	 * @return
	 */
	public static float random(float min, float max){
		Random rand = new Random();
		return rand.nextFloat() * (max - min) + min;
	}
	/**
	 * Round number up
	 * @param number
	 * @return
	 */
	public static int roundUp(float number){
		return (int)Math.ceil(number);	
	}
	/**
	 * Round number down
	 * @param number
	 * @return
	 */
	public static int roundDown(float number){
		return roundUp(number)-1;
	}
	// PATHS
	public static final String SAVED_MAPS = "map/";
	public static final String RES_PATH = FileParser.class.getResource("/").getPath(); //"res/";
}