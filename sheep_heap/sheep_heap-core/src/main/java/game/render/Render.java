package game.render;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import game.GMap;
import game.GObjectList;
import game.graphic.Animation;
import game.object.Creature;
import game.object.GObject;
import game.ui.UserInterface;

import static kryten.engine.Draw.*;
import static game.Global.*;

/**
 * Take care all game rendering stuff.
 * 
 * @author Jakub Chatrný
 *
 */
public class Render {
	public static final int DEFAULT_FIELD_SIZE = 20;
	// FLAGS
	private boolean renderGridFlag = false;
	private boolean renderCoordsFlag = false;
	private boolean renderHelpFlag = true;
	private boolean renderObjectViewFlag = true;

	private int renderFieldSize = DEFAULT_FIELD_SIZE;
	private Point renderPosition;
	
	private GObjectList objects;
	private List<Animation> animations;
	
	public Render(GObjectList objects){
		setObjects(objects);
		setAnimations(new ArrayList<Animation>());
		setRenderPosition(0, 0);
	}
	
	/**
	 * Render map.
	 * 
	 * @param map
	 */
	public void map(GMap map){
		RenderMap.mapOptimal(map, this);
		RenderMap.grid(this);
		RenderMap.coords(this);
	}
	
	public void ui(boolean godModFlag, UserInterface ui, String time, final long actualGOPS){
		RenderUI.time(time);
		RenderUI.position(getRenderPosition());
		RenderUI.graphicOperationCount(actualGOPS);
		RenderUI.help(godModFlag, this);
		RenderUI.messages(ui.getMessages(), this);
		if(ui.getInfo().getObject() != null){
			RenderUI.info(ui.getInfo());
		}
	}
	
	/**
	 * Render creatures.
	 * 
	 * @param objects
	 */
	public void objects() {
		RenderObjects.objects(objects.getGrass(), this);
		if(renderObjectViewFlag){
			RenderObjects.objectsView(objects.getCreatures(), this);
		}
		RenderObjects.objects(objects.getCreatures(), this);
		RenderObjects.animations(this);
		RenderObjects.objects(objects.getTrees(), this);
	}

	public void test() {
		int fontsize = 12;
		int SQUARE_SIZE = 200;
		Texture tex = getTexture("remder");

		float fx = getWindowWidth() / 2 - SQUARE_SIZE / 2;
		float fy = getWindowHeight() / 2 - SQUARE_SIZE / 2;
		rect(fx, fy, SQUARE_SIZE, SQUARE_SIZE, new Color(255, 255, 255), tex);

		TrueTypeFont ttf = getFont("Times New Roman", Font.PLAIN,
				fontsize);
		ttf.drawString(100f, 50f, "THE LIGHTWEIGHT JAVA GAMES LIBRARY",
				Color.yellow);
		rect(0, 0, SQUARE_SIZE / 2, SQUARE_SIZE / 2, new Color(255, 255, 255),
				getTexture("blank"));
		rect(fx + 10, fy + 10, SQUARE_SIZE / 8, SQUARE_SIZE / 8, Color.green,
				getTexture("blank"));
	}
	
	//------------------------------//
	//        ZOOM SECTION			//
	//------------------------------//
	
	/**
	 * Set rendering on center of the map.
	 */
	public void initRenderCenter() {
		setRenderPosition((getRenderPosition().x) - (getRenderColCount() / 2)
				+ (MAP_SIZE / 2), (getRenderPosition().y)
				- (getRenderRowCount() / 2) + (MAP_SIZE / 2));
	}
	

	/**
	 * Zoom in the screen to center of actual position.
	 */
	public void zoomInCenter() {
		if (getRenderFieldSize() * ZOOM <= MAX_ZOOM) {
			// new render position
			// X + colCount/2 - colCount/2/scale
			setRenderPosition((getRenderPosition().x)
					+ (getRenderColCount() / 2)
					- (getRenderColCount() / 2 / ZOOM), (getRenderPosition().y)
					+ (getRenderRowCount() / 2)
					- (getRenderRowCount() / 2 / ZOOM));

			setRenderFieldSize(getRenderFieldSize() * ZOOM);

		}
	}

	/**
	 * Zoom out the screen to center of actual position.
	 */
	public void zoomOutCenter() {
		if (getRenderFieldSize() / ZOOM >= MIN_ZOOM) {
			// new render position
			// X + colCount/2 - colCount/2*scale
			setRenderPosition((getRenderPosition().x)
					+ (getRenderColCount() / 2)
					- (getRenderColCount() / 2 * ZOOM), (getRenderPosition().y)
					+ (getRenderRowCount() / 2)
					- (getRenderRowCount() / 2 * ZOOM));
			setRenderFieldSize(getRenderFieldSize() / ZOOM);
		}
	}
	
	//------------------------------//
	//        FIELD SECTION			//
	//------------------------------//
	/**
	 * returns if field is rendered
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isFieldRendered(int x, int y) {
		// rendered position (from top left)
		int rx = 0;// game.getRenderPosition().x;
		int ry = 0;// game.getRenderPosition().y;
		// count of rendered fields in both axes
		int cx = (int) getWindowWidth() / getRenderFieldSize();
		int cy = (int) getWindowWidth() / getRenderFieldSize();
		return x >= rx // from left
				&& y >= ry // from top
				&& x <= cx // from right
				&& y <= cy; // from bottom
	}
	
	public Creature getObjectOn(int x, int y, GMap map){
		//Creature creature;
		//mouse sends coordinates from left bottom, rendering is from left
		//so there is need to recount rows
		x = roundDown(x / (float)renderFieldSize) + renderPosition.x;
		y = roundDown(getRenderRowCountFloat() - (y / (float)renderFieldSize)) + renderPosition.y;
		//System.out.println(x+","+y);
		return map.getCreatureOn(x, y);
		/*if(creature != null)
		System.out.println(creature.getName());*/
		
	}
	/**
	 * Returns count of rendered columns.
	 * 
	 * @return
	 */
	public int getRenderColCount() {
		return (int) getWindowWidth() / renderFieldSize;
	}

	/**
	 * Returns count of rendered rows.
	 * 
	 * @return
	 */
	public int getRenderRowCount() {
		return (int) getWindowHeight() / renderFieldSize;
	}
	/**
	 * Returns count of rendered rows as float.
	 * 
	 * @return
	 */
	public float getRenderRowCountFloat() {
		return getWindowHeight() / renderFieldSize;
	}

	/**
	 * @return the renderGridFlag
	 */
	public boolean isRenderGridFlag() {
		return renderGridFlag;
	}

	/**
	 * @param renderGridFlag
	 *            the renderGridFlag to set
	 */
	public void setRenderGridFlag(boolean renderGridFlag) {
		this.renderGridFlag = renderGridFlag;
	}

	/**
	 * @return the renderCoordsFlag
	 */
	public boolean isRenderCoordsFlag() {
		return renderCoordsFlag;
	}

	/**
	 * @param renderCoordsFlag
	 *            the renderCoordsFlag to set
	 */
	public void setRenderCoordsFlag(boolean renderCoordsFlag) {
		this.renderCoordsFlag = renderCoordsFlag;
	}

	/**
	 * @return the renderHelpFlag
	 */
	public boolean isRenderHelpFlag() {
		return renderHelpFlag;
	}

	/**
	 * @param renderHelpFlag
	 *            the renderHelpFlag to set
	 */
	public void setRenderHelpFlag(boolean renderHelpFlag) {
		this.renderHelpFlag = renderHelpFlag;
	}

	public boolean isRenderObjectViewFlag() {
		return renderObjectViewFlag;
	}

	public void setRenderObjectViewFlag(boolean renderObjectViewFlag) {
		this.renderObjectViewFlag = renderObjectViewFlag;
	}

	/**
	 * @return the renderFieldSize
	 */
	public int getRenderFieldSize() {
		return renderFieldSize;
	}

	/**
	 * @param renderFieldSize
	 *            the renderFieldSize to set
	 */
	public void setRenderFieldSize(int renderFieldSize) {
		this.renderFieldSize = renderFieldSize;
	}

	/**
	 * @return the renderPosition
	 */
	public Point getRenderPosition() {
		return renderPosition;
	}

	/**
	 * @param renderPosition
	 *            the renderPosition to set
	 */
	public void setRenderPosition(Point renderPosition) {
		this.renderPosition = renderPosition;
	}

	/**
	 * @param renderPosition
	 *            the renderPosition to set
	 */
	public void setRenderPosition(int x, int y) {
		this.renderPosition = new Point(x, y);
	}


	/**
	 * @return the objects
	 */
	public GObjectList getObjects() {
		return objects;
	}

	/**
	 * @param objects the objects to set
	 */
	public void setObjects(GObjectList objects) {
		this.objects = objects;
	}
	//------------------------------//
	//       ANIMATION SECTION		//
	//------------------------------//
	/**
	 * @return the animations
	 */
	public List<Animation> getAnimations() {
		return animations;
	}

	/**
	 * @param animations the animations to set
	 */
	public void setAnimations(List<Animation> animations) {
		this.animations = animations;
	}
	
	/**
	 * Creates new animation, animated object removes from list rendered object.
	 * 
	 * @param animation
	 * @param object
	 */
	public void newAnimation(Animation animation, GObject object) {
		if (DEBUG)
			System.out.println(object.getPositionString() + " NEW ANIMATION o="
					+ objects.size() + " a=" + animations.size());
		animation.setObject(object);
		animations.add(animation);
		objects.remove(object);
	}

	/**
	 * Remove animation from list rendered animations. Also set object back to
	 * rendered object list.
	 * 
	 * @param animation
	 */
	public void animationComplete(Animation animation) {
		GObject object = animation.getObject();
		objects.add(object);
		animations.remove(animation);// animationsComplete.add(a);
		if (DEBUG)
			System.out.println(object.getPositionString()
					+ " ANIMATION COMPLETE o=" + objects.size() + " a="
					+ animations.size());
	}

	public static float getWindowHeight() {
		return Display.getHeight();
	}

	public static float getWindowWidth() {
		return Display.getWidth();
	}

}