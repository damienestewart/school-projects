package listeners;

import gui.BoardDisplay;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import model.Board;

/**
 * Frame listener for resizing
 * events.
 * 
 * @author damienestewart
 * @version March 13th, 2015.
 */
public class TetrisWindowListener extends ComponentAdapter {
    /**
     * Padding offset between Board Display container
     * and Board Display.
     */
    private static final int CONTAINER_OFFSET = 10;
    
    /**
     * Dimension ratio for tetris board - 10/20.
     */
    private static final double SIZE_RATIO = 0.5;
    
    /**
     * Reference to the board display container.
     */
    private final JPanel myBoardDisplayContainer;
    
    /**
     * Reference to the board display.
     */
    private final BoardDisplay myBoardDisplay;
    
    /**
     * Reference to the board.
     */
    private final Board myBoard;
    
    /**
     * Constructs a new TetrisWindowListener.
     * 
     * @param theBoardDisplayContainer the display container.
     * @param theBoardDisplay the display.
     * @param theBoard the board.
     */
    public TetrisWindowListener(final JPanel theBoardDisplayContainer, 
                                final BoardDisplay theBoardDisplay,
                                final Board theBoard) {
        super();
        myBoardDisplayContainer = theBoardDisplayContainer;
        myBoardDisplay = theBoardDisplay;
        myBoard = theBoard;
    }
    
    @Override
    public void componentResized(final ComponentEvent theEvent) {
        int width = myBoardDisplayContainer.getWidth() - CONTAINER_OFFSET;
        int height = myBoardDisplayContainer.getHeight() - CONTAINER_OFFSET;
        
        if (Double.compare(width * 1.0 / height, SIZE_RATIO) < 0) {
            if (width % myBoard.getWidth() != 0) {
                width -= width % myBoard.getWidth();
            } 
            height = 2 * width;
        } else if (Double.compare(width * 1.0 / height, SIZE_RATIO) > 0) {
            if (height % myBoard.getHeight() != 0) {
                height -= height % myBoard.getHeight();
            }           
            width = height / 2;
        }
        myBoardDisplay.setPreferredSize(new Dimension(width, height));
    }
}
