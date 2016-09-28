package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.function.IntConsumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import listeners.ButtonKeyListener;
import listeners.TetrisKeyListener;
import model.Board;

/**
 * A menu for tetris.
 * 
 * @author Damiene Stewart
 * @version March 13th, 2015.
 */
@SuppressWarnings("serial")
public class TetrisMenuBar extends JMenuBar {
    
    /**
     * Maximum drawing thickness.
     */
    private static final int MAX_LEVEL = 20;
    
    /**
     * Minimum drawing thickness.
     */
    private static final int MIN_LEVEL = 0;
    
    /**
     * Initial drawing thickness.
     */
    private static final int INITIAL_LEVEL = 0;
    
    /**
     * Major tick space.
     */
    private static final int TICK_MAJOR_SPACE = 5;
    
    /**
     * Minor tick space.
     */
    private static final int TICK_MINOR_SPACE = 1;
    
    /**
     * Suppress warning...
     */
    private static final String PAUSE = "Pause Game";
    
    /**
     * Supress warning...
     */
    private static final String TITLE = "TCSS 305 Tetris";
    
    /**
     * Supress warning...
     */
    private static final String END = "TCSS 305 Tetris - Game Ended";
    
    /**
     * Suppress warning...
     */
    private static final String PAUSED = "TCSS 305 Tetris - Paused.";
    
    /**
     * Suppress warning...
     */
    private static final String UNPAUSE = "Un-pause Game";
    
    /**
     * Reference to tetris key listener.
     */
    private final TetrisKeyListener myTetrisKeyListener;
    
    /**
     * Reference to the timer.
     */
    private final Timer myTimer;
    
    /**
     * Reference to the board.
     */
    private final Board myBoard;
    
    /**
     * Reference to information display.
     */
    private final GameInfoDisplay myInfoDisplay;
    
    /**
     * Creates a new Tetris Menu.
     * 
     * @param theKeyListener key listener.
     * @param theTimer the timer.
     * @param theBoard the board.
     * @param theInfoDisplay the info display.
     */
    public TetrisMenuBar(final TetrisKeyListener theKeyListener, final Timer theTimer,
                         final Board theBoard, final GameInfoDisplay theInfoDisplay) {
        super();
        myTetrisKeyListener = theKeyListener;
        myTimer = theTimer;
        myBoard = theBoard;
        myInfoDisplay = theInfoDisplay;
        setup();
    }
    
    /**
     * Set up menu bar.
     */
    private void setup() {
        addFileMenu();
        addEditMenu();
        addHelpMenu();
        
        for (int i = 0; i < this.getMenuCount(); i++) {
            this.getMenu(i).addMenuListener(new MenuListener() {

                @Override
                public void menuSelected(final MenuEvent theEvent) {
                    final Component menu = (JMenu) theEvent.getSource();
                    final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menu);
                    if (!frame.getTitle().equals(PAUSED) && !frame.getTitle().equals(END)) {
                        frame.setTitle(PAUSED);
                    }
                    myTimer.stop();
                    TetrisMenuBar.this.getMenu(0).getItem(1).setText(UNPAUSE);
                    TetrisMenuBar.this.myTetrisKeyListener.setEnabled(false);
                }

                @Override
                public void menuDeselected(final MenuEvent theEvent) { /* empty */ }
                @Override
                public void menuCanceled(final MenuEvent theEvent) { /* empty */ } 
            });
        }
    }
    
    /**
     * Create and add file menu.
     */
    private void addFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.add(createNewGameMenuItem());
        fileMenu.add(createPauseMenuItem());
        fileMenu.add(createEndGameMenuItem());
        fileMenu.add(createQuitMenuItem());
        
        add(fileMenu);
    }
    
    /**
     * Create Edit Menu.
     */
    private void addEditMenu() {
        final JMenu editMenu = new JMenu("Edit");
        editMenu.add(createEditControlsMenuItem());
        editMenu.add(createLevelChoiceMenu());
        
        add(editMenu);
    }
    
    /**
     * Create Help Menu.
     */
    private void addHelpMenu() {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.add(createAboutMenuItem());
        helpMenu.add(createShowControlsMenuItem());
        
        add(helpMenu);
    }
    
    /**
     * Create new game menu item.
     * 
     * @return JMenuItem menu item.
     */
    private JMenuItem createNewGameMenuItem() {
        final JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.setEnabled(false);
        // Setup.
        newGameMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                TetrisMenuBar.this.getMenu(0).getItem(1).setEnabled(true);
                TetrisMenuBar.this.getMenu(0).getItem(2).setEnabled(true);
                final JMenu slider = (JMenu) TetrisMenuBar.this.getMenu(1).getItem(1);
                myInfoDisplay.setLevel(
                                 ((JSlider) slider.getPopupMenu().getComponent(0)).getValue());
                slider.setEnabled(false);
                ((JMenuItem) theEvent.getSource()).setEnabled(false);   
                final Component popMenu = ((JMenuItem) theEvent.getSource()).getParent();
                final Component menu = (JMenu) ((JPopupMenu) popMenu).getInvoker();
                final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menu);
                frame.setTitle(TITLE);      
                myInfoDisplay.reset();
                myBoard.clear();
                myTimer.start();
                myTetrisKeyListener.setEnabled(true);
            }   
        });
        
        return newGameMenuItem;
    }
    
    /**
     * Create pause menu item.
     * 
     * @return JMenuItem menu item.
     */
    private JMenuItem createPauseMenuItem() {
        final JMenuItem pauseGameMenuItem = new JMenuItem(PAUSE);
        // Setup.
        pauseGameMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Component popMenu = ((JMenuItem) theEvent.getSource()).getParent();
                final Component menu = (JMenu) ((JPopupMenu) popMenu).getInvoker();
                final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menu);
                if (PAUSE.equals(((JMenuItem) theEvent.getSource()).getText())) {
                    ((JMenuItem) theEvent.getSource()).setText(UNPAUSE);
                    myTimer.stop();
                    myTetrisKeyListener.setEnabled(false);
                    frame.setTitle(PAUSED);
                } else {
                    ((JMenuItem) theEvent.getSource()).setText(PAUSE);
                    myTimer.start();
                    myTetrisKeyListener.setEnabled(true);
                    frame.setTitle(TITLE);
                }
            }      
        });
        
        pauseGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                                                                ActionEvent.CTRL_MASK));
        
        return pauseGameMenuItem;
    }
    
    /**
     * Create end game menu item.
     * 
     * @return JMenuItem menu item.
     */
    private JMenuItem createEndGameMenuItem() {
        final JMenuItem endGameMenuItem = new JMenuItem("End Game");
        // Setup.
        
        endGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                TetrisMenuBar.this.getMenu(0).getItem(0).setEnabled(true);
                TetrisMenuBar.this.getMenu(0).getItem(1).setEnabled(false);
                TetrisMenuBar.this.getMenu(1).getItem(1).setEnabled(true);
                ((JMenuItem) theEvent.getSource()).setEnabled(false);
                
                final Component popMenu = ((JMenuItem) theEvent.getSource()).getParent();
                final Component menu = (JMenu) ((JPopupMenu) popMenu).getInvoker();
                final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menu);
                
                frame.setTitle(TITLE + " - Game Ended");
                
                myTimer.stop();
                myTetrisKeyListener.setEnabled(false);
            }  
        });
        
        return endGameMenuItem;
    }
    
    /**
     * Create quit menu item.
     * 
     * @return JMenuItem menu item.
     */
    private JMenuItem createQuitMenuItem() {
        final JMenuItem quitMenuItem = new JMenuItem("Quit");
        // Setup.
        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Component source = ((JMenuItem) theEvent.getSource()).getParent();
                final Component menu = (JMenu) ((JPopupMenu) source).getInvoker();
                final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(menu);
                
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }     
        });
        
        return quitMenuItem;
    }
    
    /**
     * Create edit controls menu item.
     * 
     * @return JMenuItem menu item.
     */
    private JMenuItem createEditControlsMenuItem() {
        final JMenuItem editControlsMenuItem = new JMenuItem("Edit Controls");
        
        editControlsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                showControlChange();
            }
        });
        
        return editControlsMenuItem;
    }
    
    /**
     * Create level choice slider.
     * 
     * @return JMenuItem the menu item.
     */
    private JMenu createLevelChoiceMenu() {
        final JMenu editLevelChoiceMenu = new JMenu("Change Level");
        
        final JSlider slider = new JSlider(MIN_LEVEL, MAX_LEVEL,
                                           INITIAL_LEVEL);
        
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(TICK_MAJOR_SPACE);
        slider.setMinorTickSpacing(TICK_MINOR_SPACE);
        
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final JSlider slider = (JSlider) theEvent.getSource();
                if (!slider.getValueIsAdjusting()) {
                    myInfoDisplay.setLevel(slider.getValue());
                }
            }
        });
        
        editLevelChoiceMenu.add(slider);
        
        editLevelChoiceMenu.setEnabled(false);
        return editLevelChoiceMenu;
    }
    
    /**
     * Create help controls menu item.
     * 
     * @return JMenuItem menu item.
     */
    private JMenuItem createAboutMenuItem() {
        final JMenuItem aboutMenuItem = new JMenuItem("About");
        // Setup.
        
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(TetrisMenuBar.this, 
                                              "Tetris GUI by Damiene Stewart.\n"
                                + "Underlying Model by Alan Fowler." + "\n\nScoring:\n"
                                                + "15 points for each piece correctly placed."
                                                + "\n50 * number of lines cleared at once."
                                                + "\nThese are not mutually exclusive.");
            }
        });
        
        return aboutMenuItem;
    }
    
    /**
     * Create show controls menu item.
     * 
     * @return JMenuItem menu item.
     */
    private JMenuItem createShowControlsMenuItem() {
        final JMenuItem showControlsMenuItem = new JMenuItem("Show Controls");
        // Setup.
        
        showControlsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final StringBuilder sb = new StringBuilder(80);
                sb.append("Left: ");
                sb.append(KeyEvent.getKeyText(myTetrisKeyListener.getLeftControl()));
                sb.append("\nRight: ");
                sb.append(KeyEvent.getKeyText(myTetrisKeyListener.getRightControl()));
                sb.append("\nDown: ");
                sb.append(KeyEvent.getKeyText(myTetrisKeyListener.getDownControl()));
                sb.append("\nDrop: ");
                sb.append(KeyEvent.getKeyText(myTetrisKeyListener.getDropControl()));
                sb.append("\nRotate Clockwise: ");
                sb.append(KeyEvent.getKeyText(myTetrisKeyListener.getRotateCWControl()));
                sb.append("\nRotate Counter-Clockwise: ");
                sb.append(KeyEvent.getKeyText(myTetrisKeyListener.getRotateCCWControl()));
                JOptionPane.showMessageDialog(TetrisMenuBar.this, sb.toString());
            }  
        });
        
        return showControlsMenuItem;
    }
    
    /**
     * Display controls to user.
     */
    private void showControlChange() {
        
        final JPanel controlPanel = new JPanel(new GridLayout(6, 2));
        
        controlPanel.add(new JLabel("Left"));
        controlPanel.add(createKeyButton(myTetrisKeyListener.getLeftControl(), 
                                         (IntConsumer) myTetrisKeyListener::setLeftControl));
        
        controlPanel.add(new JLabel("Right"));
        controlPanel.add(createKeyButton(myTetrisKeyListener.getRightControl(), 
                                         (IntConsumer) myTetrisKeyListener::setRightControl));
        
        controlPanel.add(new JLabel("Down"));
        controlPanel.add(createKeyButton(myTetrisKeyListener.getDownControl(), 
                                         (IntConsumer) myTetrisKeyListener::setDownControl));
        
        controlPanel.add(new JLabel("RotateCCW"));
        controlPanel.add(createKeyButton(myTetrisKeyListener.getRotateCCWControl(), 
                                      (IntConsumer) myTetrisKeyListener::setRotateCCWControl));
        
        controlPanel.add(new JLabel("RotateCW"));
        controlPanel.add(createKeyButton(myTetrisKeyListener.getRotateCWControl(), 
                                       (IntConsumer) myTetrisKeyListener::setRotateCWControl));
        
        controlPanel.add(new JLabel("Drop"));
        controlPanel.add(createKeyButton(myTetrisKeyListener.getDropControl(), 
                                         (IntConsumer) myTetrisKeyListener::setDropControl));
        
        JOptionPane.showMessageDialog(this, controlPanel);
    }
    
    /**
     * Create buttons for changing controls.
     * 
     * @param theKey the key code to change.
     * @param theFunction the function to use
     * with key listener.
     * 
     * @return a new JButton.
     */
    private JButton createKeyButton(final Integer theKey, final IntConsumer theFunction) {
        final JButton keyButton = new JButton(KeyEvent.getKeyText(theKey));
        keyButton.addKeyListener(new ButtonKeyListener(theFunction));
        return keyButton;
    }
}
