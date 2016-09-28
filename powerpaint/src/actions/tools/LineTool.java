/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package actions.tools;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

/**
 * Line Tool class.
 * 
 * @author Damiene Stewart
 * @version Febuary 24, 2015.
 */
@SuppressWarnings("serial")
public class LineTool extends AbstractTool {
    
    /**
     * Creates a new Line Tool for
     * the drawing panel.
     * 
     * @param thePanel the drawing panel.
     */
    public LineTool(final JPanel thePanel) {
        super("Line", "images/line_bw.gif", KeyEvent.VK_L, thePanel,
              new Line2D.Double());
    }

    @Override
    public void createShape(final Point thePoint) {
        if (!isStillDrawing()) {
            this.toggleDrawing();
            this.setStartPoint(thePoint);
        }
        
        this.setShape(new Line2D.Double(this.getStartPoint(), thePoint));
    }
    
}
