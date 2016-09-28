/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package actions.tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Abstract tool class.
 * 
 * @author Damiene Stewart.
 * @version Febuary 14, 2015.
 */
@SuppressWarnings("serial")
public abstract class AbstractTool extends AbstractAction implements Tool {
    
    /**
     * Set based on whether or not a shape
     * is being drawn.
     */
    protected boolean myDrawingState;
    
    /**
     * Field for the panel reference.
     */
    private final JPanel myPanel;
    
    /**
     * Start point of shape the tool should
     * draw.
     */
    private final Point myStartPoint;
    
    /**
     * End point of shape the tool should
     * draw.
     */
    private final Point myEndPoint;
    
    /**
     * The shape to create.
     */
    private Shape myShape;
    
    /**
     * Initializes action fields.
     * 
     * @param theName name of tool.
     * @param theImage image path.
     * @param theMnemonic mnemonic key.
     * @param thePanel the drawing panel.
     * @param theShape to create;
     */
    protected AbstractTool(final String theName, final String theImage,
                           final int theMnemonic, final JPanel thePanel,
                           final Shape theShape) {
        super();
        
        this.putValue(NAME, theName);
        this.putValue(SMALL_ICON, new ImageIcon(theImage));
        this.putValue(MNEMONIC_KEY, theMnemonic);
        this.putValue(SELECTED_KEY, true);
        
        myPanel = thePanel;
        myStartPoint = new Point();
        myEndPoint = new Point();
        myDrawingState = false;
        myShape = theShape;
    }
    
    /**
     * Returns the position of the starting
     * point for the tool's shape.
     * 
     * @return starting point.
     */
    protected Point getStartPoint() {
        return myStartPoint.getLocation();
    }
    
    /**
     * Returns the position of the ending
     * point for the tool's shape.
     * 
     * @return ending point.
     */
    protected Point getEndPoint() {
        return myEndPoint.getLocation();
    }
    
    /**
     * Sets start point.
     * @param thePoint the point to set.
     */
    protected void setStartPoint(final Point thePoint) {
        myStartPoint.setLocation(thePoint);
    }
    
    /**
     * Sets start point.
     * @param thePoint the point to set.
     */
    protected void setEndPoint(final Point thePoint) {
        myEndPoint.setLocation(thePoint);
    }
    
    /**
     * Changes depending on whether or not
     * a shape is being created.
     */
    @Override
    public void toggleDrawing() {
        myDrawingState ^= true;
    }
    
    @Override
    public boolean isStillDrawing() {
        return myDrawingState;
    }
    
    @Override
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Sets the current shape.
     * 
     * @param theShape the shape to set to.
     */
    protected void setShape(final Shape theShape) {
        myShape = theShape;
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        try {
            myPanel.getClass().getMethod("setCurrentTool", Tool.class).invoke(myPanel, this);
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final NoSuchMethodException e) {
            System.out.println("The setCurrentTool method has not been implemented");
            e.printStackTrace();
        } catch (final SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
