/*
 * TCSS 305 - SnapShop.
 * Assignment 4 - SnapShop.
 */

package gui;

import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.Filter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;
import image.Pixel;
import image.PixelImage;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * SnapShop GUI.
 * 
 * @author Damiene Stewart
 * @version Febuary 6, 2015.
 */
public class SnapShopGUI {
    
    // constants to capture screen dimensions, taken from
    // Professor Fowler's CompositeLayout.java example.
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    
    // EXTRA CREDIT UNDO/REDO FIELDS.
    
    /**
     * String constant for undo filter button.
     */
    private static final String UNDO_TITLE = "Undo Filter";
    
    /**
     * String constant for redo filter button.
     */
    private static final String REDO_TITLE = "Redo Filter";
    
    // INSTANCE FIELDS.
    
    /**
     * Instance field for frame object.
     */
    private final JFrame myJFrame;
    
    /**
     * Instance field for image data.
     */
    private PixelImage myImage;
    
    /**
     * Instance field for image container.
     */
    private final JLabel myImageContainer;
    
    /**
     * Instance field for file chooser.
     */
    private final JFileChooser myFileChooser;
    
    /**
     * Instance fields to refer to buttons
     * to be enabled/disabled.
     */
    private final List<JButton> myButtons;
    
    // EXTRA CREDIT UNDO/REDO FIELDS.
    
    /**
     * Instance field to allow us access
     * to undo button object.
     */
    private JButton myUndoButton;
    
    /**
     * Instance field to allow us access
     * to redo button object.
     */
    private JButton myRedoButton;
    
    /**
     * Stack for undo implementation.
     */
    private final Stack<Pixel[][]> myUndoImageStack;
    
    /**
     * Stack for redo implementation.
     */
    private final Stack<Pixel[][]> myRedoImageStack;
    
    /**
     * Constructs a new GUI.
     */
    public SnapShopGUI() {
        myJFrame = new JFrame("TCSS 305 SnapShop");
        myJFrame.setLocationRelativeTo(null);
        myJFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
        myFileChooser = new JFileChooser(".");
        myButtons = new ArrayList<JButton>();
        
        myImageContainer = new JLabel();
        
        myUndoImageStack = new Stack<Pixel[][]>();
        myRedoImageStack = new Stack<Pixel[][]>();
    }
    
    /**
     * Constructs and shows the GUI.
     */
    public void start() {
        // Create north panel.
        final JPanel filterPanel = new JPanel(new FlowLayout());
        final JPanel imagePanel = new JPanel(new BorderLayout());
        final JPanel utilityPanel = new JPanel(new FlowLayout());
        
        // Create and add filter buttons to the filter panel.
        addFilterButtonsToPanel(filterPanel);
        
        // Add image container to image panel.
        myImageContainer.setHorizontalAlignment(JLabel.CENTER);
        imagePanel.add(myImageContainer, BorderLayout.CENTER);
        
        // Create undo/redo buttons.
        myUndoButton = createUndoButton();
        myRedoButton = createRedoButton();
        
        // Add to utility Panel.
        utilityPanel.add(createOpenButton());
        utilityPanel.add(myUndoButton);
        utilityPanel.add(myRedoButton);
        utilityPanel.add(createSaveButton());
        utilityPanel.add(createCloseButton());
        
        myJFrame.add(filterPanel, BorderLayout.NORTH);
        myJFrame.add(imagePanel, BorderLayout.CENTER);
        myJFrame.add(utilityPanel, BorderLayout.SOUTH);
        
        // Disable all unnecessary buttons at start.
        setEnableButtons(false);
        
        // Shrink-wrap JFrame.
        myJFrame.pack();  
        
        // Reset minimum.
        myJFrame.setMinimumSize(new Dimension(myJFrame.getWidth(),
                                              myJFrame.getHeight()));
        
        // Center JFrame.
        myJFrame.setLocation(SCREEN_WIDTH / 2 - myJFrame.getWidth() / 2,
                    SCREEN_HEIGHT / 2 - myJFrame.getHeight() / 2);
        
        // Make GUI visible.
        myJFrame.setVisible(true);
    }

    /**
     * Disables all buttons except the close button.
     * 
     * @param theStatus - false/true for disabled/enabled
     * respectively.
     */
    private void setEnableButtons(final Boolean theStatus) {
        // Cycle through buttons and disable them.
        // Goes through all except the undo/redo buttons.        
        for (final JButton b : myButtons) {
            b.setEnabled(theStatus);
        }        
    }
    
    /**
     * Add filter buttons to a panel, also add these buttons
     * to the button list.
     * 
     * @param thePanel panel to add buttons to.
     */
    private void addFilterButtonsToPanel(final JPanel thePanel) {
        thePanel.add(createFilterButton(new EdgeDetectFilter()));
        thePanel.add(createFilterButton(new EdgeHighlightFilter()));
        thePanel.add(createFilterButton(new FlipHorizontalFilter()));
        thePanel.add(createFilterButton(new FlipVerticalFilter()));
        thePanel.add(createFilterButton(new GrayscaleFilter()));
        thePanel.add(createFilterButton(new SharpenFilter()));
        thePanel.add(createFilterButton(new SoftenFilter()));
        
        // Grab all the buttons, add them to the button list.
        final Component[] componentArray = thePanel.getComponents();
        
        for (final Component c : componentArray) {
            if (c instanceof JButton) {
                myButtons.add((JButton) c);
            }
        }
    }
    
    /**
     * Creates new filter button based on
     * a filter type.
     * 
     * @param theFilter - the filter that the button
     * will be used to implement.
     * @return the new button.
     */
    private JButton createFilterButton(final Filter theFilter) {
        final JButton filterButton = new JButton(theFilter.getDescription());
        filterButton.addActionListener(new ActionListener() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(final ActionEvent theActionEvent) {
                // Add current image to undo stack.
                myUndoImageStack.add(myImage.getPixelData());
                
                theFilter.filter(myImage);
                myImageContainer.setIcon(new ImageIcon(myImage));
                
                myUndoButton.setEnabled(true);
                myRedoButton.setEnabled(false);
            }
        });
        
        return filterButton;
    }
    
    /**
     * Creates and configures the action for the new open button.
     * @return the new open button.
     */
    private JButton createOpenButton() {
        final JButton openButton = new JButton("Open...");
        
        openButton.addActionListener(new ActionListener() {
            /**
             * Sets the method to be called when the action
             * event for the open button is triggered, using
             * a delegate pattern.
             */
            @Override
            public void actionPerformed(final ActionEvent theActionEvent) {
                openImage();
            }
        });
        
        return openButton;
    }
    
    /**
     * Creates and configures the action for the new undo button.
     * @return the new undo button.
     */
    private JButton createUndoButton() {
        final JButton undoButton = new JButton(UNDO_TITLE);
        
        undoButton.addActionListener(new ActionListener() {
            /**
             * Sets the method to be called when the action
             * event for the open button is triggered, using
             * a delegate pattern.
             */
            @Override
            public void actionPerformed(final ActionEvent theActionEvent) {
                undoImage();
            }
        });
        
        // We're just creating this so we have nothing to undo.
        // Disable this button.
        undoButton.setEnabled(false);
        
        return undoButton;
    }
    
    /**
     * Creates and configures the action for the new redo button.
     * @return the new redo button.
     */
    private JButton createRedoButton() {
        final JButton redoButton = new JButton(REDO_TITLE);
        
        redoButton.addActionListener(new ActionListener() {
            /**
             * Sets the method to be called when the action
             * event for the open button is triggered, using
             * a delegate pattern.
             */
            @Override
            public void actionPerformed(final ActionEvent theActionEvent) {
                redoImage();
            }
        });
        
        // We're just creating this so we have nothing to undo.
        // Disable this button.
        redoButton.setEnabled(false);
        
        return redoButton;
    }
    
    /**
     * Creates and configures the action for the new save button.
     * @return the new save button.
     */
    private JButton createSaveButton() {
        final JButton saveButton = new JButton("Save As...");
        
        saveButton.addActionListener(new ActionListener() {
            /**
             * Sets the method to be called when the action
             * event for the open button is triggered, using
             * a delegate pattern.
             */
            @Override
            public void actionPerformed(final ActionEvent theActionEvent) {
                saveImage();
            }
        });
        
        // Add save button to button list as we would like to
        // toggle whether or not it is enabled/disabled.
        myButtons.add(saveButton);
        
        return saveButton;
    }
    
    /**
     * Creates and configures the action for the new open button.
     * @return the new open button.
     */
    private JButton createCloseButton() {
        final JButton closeButton = new JButton("Close Image");
        
        closeButton.addActionListener(new ActionListener() {
            /**
             * Sets the method to be called when the action
             * event for the open button is triggered, using
             * a delegate pattern.
             */
            @Override
            public void actionPerformed(final ActionEvent theActionEvent) {
                closeImage();
            }
        });
        
        // Add save button to button list as we would like to
        // toggle whether or not it is enabled/disabled.
        myButtons.add(closeButton);
        
        return closeButton;
    }
    
    /**
     * Opens an image from the file open dialogue.
     */
    private void openImage() {
        if (myFileChooser.showOpenDialog(myJFrame) == JFileChooser.APPROVE_OPTION) {
            try {
                myImage = PixelImage.load(myFileChooser.getSelectedFile());
                myImageContainer.setIcon(new ImageIcon(myImage));
                
                // Release minimum.
                myJFrame.setMinimumSize(null);
                
                myJFrame.pack();
                // Seems to be necessary to reset position
                // on a Mac.
                
                // Reset minimum.
                myJFrame.setMinimumSize(new Dimension(myJFrame.getWidth(),
                                                      myJFrame.getHeight()));
                
                myJFrame.setLocation(SCREEN_WIDTH / 2 - myJFrame.getWidth() / 2,
                                     SCREEN_HEIGHT / 2 - myJFrame.getHeight() / 2);
                setEnableButtons(true);
            } catch (final IOException e) {
                JOptionPane.showMessageDialog(myJFrame,
                                              "The selected file did not contain an image!",
                                              "Error!",
                                              JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Undo the previous change to image.
     * 
     * @throws NoSuchElementException() if
     * this method is called without first
     * having performed a filter.
     */
    private void undoImage() {
        // This method should not be called if there has been
        // no action performed.
        if (myUndoImageStack.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        // Save current image to redo stack.
        myRedoImageStack.push(myImage.getPixelData());
        
        // Enable redo button.
        myRedoButton.setEnabled(true);
        
        // Restore previous image by popping from stack.
        myImage.setPixelData(myUndoImageStack.pop());
        
        // Show image.
        myImageContainer.setIcon(new ImageIcon(myImage));
        
        // Disable the undo button if we have no more images to restore.
        if (myUndoImageStack.isEmpty()) {
            myUndoButton.setEnabled(false);
        }
    }
    
    /**
     * Redo the previously undone filter.
     * 
     * @throws NoSuchElementException if this
     * method is called without first performing
     * an undo.
     */
    private void redoImage() {
        // This method should not be called if there is nothing
        // to redo.
        if (myRedoImageStack.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        // Save current image to undo stack.
        myUndoImageStack.add(myImage.getPixelData());
        
        // We have put something in the undo stack, we can undo.
        myUndoButton.setEnabled(true);
        
        // Restore next image.
        myImage.setPixelData(myRedoImageStack.pop());
        
        // Change JLabel to reflect the new image.
        myImageContainer.setIcon(new ImageIcon(myImage));
        
        // Disable the undo button if we have no more images to restore.
        if (myRedoImageStack.isEmpty()) {
            myRedoButton.setEnabled(false);
        }
    }
    
    /**
     * Saves image to specified file name.
     */
    private void saveImage() {
        // Show save option.
        if (myFileChooser.showSaveDialog(myJFrame) == JFileChooser.APPROVE_OPTION) {
            try {
                myImage.save(myFileChooser.getSelectedFile());
            } catch (final IOException e) {
                JOptionPane.showMessageDialog(myJFrame,
                                              "The was an issue saving the file!",
                                              "Save Error!",
                                              JOptionPane.ERROR_MESSAGE);
            }
        }  
    }
    
    /**
     * Closes the image file and resets the GUI.
     */
    private void closeImage() {
        // Clear the image.
        myImageContainer.setIcon(null);
        myImageContainer.revalidate();
        
        // Release minimum.
        myJFrame.setMinimumSize(null);
        
        // Shrink-wrap JFrame.
        myJFrame.pack();
        
        // Reset minimum.
        myJFrame.setMinimumSize(new Dimension(myJFrame.getWidth(),
                                              myJFrame.getHeight()));
        
        // Seems to be necessary to reset position
        // on a Mac.
        myJFrame.setLocation(SCREEN_WIDTH / 2 - myJFrame.getWidth() / 2,
                             SCREEN_HEIGHT / 2 - myJFrame.getHeight() / 2);
        
        // Disable relevant buttons.
        myUndoButton.setEnabled(false);
        myRedoButton.setEnabled(false);
        setEnableButtons(false);
    }
}
