/*
 * TCSS 305
 * 
 * An implementation of the classic game "Tetris".
 */

package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Board.BoardData;

/**
 * A graphical representation
 * of the game board.
 * 
 * @author Damiene Stewart
 * @version March 6th, 2015.
 */
@SuppressWarnings("serial")
public class BoardDisplay extends JPanel implements Observer {
    
    /**
     * Space at the top of the display.
     */
    private static final int NORTH_OFFSET = 5;
    
    /**
     * Board Data reference.
     */
    private List<Color[]> myBoardCells;
    
    /**
     * Number of columns.
     */
    private final int myColumns;
    
    /**
     * Number of rows.
     */
    private final int myRows;
    
    /**
     * Constructs a new display
     * for the Tetris Board.
     * 
     * @param theColumns the board width.
     * @param theRows the board height.
     */
    public BoardDisplay(final int theColumns, final int theRows) {
        super();
        
        myColumns = theColumns;
        myRows = theRows;
        
        myBoardCells = new ArrayList<Color[]>();
        
        setup();
    }
    
    /**
     * Set-up Board Display.
     */
    private void setup() {
        
        final Dimension dimension = new Dimension(200,
                                                  400);
        
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setBackground(Color.LIGHT_GRAY); 
    }

    /**
     * Allows us to respond to updates from the board.
     */
    @Override
    public void update(final Observable theBoard, final Object theArgument) {
        if (theArgument instanceof BoardData) {
            myBoardCells = ((BoardData) theArgument).getBoardData();
            repaint();
        }
    }
    
    /**
     * Paints the tetris board.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        int startY = 0;
        for (int i = myBoardCells.size() - NORTH_OFFSET; i >= 0; i--) {
            // Loop through height. Get pointer to color array.
            final Color[] row = myBoardCells.get(i);
            int startX = 0;
            
            final double cellWidth = this.getWidth() * 1.0 / myColumns;
            final double cellHeight = this.getHeight() * 1.0 / myRows;
            
            for (final Color c : row) {
                final Shape s = new Rectangle2D.Double(startX, startY, 
                                                       cellWidth, 
                                                       cellHeight);
                
                if (c == null) {
                    g2d.setColor(Color.BLACK);
                    g2d.draw(s); 
                } else {
                    g2d.setColor(Color.RED);
                    g2d.draw(s);
                    g2d.setColor(c);
                    g2d.fill(s); 
                }
                
                startX += cellHeight;
            }    
            
            startY += cellWidth;
        }
    }
}
