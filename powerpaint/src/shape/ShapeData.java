/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package shape;

import java.awt.Color;
import java.awt.Shape;

/**
 * Class that encapsulates
 * a shape and it's data.
 * 
 * @author Damiene Stewart
 * @version Febuary 24, 2015.
 */
public class ShapeData {
    
    /**
     * The shape.
     */
    private final Shape myShape;
    
    /**
     * The shape's color.
     */
    private final Color myColor;
    
    /**
     * The thickness.
     */
    private final int myThickness;
    
    /**
     * Creates a new shape
     * with the relevant data.
     * 
     * @param theShape the shape.
     * @param theColor the color.
     * @param theThickness the thickness.
     */
    public ShapeData(final Shape theShape, final Color theColor, final int theThickness) {
        myShape = theShape;
        myColor = theColor;
        myThickness = theThickness;
    }
    
    /**
     * Return the shape.
     * 
     * @return the shape.
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Return the color.
     * 
     * @return the color.
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * Return the thickness.
     * 
     * @return the thickness.
     */
    public int getThickness() {
        return myThickness;
    }
}
