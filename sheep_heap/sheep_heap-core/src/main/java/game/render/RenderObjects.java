package game.render;

import static kryten.engine.Draw.*;
import static game.Global.*;

import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import game.graphic.Animation;
import game.object.GObject;
import game.object.Field;
import game.object.creature.Sheep;

public class RenderObjects {
	/**
	 * Render creatures.
	 * 
	 * @param objects
	 * @param render
	 * @param sprites
	 */
	public static void objects(List<GObject> objects, Render render) {
		int objectX, objectY;
		final int renderX = render.getRenderPosition().x;
		final int renderY = render.getRenderPosition().y;

		int renderFieldSize = render.getRenderFieldSize();
		float textureScale;

		for (int i = 0; i < objects.size(); i++) {
			GObject o = objects.get(i);
		
			objectX = o.getPosition().x;
			objectY = o.getPosition().y;
			
			objectX -= renderX; 
			objectY -= renderY;
			
			if (render.isFieldRendered(objectX, objectY)) {
				objectX *= renderFieldSize;
				objectY *= renderFieldSize;
				textureScale = o.getTextureScale();


					rectRot(objectX - ((textureScale - 1) * renderFieldSize/2 ), objectY - ((textureScale - 1) * renderFieldSize/2 ), renderFieldSize * textureScale, renderFieldSize *textureScale,
						o.getRotation(), Color.white, o.getTexture());
				if (DEBUG)
					System.out.println(o.getPositionString()
							+ " object rendered");
			}
		}

	}
	/**
	 * Render animations.
	 * @param render
	 */
	public static void animations(Render render) {
		Texture texture;
		int renderFieldSize = render.getRenderFieldSize();
		float x, y;
		float speed = 0.01f;

		Animation a;

		Iterator<Animation> i = render.getAnimations().iterator();
		while (i.hasNext()) {
			a = i.next();
			a.run(speed);
			texture = a.getObject().getTexture();
			 // (ANMATION POSITION - VIEW POSITON) * SCALE;
			x = (a.getX() - render.getRenderPosition().x) * renderFieldSize;
			y = (a.getY() - render.getRenderPosition().y) * renderFieldSize; 
			
			// System.out.println(y+"   "+game.getRenderPosition().y+"   "+a.getY());
			rectRot(x, y, renderFieldSize, renderFieldSize, a.getRotation(),
					Color.white, texture);
			if (DEBUG_ANIMATION)
				System.out.println(a.getObject().getPositionString()
						+ " animation rendered on postion ( " + x + ", " + y
						+ ")");
			if (a.isFinished()) {
				i.remove();
				render.animationComplete(a);

			}
		}
	}
	
	/**
	 * Render creatures.
	 * 
	 * @param objects
	 * @param render
	 * @param sprites
	 */
	public static void objectsView(List<GObject> objects, Render render) {
		//TODO OPTIMILAZATION
		int objectX, objectY;
		final float textureScale = 1.0f;
		final Color background = new Color(0f, 0f, 0f, 0.15f);

		for (int i = 0; i < objects.size(); i++) {
			
			GObject o = objects.get(i);
			
			if(!(o instanceof Sheep)) continue;
			
			for(Field f : ((Sheep) o).getVisionFields() ){
				final int renderX = render.getRenderPosition().x;
				final int renderY = render.getRenderPosition().y;
	
				int renderFieldSize = render.getRenderFieldSize();
				
				
					
				objectX = f.getPosition().x;
				objectY = f.getPosition().y;
				
				objectX -= renderX; 
				objectY -= renderY;
				
				if (render.isFieldRendered(objectX, objectY)) {
					objectX *= renderFieldSize;
					objectY *= renderFieldSize;
	
	
						rectRot(objectX - ((textureScale - 1) * renderFieldSize/2 ), objectY - ((textureScale - 1) * renderFieldSize/2 ), renderFieldSize * textureScale, renderFieldSize *textureScale,
							0, background, getTexture("blank"));
					if (DEBUG)
						System.out.println(o.getPositionString()
								+ " object view rendered");
				}
			}
		}

	}
	
	
}