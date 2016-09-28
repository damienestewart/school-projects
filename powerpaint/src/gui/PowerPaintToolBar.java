/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package gui;

import actions.tools.Tool;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * JToolBar class for GUI.
 * All ideas here were 
 * taken from Professor
 * Fowler's code.
 * 
 * This class helps to break apart
 * the GUI, and avoid the high
 * import warning.
 * 
 * @author Damiene Stewart.
 * @version Febuary 14, 2015.
 */
@SuppressWarnings("serial")
public class PowerPaintToolBar extends JToolBar {
    /**
     * Toolbar button group.
     */
    private final ButtonGroup myToolBarButtonGroup;
    
    /**
     * Constructor.
     */
    public PowerPaintToolBar() {
        super();
        myToolBarButtonGroup = new ButtonGroup();
    }
    
    /**
     * Add toggle button with incoming action.
     * 
     * @param theTool the tool for the button.
     */
    public void createToggleButton(final Tool theTool) {
        final JToggleButton toggleButton = new JToggleButton((Action) theTool);
        
        myToolBarButtonGroup.add(toggleButton);
        add(toggleButton);
    }
}
