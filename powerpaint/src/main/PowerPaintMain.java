/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package main;

import gui.PowerPaintView;
import java.awt.EventQueue;


/**
 * Starts GUI process from
 * dispatch thread.
 * 
 * @author Damiene Stewart
 * @version Febuary 14, 2015.
 */
public final class PowerPaintMain {
    
    /**
     * Disallow instatiation of this
     * class.
     */
    private PowerPaintMain() {
        // Empty.
    }
    
    /**
     * Instantiates and adds
     * GUI to the system event queue.
     * 
     * @param theArgs command line argumnets, ignore.
     */
    public static void main(final String... theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintView().start();
            }
        });
    }

}
