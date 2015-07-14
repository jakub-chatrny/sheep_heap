package game.render;

import static kryten.engine.Draw.rect;
import static game.Global.getFont;

import java.awt.Font;
import java.awt.Point;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import game.ui.Help;
import game.ui.Info;
import game.ui.Message;

import static game.Global.*;

public class RenderUI {
	public static final int MESSAGE_WIDTH = 300; 
	public static final int MESSAGE_HEIGHT = 10; 
	public static final int HELP_HEIGHT = 40;
	
	/**
	 * Render help on top of screen
	 * @param game
	 */
	public static void help(boolean godModFlag, Render render) {
		if (render.isRenderHelpFlag()) {
			
			// local variables
			Point pos;
			float helpWidth = Render.getWindowWidth();
			int fontSize = 12;
			int marginTop = 5;
			int marginLeft = 10;
			Color background = new Color(0f, 0f, 0f, 0.8f);
			
			// render background
			rect(0f, 0f, helpWidth, HELP_HEIGHT, background, getTexture("blank"));

			Color white = Color.white;
			Color on = new Color(255, 120, 120);
			Color off = new Color(120, 120, 120);
			pos = text(marginLeft, marginTop, Help.F1, fontSize, white);
			pos = text(marginLeft + pos.x, marginTop, Help.F2, fontSize,
					render.isRenderGridFlag() ? on : off);
			pos = text(marginLeft + pos.x, marginTop, Help.F3, fontSize,
					render.isRenderCoordsFlag() ? on : off);
			pos = text(marginLeft + pos.x, marginTop, Help.F4, fontSize, white);
			pos = text(marginLeft + pos.x, marginTop, Help.F5, fontSize, white);
			pos = text(marginLeft + pos.x, marginTop, Help.F6, fontSize,
					godModFlag ? on : off);
			pos = text(marginLeft + pos.x, marginTop, Help.F7, fontSize,
					render.isRenderObjectViewFlag() ? on : off);
			pos = text(marginLeft + pos.x, marginTop, Help.F9, fontSize, white);
			pos = text(marginLeft + pos.x, marginTop, Help.ESC, fontSize, white);
			// NEWLINE
			pos = text(marginLeft, marginTop + fontSize + marginTop,
					"+/- Zoom In/Out   Movement: Arrows", fontSize, white);
			//
			/*text(0, (int)render.getWindowHeight()-fontSize,
					render.getSeconds()+"s", fontSize, white);*/
		}
	}
	
	/**
	 * Render messages 
	 * @param game
	 * @param messages
	 */
	public static void messages(List<Message> messages, Render render) {
		int marginTop = 10;
		int marginLeft = 10;
		int nextY = 0;
		int messageX = (int) Render.getWindowWidth() - MESSAGE_WIDTH;
		int messageWidth = (int) Render.getWindowWidth() - marginTop;
		int messageHeight = messages.size() * (marginTop + MESSAGE_HEIGHT);
		int helpY = 0;
		
		Texture texture = getTexture("blank");
		Color white = Color.white;
		Color background = new Color(0f, 0f, 0f, 0.7f);

		if (render.isRenderHelpFlag()) {
			helpY += HELP_HEIGHT;
		}

		rect(messageX, helpY, messageWidth, messageHeight, background, texture);

		for (int i = 0; i < messages.size(); i++) {
			nextY = i * (marginTop + MESSAGE_HEIGHT) + helpY;
			text(messageX+marginLeft, nextY, messages.get(i).getMessage(), MESSAGE_HEIGHT,
					white);
		}
	}
	
	public static void info(Info info){
		int x, y;
		int padding = 5;
		int fontSize = 12;
		x = ((int)Render.getWindowWidth()) - info.width - 20;
		y = ((int)Render.getWindowHeight()) - info.height - 20;
		rect(x, y, info.width, info.height, info.getColor(), info.getTexture());
		//name
		text(x+padding, y+padding, info.getName(), fontSize*2, Color.white);
		//quote
		text(x+padding, y+(fontSize*2)+(padding*2), info.getQuote(), fontSize-2, Color.white);
		//age
		text(x+padding, y+(fontSize*3)+(padding*3), "Age: "+info.getAge(), fontSize, Color.white);
		//hunger
		text(x+padding, y+(fontSize*4)+(padding*4), "Hunger: "+info.getHunger(), fontSize, Color.white);
		//thirst
		text(x+padding, y+(fontSize*5)+(padding*5), "Thirst: "+info.getThirst(), fontSize, Color.white);
		//leader
		text(x+padding, y+(fontSize*6)+(padding*6), "Leader: "+info.getLeader(), fontSize, Color.white);
	}

	public static void time(final String time){
		final int row = 1;
		final int padding = 5;
		final int fontSize = 12;
		final int posX = (fontSize * row) + (padding * row);
		
		TrueTypeFont ttf = getFont("Times New Roman", Font.PLAIN,
				fontSize);
		ttf.drawString(padding, Render.getWindowHeight()-posX, time,
				Color.white);
	}
	
	public static void position(final Point pos){
		final int row = 2;
		final int padding = 5;
		final int fontSize = 12;
		final int posX = (fontSize * row) + (padding * row);
		
		TrueTypeFont ttf = getFont("Times New Roman", Font.PLAIN,
				fontSize);
		ttf.drawString(padding, Render.getWindowHeight()-posX, pos.x+", "+pos.y,
				Color.white);
	}
	
	public static void graphicOperationCount(final int count){
		final int row = 3;
		final int padding = 5;
		final int fontSize = 12;
		final int posX = (fontSize * row) + (padding * row);
		
		TrueTypeFont ttf = getFont("Times New Roman", Font.PLAIN,
				fontSize);
		ttf.drawString(padding, Render.getWindowHeight()-posX, "GOPS: " + count,
				Color.white);
	}
	/**
	 * Render text on position. And returns where rendering ended.
	 * @param x
	 * @param y
	 * @param text
	 * @param size
	 * @param color
	 * @param fonts
	 * @return
	 */
	public static Point text(float x, float y, String text, int size, Color color){
		TrueTypeFont  ttf = getFont("Times New Roman", Font.PLAIN, size);
		ttf.drawString(x, y, text, color);
		return new Point((int)x + ttf.getWidth(text), (int)y
				+ size);
	}
}