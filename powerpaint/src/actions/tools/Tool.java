/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package actions.tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * Tool interface.
 * 
 * @author Damiene Stewart
 * @version Febuary 24, 2015.
 */
public interface Tool {
    /**
     * Create a new particular shape
     * based on the incoming point.
     * 
     * @param thePoint to use.
     */
    void createShape(final Point thePoint);
    
    /**
     * Create a new particular shape
     * based on the incoming point.
     * 
     * @return the shape.
     */
    Shape getShape();
    
    /**
     * Sets whether or not we are done
     * creating the current shape.
     * 
     */
    void toggleDrawing();
    
    /**
     * Allows a client to check if
     * drawing is still being done.
     * 
     * @return true/false
     */
    boolean isStillDrawing();
}
