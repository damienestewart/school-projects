/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package actions;

import gui.DrawingPanel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/**
 * Action calling panel's undo.
 * 
 * @author Damiene Stewart
 * @version Febuary 14, 2015.
 */
@SuppressWarnings("serial")
public class Undo extends AbstractAction {

    /**
     * Panel reference.
     */
    private final DrawingPanel myPanel;
    
    /**
     * Constructor.
     * @param thePanel the drawing panel.
     */
    public Undo(final DrawingPanel thePanel) {
        super();
        
        this.putValue(NAME, "undo");
        this.putValue(SMALL_ICON, new ImageIcon("images/undoicon.png"));
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('z',
                                                        java.awt.event.InputEvent.CTRL_MASK));
        this.setEnabled(false);
        myPanel = thePanel;
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.undo();
    }

}
