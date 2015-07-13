package game;

import kryten.engine.Engine;

public class Run {

	public static void main(String[] args) {
		//Global.loadConfig();
		Engine.init(800, 600);
		Game game = new Game();
		Engine.initGame(game);
		Engine.run();
	}

}
