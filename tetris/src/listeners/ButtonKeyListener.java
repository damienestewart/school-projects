package listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.IntConsumer;

import javax.swing.JButton;

/**
 * Key Listener for game control
 * reconfiguration pop up.
 * 
 * @author Damiene Stewart
 * @version March 13th, 2015.
 */
public class ButtonKeyListener extends KeyAdapter {
    
    /**
     * A reference to the function
     * to be called on key press.
     */
    private final IntConsumer myFunction;
    
    /**
     * Constructs a new button key
     * listener.
     * 
     * @param theFunction the function to use.
     */
    public ButtonKeyListener(final IntConsumer theFunction) {
        super();
        myFunction = theFunction;
    }
    
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        myFunction.accept(theEvent.getKeyCode());
        final String newKeyString = KeyEvent.getKeyText(theEvent.getKeyCode());
        ((JButton) theEvent.getSource()).setText(newKeyString);
    }
}
