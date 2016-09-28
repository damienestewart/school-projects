/*
 * TCSS 305
 * 
 * An implementation of the classic game "Tetris".
 */

package main;

import gui.TetrisUI;

import java.awt.EventQueue;

/**
 * Starts the tetris program.
 * 
 * @author Damiene Stewart
 * @version March 6th, 2015.
 */
public final class TetrisMain {
    
    /**
     * Private constructor to prevent
     * initialization.
     */
    private TetrisMain() { /* empty */ }
    
    /**
     * Main method to start the
     * tetris program.
     * 
     * @param theArgs command-line arguments. Unused.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisUI().start();
            }
        });
    }
}
