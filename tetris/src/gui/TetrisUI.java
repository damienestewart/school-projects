/*
 * TCSS 305
 * 
 * An implementation of the classic game "Tetris".
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import listeners.TetrisKeyListener;
import model.Board;
import model.Board.GameStatus;

/**
 * Tetris User Interface Class.
 * 
 * @author Damiene Stewart
 * @version March 13th, 2015.
 */
public class TetrisUI implements Observer {
    
    /**
     * Default game speed.
     */
    private static final int DEFAULT_GAME_SPEED = 1000;
    
    /**
     * Supress warning...
     */
    private static final String TITLE = "TCSS 305 Tetris";
    
    /**
     * Rigid area height.
     */
    private static final int RIGID_AREA_HEIGHT = 10;
    
    /**
     * Holds reference to the window.
     */
    private final TetrisFrame myFrame;
    
    /**
     * Holds reference to game model.
     */
    private final Board myBoard;
    
    /**
     * Holds reference to the game
     * key listener.
     */
    private final TetrisKeyListener myTetrisKeyListener;
    
    /**
     * Reference to info display.
     */
    private GameInfoDisplay myGameInfoDisplay;
    
    /**
     * Holds reference to timer.
     */
    private Timer myTimer;
    
    /**
     *  The game's speed.
     */
    private final int myGameSpeed;
    
    /**
     * Construct new UI for Tetris
     * program.
     */
    public TetrisUI() {
        // Initialize variables.
        myFrame = new TetrisFrame(TITLE);     
        myBoard = new Board();
        myTetrisKeyListener = new TetrisKeyListener(myBoard);
        myGameSpeed = DEFAULT_GAME_SPEED;
        setup();
    }
    
    /**
     * Start the game.
     */
    public void start() {
        myBoard.clear();
        myFrame.setVisible(true);
        myTimer.start();
    }
    
    /**
     * Set up UI.
     */
    private void setup() {
        myBoard.addObserver(this);
        configureTimer();
        myGameInfoDisplay = new GameInfoDisplay(myTimer);
        myFrame.setJMenuBar(new TetrisMenuBar(myTetrisKeyListener, myTimer, myBoard,
                                              myGameInfoDisplay));
        
        addComponents();
        addTetrisKeyListener();
        
        myFrame.finishSetUp();
    }
    
    /**
     * Set up the timer.
     */
    private void configureTimer() {
        myTimer = new Timer(myGameSpeed, new ActionListener() {
            /** Progress pieces on board. **/
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myBoard.step();
            } 
        });
    }
    
    /**
     * Add necessary components.
     */
    private void addComponents() {
        addBoardDisplayComponent();
        addSideBar();
    }
    
    /**
     * Add Board Display Component.
     */
    private void addBoardDisplayComponent() {
        final BoardDisplay boardDisplay = new BoardDisplay(myBoard.getWidth(), 
                                                           myBoard.getHeight());
        final JPanel boardDisplayContainer = new JPanel();
        
        myBoard.addObserver(boardDisplay);
        
        boardDisplayContainer.add(boardDisplay);
        
        myFrame.add(boardDisplayContainer, BorderLayout.CENTER);
    }

    /**
     * Add Side Panel.
     */
    private void addSideBar() {
        final Box box = new Box(BoxLayout.PAGE_AXIS);
        final JPanel sidePanel = new JPanel();
        
        addNextPieceDisplay(box);
        
        box.add(Box.createRigidArea(new Dimension(0, RIGID_AREA_HEIGHT)));
        
        addInformationDisplay(box);
        
        sidePanel.add(box);
        
        myFrame.add(sidePanel, BorderLayout.EAST);
    }
    
    /**
     * Add next piece display to the sidebar box.
     * 
     * @param theBox the side panel box.
     */
    private void addNextPieceDisplay(final Box theBox) {
        final NextPieceDisplay nextPieceDisplay = new NextPieceDisplay();
        myBoard.addObserver(nextPieceDisplay);
        nextPieceDisplay.setBackground(Color.darkGray);
        theBox.add(nextPieceDisplay);
    }
    
    /**
     * Add the game information display.
     * 
     * @param theBox the box.
     */
    private void addInformationDisplay(final Box theBox) {
        myBoard.addObserver(myGameInfoDisplay);
        myGameInfoDisplay.setBackground(Color.darkGray);
        theBox.add(myGameInfoDisplay);
    }
    
    /**
     * Add key listner to control the game.
     */
    private void addTetrisKeyListener() {
        myFrame.addKeyListener(myTetrisKeyListener);
    }
    
    /**
     * Show game over message.
     */
    private void showGameOverDialog() {
        
        JOptionPane.showMessageDialog(myFrame, "Game Over!"
                        + "\nYou can restart from the menu.");
        
        myFrame.getJMenuBar().getMenu(0).getItem(0).setEnabled(true);
        myFrame.getJMenuBar().getMenu(0).getItem(1).setEnabled(false);
        myFrame.getJMenuBar().getMenu(0).getItem(2).setEnabled(false);
        
        myTimer.stop();
        myTetrisKeyListener.setEnabled(false);

    }
    
    @Override
    public void update(final Observable theObservable, final Object theData) {
        if (theData instanceof GameStatus && ((GameStatus) theData).isGameOver()) {
            myFrame.setTitle(TITLE + " - Game Ended");
            myTimer.stop();
            showGameOverDialog();       
        }
    }
}