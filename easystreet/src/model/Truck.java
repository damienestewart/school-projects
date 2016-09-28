/*
 * TCSS 305 - Winter 2015
 * Assignment 3 - Easy Street
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents a truck.
 * 
 * @author Damiene Stewart
 * @version 22 January 2015
 */
public final class Truck extends AbstractVehicle {

    /**
     * Death time constant for magic_number warning.
     */
    private static final int DEATH_TIME = 0;
    
    /**
     * Constructs a new Truck.
     * 
     * @param theX the assigned X position.
     * @param theY the assigned Y position.
     * @param theDir the assigned Direction.
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }
    
    /**
     * Trucks can pass through streets and lights. They
     * ignore the color of lights.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        
        if (theTerrain == Terrain.LIGHT || theTerrain == Terrain.STREET) {
            canPass = true;
        }
        
        return canPass;
    }

    /**
     * Trucks pick a random valid direction from forward,
     * left, or right. If none are available, trucks reverse.
     * 
     * {@inheritDoc}
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction preferredDirection = null;
        final List<Direction> directionOptions = new ArrayList<Direction>();
        
        // Variables used to make boolean expressions more clear.
        final Direction forward = getDirection();
        final Direction left = forward.left();
        final Direction right = forward.right();
        
        final Terrain forwardTerrain = theNeighbors.get(forward);
        final Terrain leftTerrain = theNeighbors.get(left);
        final Terrain rightTerrain = theNeighbors.get(right);
        
        if (forwardTerrain == Terrain.STREET || forwardTerrain == Terrain.LIGHT) {
            directionOptions.add(forward);
        }
        
        if (leftTerrain == Terrain.STREET || leftTerrain == Terrain.LIGHT) {
            directionOptions.add(left);
        }
        
        if (rightTerrain == Terrain.STREET || rightTerrain == Terrain.LIGHT) {
            directionOptions.add(right);
        }
        
        // Let's check our options.
        if (directionOptions.isEmpty()) {
            // We can't move forward, left, or right. Let's reverse.
            preferredDirection = forward.reverse();
        } else {
            // There were options. Let's 'randomly' pick one.
            final Random rand = new Random();
            // We'll pick a random number based on the size of our
            // array.
            final int randomInt = rand.nextInt(directionOptions.size());
            
            // Let's grab the direction that we got... randomly.
            preferredDirection = directionOptions.get(randomInt);
        }
        
        return preferredDirection;
    }
}
