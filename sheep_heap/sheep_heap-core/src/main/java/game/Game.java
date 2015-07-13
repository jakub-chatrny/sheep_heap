package game;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import kryten.engine.Draw;
import kryten.factory.FontFactory;
import kryten.factory.TextureFactory;
import kryten.game.GameTemplate;

public class Game extends GameTemplate {
	private int fontSize = 10;
	
	@Override
	public void getInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		final int posX = 50;
		final int posY = 50;
		final int width = 600;
		final int height = 100;
		final Color color = Color.gray;
		
		
		Draw.rect(posX, posY, width, height, color, TextureFactory.get("blank"));
		
		TrueTypeFont ttf = FontFactory.getFont("Times New Roman", Font.PLAIN,
				fontSize);
		ttf.drawString(posX, posY, "Hello world!",
				Color.white);
		ttf.drawString(posX, posY+(fontSize*2), "True type font loaded to FontFactory:"+FontFactory.capacity,
				Color.white);
		
	}

	@Override
	public void update(int FPS) {
		//update whenever is FPS counter reseted => every second
		if(FPS == 1){
			if(fontSize < 30){
				fontSize += 5;
			}
			else{
				fontSize -= 5;
			}
		}
		
	}
}
