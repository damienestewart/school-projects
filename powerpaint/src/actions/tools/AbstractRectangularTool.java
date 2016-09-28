/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package actions.tools;

import java.awt.Point;
import java.awt.Shape;

import javax.swing.JPanel;

/**
 * Abstract rectangular
 * shape class.
 * 
 * @author Damiene Stewart
 * @version Febuary 24, 2015.
 */
@SuppressWarnings("serial")
public abstract class AbstractRectangularTool extends AbstractTool {

    /**
     * First quadrant using first point as
     * (0,0).
     */
    private static final int FIRST_QUADRANT = 1;
    
    /**
     * Second quadrant using first point as
     * (0,0).
     */
    private static final int SECOND_QUADRANT = 2;
    
    /**
     * Third quadrant using first point as
     * (0,0).
     */
    private static final int THIRD_QUADRANT = 3;
    
    /**
     * Fourth quadrant using first point as
     * (0,0).
     */
    private static final int FOURTH_QUADRANT = 4;
    
    /**
     * X coordinate of current shape.
     */
    protected double myCurrentX;
    
    /**
     * Y coordinate of current shape.
     */
    protected double myCurrentY;
    
    /**
     * Width of current shape.
     */
    protected double myCurrentWidth;
    
    /**
     * Height of current shape.
     */
    protected double myCurrentHeight;
    
    /**
     * Constructs a new rectangular shape
     * tool.
     * 
     * @param theName the name.
     * @param theImage the image.
     * @param theMnemonic the mnemonic for the tool.
     * @param thePanel the panel.
     * @param theShape the shape.
     */
    protected AbstractRectangularTool(final String theName, final String theImage, 
                                   final int theMnemonic, 
                                   final JPanel thePanel, final Shape theShape) {
        super(theName, theImage, theMnemonic, thePanel, theShape);
    }

    /**
     * Creates the shape.
     * 
     * @param thePoint the point.
     */
    @Override
    public void createShape(final Point thePoint) {
        // TODO Auto-generated method stub
        if (!isStillDrawing()) {
            toggleDrawing();
            this.setStartPoint(thePoint);
        }
        
        final double width = Math.abs(getStartPoint().getX() - thePoint.getX());
        final double height = Math.abs(getStartPoint().getY() - thePoint.getY());
        
        double xCoordinate = 0;
        double yCoordinate = 0;
        
        final double xDiff = thePoint.getX() - getStartPoint().getX();
        final double yDiff = thePoint.getY() - getStartPoint().getY();
        
        final int quadrant = getQuadrant(xDiff, yDiff); 
        
        switch (quadrant) {
            case FIRST_QUADRANT: xCoordinate = getStartPoint().getX();
                    yCoordinate = thePoint.getY();
                    break;
            case SECOND_QUADRANT: xCoordinate = thePoint.getX();
                    yCoordinate = thePoint.getY();
                    break;
            case THIRD_QUADRANT: xCoordinate = thePoint.getX();
                    yCoordinate = getStartPoint().getY();
                    break;
            case FOURTH_QUADRANT: xCoordinate = getStartPoint().getX();
                    yCoordinate = getStartPoint().getY();
                    break;
            default: break;
        }
        
        myCurrentX = xCoordinate;
        myCurrentY = yCoordinate;
        myCurrentWidth = width;
        myCurrentHeight = height; 
    }
    
    /**
     * Returns x position.
     * 
     * @return x position.
     */
    protected double getX() {
        return myCurrentX;
    }
    
    /**
     * Returns y position.
     * 
     * @return y position.
     */
    protected double getY() {
        return myCurrentY;
    }
    
    /**
     * Returns width.
     * 
     * @return width.
     */
    protected double getWidth() {
        return myCurrentWidth;
    }
    
    /**
     * Returns height.
     * 
     * @return height.
     */
    protected double getHeight() {
        return myCurrentHeight;
    }
    
    /**
     * Returns the quadrant
     * of incoming shape.
     * 
     * @param theXDifference the x difference.
     * @param theYDifference the y difference.
     * @return the quadrant
     */
    private int getQuadrant(final double theXDifference, 
                            final double theYDifference) {
        int quadrant = 0;
        
        if (theXDifference > 0 && theYDifference < 0) {
            quadrant = FIRST_QUADRANT;
        } else if (theXDifference < 0 && theYDifference < 0) {
            quadrant = SECOND_QUADRANT;
        } else if (theXDifference < 0 && theYDifference > 0) {
            quadrant = THIRD_QUADRANT;
        } else {
            quadrant = FOURTH_QUADRANT;
        }
        
        return quadrant;
    }
}
