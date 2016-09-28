/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package actions;

import color.ColorIcon;
import gui.DrawingPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.KeyStroke;

/**
 * Action for color changing.
 * 
 * @author Damiene Stewart
 * @version Febuary 14, 2015.
 */
@SuppressWarnings("serial")
public class ColorChange extends AbstractAction {
    
    /**
     * The drawing panel.
     */
    private final DrawingPanel myPanel;
    
    /**
     * The last chosen color.
     */
    private Color myColor;
    
    /**
     * Creates a new ColorChangeAction object.
     * 
     * @param thePanel the panel.
     */
    public ColorChange(final DrawingPanel thePanel) {
        super();
        
        putValue(NAME, "Color...");
        putValue(MNEMONIC_KEY, KeyEvent.VK_C);
        putValue(SMALL_ICON, new ColorIcon(Color.BLACK));
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('C',
                                                        java.awt.event.InputEvent.ALT_MASK));
        myPanel = thePanel;
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final Color color = JColorChooser.showDialog((Component) theEvent.getSource(), 
                                                        "Select a color", myColor);
        if (color != null) {
            this.putValue(SMALL_ICON, new ColorIcon(color));
            myPanel.setColor(color);
            myColor = color;
        }
    }
}
