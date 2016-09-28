/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package gui;

import actions.Redo;
import actions.Undo;
import actions.tools.EllipseTool;
import actions.tools.LineTool;
import actions.tools.PencilTool;
import actions.tools.RectangleTool;
import actions.tools.Tool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Presents the view for
 * the PowerPaint application.
 * Communicates with a PowerPaintController 
 * and a PowerPaintModel when necessary.
 * 
 * @author Damiene Stewart.
 * @version Febuary 14, 2015.
 */
public class PowerPaintView {
    
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
     * Holds the drawing panel in use.
     */
    private final DrawingPanel myPanel;
    
    /**
     * Constructor.
     */
    public PowerPaintView() {
        super();
        
        myPanel = new DrawingPanel();
    }
    
    /**
     * Constructs the view.
     */
    public void start() {
        // Create JFrame.
        final JFrame frame = createFrame();

        // Actions.
        final List<Tool> tools = getToolList();    
        final Undo undoAction = new Undo(myPanel);
        final Redo redoAction = new Redo(myPanel);
        
        // Menu bar.
        final PowerPaintMenuBar menu = createMenuBar(tools);     
        menu.addUndoAction(undoAction);
        menu.addRedoAction(redoAction);
        menu.addExitAction(createExitActionListener(frame));
        frame.setJMenuBar(menu);
        
        // Drawing Panel.
        frame.add(myPanel, BorderLayout.CENTER);
        myPanel.setCurrentTool(tools.get(0));
        myPanel.addPropertyChangeListener(menu);
        
        // Toolbar.
        final PowerPaintToolBar toolbar = createToolBar(tools);
        toolbar.add(new JButton(undoAction));
        toolbar.add(new JButton(redoAction));
        frame.add(toolbar, BorderLayout.SOUTH);
        
        // Shrinkwrap gui.
        frame.pack();
        
        // Taken from Professor Fowler's example.
        frame.setLocation(SCREEN_WIDTH / 2 - frame.getWidth() / 2, 
                          SCREEN_HEIGHT / 2 - frame.getHeight() / 2);
        
        frame.setVisible(true);
    }
    
    /**
     * Create window.
     * 
     * @return the JFrame.
     */
    private JFrame createFrame() {
        final JFrame frame = new JFrame("TCSS 305 PowerPaint");
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("images/w.gif"));
            final Image tmp = img.getScaledInstance(32, 32, 
                                                    BufferedImage.SCALE_SMOOTH);
            frame.setIconImage(tmp);
        } catch (final IOException e) {
            System.out.println(e);
        }

        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        return frame;
    }
    
    /**
     * Set what should happen when exit item is selected.
     * 
     * @param theFrame the window.
     * @return a new action listener for exit.
     */
    private ActionListener createExitActionListener(final JFrame theFrame) {
        return new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame, 
                                                       WindowEvent.WINDOW_CLOSING));
            }        
        };
    }
    
    /**
     * Get the list of our tools.
     * 
     * @return the list of tools.
     */
    private List<Tool> getToolList() {
        final List<Tool> tools = new ArrayList<Tool>();
        
        tools.add(new PencilTool(myPanel));
        tools.add(new LineTool(myPanel));
        tools.add(new RectangleTool(myPanel));
        tools.add(new EllipseTool(myPanel));
        
        return tools;
    }
    
    /**
     * Create the menu bar.
     * @param theTools the tools to include.
     * @return the new menu bar.
     */
    private PowerPaintMenuBar createMenuBar(final List<Tool> theTools) {
        final PowerPaintMenuBar menu = new PowerPaintMenuBar(myPanel);
        
        for (final Tool tool : theTools) {
            menu.addTool(tool);
        }
        
        return menu;
    }
    
    /**
     * Create the toolbar.
     * @param theTools the tools to include.
     * @return the new menu bar.
     */
    private PowerPaintToolBar createToolBar(final List<Tool> theTools) {
        final PowerPaintToolBar toolbar = new PowerPaintToolBar();
        
        for (final Tool tool : theTools) {
            toolbar.createToggleButton(tool);
        }
        
        return toolbar;
    }
}