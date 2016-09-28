/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package gui;

import actions.tools.Tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;


import shape.ShapeData;

/**
 * Represents the drawing area
 * for the PowerPaint application.
 * 
 * MUST set the initial tool before
 * use.
 * 
 * @author Damiene Stewart
 * @version Febuary 14, 2015.
 */
@SuppressWarnings("serial")
public class DrawingPanel extends JPanel {
    
    /**
     * Drawing panel's preferred width.
     */
    private static final int PREFERRED_WIDTH = 400;
    
    /**
     * Drawing panel's preferred height.
     */
    private static final int PREFERRED_HEIGHT = 200;
    
    /**
     * Grid spacing.
     */
    private static final int GRID_SPACING = 10;
    
    /**
     * Grid thickness.
     */
    private static final int GRID_THICKNESS = 1;
    
    /**
     * Redo property change name.
     */
    private static final String REDO_PROPERTY_NAME = "redoOk";
    
    /**
     * Undo property change name.
     */
    private static final String UNDO_PROPERTY_NAME = "undoOk";
    
    /**
     * Stroke thickness.
     */
    private int myStrokeThickness;
    
    /**
     * Stores whether or not grid has been activated.
     */
    private boolean myGridDisplayed;
    
    /**
     * Stroke color.
     */
    private Color myColor;
    
    /**
     * Current tool to draw with.
     */
    private Tool myCurrentTool;
    
    /**
     * Double ended queue for storing Shapes drawn.
     */
    private final Deque<ShapeData> myShapeData;
    
    /**
     * Tracks how many changes have been performed thus
     * far.
     */
    private int myChangeCount;
    
    /**
     * Tracks how many times the panel can redo previous
     * action.
     */
    private int myRedoCount;
    
    /**
     * States whether or not an undo attempt is ok.
     */
    private boolean myUndoOk;
    
    /**
     * States whether or not an redo attempt is ok.
     */
    private boolean myRedoOk;
    
    /**
     * Constructs a new DrawingPanel.
     */
    public DrawingPanel() {
        super();
        myStrokeThickness = 1;
        myColor = Color.black; 
        myShapeData = new LinkedList<ShapeData>();
        myGridDisplayed = false;
        
        setup();
    }
    
    /**
     * Flip grid state.
     */
    public void toggleGrid() {
        myGridDisplayed ^= true;
        repaint();
    }
    
    /**
     * Undo last action.
     */
    public void undo() {
        final ShapeData sd = myShapeData.pop();
        myShapeData.addLast(sd);
        myChangeCount--;
        myRedoCount++;
        
        DrawingPanel.this.firePropertyChange(REDO_PROPERTY_NAME , myRedoOk, true);
        myRedoOk = true;
        
        if (myChangeCount == 0) {
            DrawingPanel.this.firePropertyChange(UNDO_PROPERTY_NAME, myUndoOk, false);
            myUndoOk = false;
        }
        this.repaint();
    }
    
    /**
     * Redo last undo.
     */
    public void redo() {
        final ShapeData sd = myShapeData.removeLast();
        myShapeData.addFirst(sd);
        myChangeCount++;
        myRedoCount--;
        
        firePropertyChange(UNDO_PROPERTY_NAME , myUndoOk, true);
        myUndoOk = true;
        
        if (myRedoCount == 0) {
            firePropertyChange(REDO_PROPERTY_NAME, myRedoOk, false);
            myRedoOk = false;
        }
        this.repaint();
    }
    
    /**
     * Undo's all previous actions.
     */
    public void undoAll() {
        for (int i = myChangeCount; i > 0; i--) {
            final ShapeData sd = myShapeData.pop();
            myShapeData.addLast(sd);
            myChangeCount--;
            myRedoCount++;
        }
        firePropertyChange(UNDO_PROPERTY_NAME, myUndoOk, false);
        firePropertyChange(REDO_PROPERTY_NAME , myRedoOk, true);
        
        myUndoOk = false;
        myRedoOk = true;
        
        this.repaint();
    }
    
    /**
     * Redo all previous actions.
     */
    public void redoAll() {
        for (int i = myRedoCount; i > 0; i--) {
            final ShapeData sd = myShapeData.removeLast();
            myShapeData.addFirst(sd);
            myChangeCount++;
            myRedoCount--;
        }

        firePropertyChange(REDO_PROPERTY_NAME , myRedoOk, false);
        firePropertyChange(UNDO_PROPERTY_NAME , myUndoOk, true);
        
        myRedoOk = false;
        myUndoOk = true;
        
        this.repaint();
    }
    
    /**
     * Sets the current tool.
     * 
     * @param theTool the tool to set to.
     */
    public void setCurrentTool(final Tool theTool) {
        myCurrentTool = theTool;
    }
    
    /**
     * Sets the current thickness.
     * 
     * @param theThickness the thickness to set to.
     */
    public void setThickness(final int theThickness) {
        myStrokeThickness = theThickness;
    }
    
    /**
     * Sets the current color.
     * 
     * @param theColor the color to set to.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * Repaint the canvas (panel).
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        final List<ShapeData> temp = new ArrayList<ShapeData>();
        
        int iterations = 0;
        for (final ShapeData sd : myShapeData) {
            
            if (iterations >= myChangeCount) {
                break;
            }
            iterations++;
            temp.add(sd);
        }
        
        for (int i = temp.size() - 1; i >= 0; i--) {
            final ShapeData sd = temp.get(i);
            g2d.setStroke(new BasicStroke(sd.getThickness()));
            g2d.setPaint(sd.getColor());
            g2d.draw(sd.getShape());
        }
        
        if (myCurrentTool != null && myCurrentTool.isStillDrawing()) {
            g2d.setPaint(myColor);
            g2d.setStroke(new BasicStroke(myStrokeThickness));
            g2d.draw(myCurrentTool.getShape());
        }
        
        if (myGridDisplayed) {
            g2d.setPaint(Color.GRAY);
            g2d.setStroke(new BasicStroke(GRID_THICKNESS));
            
            // Rows
            for (int i = GRID_SPACING; i < getHeight(); i += GRID_SPACING) {
                g2d.drawLine(0, i, getWidth(), i);
            }
            
            // Columns.
            for (int i = GRID_SPACING; i < getWidth(); i += GRID_SPACING) {
                g2d.drawLine(i, 0, i, getHeight());
            }
        }  
    }
    
    /**
     * Configures new panel.
     */
    private void setup() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        addMouseListeners();
    }
    
    /**
     * Add necessary mouse listners.
     */
    private void addMouseListeners() {
        
        Object mouseListen = null;
        
        /**
         * Local inner class for mouse input adapter.
         * 
         * @author Damiene Stewart
         * @version Febuary 14, 2015.
         */
        class PanelMouseListener extends MouseInputAdapter {
            
            @Override
            public void mousePressed(final MouseEvent theEvent) {
                if (myStrokeThickness != 0) {
                    if (myRedoOk) {
                        clearRedoData();
                    }
                    myCurrentTool.createShape(theEvent.getPoint());
                    repaint();  
                }
            }          
            @Override
            public void mouseReleased(final MouseEvent theEvent) {
                if (myStrokeThickness != 0) {
                    myCurrentTool.toggleDrawing();
                    myShapeData.addFirst(new ShapeData(myCurrentTool.getShape(), myColor,
                                                 myStrokeThickness));
                    myChangeCount++;
                    DrawingPanel.this.firePropertyChange(UNDO_PROPERTY_NAME, myUndoOk, true);
                    myUndoOk = true;
                    repaint();
                }  
            }
            
            @Override
            public void mouseDragged(final MouseEvent theEvent) {
                if (myStrokeThickness != 0) {
                    myCurrentTool.createShape(theEvent.getPoint());
                    repaint();
                } 
            }
        }
        
        mouseListen = new PanelMouseListener();
        
        addMouseListener((PanelMouseListener) mouseListen);
        addMouseMotionListener((PanelMouseListener) mouseListen);
    }

    /**
     * Clear the data for redo since it is no longer
     * allowed.
     */
    private void clearRedoData() {
        for (int i = myRedoCount; i > 0; i--) {
            myShapeData.removeLast();
        }
        myRedoCount = 0;
        DrawingPanel.this.firePropertyChange(REDO_PROPERTY_NAME, myRedoOk, false);
        myRedoOk = false;
    }
}
