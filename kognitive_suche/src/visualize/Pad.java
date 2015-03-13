package visualize;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

/**
 * @author Fabian Freihube
 */
public class Pad extends Group{
	 Polygon shape = new Polygon();
	 Polygon expandedShape = new Polygon();
	 Polygon lightShape = new Polygon();
	 Polygon expandedLightShape = new Polygon(); 
	 Polygon lightFrame = new Polygon(); 
	 
	 private static final Image blackNormalImage = new Image("file:static/icons/normal_schwarz.png");
	 private static final Image ligtFrameImage = new Image("file:static/icons/lightFrame.png");
	 
	public Pad(double size, double locationX, double locationY, Color fillColor) {
		this.shape = setHexagon(size, locationX, locationY);
		this.lightShape = setHexagon(size+1, locationX, locationY);
		this.expandedShape = setHexagon(2.2*size, locationX, locationY);
		this.expandedLightShape = setHexagon(2.2*size+1, locationX, locationY);
		
		this.shape.setFill(fillColor);
		this.expandedShape.setFill(fillColor);
		this.lightShape.setFill(new ImagePattern(blackNormalImage, 0, 0, 1, 1, true));
		this.expandedLightShape.setFill(new ImagePattern(ligtFrameImage, 0, 0, 1, 1, true));

	}

	private Polygon setHexagon(double size, double locationX, double locationY) {
		
		Polygon newHexagon = new Polygon();
		newHexagon.getPoints().addAll(new Double[]{
				
				locationX-size, locationY,
				Math.cos(Math.toRadians(120))*size + locationX, Math.sin(Math.toRadians(120))*size + locationY,
				Math.cos(Math.toRadians(60))*size + locationX, Math.sin(Math.toRadians(60))*size + locationY,
				
				locationX+size, locationY,
				-Math.cos(Math.toRadians(120))*size + locationX, -Math.sin(Math.toRadians(120))*size + locationY,
				-Math.cos(Math.toRadians(60))*size + locationX, -Math.sin(Math.toRadians(60))*size + locationY
		});
		
		return newHexagon;
	}

	public Polygon getShape() {
		return shape;
	}	
	
	public Polygon getExShape() {
		return expandedShape;
	}	
	
	public Polygon getLightShape() {
		return lightShape;
	}

	public Polygon getExLightShape() {
		return expandedLightShape;
	}
	
}
