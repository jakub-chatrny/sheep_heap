package game;

import static game.ui.Message.MES_GAME_GENERATED;
import static game.ui.Message.MES_GAME_LOADED;
import static game.ui.Message.MES_GAME_SAVED;
import kryten.engine.DisplayConfig;
import kryten.game.InputTemplate;

public class Input extends InputTemplate {
	private Game game;
	
	public Input(Game game){
		this.game=game;
	}
	
	public void keyboardCheck() {
		keyLeftHoldCheck();
		keyRightHoldCheck();
		keyUpHoldCheck();
		keyDownHoldCheck();
		
		keyAddPressedCheck();
		keySubPressedCheck();
		
		keyWHoldCheck();
		keySHoldCheck();
		keyAHoldCheck();
		keyDHoldCheck();
		
		keyF1PressedCheck();
		keyF2PressedCheck();
		keyF3PressedCheck();
		keyF4PressedCheck();
		keyF5PressedCheck();
		keyF6PressedCheck();
		keyF7PressedCheck();
		keyF9PressedCheck();
		keyF12PressedCheck();
		keyEscPressedCheck();
	}
	public void mouseCheck() {
		mouseLeftPressedCheck();
		mouseRightPressedCheck();
	}
	@Override
	protected void mouseLeftPressedAction(int x, int y) {
		if(game.isGodModFlag()){
			game.godModLeftMouse(x, y);
		}
		else{
			game.getUi().getInfo().setObject( game.getRender().getObjectOn(x, y, game.getMap()) );
		}
		
	}

	@Override
	protected void mouseRightPressedAction(int x, int y) {
		if(game.isGodModFlag()){
			game.godModRightMouse(x, y);
		}
		
	}

	@Override
	protected void keyLeftPressedAction() {
		game.setRenderPosition(game.getRenderPosition().x - 1,
				game.getRenderPosition().y);
		
	}

	@Override
	protected void keyLeftHoldAction() {
		game.setRenderPosition(game.getRenderPosition().x - 1,
				game.getRenderPosition().y);
		
	}

	@Override
	protected void keyRightPressedAction() {		
		game.setRenderPosition(game.getRenderPosition().x + 1,
				game.getRenderPosition().y);
		
	}

	@Override
	protected void keyRightHoldAction() {
		game.setRenderPosition(game.getRenderPosition().x + 1,
				game.getRenderPosition().y);
		
	}

	@Override
	protected void keyUpPressedAction() {
		game.setRenderPosition(game.getRenderPosition().x,
				game.getRenderPosition().y - 1);
		
	}

	@Override
	protected void keyUpHoldAction() {
		game.setRenderPosition(game.getRenderPosition().x,
				game.getRenderPosition().y - 1);
		
	}

	@Override
	protected void keyDownPressedAction() {
		game.setRenderPosition(game.getRenderPosition().x,
				game.getRenderPosition().y + 1);
		
	}

	@Override
	protected void keyDownHoldAction() {
		game.setRenderPosition(game.getRenderPosition().x,
				game.getRenderPosition().y + 1);
		
	}

	@Override
	protected void keyF1PressedAction() {
		game.getRender().setRenderHelpFlag(!game.getRender().isRenderHelpFlag());
		
	}

	@Override
	protected void keyF2PressedAction() {
		game.getRender().setRenderGridFlag(!game.getRender().isRenderGridFlag());
		game.addMessageString(game.getRender().isRenderGridFlag() ? "Grid on." : "Grid off." );
		
	}

	@Override
	protected void keyF3PressedAction() {
		game.getRender().setRenderCoordsFlag(!game.getRender().isRenderCoordsFlag());
		game.addMessageString(game.getRender().isRenderCoordsFlag() ? "Coordinates on." : "Coordinates off." );
		
	}

	@Override
	protected void keyF4PressedAction() {
		game.newMap();
		game.addMessageString(MES_GAME_GENERATED+game.getMap().getBuildTime()+"ms");
		
	}

	@Override
	protected void keyF5PressedAction() {
		game.saveMap();
		game.addMessageString(MES_GAME_SAVED);
		
	}

	@Override
	protected void keyF6PressedAction() {
		game.setGodModFlag(!game.isGodModFlag());
		
	}

	@Override
	protected void keyF7PressedAction() {
		game.getRender().setRenderObjectViewFlag(!game.getRender().isRenderObjectViewFlag());
		game.addMessageString(game.getRender().isRenderObjectViewFlag() ? "Object View on." : "Object View off." );
		
	}

	@Override
	protected void keyF8PressedAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyF9PressedAction() {
		game.loadMap();
		game.addMessageString(MES_GAME_LOADED);
		
	}

	@Override
	protected void keyF10PressedAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyF11PressedAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyF12PressedAction() {
		DisplayConfig.setDisplayMode(Game.getWindowWidth() == 800 ? 1600 : 800, Game.getWindowHeight() == 600 ? 1000 : 600, false);
		
	}

	@Override
	protected void keyEscPressedAction() {
		System.exit(0);
		
	}

	@Override
	protected void keyAddPressedAction() {
		game.getRender().zoomInCenter();
		
	}

	@Override
	protected void keySubPressedAction() {
		game.getRender().zoomOutCenter();
		
	}

	@Override
	protected void keyWPressedAction() {
		keyUpPressedAction();
		
	}

	@Override
	protected void keySPressedAction() {
		keyDownPressedAction();
		
	}

	@Override
	protected void keyAPressedAction() {
		keyLeftPressedAction();
		
	}

	@Override
	protected void keyDPressedAction() {
		keyRightPressedAction();
		
	}

	@Override
	protected void keyWHoldAction() {
		keyUpHoldAction();
		
	}

	@Override
	protected void keySHoldAction() {
		keyDownHoldAction();
		
	}

	@Override
	protected void keyAHoldAction() {
		keyLeftHoldAction();
		
	}

	@Override
	protected void keyDHoldAction() {
		keyRightHoldAction();
		
	}

}