package listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Board;

/**
 * Key Listener for controlling
 * the game.
 * 
 * @author Damiene Stewart
 * @version March 13th, 2015.
 */
public class TetrisKeyListener extends KeyAdapter {
    
    /**
     * Controls whether or not button presses
     * do anything.
     */
    private boolean myEnabled;
    
    /**
     * Left control.
     */
    private int myLeftControl;
    
    /**
     * Right control.
     */
    private int myRightControl;
    
    /**
     * Down control.
     */
    private int myDownControl;
    
    /**
     * Drop control.
     */
    private int myDropControl;
    
    /**
     * Rotate Clockwise control.
     */
    private int myRotateCWControl;
    
    /**
     * Rotate Counter Clockwise control.
     */
    private int myRotateCCWControl;
    
    /**
     * Reference to the board.
     */
    private final Board myBoard;
    
    /**
     * Creates a new TetrisKeyListener.
     * @param theBoard the Board.
     */
    public TetrisKeyListener(final Board theBoard) {
        super();
        
        myBoard = theBoard;
        myLeftControl = KeyEvent.VK_LEFT;  
        myRightControl = KeyEvent.VK_RIGHT;    
        myDownControl = KeyEvent.VK_DOWN;
        myRotateCWControl = KeyEvent.VK_D;
        myRotateCCWControl = KeyEvent.VK_A;
        myDropControl = KeyEvent.VK_SPACE;
        
        myEnabled = true;
    }
    
    /**
     * Check which key is pressed.
     */
    @Override
    public void keyPressed(final KeyEvent theEvent) {
        super.keyPressed(theEvent);
        
        // Don't process anymore if we're disabled.
        if (!myEnabled) {
            return;
        }
        
        final int keyCode = theEvent.getKeyCode();
        
        if (keyCode == myLeftControl) {
            myBoard.left();
        } else if (keyCode == myRightControl) {
            myBoard.right();
        } else if (keyCode == myDownControl) {
            myBoard.down();
        } else if (keyCode == myDropControl) {
            myBoard.drop();
        } else if (keyCode == myRotateCWControl) {
            myBoard.rotateCW();
        } else if (keyCode == myRotateCCWControl) {
            myBoard.rotateCCW();
        }
    }
    
    /**
     * Getter for left control.
     * 
     * @return left control.
     */
    public int getLeftControl() {
        return myLeftControl;
    }
    
    /**
     * Getter for right control.
     * 
     * @return right control.
     */
    public int getRightControl() {
        return myRightControl;
    }
    
    /**
     * Getter for down control.
     * 
     * @return down control.
     */
    public int getDownControl() {
        return myDownControl;
    }
    
    /**
     * Getter for drop control.
     * 
     * @return drop control.
     */
    public int getDropControl() {
        return myDropControl;
    }
    
    /**
     * Getter for rotate clockwise control.
     * 
     * @return rotate clockwise control.
     */
    public int getRotateCWControl() {
        return myRotateCWControl;
    }
    
    /**
     * Getter for rotate counter clockwise control.
     * 
     * @return rotate counter clockwise control.
     */
    public int getRotateCCWControl() {
        return myRotateCCWControl;
    }
    
    /**
     * Enables/Disables keyboard control.
     * 
     * @param theState true/false.
     */
    public void setEnabled(final boolean theState) {
        myEnabled = theState;
    }
    
    /**
     * Setter for left control.
     * @param theKeyCode the key code.
     */
    public void setLeftControl(final int theKeyCode) {
        myLeftControl = theKeyCode;
    }
    
    
    /**
     * Setter for right control.
     * @param theKeyCode the key code.
     */
    public void setRightControl(final int theKeyCode) {
        myRightControl = theKeyCode;
    }
    
    /**
     * Setter for down control.
     * @param theKeyCode the key code.
     */
    public void setDownControl(final int theKeyCode) {
        myDownControl = theKeyCode;
    }
    
    /**
     * Setter for drop control.
     * @param theKeyCode the key code.
     */
    public void setDropControl(final int theKeyCode) {
        myDropControl = theKeyCode;
    }
    
    /**
     * Setter for rotate clockwise control.
     * @param theKeyCode the key code.
     */
    public void setRotateCWControl(final int theKeyCode) {
        myRotateCWControl = theKeyCode;
    }
    
    /**
     * Setter for rotate counter clockwise control.
     * @param theKeyCode the key code.
     */
    public void setRotateCCWControl(final int theKeyCode) {
        myRotateCCWControl = theKeyCode;
    }
}
