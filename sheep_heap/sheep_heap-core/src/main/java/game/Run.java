package game;

import kryten.engine.Engine;

public class Run {

	public static void main(String[] args) {
		//Global.loadConfig();
		Engine.init();
		Game game = new Game();
		Engine.initGame(game);
		Engine.run();
	}

}
