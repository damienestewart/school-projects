/*
 * TCSS 305 - Winter 2015
 * Assignment 5 - Power Paint
 */

package gui;

import actions.ColorChange;
import actions.tools.Tool;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

/**
 * Menu bar for GUI.
 * 
 * @author Damiene Stewart.
 * @version Febuary 14, 2015.
 */
@SuppressWarnings("serial")
public class PowerPaintMenuBar extends JMenuBar implements PropertyChangeListener {
    
    /**
     * Maximum drawing thickness.
     */
    private static final int MAX_THICKNESS = 20;
    
    /**
     * Minimum drawing thickness.
     */
    private static final int MIN_THICKNESS = 0;
    
    /**
     * Initial drawing thickness.
     */
    private static final int INITIAL_THICKNESS = 1;
    
    /**
     * Major tick space.
     */
    private static final int TICK_MAJOR_SPACE = 5;
    
    /**
     * Minor tick space.
     */
    private static final int TICK_MINOR_SPACE = 1;
    
    /**
     * Tools menu button group.
     */
    private final ButtonGroup myToolsMenuGroup;
    
    /**
     * Drawing panel reference.
     */
    private final DrawingPanel myPanel;
    
    /**
     * Constructor.
     * 
     * @param thePanel drawing panel reference.
     */
    public PowerPaintMenuBar(final DrawingPanel thePanel) {
        super();
        
        myToolsMenuGroup = new ButtonGroup();
        myPanel = thePanel;
        
        setup();
    }
    
    /**
     * Set up menu bar.
     */
    private void setup() {
        createFileMenu();
        createOptionsMenu();
        createToolsMenu();
        createHelpMenu();
        createColorMenuItem();
    }
    
    /**
     * Creates the file menu.
     */
    private void createFileMenu() {
        // Create file menu.
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        final JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_X);
        
        fileMenu.add(createUndoAllItem());
        fileMenu.add(createRedoAllItem());
        fileMenu.addSeparator();
        fileMenu.add(exit);
        
        add(fileMenu);
    }
    
    /**
     * Helper method to create undo all menu item.
     * 
     * @return undo all menu item.
     */
    private JMenuItem createUndoAllItem() {
        final JMenuItem undoAll = new JMenuItem("Undo all changes");
        undoAll.setMnemonic(KeyEvent.VK_U);
        
        undoAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                myPanel.undoAll();
            }
        });
        
        undoAll.setEnabled(false);
        
        return undoAll;
    }
    
    /**
     * Add undo action to file menu.
     * 
     * @param theAction the action to add.
     */
    public void addUndoAction(final Action theAction) {
        getMenu(0).insert(theAction, 0);
    }
    
    /**
     * Add redo action to file menu.
     * 
     * @param theAction the action to add.
     */
    public void addRedoAction(final Action theAction) {
        getMenu(0).insert(theAction, 1);
    }
    
    /**
     * Add the action listener to the exit
     * menu item.
     * 
     * @param theActionListener to add.
     */
    public void addExitAction(final ActionListener theActionListener) {
        getMenu(0).getItem(1 + 1 + 1 + 1 + 1).addActionListener(theActionListener);
    }
    
    /**
     * Helper method to create redo all menu item.
     * 
     * @return redo all menu item.
     */
    private JMenuItem createRedoAllItem() {
        final JMenuItem redoAll = new JMenuItem("Redo all changes");
        redoAll.setMnemonic(KeyEvent.VK_R);
        
        redoAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                myPanel.redoAll();
            }
        });
        
        redoAll.setEnabled(false);
        
        return redoAll;
    }
    
    /**
     * Creates the options menu.
     */
    private void createOptionsMenu() {
        // Create options menu.
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        
        // Create file menu items.
        
        final JRadioButtonMenuItem grid = new JRadioButtonMenuItem("Grid");
        grid.setMnemonic(KeyEvent.VK_G);
        
        grid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPanel.toggleGrid();
            }
            
        });
        
        // Thickness submenu.
        final JMenu thickness = new JMenu("Thickness");
        thickness.setMnemonic(KeyEvent.VK_T);
        
        final JSlider slider = new JSlider(MIN_THICKNESS, MAX_THICKNESS,
                                           INITIAL_THICKNESS);
        
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final JSlider slider = (JSlider) theEvent.getSource();
                if (!slider.getValueIsAdjusting()) {
                    myPanel.setThickness(slider.getValue());
                }
            }
        });
        
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(TICK_MAJOR_SPACE);
        slider.setMinorTickSpacing(TICK_MINOR_SPACE);
        
        thickness.add(slider);
        
        // Add these items to the menu, and return.
        optionsMenu.add(grid);
        optionsMenu.add(thickness);
        
        add(optionsMenu);
    }
    
    /**
     * Creates the tools menu.
     */
    private void createToolsMenu() {
        // Create tools menu.
        final JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setMnemonic(KeyEvent.VK_T);
        
        add(toolsMenu);
    }
    
    /**
     * Add tools to menu.
     * @param theTool the tool to add.
     */
    public void addTool(final Tool theTool) {
        final JRadioButtonMenuItem item = new JRadioButtonMenuItem((Action) theTool);
        myToolsMenuGroup.add(item);
        this.getMenu(2).add(item);
    }
    
    /**
     * Creates the help menu.
     */
    private void createHelpMenu() {
        // Create help menu.
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        final JMenuItem about = new JMenuItem("About...");
        about.setMnemonic(KeyEvent.VK_A);
        
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File("images/w.gif"));
                    final Image tmp = img.getScaledInstance(32, 32, 
                                                            BufferedImage.SCALE_SMOOTH);
                    JOptionPane.showMessageDialog(myPanel, 
                                                  "TCSS 305 PowerPaint\nWinter 2015",
                                                  "About",
                                                  JOptionPane.INFORMATION_MESSAGE,
                                                  new ImageIcon(tmp));
                } catch (final IOException e) {
                    System.out.println(e);
                }
            }
            
        });
        
        helpMenu.add(about);
        
        add(helpMenu);
    }
    
   /** 
    * Creates the color menu item.
    */
    private void createColorMenuItem() {
        final JMenu colorMenuItem = new JMenu(new ColorChange(myPanel));
        
        colorMenuItem.addMenuKeyListener(new MenuKeyListener() {
            
            @Override
            public void menuKeyTyped(final MenuKeyEvent theEvent) { /* empty */ }
        
            @Override
            public void menuKeyPressed(final MenuKeyEvent theEvent) {
                if (theEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    
                    colorMenuItem.getAction().actionPerformed(new ActionEvent(colorMenuItem,
                                                                ActionEvent.ACTION_PERFORMED, 
                                                                "ActionEvent"));
                    }
            }
            
            @Override
            public void menuKeyReleased(final MenuKeyEvent theEvent) { /* empty */ }

        });
        
        add(colorMenuItem);
    }
    
    /**
     * Handles change events from the drawing panel.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("undoOk".equals(theEvent.getPropertyName())) {
            setUndoEnabled((Boolean) theEvent.getNewValue());
        }
        
        if ("redoOk".equals(theEvent.getPropertyName())) {
            setRedoEnabled((Boolean) theEvent.getNewValue());
        }
    }
    
    /**
     * Change the undo all item state.
     * 
     * @param theBool true vs. false.
     */
    private void setUndoEnabled(final Boolean theBool) {
        this.getMenu(0).getItem(0).getAction().setEnabled(theBool);
        this.getMenu(0).getItem(1 + 1).setEnabled(theBool);
    }
    
    /**
     * Change the undo all item state.
     * 
     * @param theBool true vs. false.
     */
    private void setRedoEnabled(final Boolean theBool) {
        this.getMenu(0).getItem(1).getAction().setEnabled(theBool);
        this.getMenu(0).getItem(1 + 1 + 1).setEnabled(theBool); // 3 is a magic #... :)
    }
}
