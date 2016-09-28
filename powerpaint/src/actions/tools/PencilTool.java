/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package actions.tools;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

/**
 * Pencil Tool class.
 * 
 * @author Damiene Stewart
 * @version Febuary 24, 2015.
 */
@SuppressWarnings("serial")
public class PencilTool extends AbstractTool {
    
    /**
     * Creates a new Pencil Tool for
     * the drawing panel.
     * 
     * @param thePanel the drawing panel.
     */
    public PencilTool(final JPanel thePanel) {
        super("Pencil", "images/pencil_bw.gif", KeyEvent.VK_P, thePanel,
              new GeneralPath());
    }

    /**
     * Creates the path.
     */
    @Override
    public void createShape(final Point thePoint) {
        
        if (!isStillDrawing()) {
            toggleDrawing();
            setShape(new GeneralPath());
            ((GeneralPath) getShape()).moveTo(thePoint.getX(), thePoint.getY());
        }
        
        ((GeneralPath) getShape()).lineTo(thePoint.getX(), thePoint.getY());
    } 
}