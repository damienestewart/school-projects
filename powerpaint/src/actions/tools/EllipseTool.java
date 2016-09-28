/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package actions.tools;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

/**
 * Ellipse Tool class.
 * 
 * @author Damiene Stewart
 * @version Febuary 24, 2015.
 */
@SuppressWarnings("serial")
public class EllipseTool extends AbstractRectangularTool {
    
    /**
     * Creates a new Ellipse Tool for
     * the drawing panel.
     * 
     * @param thePanel the drawing panel.
     */
    public EllipseTool(final JPanel thePanel) {
        super("Ellipse", "images/ellipse_bw.gif", KeyEvent.VK_E, thePanel,
              new Ellipse2D.Double());
    }

    @Override
    public void createShape(final Point thePoint) {
        super.createShape(thePoint);
        setShape(new Ellipse2D.Double(getX(), getY(), getWidth(), getHeight()));
    }
}
