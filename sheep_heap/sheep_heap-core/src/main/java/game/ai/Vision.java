package game.ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import game.GMap;
import game.object.Field;

public class Vision /*implements IVision*/ {
	private static int[][] DIRECTIONS;
	private static final int[][] CARDINAL_DIRECTIONS = new int[][]{{-1,0},  {0,1},  {1,0},  {0, -1}};
	private static final int[][] ALL_DIRECTIONS = new int[][] { { 0, -1 },
		{ 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 },
		{ -1, -1 } };
	public static GMap map;
	public static List<Point> visited;
	public static Point center;

	public Vision(GMap map) {
		Vision.map = map;
	}
	
	public List<Point> getVisionFieldsPos(Point pos, int rotation, int visionAngle, int radius){
		center = pos;
		markCircualSector(radius, visionAngle, rotation);
		return visited;
	}
	
	public static List<Point> getVisionFieldsPos(GMap gmap, Point pos, int rotation, int visionAngle, int radius){
		map = gmap;
		center = pos;
		markCircualSector(radius, visionAngle, rotation);
		return visited;
	}
	
	private static void markCircualSector(final int radius, final int angle, final int rotation) {
		visited = new ArrayList<Point>();
		if (angle <= 180) {
			// if angle is less than 180 degrees then apply algorithm
			markCircularSectorFromTo(center, radius, rotation, angle / 2, -angle / 2);
		} else {
			// otherwise split circular sector in two. From 0° to left and from 0° to right 
			markCircularSectorFromTo(center, radius, rotation, angle / 2, 0);
			markCircularSectorFromTo(center, radius, rotation, 0, -angle / 2);
		}

	}

	public static void markCircularSectorFromTo(final Point center,
			final int radius, final int rotation, final int startAngle, final int endAngle) {
		if(rotation % 90 == 0){
			DIRECTIONS = CARDINAL_DIRECTIONS;
		}
		else{
			DIRECTIONS = ALL_DIRECTIONS;
			//System.out.println("ALL");
		}
		Point startPoint = getVectorOfAngle(radius, startAngle+rotation);
		Point endPoint = getVectorOfAngle(radius, endAngle+rotation);

		
		markCircularSectorFromToRec(center, radius, startPoint, endPoint);
	}

	public static void markCircularSectorFromToRec(final Point point, final int radius,
			final Point startPoint, final Point endPoint) {
		List<Point> surroundings = getSurroundings(point, map.getFields());
		for (Point s : surroundings) {
			if (!visited.contains(s)
					&& isInsideSector(s, center, startPoint, endPoint,
							radius * radius)) {
				visited.add(s);
				markCircularSectorFromToRec(s, radius, startPoint,
						endPoint);
			}
		}
	}

	public static Point getVectorOfAngle(final int radius,
			final int angleInDegrees) {
		// Convert from degrees to radians via multiplication by PI/180
		int x = (int) (radius * Math.cos(angleInDegrees * Math.PI / 180F));
		int y = (int) (radius * Math.sin(angleInDegrees * Math.PI / 180F));

		return new Point(x, y);
	}

	public static boolean areClockwise(Point v1, Point v2) {
		if((v1.y < 0 && v1.x < 0) || (v1.y > 0 && v1.x > 0))
			return -v1.x * v2.y + v1.y * v2.x > 0;
		else
			return -v1.x * v2.y + v1.y * v2.x >= 0;
	}

	public static boolean isWithinRadius(Point v, float radiusSquared) {
		return v.x * v.x + v.y * v.y <= radiusSquared;
	}

	public static boolean isInsideSector(Point point, Point center,
			Point sectorStart, Point sectorEnd, float radiusSquared) {
		Point relPoint = new Point(point.x - center.x, point.y - center.y);

		return !areClockwise(sectorStart, relPoint)
				&& areClockwise(sectorEnd, relPoint)
				&& isWithinRadius(relPoint, radiusSquared);
	}

	/**
	 * Get list of all surroundings. 1 1 1 1 x 1 1 1 1
	 * 
	 * @param pos
	 * @return
	 */
	public static List<Point> getSurroundings(Point pos, Field[][] matrix) {
		List<Point> surroundings = new ArrayList<>();
		for (int[] direction : DIRECTIONS) {
			int cx = pos.x + direction[0];
			int cy = pos.y + direction[1];
			if (cy >= 0 && cy < matrix.length)
				if (cx >= 0 && cx < matrix[cy].length) {
					surroundings.add(new Point(cx, cy));
				}
		}
		return surroundings;
	}


}