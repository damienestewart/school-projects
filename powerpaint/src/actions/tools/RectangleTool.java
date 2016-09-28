/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package actions.tools;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/**
 * Rectangle Tool class.
 * 
 * @author Damiene Stewart
 * @version Febuary 24, 2015.
 */
@SuppressWarnings("serial")
public class RectangleTool extends AbstractRectangularTool {
    
    /**
     * Creates a new Rectangle Tool for
     * the drawing panel.
     *  
     * @param thePanel the drawing panel.
     */
    public RectangleTool(final JPanel thePanel) {
        super("Rectangle", "images/rectangle_bw.gif", KeyEvent.VK_R, thePanel,
              new Rectangle2D.Double());
    }
    
    @Override
    public void createShape(final Point thePoint) {
        super.createShape(thePoint);
        setShape(new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight()));
    }
}
