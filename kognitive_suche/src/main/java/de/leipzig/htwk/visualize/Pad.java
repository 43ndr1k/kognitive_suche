package de.leipzig.htwk.visualize;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

/**
 * Hexagon Objekt.
 * 
 * @author Fabian Freihube
 */
public class Pad extends Group {
  /**
   * Kleines Hexagon
   */
  Polygon shape = new Polygon();
  /**
   * Gro�es Hexagon
   */
  Polygon expandedShape = new Polygon();
  /**
   * Umrandung des kleinen Hexagons
   */
  Polygon lightShape = new Polygon();
  /**
   * Umrandung des gro�en Hexagons
   */
  Polygon expandedLightShape = new Polygon();
  /**
   * Rahmen des gro�en Hexagons
   */
  Polygon lightFrame = new Polygon();



  private final Image PAD_NORMALIMAGE = new Image(String.valueOf(getClass().getResource("/resources/icons/normal_schwarz.png")));
  private final Image PAD_LIGHTFRAME = new Image(String.valueOf(getClass().getResource("/resources/icons/lightFrame.png")));

  /**
   * Deklaration der verschiedenen Shapes.
   * 
   * @author Fabian Freihube
   * @param size L�nge von Mittelpunkt des Hexagons bis in die Spitzen
   * @param locationX Postition entlang der X-Achse
   * @param locationY Postition entlang der Y-Achse
   * @param fillColor F�llfarbe
   */
  public Pad(double size, double locationX, double locationY, Color fillColor) {
    this.shape = setHexagon(size, locationX, locationY);
    this.lightShape = setHexagon(size + 1, locationX, locationY);
    this.expandedShape = setHexagon(2.2 * size, locationX, locationY);
    this.expandedLightShape = setHexagon(2.2 * size + 1, locationX, locationY);

    this.shape.setFill(fillColor);
    this.expandedShape.setFill(fillColor);
    this.lightShape.setFill(new ImagePattern(PAD_NORMALIMAGE, 0, 0, 1, 1, true));
    this.expandedLightShape.setFill(new ImagePattern(PAD_LIGHTFRAME, 0, 0, 1, 1, true));

  }

  /**
   * Erstellt ein neues Hexagon.
   * 
   * @author Fabian Freihube
   * @param size L�nge von Mittelpunkt des Hexagons bis in die Spitzen
   * @param locationX Postition entlang der X-Achse
   * @param locationY Postition entlang der Y-Achse
   * @return Polygon
   */
  private Polygon setHexagon(double size, double locationX, double locationY) {

    Polygon newHexagon = new Polygon();
    newHexagon.getPoints().addAll(
        new Double[] {

        locationX - size, locationY, Math.cos(Math.toRadians(120)) * size + locationX,
            Math.sin(Math.toRadians(120)) * size + locationY,
            Math.cos(Math.toRadians(60)) * size + locationX,
            Math.sin(Math.toRadians(60)) * size + locationY,

            locationX + size, locationY, -Math.cos(Math.toRadians(120)) * size + locationX,
            -Math.sin(Math.toRadians(120)) * size + locationY,
            -Math.cos(Math.toRadians(60)) * size + locationX,
            -Math.sin(Math.toRadians(60)) * size + locationY});

    return newHexagon;
  }

  /**
   * Gibt das kleine Hexagon zurück.
   * 
   * @return kleines Hexagon
   */
  public Polygon getShape() {
    return shape;
  }

  /**
   * Gibt das große Hexagon zurück.
   * 
   * @return großes Hexagon
   */
  public Polygon getExShape() {
    return expandedShape;
  }

  /**
   * Gibt die Umrandung des kleinen Hexagons zurück.
   * 
   * @return Umrandung des kleinen Hexagons
   */
  public Polygon getLightShape() {
    return lightShape;
  }

  /**
   * Gibt die Umrandung des großen Hexagons zurück.
   * 
   * @return Umrandung des großen Hexagons
   */
  public Polygon getExLightShape() {
    return expandedLightShape;
  }

}
