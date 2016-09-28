/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package color;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * 
 * 
 * @author Damiene Stewart
 * @version Febuary 14, 2015.
 */
public class ColorIcon implements Icon {
    /**
     * Icon width.
     */
    private static final int WIDTH = 14;
    
    /**
     * Icon height.
     */
    private static final int HEIGHT = 14;
    
    /**
     * Icon color.
     */
    private final Color myColor;

    /**
     * Constructs a new color icon.
     * 
     * @param theColor the color.
     */
    public ColorIcon(final Color theColor) {
        myColor = theColor;
    }
    
    @Override
    public void paintIcon(final Component theComponent, final Graphics theGraphics, 
                          final int theX, final int theY) {
        theGraphics.setColor(myColor);
        theGraphics.fillRect(theX, theY, WIDTH, HEIGHT);
        
        theGraphics.setColor(Color.BLACK);
        theGraphics.drawRect(theX, theY, WIDTH, HEIGHT);
    }

    @Override
    public int getIconWidth() {
        return WIDTH;
    }

    @Override
    public int getIconHeight() {
        return HEIGHT;
    }
}
