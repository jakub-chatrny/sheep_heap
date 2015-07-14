package game.object;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import game.object.Creature;

/**
 * Every creatures has this attributes
 * 
 * @author jchatrny
 *
 */
public abstract class Creature extends GObject {
	protected String name;
	protected String quote;

	protected float age;
	protected float hunger;
	protected float thirst;
	protected float rotation = 0.0f;
	protected float speed = 1.0f;

	protected Creature father;
	protected Creature mother;
	
	protected List<Field> viewFields;
	
	protected Creature(String name, String quote, Point pos, float age, float hunger, float thirst, Texture texture){
		setName(name);
		setQuote(quote);
		setTexture(texture);
		setPosition(pos);
		setAge(age);
		setHunger(hunger);
		setThirst(thirst);
		setTexture(texture);
		viewFields = new ArrayList<Field>();
	}

	// protected GCreatureGender gender;

	/**
	 * Get velocity = direction*speed
	 * 
	 * @return
	 */
	public float[] getVelocity() {
		float[] f = new float[2];
		f[0] = getDirection().x * speed;
		f[1] = getDirection().y * speed;
		return f;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the quote
	 */
	public String getQuote() {
		return quote;
	}

	/**
	 * @param quote
	 *            the quote to set
	 */
	public void setQuote(String quote) {
		this.quote = quote;
	}

	/**
	 * @return the age
	 */
	public float getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(float age) {
		this.age = age;
	}

	/**
	 * @return the hunger
	 */
	public float getHunger() {
		return hunger;
	}

	/**
	 * @param hunger the hunger to set
	 */
	public void setHunger(float hunger) {
		this.hunger = hunger;
	}

	/**
	 * @return the thirst
	 */
	public float getThirst() {
		return thirst;
	}

	/**
	 * @param thirst the thirst to set
	 */
	public void setThirst(float thirst) {
		this.thirst = thirst;
	}

	/**
	 * @return the father
	 */
	public Creature getFather() {
		return father;
	}

	/**
	 * @param father the father to set
	 */
	public void setFather(Creature father) {
		this.father = father;
	}

	/**
	 * @return the mother
	 */
	public Creature getMother() {
		return mother;
	}

	/**
	 * @param mother the mother to set
	 */
	public void setMother(Creature mother) {
		this.mother = mother;
	}

	public List<Field> getViewFields() {
		return viewFields;
	}
	public void setViewFields(List<Field> viewFields) {
		this.viewFields = viewFields;
	}
}