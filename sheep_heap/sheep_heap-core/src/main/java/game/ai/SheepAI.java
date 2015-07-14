package game.ai;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import game.GMap;
import game.object.creature.Sheep;
import game.object.Field;

public class SheepAI {
	public static int SHEEP_VISION_ANGLE = 220;
	public static int SHEEP_VISION_DISTANCE = 8;
	public static GMap map;
	private static Sheep nearbySheep;
	
	public static void behavior(Sheep sheep){
		if(!lookAround(sheep)){
			//TODO behavior ... sheep is blind?
			return;
		}
		if((nearbySheep = getSheepAround(sheep)) == null){
			//TODO behavior ... sheep is alone?
			nearbySheep.amILeader();
			return;
		}
		else{
			//follow the leader
			nearbySheep = null;
			//TODO path finding
		}
	}
	
	public static List<Field> getVisionFields(Sheep sheep){
		if(map == null) return null;
		
		
		//Rotation of sheep is in interval <-180, 180>. Vision algorithm counts <0, 360> so you need add one quadrant => +90
		List<Point> fieldPositions = Vision.getVisionFieldsPos(map, sheep.getPosition(), ((int) (sheep.getRotation()))+90, SHEEP_VISION_ANGLE, SHEEP_VISION_DISTANCE);
		List<Field> fields = new ArrayList<Field>();
		for(Point p: fieldPositions){
			fields.add(map.getField(p));
		}
		return fields;
	}
	
	public static boolean lookAround(Sheep sheep){
		List<Field> fields = getVisionFields(sheep);
		if((fields == null) || 	fields.size() == 0){
			return false;
		}
		else{
			sheep.setViewFields(fields);
			return true;
		}
	}
	
	public static boolean isSheepAround(Sheep sheep){
		if(getSheepAround(sheep) == null){
			return false;
		}
		else return true;
	}
	
	public static Sheep getSheepAround(Sheep sheep){
		List<Field> viewFields = sheep.getViewFields();
		for(Field f: viewFields){
			if(f.getCreature() instanceof Sheep){
				return (Sheep) f.getCreature();
			}
		}
		return null;
	}

}