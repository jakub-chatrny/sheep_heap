package game;

import static game.Global.*;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.lwjgl.opengl.Display;

import kryten.engine.Draw;
import kryten.factory.TextureFactory;
import kryten.game.GameTemplate;

import game.ai.SheepAI;
import game.generator.ObjectGenerator;
import game.render.Render;
import game.ui.Message;
import game.ui.UserInterface;

/**
 * Game class handle all game mechanics also call for rendering and update
 * methods which delivers data to openGL.
 * 
 * @author Jakub Chatrný
 *
 */
public class Game extends GameTemplate{
	private boolean godModFlag;

	private UserInterface ui;
	private Input input;
	private Render render;
	private GMap map;

	private GObjectList objects;

	/**
	 * Initialize new game
	 */
	public Game() {
		TextureFactory.RES_PATH = Game.class.getResource("/").getPath();
		loadConfig();
		objects = new GObjectList();

		input = new Input(this);
		render = new Render(objects);
		ui = new UserInterface();

		newMap();

		if (DEBUG_MAP) {
			map.print();
		}
	}

	// RENDER
	public void render() {
		render.map(map);
		render.objects();
		render.ui(godModFlag, ui, Time.getTimeString());
	}

	// UPDATE
	public void update(int FPS) {
		if (FPS == 1) {
			Draw.resetDrawCounter();
			Update.creatures(this);
			Update.messages(ui.getMessages());
			Time.inc();
		}
	}

	// INPUT
	public void getInput() {
		input.keyboardCheck();
		input.mouseCheck();
	}

	/**
	 * Generate new map.
	 */
	public void newMap() {
		GMap newmap = new GMap(MAP_SIZE, LAKE_COUNT);
		map = newmap;
		objects.clear();// clear objects
		render.getAnimations().clear(); // clear animations
		ui.getMessages().clear(); // clear messages
		ObjectGenerator og = new ObjectGenerator(getMap());
		og.sheepHeaps(objects, SHEEP_COUNT, SHEEP_COUNT/10);
		og.treesClusters(objects, 2000, 30);
		og.grassClusters(objects, 4000, 30);
		
		//add reference to map for AI
		SheepAI.map = newmap;
	}

	/**
	 * Load map from file
	 */
	public void loadMap() {
		GMap newmap = new GMap(SAVED_MAPS, "default_map.map");
		map = newmap;
		objects.clear();// clear objects
		render.getAnimations().clear(); // clear animations
		ui.getMessages().clear(); // clear messages
		ObjectGenerator og = new ObjectGenerator(getMap());
		og.sheepHeaps(objects, SHEEP_COUNT, 20);
		og.treesClusters(objects, 200, 50);
	}

	/**
	 * Save map to file
	 */
	public void saveMap() {
		if (map != null) {
			try {
				map.saveToFile(map.getName());
			} catch (FileNotFoundException e) {
				System.err.println("Saving map failed. File " + map.getName()
						+ " not found.");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				System.err.println("Saving map failed. File " + map.getName()
						+ " doesn't support encoding.");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Action of left mouse when god mod is on
	 * 
	 * @param x
	 * @param y
	 */
	public void godModLeftMouse(int x, int y) {
		godMod(x, y, 1);
	}

	/**
	 * Action of right mouse when god mod is on
	 * 
	 * @param x
	 * @param y
	 */
	public void godModRightMouse(int x, int y) {
		godMod(x, y, -1);
	}

	/**
	 * Enables to change field altitude by mouse buttons.
	 * 
	 * @param x
	 * @param y
	 * @param add
	 */
	public void godMod(int x, int y, int add) {
		int col = (x / render.getRenderFieldSize()) + getRenderPosition().x;
		int row = (int) (getWindowHeight() - y) / render.getRenderFieldSize()
				+ getRenderPosition().y;
		map.getField(col, row).setAltitude(
				map.getField(col, row).getAltitude() + add);
	}

	/**
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return ui.getMessages();
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void addMessageString(String text) {
		addMessage(new Message(text));
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void addMessage(Message message) {
		if (getMessages().size() >= MAX_MESSAGES) {
			getMessages().remove(0); // POP FIRST ONE
		}
		this.ui.getMessages().add(message);
	}

	/**
	 * @return rendering position (top left corner of window) in map.
	 */
	public Point getRenderPosition() {
		return render.getRenderPosition();
	}

	/**
	 * Set position (top left corner of window) in map.
	 * 
	 * @param x
	 * @param y
	 */
	public void setRenderPosition(int x, int y) {
		render.setRenderPosition(new Point(x, y));
	}

	/**
	 * @return the godModFlag
	 */
	public boolean isGodModFlag() {
		return godModFlag;
	}

	/**
	 * @param godModFlag
	 *            the godModFlag to set
	 */
	public void setGodModFlag(boolean godModFlag) {
		this.godModFlag = godModFlag;
	}

	/**
	 * @return game time as string
	 */
	public String getTime() {
		return Time.getTimeString();
	}

	/**
	 * @return the render
	 */
	public Render getRender() {
		return render;
	}

	/**
	 * @return the map
	 */
	public GMap getMap() {
		return map;
	}

	/**
	 * @return the ui
	 */
	public UserInterface getUi() {
		return ui;
	}

	/**
	 * @return the objects
	 */
	public GObjectList getObjects() {
		return objects;
	}

	/**
	 * @param objects
	 *            the objects to set
	 */
	public void setObjects(GObjectList objects) {
		this.objects = objects;
	}

	/**
	 * @return actual window height
	 */
	public static float getWindowHeight() {
		return Display.getHeight();
	}

	/**
	 * @return actual window width
	 */
	public static float getWindowWidth() {
		return Display.getWidth();
	}
}