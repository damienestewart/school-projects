package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board.CompletedLines;
import model.TetrisPiece;


/**
 * Displays game information.
 * 
 * @author Damiene Stewart
 * @version March 13th, 2015
 */
@SuppressWarnings("serial")
public class GameInfoDisplay extends JPanel implements Observer {
    /**
     * Score offset.
     */
    private static final int OFFSET = -15;
    
    /**
     * Cleared lines multiplier.
     */
    private static final int MULTIPLIER = 50;
    
    /**
     * Default timer speed.
     */
    private static final int DEFAULT_SPEED = 1000;
    
    /**
     * Level separator.
     */
    private static final int LEVEL_SEPERATOR = 200;
    
    /**
     * Height of display.
     */
    private static final int DEFAULT_HEIGHT = 70;
    
    /**
     * Left offset for text in display.
     */
    private static final int TEXT_LEFT_OFFSET = 10;
    
    /**
     * Top offset for text in display.
     */
    private static final int TEXT_HEIGHT_OFFSET = 20;
    
    /**
     * Records current score.
     */
    private int myScore;
    
    /**
     * Records total lines cleared.
     */
    private int myTotalCleared;
    
    /**
     * Records current level.
     */
    private int myCurrentLevel;
    
    /**
     * Reference to the timer.
     */
    private final Timer myTimer;
    
    /**
     * Constructor.
     * 
     * @param theTimer the timer.
     */
    public GameInfoDisplay(final Timer theTimer) {
        super();
        myScore = OFFSET;
        myTotalCleared = 0;
        myCurrentLevel = 0;
        myTimer = theTimer;
        setup();
    }
    
    /**
     * Resets scoring, level, etc.
     */
    public void reset() {
        myScore = OFFSET + OFFSET;
        myTotalCleared = 0;
    }
    
    /**
     * Set game level.
     * 
     * @param theLevel theLevel.
     */
    public void setLevel(final int theLevel) {
        myCurrentLevel = theLevel;
        myTimer.setDelay(DEFAULT_SPEED + myCurrentLevel * -1 * MULTIPLIER);
        repaint();
    }
    
    /**
     * Setup display.
     */
    private void setup() {
        setPreferredSize(new Dimension(0, DEFAULT_HEIGHT));
    }
    
    /**
     * 
     */
    private void checkLevel() {
        final int cLevel = myCurrentLevel;
        final int nLevel = myScore / LEVEL_SEPERATOR;
        
        if (cLevel != nLevel && nLevel > 0) {
            myCurrentLevel++;
            myTimer.setDelay(DEFAULT_SPEED + myCurrentLevel * -1 * MULTIPLIER);
            repaint();
        }
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setColor(Color.WHITE);
        
        g2d.drawString("Level: " + myCurrentLevel, TEXT_LEFT_OFFSET, TEXT_HEIGHT_OFFSET);
        g2d.drawString("Score: " + myScore, TEXT_LEFT_OFFSET, TEXT_HEIGHT_OFFSET 
                       + TEXT_HEIGHT_OFFSET);
        g2d.drawString("Cleared: " + myTotalCleared, TEXT_LEFT_OFFSET, TEXT_HEIGHT_OFFSET
                       + TEXT_HEIGHT_OFFSET + TEXT_HEIGHT_OFFSET);
        
    }
    
    @Override
    public void update(final Observable theBoard, final Object theData) {
        if (theData instanceof TetrisPiece) {
            // This means we successfully placed a piece
            // so another one was generated.
            myScore += -1 * OFFSET;
            checkLevel();
            repaint();
        } else if (theData instanceof CompletedLines) {
            final List<Integer> l = ((CompletedLines) theData).getCompletedLines();
            myScore += MULTIPLIER * l.size();
            checkLevel();
            myTotalCleared += l.size();
            repaint();
        }
    }
}
