/*
 * TCSS 305 - Winter 2015
 * Assignment 3 - Easy Street
 */

package model;

import java.util.Map;

/**
 * Represents a car.
 * 
 * @author Damiene Stewart
 * @version 22 January 2015
 */
public final class Car extends AbstractVehicle {

    /**
     * Death time constant for magic_number warning.
     */
    private static final int DEATH_TIME = 10;
    
    /**
     * Constructs a new Car.
     * 
     * @param theX the assigned X position.
     * @param theY the assigned Y position.
     * @param theDir the assigned Direction.
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }
    
    /**
     * Cars can pass through roads, and green and yellow lights.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        
        if (theTerrain == Terrain.LIGHT && theLight == Light.RED) {
            canPass = false;
        } else if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {
            canPass = true;
        }
      
        return canPass;
    }

    /**
     * A car prefers to drive straight ahead. If not, it turns left.
     * If not, it turns right. If not, it reverses it's direction.
     * 
     * {@inheritDoc}
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction preferredDirection = null;
        final Direction forward = getDirection();
        
        if (theNeighbors.get(forward) == Terrain.STREET
                              || theNeighbors.get(forward) == Terrain.LIGHT) {
            preferredDirection = forward;
        } else if (theNeighbors.get(forward.left()) == Terrain.STREET
                              || theNeighbors.get(forward.left()) == Terrain.LIGHT) {
            preferredDirection = forward.left();
        } else if (theNeighbors.get(forward.right()) == Terrain.STREET
                              || theNeighbors.get(forward.right()) == Terrain.LIGHT) {
            preferredDirection = forward.right();
        } else {
            preferredDirection = forward.reverse();
        }
        
        return preferredDirection;
    }
}
