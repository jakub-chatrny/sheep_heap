package game;

import java.util.List;

import game.object.GObject;
import game.object.creature.Sheep;
import game.ui.Message;
import game.graphic.Animation;

/**
 * Updates game state.
 * 
 * @author Jakub Chatrný
 *
 */
public class Update {

	/**
	 * Updates state of creatures (sheeps so far). If creature decide change
	 * state, creates animation.
	 * 
	 * @param game
	 */
	public static void creatures(Game game) {
		List<GObject> objects = game.getObjects().getCreatures();
		GObject o;
		if (!objects.isEmpty()) {
			for (int i = 0; i < objects.size(); i++) {
				o = objects.get(i);
				if (o instanceof Sheep) {
					Animation a = ((Sheep) o).move(game.getMap());
					if (null != a) {
						game.getRender().newAnimation(a, o);
					}
				}
			}
		}
	}

	/**
	 * Delete messages base of their duration and count of max messages
	 * 
	 * @param messages
	 */
	public static void messages(List<Message> messages) {
		// update messages
		for (int i = 0; i < messages.size(); i++) {
			Message m = messages.get(i);
			if (m.decDuration() <= 0) {

				messages.remove(m);
			}
		}
	}
}