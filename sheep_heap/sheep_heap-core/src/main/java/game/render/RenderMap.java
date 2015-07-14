package game.render;

import static kryten.engine.Draw.rect;
import static kryten.engine.Draw.rectRepTex;
import static game.render.Render.getWindowHeight;
import static game.render.Render.getWindowWidth;

import java.awt.Font;
import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import game.GMap;
import game.object.Field;

import static game.Global.*;

public class RenderMap {
	/**
	 * Improved map rendering. Render ground as one rectangle with scaled texture. and everything else over it.
	 * @param map
	 * @param render
	 */
	public static void mapOptimal(GMap map, Render render) {
		ground(map, render);
		mountainAndLakes(map, render);
	}
	/**
	 * Render ground. Should be fully optimal.
	 * @param map
	 * @param render
	 */
	private static void ground(GMap map, Render render) {
		int renderFieldSize = render.getRenderFieldSize();
		Point renderPosition = render.getRenderPosition();
		int windowWidth = (int) getWindowWidth();
		int windowHeight = (int) getWindowHeight();
		int maxCols = windowWidth / renderFieldSize;
		int maxRows = windowHeight / renderFieldSize;

		// shift object right if render position is greater then zero
		int startX = (-renderPosition.x) * renderFieldSize;
		startX = startX >= 0 ? startX : 0;
		// shift object bottom if render position is greater then zero
		int startY = (-renderPosition.y) * renderFieldSize;
		startY = startY >= 0 ? startY : 0;
		// count object width
		int width = (map.getCols() - renderPosition.x)
				* renderFieldSize;
		width = width > windowWidth ? windowWidth : width - startX;
		// count object height
		int height = (map.getRows() - renderPosition.y)
				* renderFieldSize;
		height = height > windowHeight ? windowHeight : height - startY;

		// count texture scaling
		int textureScaleX = width == windowWidth ? maxCols
				: (renderPosition.x > 0 ? map.getCols() - renderPosition.x
						: maxCols + renderPosition.x);
		int textureScaleY = height == windowHeight ? maxRows
				: (renderPosition.y > 0 ? map.getRows() - renderPosition.y
						: maxRows + renderPosition.y);
		// check if is object on screen
		if ((0 < width && 0 < height)) {
			rectRepTex(startX, startY, width, height, new Color(95, 186, 48),
					Field.getFieldTexture(0), textureScaleX,
					textureScaleY);
			/*System.out.println(renderPosition.x + ", " + renderPosition.y);
			System.out.println(startX + ", " + startY + ", " + width + ", "
					+ height + ", ");*/
		}

	}

	/**
	 * Render on screen mountains and lakes. Not Optimal
	 * @param map
	 * @param render
	 */
	private static void mountainAndLakes(GMap map, Render render) {
		//TODO make optimal
		//int vertexCounter = 0;
		int startX, startY;
		int renderFieldSize = render.getRenderFieldSize();
		Point renderPosition = render.getRenderPosition();
		int maxCols = (int) getWindowWidth() / renderFieldSize;
		int maxRows = (int) getWindowHeight() / renderFieldSize;
		if (maxRows % renderFieldSize != 0)
			maxRows++;

		int col, row;
		for (col = 0; (col - renderPosition.x) < maxCols && col < map.getCols(); col++) {
			if ((col - renderPosition.x) < 0) //out of screen
				continue;
			for (row = 0; (row - renderPosition.y) < maxRows
					&& row < map.getRows(); row++) {
				if ((row - renderPosition.y) < 0) //out of screen
					continue;
				startX = (col - renderPosition.x) * renderFieldSize;
				startY = (row - renderPosition.y) * renderFieldSize;

				// set color
				Field field = map.getField(col, row);
				if (field.getAltitude() == 0)
					continue; // this method doesn't render ground
				Color color = field.getColor();//getFieldColor(field);
				//vertexCounter++;
				// set texture
				Texture texture = field.getTexture();//getFieldTexture(field, render.getSprites());

				// if (field.getAltitude() != 255)
				rect(startX, startY, renderFieldSize, renderFieldSize, color,
						texture);

			}
		}
		//System.out.println(vertexCounter);
	}
	
	/**
	 * Render coordinates of map to screen when is @game.renderCoordsFlag set
	 * 
	 * @param flag
	 * @param render
	 * @param sprites
	 */
	public static void coords(Render render) {
		int renderFieldSize = render.getRenderFieldSize();
		Point renderPosition = render.getRenderPosition();
		for (int col = 0; col < getWindowWidth() / renderFieldSize; col++)
			for (int row = 0; row < getWindowHeight() / renderFieldSize; row++)
				if (render.isRenderCoordsFlag() && renderFieldSize > 20) {
					TrueTypeFont ttf = getFont("Times New Roman",
							Font.PLAIN, renderFieldSize == 80 ? 12 : 6);
					ttf.drawString(col * renderFieldSize + 1, row
							* renderFieldSize + 1, (col + renderPosition.x)
							+ "," + (row + renderPosition.y), Color.white);
				}
	}

	/**
	 * Render grid to screen when is @game.renderGridFlag set
	 * 
	 * @param game
	 */
	public static void grid(Render render) {
		if (render.isRenderGridFlag()) {
			// local variables
			Texture texture = getTexture("blank");
			int renderFieldSize = render.getRenderFieldSize();

			for (int i = 1; i < getWindowWidth() / renderFieldSize; i++)
				rect(i * renderFieldSize, 0, (int) 1, getWindowHeight(),
						Color.black, texture);
			for (int j = 1; j < getWindowHeight() / renderFieldSize; j++)
				rect(0, j * renderFieldSize, getWindowWidth(), 0.6f,
						Color.black, texture);
		}
	}

}