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

import model.Point;
import model.TetrisPiece;

/**
 * Displays the next piece.
 * 
 * @author Damiene Stewart
 * @version March 6th, 2015.
 */
@SuppressWarnings("serial")
public class NextPieceDisplay extends JPanel implements Observer {

    /**
     * Cell size.
     */
    private static final int CELL_SIZE = 20;
    
    /**
     * Number of columns.
     */
    private static final int COLUMNS = 6;
    
    
    /**
     * Tetris data to draw.
     */
    private final List<Color[]> myNextPieceData;
    
    /**
     * Creates a new Display for the
     * next piece.
     */
    public NextPieceDisplay() {
        super();    
        myNextPieceData = new ArrayList<Color[]>();
        
        setup();
    }
    
    /**
     * Display setup.
     */
    private void setup() {
        setPreferredSize(new Dimension(COLUMNS * CELL_SIZE, COLUMNS * CELL_SIZE));
    }
    
    @Override
    public void update(final Observable theBoard, 
                       final Object theData) {
        if (theData instanceof TetrisPiece) {
            myNextPieceData.clear();
            
            myNextPieceData.add(new Color[COLUMNS]);
            myNextPieceData.add(new Color[COLUMNS]);
            myNextPieceData.add(new Color[COLUMNS]);
            
            for (final Point p : ((TetrisPiece) theData).getPoints()) {
                final Color[] row = myNextPieceData.get(p.y());
                row[p.x() + 1] = ((TetrisPiece) theData).getColor();
            }
            
            myNextPieceData.add(0, new Color[COLUMNS]);
            myNextPieceData.add(new Color[COLUMNS]);
            myNextPieceData.add(new Color[COLUMNS]);
            
            repaint();
        } 
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        int startY = 0;
        for (int i = myNextPieceData.size() - 1; i >= 0; i--) {
            // Loop through height. Get pointer to color array.
            final Color[] row = myNextPieceData.get(i);
            int startX = 0;
            
            for (final Color c : row) {
                final Shape s = new Rectangle2D.Double(startX, startY, 
                                                       CELL_SIZE, CELL_SIZE);
                
                if (c == null) {
                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.fill(s); 
                } else {
                    g2d.setColor(Color.RED);
                    g2d.draw(s);
                    g2d.setColor(c);
                    g2d.fill(s); 
                }
                
                startX += CELL_SIZE;
            }     
            startY += CELL_SIZE;
        }
        
    }
    
}
