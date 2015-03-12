package visualize;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author Fabian Freihube
 */
public class Pad extends Group{
	 Polygon shape = new Polygon();
	 Polygon expandedShape = new Polygon();
	 Polygon lightShape = new Polygon();
	 Polygon expandedLightShape = new Polygon(); 
	 Polygon lightFrame = new Polygon(); 
	 Text link;

	 
	 private static final Image blackNormalImage = new Image("file:static/icons/normal_schwarz.png");
	 private static final Image ligtFrameImage = new Image("file:static/icons/lightFrame.png");
	 
	public Pad(double size, double locationX, double locationY, Color fillColor) {
		this.shape = setHexagon(size, locationX, locationY);
		this.lightShape = setHexagon(size+1, locationX, locationY);
		this.expandedShape = setHexagon(2.2*size, locationX, locationY);
		this.expandedLightShape = setHexagon(2.2*size+1, locationX, locationY);
		
		this.link = new Text(locationX-50, locationY, "Beispiel Link");
		
		this.shape.setFill(fillColor);
		this.expandedShape.setFill(fillColor);
		this.lightShape.setFill(new ImagePattern(blackNormalImage, 0, 0, 1, 1, true));
		this.expandedLightShape.setFill(new ImagePattern(ligtFrameImage, 0, 0, 1, 1, true));
		
		this.link.setTextOrigin(VPos.CENTER);
		this.link.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

		this.expandedShape.setVisible(false);
		this.expandedLightShape.setVisible(false);
		
		this.lightShape.setOnMouseEntered(event -> {
			this.expandedShape.setVisible(true);
			this.expandedShape.toFront();
			this.expandedLightShape.setVisible(true);
			this.link.toFront();
			this.link.setX(locationX-120);
			this.link.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
	        this.expandedLightShape.toFront();
	    });
	    
		this.expandedLightShape.setOnMouseExited(event -> {
			this.expandedShape.setVisible(false);
			this.expandedLightShape.setVisible(false);
			this.link.setX(locationX-50);
			this.link.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
	    });
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

	public Text getLink() {
		return link;
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
