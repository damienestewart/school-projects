/*
 * TCSS 305 - Winter 2015
 * Assignment 3 - Easy Street
 */

package model;

/**
 * An abstract class representing the
 *  commonality for all vehicles.
 * 
 * @author Damiene Stewart
 * @version 22 January 2015
 */
public abstract class AbstractVehicle implements Vehicle {
    
    /**
     * Record of initial X position of vehicle.
     */
    private final int myInitialX;
    
    /**
     * Record of initial Y position of vehicle.
     */
    private final int myInitialY;
    
    /**
     * Record of initial direction of vehicle.
     */
    private final Direction myInitialDirection;
    
    /**
     * Record of current X position of vehicle.
     */
    private int myCurrentX;
    
    /**
     * Record of current Y position of vehicle.
     */
    private int myCurrentY;
    
    /**
     * Record of current direction of vehicle.
     */
    private Direction myCurrentDirection;
    
    /**
     * Record of how long vehicle stays dead.
     */
    private final int myDeathTime;
    
    /**
     * Record of how long vehicle has been
     * dead.
     */
    private int myPokeCount; 
    
    /**
     * Record of whether vehicle is 'alive'
     * or not.
     */
    private boolean myAliveStatus;
    
    /**
     * Constructor to initialize
     * abstract class' fields.
     * 
     * @param theX the assigned X position.
     * @param theY the assigned Y position.
     * @param theDir the assigned Direction.
     * @param theDeathTime the assigned death time.
     */
    protected AbstractVehicle(final int theX, final int theY, 
                              final Direction theDir, final int theDeathTime) {
        myInitialX = theX;
        myInitialY = theY;
        myInitialDirection = theDir;
        
        myDeathTime = theDeathTime;
        myAliveStatus = true;
        myPokeCount = 0;
        
        myCurrentX = theX;
        myCurrentY = theY;
        myCurrentDirection = theDir;
    }
    
    /**
     * Controls vehicle state after collision.
     * 
     * {@inheritDoc}
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (isAlive() && theOther.isAlive() 
                                        && getDeathTime() > theOther.getDeathTime()) {
            myAliveStatus = false;
        }
    }

    /**
     * Returns the death time for
     * a vehicle.
     * 
     * {@inheritDoc}
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    /**
     * Returns the appropriate image file
     * based on whether or not the vehicle
     * is alive.
     * 
     * {@inheritDoc}
     */
    @Override
    public String getImageFileName() {       
        String returnString;
        
        if (myAliveStatus) {
            returnString = getClass().getSimpleName().toLowerCase() + ".gif";
        } else {
            returnString = getClass().getSimpleName().toLowerCase() + "_dead.gif";
        }
        
        return returnString;
    }

    /**
     * Returns the current direction of
     * the vehicle.
     * 
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return myCurrentDirection;
    }

    /**
     * Get current X position.
     * 
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return myCurrentX;
    }

    /**
     * Get current Y position.
     * 
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return myCurrentY;
    }

    /**
     * Returns true if vehicle is alive,
     * false if not.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean isAlive() {
        return myAliveStatus;
    }

    /**
     * Update count of time spent dead.
     * 
     * {@inheritDoc}
     */
    @Override
    public void poke() {
        myPokeCount++;
        
        if (myPokeCount == myDeathTime) {
            myAliveStatus = true;
            setDirection(Direction.random());
            myPokeCount = 0;
        }
    }

    /**
     * Resets the vehicle to it's
     * initial position on screen.
     * 
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        setX(myInitialX);
        setY(myInitialY);
        setDirection(myInitialDirection);
        
        myAliveStatus = true;
        myPokeCount = 0;
    }

    /**
     * Sets the direction of the vehicle.
     * 
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction theDir) {
        myCurrentDirection = theDir;
    }

    /**
     * Setter for X position.
     * 
     * {@inheritDoc}
     */
    @Override
    public void setX(final int theX) {
        myCurrentX = theX;
    }

    /**
     * Setter for Y position.
     * 
     * {@inheritDoc}
     */
    @Override
    public void setY(final int theY) {
        myCurrentY = theY;
    }

    /**
     * Returns string representation of a
     * vehicle.
     * 
     * Format:
     */
    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();
        str.append(getClass().getSimpleName());
 
        return str.toString();
    }
}
