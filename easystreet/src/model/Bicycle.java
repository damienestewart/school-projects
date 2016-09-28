/*
 * TCSS 305 - Winter 2015
 * Assignment 3 - Easy Street
 */

package model;

import java.util.Map;

/**
 * Represents a bicycle.
 * 
 * @author Damiene Stewart
 * @version 22 January 2015
 */
public final class Bicycle extends AbstractVehicle {

    /**
     * Death time constant for magic_number warning.
     */
    private static final int DEATH_TIME = 20;
    
    /**
     * Constructs a new Bicycle.
     * 
     * @param theX the assigned X position.
     * @param theY the assigned Y position.
     * @param theDir the assigned Direction.
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }
    
    /**
     * Bicycles can travel on trails, streets, and lights.
     * They ignore green lights.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        
        if (theTerrain == Terrain.TRAIL || theTerrain == Terrain.STREET
                                        || theTerrain == Terrain.LIGHT) {
            if (theTerrain == Terrain.LIGHT && theLight != Light.GREEN) {
                canPass = false;
            } else {
                canPass = true;
            }
        }
        
        return canPass;
    }
    
    /**
     * A bicycle prefers to drive straight if on trail.
     * If it's not on a trail, it searches for one, or
     * takes an otherwise valid path.
     * 
     * {@inheritDoc}
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction preferredDirection = null;        
        final Direction forward = getDirection();
        
        final Terrain currentTerrain = theNeighbors.get(Direction.CENTER);    
        
        if (currentTerrain == Terrain.TRAIL) {
            preferredDirection = forward;
        } else {
            // Look for trail. Assume only one of these
            // will be true.
            boolean trailFound = false;
            
            for (final Direction d : theNeighbors.keySet()) {
                // This will also look at center, but that look will always fail here
                // because we already know our center point is not a trail.
                if (d != forward.reverse() && theNeighbors.get(d) == Terrain.TRAIL) {
                    preferredDirection = d;
                    trailFound = true;
                }
            }
            
            if (!trailFound) {
                final Direction[] directionArray = {forward, forward.left(), forward.right()};
                
                for (final Direction d : directionArray) {
                    if (theNeighbors.get(d) == Terrain.STREET 
                                                    || theNeighbors.get(d) == Terrain.LIGHT) {
                        preferredDirection = d;
                        break;
                    }
                }       
            }
        }
        return preferredDirection;
    }
}
