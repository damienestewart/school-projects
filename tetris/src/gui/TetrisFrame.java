package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Tetris JFrame.
 * 
 * @author Damiene Stewart
 * @version March 13th, 2015.
 */
@SuppressWarnings("serial")
public class TetrisFrame extends JFrame {
    // Code snippet taken from Professor Fowler's example.
    // ****
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    // ****
    // Code snippet above taken from Professor Fowler's example.
    
    /**
     * Constructs a new TetrisFrame.
     * 
     * @param theString the title.
     */
     
    public TetrisFrame(final String theString) {
        super(theString);
    }
    
    /**
     * Centers position on screen.
     * 
     * Call only after pack() is called; or
     * dimensions are otherwise configured.
     */
    public void finishSetUp() {
        pack();
        setLocation(SCREEN_WIDTH / 2 - getWidth() / 2, SCREEN_HEIGHT / 2 - getHeight() / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(getSize());
        setMaximumSize(getSize());
    }
}
