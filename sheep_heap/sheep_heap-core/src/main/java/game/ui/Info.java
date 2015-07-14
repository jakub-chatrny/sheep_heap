package game.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import game.graphic.Sprite;
import game.object.Creature;
import game.object.creature.Sheep;


public class Info {
	private Sprite sprite;
	private Creature object;
	public int width = 300;
	public int height = 120;
	
	public Info(){
		sprite = new Sprite(game.Global.getTexture("blank"));
		sprite.setColor(0f, 0f, 0f, .9f);
	}
	
	public String getName(){
		if(object != null){
			return object.getName();
		}
		return "";
	}
	
	public String getQuote(){
		if(object != null){
			return object.getQuote();
		}
		return "";
	}
	
	public String getAge(){
		if(object != null){
			return object.getAge()+"";
		}
		return "";
	}
	
	public String getLeader(){
		Sheep leader = null;
		if(object != null && object instanceof Sheep){
			if( (leader = ((Sheep) object).getLeader()) != null ){
				return leader.getName();
			}
			else{
				return "me";	
			}
		}
		return "";	
	}
	
	public String getHunger(){
		/*if(object != null){
			return object.getHunger()+"";
		}*/
		return "";
	}
	
	public String getThirst(){
		/*if(object != null){
			return object.getThirst()+"";
		}*/
		return "";
	}
	

	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the object
	 */
	public Creature getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Creature object) {
		this.object = object;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Return	s texture of sprite
	 * @return
	 */
	public Texture getTexture(){
		if(sprite != null){
			return sprite.getTexture();
		}
		return null;
	}
	
	/**
	 * Returns color of sprite
	 * @return
	 */
	public Color getColor(){
		if(sprite != null){
			return sprite.getColor();
		}
		return null;
	}
	
}